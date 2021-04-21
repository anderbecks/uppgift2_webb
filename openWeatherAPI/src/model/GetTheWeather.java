package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public class GetTheWeather {

	public static void getWeather(weatherBean wBean) throws IOException {
	
		String urlToSend = "http://api.openweathermap.org/data/2.5/weather?q=" + wBean.getCity() + ","
				+ wBean.getNation() + "&appid=5e8c82c72da18a8786e106963c611ad1&mode=xml";
		
		
		System.out.println(urlToSend);
		
		URL line_api_url = new URL(urlToSend);
		
		HttpURLConnection linec = (HttpURLConnection) line_api_url.openConnection();
	
		linec.setDoInput(true);
		linec.setDoOutput(true);
		linec.setRequestMethod("GET");
	
		
		BufferedReader in = new BufferedReader(new InputStreamReader(linec.getInputStream()));
		
		
		String inputLine;
		
		String apiResponse = "";
		
		while((inputLine=in.readLine())!= null) {
			
			apiResponse += inputLine;
		}
	in.close();
	
	System.out.println(apiResponse);
	
	Document doc = convertStringToXMLDocument(apiResponse);
	
	doc.getDocumentElement().normalize();
	
	System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
	
	NodeList nList = doc.getElementsByTagName("clouds");
	
	for (int temp = 0; temp < nList.getLength(); temp++) {
		
		Node node = nList.item(temp);
		if (node.getNodeType() == Node.ELEMENT_NODE) {

			// set the current node as an Element
			Element eElement = (Element) node;
			// get the content of an attribute in element
			String XMLclouds = eElement.getAttribute("name");
			// and print it
			System.out.println(wBean.getCity() + " is now a " + XMLclouds);
			// save it
			wBean.setCloudsStr(XMLclouds);

		}
	}
	
	NodeList noList = doc.getElementsByTagName("temperature");
	
	for (int temp = 0; temp < noList.getLength(); temp++) {
		
		Node node = noList.item(temp);
		if (node.getNodeType() == Node.ELEMENT_NODE) {

			// set the current node as an Element
			Element eElement = (Element) node;
			// get the content of an attribute in element
			String XMLtemperature = eElement.getAttribute("value");
			// and print it
			System.out.println(wBean.getCity() + " is now a " + XMLtemperature);
			// save it
			Double tempDou = Double.parseDouble(XMLtemperature);
			wBean.setTemperature(tempDou-273.15);
		}
	}
	
	NodeList nodList = doc.getElementsByTagName("lastupdate");
	
	for (int temp = 0; temp < nodList.getLength(); temp++) {
		
		Node node = nodList.item(temp);
		if (node.getNodeType() == Node.ELEMENT_NODE) {

			// set the current node as an Element
			Element eElement = (Element) node;
			// get the content of an attribute in element
			String XMLtime = eElement.getAttribute("value");
			// and print it
			System.out.println(wBean.getCity() + " is now a " + XMLtime);
			// save it
			wBean.setDate(XMLtime);
		}
	}
	
	
	
	}

	private static Document convertStringToXMLDocument(String xmlString) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder builder = null;
		
		try {
			builder = factory.newDocumentBuilder();
			
			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
			
			return doc;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
