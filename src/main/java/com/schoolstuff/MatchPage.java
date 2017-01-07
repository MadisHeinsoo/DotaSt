package com.schoolstuff;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.w3c.dom.Document;

import java.nio.charset.StandardCharsets;

/**
 * Created by Madis on 06/01/2017.
 */
public class MatchPage extends WebPage
{
    public MatchPage(final PageParameters parameters)
    {


        //Variables that are required for basic functioning.
        String url = "https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/v1/";
        String charset = StandardCharsets.UTF_8.toString();
        String key = "DD2F5A5CC5099227EA909DE0C6B7E35D";
        String format = "XML";
        String matchid = "";

        if(parameters.getNamedKeys().contains("matchid"))
        {
            matchid = parameters.get("matchid").toString();
        }

        //Generates query part in URL
        String query = String.format("format=%s&match_id=%s&key=%s", format, matchid, key);

        //Calls responseXML method and saves return value in Document object
        ResponseDocument matchidResponse = new ResponseDocument(url, charset, query);
        Document response = matchidResponse.getResponseDoc();

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
