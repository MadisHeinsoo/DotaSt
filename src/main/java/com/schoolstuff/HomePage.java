package com.schoolstuff;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
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
            URLConnection connection = new URL(url + "?" + query).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            InputStream response = connection.getInputStream();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            respXML = builder.parse(response);
        }
        catch(Exception e){}

        respXML.getDocumentElement().normalize();
        return respXML;
    }

	public HomePage(final PageParameters parameters)
	{
		super(parameters);
        final String url = "https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/v1/";
        final String charset = StandardCharsets.UTF_8.toString();
        final String key = "DD2F5A5CC5099227EA909DE0C6B7E35D";
        final String matchID = "2880899735";
        final String format = "XML";

        String query = String.format("format=%s&match_id=%s&key=%s", format, matchID, key);

        final MatchidModel matchidModel = new MatchidModel();

        Form<?> form = new Form("messageInputForm");

        final TextField<String> text = new TextField<String>("text", new PropertyModel<String>(matchidModel, "matchid"));

        Button button = new Button("submit")
        {
            @Override
            public void onSubmit()
            {
                String query = String.format("format=%s&match_id=%s&key=%s", format, text.getConvertedInput(), key);
                Document response = responseXML(url, charset, query);
                MatchDetails match = new MatchDetails(response);
                add(new Label("matchnumber", match.getMatchid()));
                add(new Label("matchduration", match.getDuration()));
                add(new Label("rscore", match.getRadiantScore()));
                add(new Label("dscore", match.getDireScore()));
                add(new Label("winner", match.getWinner()));
                super.onSubmit();
            }
        };
        add(form);
        form.add(text);
        form.add(button);

        Document response = responseXML(url, charset, query);
        MatchDetails match = new MatchDetails(response);

        add(new Label("matchnumber", match.getMatchid()));
        add(new Label("matchduration", match.getDuration()));
        add(new Label("rscore", match.getRadiantScore()));
        add(new Label("dscore", match.getDireScore()));
        add(new Label("winner", match.getWinner()));
    }
}
