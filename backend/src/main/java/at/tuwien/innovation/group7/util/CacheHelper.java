package at.tuwien.innovation.group7.util;

import at.tuwien.innovation.group7.model.Record;
import com.jayway.restassured.path.xml.XmlPath;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CacheHelper {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

    public static String getResumptionToken(XmlPath xmlPath) {
        return xmlPath.getString("OAI-PMH.ListRecords.resumptionToken");
    }

    public static int getLengthOfHeaders(XmlPath xmlPath) {
        return xmlPath.getInt("OAI-PMH.ListRecords.record.size()");
    }

    public static Record createRecord(XmlPath xmlPath, int i) throws ParseException {
        return new Record(
                getIdentifier(xmlPath, i),
                gateDate(xmlPath, i),
                getSpecs(xmlPath, i),
                getStringFromMetadata(xmlPath, i, "title"),
                getStringFromMetadata(xmlPath, i, "subject"),
                getStringFromMetadata(xmlPath, i, "creator"),
                getStringFromMetadata(xmlPath, i, "publisher"),
                getStringFromMetadata(xmlPath, i, "date"),
                getStringFromMetadata(xmlPath, i, "language"),
                getStringFromMetadata(xmlPath, i, "rights"),
                getListOfStringsFromMetadata(xmlPath, i, "coverage"),
                getListOfStringsFromMetadata(xmlPath, i, "description"),
                getListOfStringsFromMetadata(xmlPath, i, "type"),
                getListOfStringsFromMetadata(xmlPath, i, "format"),
                getListOfStringsFromMetadata(xmlPath, i, "identifier"),
                Collections.emptyList()
        );
    }

    private static String getIdentifier(XmlPath xmlPath, int i) {
        String identifier = xmlPath.getString(String.format("OAI-PMH.ListRecords.record[%d].header.identifier", i));
        return identifier.substring(identifier.lastIndexOf(":") + 1);
    }

    private static Date gateDate(XmlPath xmlPath, int i) throws ParseException {
        return DATE_FORMAT.parse(xmlPath.getString(String.format("OAI-PMH.ListRecords.record[%d].header.datestamp", i)));
    }

    private static List<String> getSpecs(XmlPath xmlPath, int i) {
        int specsLength = xmlPath.getInt(String.format("OAI-PMH.ListRecords.record[%d].header.setSpec.size()", i));
        List<String> specs = new ArrayList<>(specsLength);

        for (int j = 0; j < specsLength; j++) {
            specs.add(xmlPath.getString(String.format("OAI-PMH.ListRecords.record[%d].header.setSpec[%d]", i, j)));
        }

        return specs;
    }

    private static String getStringFromMetadata(XmlPath xmlPath, int i, String field) {
        return xmlPath.getString(String.format("OAI-PMH.ListRecords.record[%d].metadata.dc.".concat(field), i));
    }

    private static List<String> getListOfStringsFromMetadata(XmlPath xmlPath, int i, String field) {
        int length = xmlPath.getInt(String.format("OAI-PMH.ListRecords.record[%d].metadata.dc.".concat(field).concat(".size()"), i));
        List<String> list = new ArrayList<>();

        for (int j = 0; j < length; j++) {
            list.add(xmlPath.getString(String.format("OAI-PMH.ListRecords.record[%d].metadata.dc.".concat(field).concat("[%d]"), i, j)));
        }

        return list;
    }
}
