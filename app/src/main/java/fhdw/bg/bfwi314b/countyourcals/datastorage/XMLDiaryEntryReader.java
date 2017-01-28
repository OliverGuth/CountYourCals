package fhdw.bg.bfwi314b.countyourcals.datastorage;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Niko.
 */

public class XMLDiaryEntryReader {

    public ArrayList<DiaryEntry> readDiaryEntry(File file) throws ParserConfigurationException, IOException, SAXException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();
        String entryXML = "";
        while (line != null) {
            entryXML = entryXML + line + "\n";
            line = bufferedReader.readLine();
        }
        bufferedReader.close();

        Document document;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        InputSource inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(entryXML));
        document = documentBuilder.parse(inputSource);

        Element xmlData = document.getDocumentElement();
        NodeList roodNode = xmlData.getElementsByTagName("DiaryEntrys");
        NodeList entryNodeList = roodNode.item(0).getChildNodes();

        Node entryNode;
        NodeList entryChildNodes;
        String tmpTimeStamp = "";
        String tmpName = "";
        Integer tmpQuantity = 0;
        String tmpUnit = "";
        Integer tmpKCal = 0;

        ArrayList<DiaryEntry> diaryList = new ArrayList<DiaryEntry>();

        for (int i = 0; i < entryNodeList.getLength(); i++) {
            entryNode = entryNodeList.item(i);
            entryChildNodes = entryNode.getChildNodes();
            tmpTimeStamp = entryChildNodes.item(0).getNodeValue();
            tmpName = entryChildNodes.item(1).getNodeValue();
            tmpQuantity = Integer.parseInt(entryChildNodes.item(2).getNodeValue());
            tmpUnit = entryChildNodes.item(2).getNodeValue();
            tmpKCal = Integer.parseInt(entryChildNodes.item(3).getNodeValue());

            diaryList.add(new DiaryEntry(tmpTimeStamp, tmpName, tmpQuantity, tmpUnit, tmpKCal));
            tmpTimeStamp = "";
            tmpName = "";
            tmpQuantity = 0;
            tmpUnit = "";
            tmpKCal = 0;
        }

        return diaryList;
    }
}
