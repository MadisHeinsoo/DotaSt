package com.schoolstuff;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;


public class HomePage extends WebPage
{
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters)
	{
		super(parameters);

		//add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

		// TODO Add your page's components here

		String url = "https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/v1/";
		String charset = StandardCharsets.UTF_8.toString();
		String key = "DD2F5A5CC5099227EA909DE0C6B7E35D";
		String matchID = "2880090255";
		String format = "XML";

		String query = String.format("format=%s&match_id=%s&key=%s", format, matchID, key);

		String responseXML = "";


		Document respXML = null;



		try
		{
			URLConnection connection = new URL(url + "?" + query).openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			InputStream response = connection.getInputStream();

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			respXML = builder.parse(response);


		}
		catch(IOException e){}
		catch(ParserConfigurationException e){}
		catch(SAXException e){}

		//responseXML = respXML.getDocumentElement().getTagName();


		Element rootElement = respXML.getDocumentElement();
		NodeList children = rootElement.getChildNodes();

		for (int i = 0; i < children.getLength(); i++)
		{
			Node child = children.item(i);
			responseXML = responseXML + " " + child;
		}
		//responseXML = rootElement.getChildNodes().item(0).toString();

		add(new Label("message", responseXML));
    }
}
