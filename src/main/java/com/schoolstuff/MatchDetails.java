package com.schoolstuff;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by Madis on 03/01/2017.
 */
public class MatchDetails
{
    private String durationHours;
    private String durationMinutes;
    private String durationSeconds;
    private String matchid;
    private String radiantScore;
    private String direScore;
    private String winner;

    //Constructor that takes XML document as input.
    public MatchDetails(Document xmlDocument)
    {
        Element rootElement = xmlDocument.getDocumentElement();
        matchid = rootElement.getElementsByTagName("match_id").item(0).getTextContent();
        radiantScore = rootElement.getElementsByTagName("radiant_score").item(0).getTextContent();
        direScore =  rootElement.getElementsByTagName("dire_score").item(0).getTextContent();
        if(rootElement.getElementsByTagName("radiant_win").item(0).getTextContent().equals("false"))
            winner = "Dire";
        else
            winner = "Radiant";

        //Default match duration is in seconds so convert it into hours, minutes and seconds.
        int durationinseconds = Integer.parseInt(rootElement.getElementsByTagName("duration").item(0).getTextContent());
        durationHours = Integer.toString(durationinseconds/3600);
        durationinseconds = durationinseconds%3600;
        if (durationinseconds/60 < 10)
            durationMinutes = "0" + Integer.toString(durationinseconds/60);
        else
            durationMinutes = Integer.toString(durationinseconds/60);
        durationinseconds = durationinseconds%60;
        if (durationinseconds < 10)
            durationSeconds = "0" + Integer.toString(durationinseconds);
        else
            durationSeconds = Integer.toString(durationinseconds);
    }

    public String getDurationHours()
    {
        return durationHours;
    }

    public String getDurationMinutes()
    {
        return durationMinutes;
    }

    public String getDurationSeconds()
    {
        return durationSeconds;
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
