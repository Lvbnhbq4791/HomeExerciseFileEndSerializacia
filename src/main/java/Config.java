import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

public class Config {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse("shop.xml");

    XPath xPath = XPathFactory.newInstance().newXPath();

    public Config() throws ParserConfigurationException, IOException, SAXException {
    }

    public void configload() throws XPathExpressionException {

        boolean doLoad = Boolean.parseBoolean(xPath
                .compile("/config/load/enabled")
                .evaluate(doc));
        String loadFileName = xPath
                .compile("/config/load/fileName")
                .evaluate(doc);
        String loadFormat = xPath
                .compile("/config/load/format")
                .evaluate(doc);

        if (doLoad) {
            File loadFile = new File(loadFileName);
            switch (loadFormat) {
                case "json" -> Basket.loadFromJsonFile(loadFile);
                case "txt" -> Basket.loadFromTxtFile(loadFile);
            }
        }
    }

    public void configsave(Basket basket) throws XPathExpressionException {
        boolean doSave = Boolean.parseBoolean(xPath
                .compile("/config/save/enabled")
                .evaluate(doc));
        String saveFileName = xPath
                .compile("/config/save/fileName")
                .evaluate(doc);
        String saveFormat = xPath
                .compile("/config/save/format")
                .evaluate(doc);

        if (doSave) {
            File saveFile = new File(saveFileName);
            switch (saveFormat) {
                case "json" -> basket.saveJson(saveFile);
                case "txt" -> basket.saveTxt(saveFile);
            }
        }
    }

    public void configLog(ClientLog clientLog) throws XPathExpressionException {
        boolean doLog = Boolean.parseBoolean(xPath
                .compile("/config/log/enabled")
                .evaluate(doc));
        String logFileName = xPath
                .compile("/config/log/fileName")
                .evaluate(doc);

        if (doLog) {
            String[] strings = logFileName.split("\\.",2);
            System.out.println(logFileName);
            String logFormat = strings[1];
            if (logFormat.equals("csv")){
                File logFile = new File (logFileName);
                clientLog.exportAsCSV(logFile);
            }
        }
    }
}

