package com.schoolstuff;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by Madis on 03/01/2017.
 */
public class MatchDetails
{
    private String duration;
    private String matchid;
    private String radiantScore;
    private String direScore;
    private String winner;

    //Constructor that takes XML document as input.
    public MatchDetails(Document xmlDocument)
    {
        Element rootElement = xmlDocument.getDocumentElement();
        duration = rootElement.getElementsByTagName("duration").item(0).getTextContent();
        matchid = rootElement.getElementsByTagName("match_id").item(0).getTextContent();
        radiantScore = rootElement.getElementsByTagName("radiant_score").item(0).getTextContent();
        direScore =  rootElement.getElementsByTagName("dire_score").item(0).getTextContent();
        if(rootElement.getElementsByTagName("radiant_win").item(0).getTextContent().equals("false"))
            winner = "Dire";
        else
            winner = "Radiant";
    }

    public String getDuration()
    {
        return duration;
    }

    public String getMatchid()
    {
        return matchid;
    }

    public String getRadiantScore()
    {
        return radiantScore;
    }

    public String getDireScore()
    {
        return direScore;
    }

    public String getWinner()
    {
        return winner;
    }


}
