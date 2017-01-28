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

public class XMLUserReader {

    public ArrayList<User> readUser(File file) throws ParserConfigurationException, IOException, SAXException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();
        String userXML = "";
        while (line != null) {
            userXML = userXML + line + "\n";
            line = bufferedReader.readLine();
        }
        bufferedReader.close();

        Document document;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        InputSource inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(userXML));
        document = documentBuilder.parse(inputSource);

        Element xmlData = document.getDocumentElement();
        NodeList userNodeList = xmlData.getElementsByTagName("User");

        Node userNode;
        NodeList userChildNodes;
        String tmpName = "";
        Character tmpGender = ' ';
        Integer tmpMaxKCal = 0;

        ArrayList<User> userList = new ArrayList<User>();

        for (int i = 0; i < userNodeList.getLength(); i++) {
            userNode = userNodeList.item(i);
            userChildNodes = userNode.getChildNodes();
            tmpName = userChildNodes.item(0).getNodeValue();
            tmpGender = userChildNodes.item(1).getNodeValue().charAt(0);
            tmpMaxKCal = Integer.parseInt(userChildNodes.item(2).getNodeValue());
            userList.add(new User(tmpName, tmpGender, tmpMaxKCal));
            tmpName = "";
            tmpGender = ' ';
            tmpMaxKCal = 0;
        }

        return userList;
    }
}
