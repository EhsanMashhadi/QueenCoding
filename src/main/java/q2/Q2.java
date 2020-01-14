package q2;

import com.opencsv.CSVWriter;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Q2 {

    public static void main(String[] args) throws ParserConfigurationException,
            IOException, SAXException, ParseException {

        Q2 q2 = new Q2();
        Document document = q2.parseUrl(
                "https://issues.apache.org/jira/si/jira.issueviews:issue-xml/CAMEL-10597/CAMEL-10597.xml");
        document.getDocumentElement().normalize();
        Element root = document.getDocumentElement();

        Issue issue = new Issue();

        Detail detail = q2.parseDetails(root);
        issue.setDetail(detail);

        People people = q2.parsePeople(root);
        issue.setPeople(people);

        Dates date = q2.parseDate(root);
        issue.setDates(date);

        Description description = q2.parseDescription(root);
        issue.setDescription(description);

        List<Comment> comments = q2.parseComments(root);
        issue.setComments(comments);

        CSVWriter csvWriter = writeHeader("output.csv");
        Q2.writeData(csvWriter, issue);
        csvWriter.close();
    }

    /**
     * Parsing details section
     *
     * @param root the root element of XML
     */
    private Detail parseDetails(Element root) {
        String type = root.getElementsByTagName("type").item(0).getTextContent();
        String priority = root.getElementsByTagName("priority").item(0).getTextContent();
        String version = root.getElementsByTagName("version").item(1).getTextContent();

        NodeList componentNodes = root.getElementsByTagName("component");
        String component = "";
        for (int i = 0; i < componentNodes.getLength(); i++) {
            component += componentNodes.item(i).getTextContent();
        }

        NodeList labelNodes = root.getElementsByTagName("labels");
        String label = "";
        for (int i = 0; i < labelNodes.getLength(); i++) {
            label += labelNodes.item(i).getTextContent();
        }

        String status = root.getElementsByTagName("status").item(0).getTextContent();
        String resolution = root.getElementsByTagName("resolution").item(0).getTextContent();

        NodeList fixVersions = root.getElementsByTagName("fixVersion");
        String fixVersion = "";
        for (int i = 0; i < fixVersions.getLength(); i++) {
            fixVersion += fixVersions.item(i).getTextContent();
            if (i != fixVersions.getLength() - 1) {
                fixVersion += ", ";
            }
        }

        NodeList nodeList = root.getElementsByTagName("customfieldname");
        String patch = "";
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getTextContent().equals("Patch Info")) {
                patch = node.getNextSibling().getNextSibling().getTextContent();
                break;
            }
        }
        String estimatedComplexity = "";
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getTextContent().equals("Estimated Complexity")) {
                estimatedComplexity = node.getNextSibling().getNextSibling().getTextContent();
                break;
            }
        }

        Detail detail = new Detail(type, priority, version, component, label, status,
                resolution, fixVersion, patch, estimatedComplexity);
        return detail;

    }

    /**
     * Parsing people section
     *
     * @param root the root element of XML
     */
    private People parsePeople(Element root) {
        String assignee = root.getElementsByTagName("assignee").item(0).getTextContent();
        String reporter = root.getElementsByTagName("reporter").item(0).getTextContent();
        String votes = root.getElementsByTagName("votes").item(0).getTextContent();
        String watchers = root.getElementsByTagName("watches").item(0).getTextContent();
        return new People(assignee, reporter, votes, watchers);
    }

    /**
     * Parsing date section
     *
     * @param root the root element of XML
     */

    private Dates parseDate(Element root) {
        String created = root.getElementsByTagName("created").item(0).getTextContent();
        String updated = root.getElementsByTagName("updated").item(0).getTextContent();
        String resolved = root.getElementsByTagName("resolved").item(0).getTextContent();
        return new Dates(created, updated, resolved);
    }

    /**
     * Parsing description section
     *
     * @param root the root element of XML
     */

    private Description parseDescription(Element root) {
        String description = root.getElementsByTagName("description").item(1).getTextContent();
        return new Description(description);
    }

    /**
     * Parsing comment section
     *
     * @param root the root element of XML
     */
    private List<Comment> parseComments(Element root) throws ParseException {

        NodeList nodeList = root.getElementsByTagName("comments").item(0).getChildNodes();

        List<Comment> comments = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            NamedNodeMap namedNodeMap = nodeList.item(i).getAttributes();
            if (namedNodeMap != null) {
                String author = namedNodeMap.getNamedItem("author").getTextContent();
                String created = namedNodeMap.getNamedItem("created").getTextContent();
                String description = nodeList.item(i).getTextContent();

                Comment comment = new Comment(author, created, description);
                comments.add(comment);
            }
        }
        return comments;
    }

    /**
     * Downloading and parsing the url content
     *
     * @param url URL address
     */
    private Document parseUrl(String url) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new URL(url).openStream());
        return document;
    }

    /**
     * Writing header of CSV file
     *
     * @param fileName file name of CSV
     */
    private static CSVWriter writeHeader(String fileName) throws IOException {
        File file = new File("result/" + fileName);
        file.getParentFile().mkdir();
        FileWriter fileWriter = new FileWriter(file);
        CSVWriter csvWriter = new CSVWriter(fileWriter);

        String[] headers = {"Type", "Priority", "Affect Versions", "Components",
                "Labels", "Patch Info", "Estimated Complexity", "Status", "Resolution", "Fix Version",
                "Assignee", "Reporter", "Votes", "Watchers",
                "Created", "Created Epoch", "Updated", "Updated Epoch", "Resolved", "Resolved Epoch",
                "q2.Description",
                "Comments"
        };
        csvWriter.writeNext(headers);
        return csvWriter;
    }

    /**
     * Writing data to CSV file
     *
     * @param csvWriter Object for writing CSV
     * @param issue     Object which should be written to the file
     */
    private static void writeData(CSVWriter csvWriter, Issue issue) {

        String[] line = {issue.getDetail().getType(), issue.getDetail().getPriority(),
                issue.getDetail().getAffectVersions(), issue.getDetail().getComponents(),
                issue.getDetail().getLabels(), issue.getDetail().getPatchInfo(),
                issue.getDetail().getEstimatedComplexity(), issue.getDetail().getStatus(),
                issue.getDetail().getResolution(), issue.getDetail().getFixVersion(),

                issue.getPeople().getAssignee(), issue.getPeople().getReporter(), issue.getPeople().getVotes(),
                issue.getPeople().getWatchers(),

                issue.getDates().getCreated(), issue.getDates().getCreatedEpoch(),
                issue.getDates().getUpdated(), issue.getDates().getUpdatedEpoch(),
                issue.getDates().getResolved(), issue.getDates().getResolvedEpoch(),

                issue.getDescription().getDescription(),

                Util.listToString(issue.getComments())

        };
        csvWriter.writeNext(line);
    }
}
