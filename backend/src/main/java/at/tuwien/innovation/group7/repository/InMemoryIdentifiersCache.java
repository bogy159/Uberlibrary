package at.tuwien.innovation.group7.repository;

import at.tuwien.innovation.group7.endpoint.WienBibliothekEndpoint;
import at.tuwien.innovation.group7.model.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryIdentifiersCache {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(InMemoryIdentifiersCache.class);

    private final WienBibliothekEndpoint wienBibliothekEndpoint;
    private final List<Header> identifiers;

    @Autowired
    public InMemoryIdentifiersCache(WienBibliothekEndpoint wienBibliothekEndpoint) {
        this.wienBibliothekEndpoint = wienBibliothekEndpoint;
        this.identifiers = new ArrayList<Header>();
    }

    @PostConstruct
    private void init() {
        wienBibliothekEndpoint.getIndentifiers();
    }

    public List<Header> getIdentifiers() {
        return identifiers;
    }
}
