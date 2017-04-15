package at.tuwien.innovation.group7.repository;

import at.tuwien.innovation.group7.endpoint.WienBibliothekEndpoint;
import at.tuwien.innovation.group7.model.Record;
import at.tuwien.innovation.group7.util.CacheHelper;
import com.jayway.restassured.path.xml.XmlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.path.xml.XmlPath.with;


@Service
public class Cache {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(Cache.class);

    private final WienBibliothekEndpoint wienBibliothekEndpoint;
    private final List<Record> records;

    @Autowired
    public Cache(WienBibliothekEndpoint wienBibliothekEndpoint) {
        this.wienBibliothekEndpoint = wienBibliothekEndpoint;
        this.records = new ArrayList<>();
    }

    @PostConstruct
    private void init() throws ParseException {
        LOG.info("Starting to import the records into the cache!");

        String response = wienBibliothekEndpoint.getRecords();
        if (response != null && !response.isEmpty()) {
            XmlPath xmlPath = with(response);
            String resumptionToken = CacheHelper.getResumptionToken(xmlPath);
            while (resumptionToken != null && !resumptionToken.isEmpty()) {
                int length = CacheHelper.getLengthOfHeaders(xmlPath);
                for (int i = 0; i < length; i++) {
                    records.add(CacheHelper.createRecord(xmlPath, i));
                }
                xmlPath = with(wienBibliothekEndpoint.getRecords(resumptionToken));
                resumptionToken = CacheHelper.getResumptionToken(xmlPath);
            }

            LOG.info("Imported {} records into the cache!", records.size());
        }
    }

    public List<Record> getRecords() {
        return records;
    }
}
