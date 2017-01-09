package com.schoolstuff;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by Madis on 09/01/2017.
 */
public class HeroList
{
    private String name;
    private String id;
    private String localizedname;

    public HeroList(Document xmlDocument)
    {
        Element rootElement = xmlDocument.getDocumentElement();
        name = rootElement.getElementsByTagName("name").item(0).getTextContent();
        id = rootElement.getElementsByTagName("id").item(0).getTextContent();
        localizedname = rootElement.getElementsByTagName("localized_name").item(0).getTextContent();
    }

    public String getName()
    {
        return name;
    }

    public String getId()
    {
        return id;
    }

    public String getLocalizedname()
    {
        return localizedname;
    }

}
