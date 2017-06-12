package at.tuwien.innovation.group7.repository;

import at.tuwien.innovation.group7.endpoint.WienBibliothekEndpoint;
import at.tuwien.innovation.group7.model.Record;
import at.tuwien.innovation.group7.util.CacheHelper;
import com.jayway.restassured.path.xml.XmlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import static com.jayway.restassured.path.xml.XmlPath.with;


@Service
public class Cache {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(Cache.class);

    private final WienBibliothekEndpoint wienBibliothekEndpoint;
    private final List<Record> records;

    private final Set<String> globalWordsSet = new HashSet<>();
    private List<String> globalWordsList = null;
    private final Map<String, Integer []> bookMatrix = new HashMap<String, Integer []>();

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
            while (resumptionToken != null && !resumptionToken.isEmpty() && records.size() < 50) {
                int length = CacheHelper.getLengthOfHeaders(xmlPath);
                for (int i = 0; i < length; i++) {
                    Record r = CacheHelper.createRecord(xmlPath, i);

                    String ID = r.getIdentifier();
                    String creator = r.getCreator();
                    String desc = String.join(", ", r.getDescription());
                    String title = r.getTitle();


                    // make global word vector
                    fillTheGlobalWordSet(creator + " " + desc + " " + title);

                    // make book word vector
                    //bookMatrix.put(ID, null);

                    // put record to DB
                    records.add(r);
                }
                xmlPath = with(wienBibliothekEndpoint.getRecords(resumptionToken));
                resumptionToken = CacheHelper.getResumptionToken(xmlPath);
            }

            LOG.info("Imported {} records into the cache!", records.size());
            LOG.info("Initialising Recommender calculations");

            // convert to list for index reasons
            globalWordsList = new ArrayList<>(globalWordsSet);

            //TODO: make matrix, title -> value vector
            int recordNumber = records.size();

            for (int i = 0; i<recordNumber; i++){
                Record r = records.get(i);

                String ID = r.getIdentifier();
                String creator = r.getCreator();
                String desc = String.join(", ", r.getDescription());
                String title = r.getTitle();

                Integer[] bookV = new Integer[globalWordsList.size()];
                Collection<String> wordV = createWordVector(creator + " " + desc + " " + title);
                Iterator<String> wordWIter = wordV.iterator();

                while(wordWIter.hasNext()){
                    String w = wordWIter.next();
                    int wordIndex = globalWordsList.indexOf(w);
                    bookV[wordIndex] = 1;

                }
                // save to matrix
                bookMatrix.put(ID, bookV);
            }

            LOG.info("Recommender calculations FINISHED");

            LOG.info("Writing Matrix to File");
            try {
                writeMatrixToFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            LOG.info("Writing Matrix to File FINISHED");
        }
    }

    public List<Record> getRecords() {
        return records;
    }

    private void fillTheGlobalWordSet(String info){

        Collection<String> filteredWordCollection = createWordVector(info);
        Iterator<String> wordIter = filteredWordCollection.iterator();

        while (wordIter.hasNext()){
            globalWordsSet.add(wordIter.next());
        }

    }

    private Collection<String> createWordVector(String in){
        in = in.replaceAll("[^A-Za-z0-9]", " ");
        String in2 = in.trim().replaceAll(" +", " ");

        String [] wordArr = in2.split(" ");

        List<String> bookWords = Arrays.asList(wordArr);
        Iterator<String> bookWordsIter = bookWords.iterator();

        Collection<String> coll = new ArrayList<String>(bookWords);
        //populate

        // remove single characters
        coll.removeIf(word -> word.length() < 3);

        //System.out.println(coll);

        return coll;
    }

    private void writeMatrixToFile() throws IOException {
        FileWriter fw = new FileWriter("matrixOut.txt");

        Iterator it = bookMatrix.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String key = (String) pair.getKey();
            Integer[] values = (Integer[]) pair.getValue();

            String line = key;
            for (int i = 0; i < values.length; i++){
                int val = 0;
                if(values[i] != null)
                    val = 1;

                line += "," + val;

            }
            fw.write(line + System.lineSeparator());
        }

        fw.close();
    }
}
