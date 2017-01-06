package com.schoolstuff;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;


public class HomePage extends WebPage
{
    //Generates XML document based on query.
    private Document responseXML(String url, String charset, String query)
    {
        Document respXML = null;
        try
        {
            //Creates connection object and streams information from constructed URL to response variable.
            URLConnection connection = new URL(url + "?" + query).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            InputStream response = connection.getInputStream();

            //Defines a factory API to create DOM object trees from XML document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Parses information from recieved GET request into previously created Document object.
            respXML = builder.parse(response);
        }
        catch(Exception e)
        {
            //There could possibly be 3 different exceptions in try block.
            e.printStackTrace();
        }

        //Removes whitespaces and fixes some structure issues.
        respXML.getDocumentElement().normalize();
        return respXML;
    }

	public HomePage(final PageParameters parameters)
	{
		super(parameters);

		//Variables that are required for basic functioning.
        String url = "https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/v1/";
        String charset = StandardCharsets.UTF_8.toString();
        String key = "DD2F5A5CC5099227EA909DE0C6B7E35D";
        String matchID = "2880899735";
        String format = "XML";

        //Generates query part in URL
        String query = String.format("format=%s&match_id=%s&key=%s", format, matchID, key);

        //Calls responseXML method and saves return value in Document object
        Document response = responseXML(url, charset, query);

        //Creates a match object.
        MatchDetails match = new MatchDetails(response);

        //Shows information on html file
        add(new Label("matchnumber", match.getMatchid()));
        add(new Label("matchduration", match.getDuration()));
        add(new Label("rscore", match.getRadiantScore()));
        add(new Label("dscore", match.getDireScore()));
        add(new Label("winner", match.getWinner()));
    }
}
