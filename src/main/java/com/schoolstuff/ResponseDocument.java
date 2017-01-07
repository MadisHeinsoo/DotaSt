package com.schoolstuff;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Madis on 07/01/2017.
 */
public class ResponseDocument
{
    private Document responseDoc;

    //Generates XML document based on query.
    public ResponseDocument(String url, String charset, String query)
    {
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
            responseDoc = builder.parse(response);
        }
        catch(Exception e)
        {
            //There could possibly be 3 different exceptions in try block.
            e.printStackTrace();
        }

        //Removes whitespaces and fixes some structure issues.
        responseDoc.getDocumentElement().normalize();
    }

    public Document getResponseDoc()
    {
        return responseDoc;
    }

}
