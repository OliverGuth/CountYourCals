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
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import fhdw.bg.bfwi314b.countyourcals.Models.DiaryEntry;
import fhdw.bg.bfwi314b.countyourcals.Models.Unit;

/**
 * Created by Niko Reder
 */

public class XMLDiaryEntryReader {

    //Reads the DiaryEntrys  from  an XML-File and returns these as ArrayList<DiaryEntry>
    //Therefore the XML-Opject is mapped into a tree and all attributes are read
    //From these Attributes the DiaryEntrys are creadted (as DiaryEntry)
    public ArrayList<DiaryEntry> readDiaryEntry(File file) throws ParserConfigurationException, IOException, SAXException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();
        String entryXML = "";
        while (line != null) {
            entryXML = entryXML + line /*+ System.getProperty("line.Seperator")*/;
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
        /*NodeList rootNode = xmlData.getElementsByTagName("DiaryEntrys");
        NodeList entryNodeList = rootNode.item(0).getChildNodes();*/
        NodeList entryNodeList = xmlData.getElementsByTagName("DiaryEntry");

        Node entryNode;
        NodeList entryChildNodes;
        Date tmpTimeStamp = null;
        Integer tmpQuantity = null;
        String tmpUnitName = "";
        String tmpUnitShort = "";
        Unit tmpUnit = null;
        Integer tmpKCal = null;

        ArrayList<DiaryEntry> diaryList = new ArrayList<DiaryEntry>();

        for (int i = 0; i < entryNodeList.getLength(); i++) {
            entryNode = entryNodeList.item(i);
            entryChildNodes = entryNode.getChildNodes();
            tmpTimeStamp = new Date(entryChildNodes.item(0).getTextContent());
            tmpQuantity = Integer.parseInt(entryChildNodes.item(2).getTextContent());
            tmpUnitName = entryChildNodes.item(3).getTextContent();
            tmpUnitShort = entryChildNodes.item(4).getTextContent();
            tmpUnit = new Unit(tmpUnitName, tmpUnitShort, tmpQuantity);
            tmpKCal = Integer.parseInt(entryChildNodes.item(1).getTextContent());
            diaryList.add(new DiaryEntry(tmpTimeStamp, tmpUnit, tmpKCal));
            tmpTimeStamp = null;
            tmpQuantity = null;
            tmpUnitName = "";
            tmpUnitShort = "";
            tmpUnit = null;
            tmpKCal = null;
        }

        return diaryList;
    }
}
