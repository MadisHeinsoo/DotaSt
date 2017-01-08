package com.schoolstuff;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;


public class HomePage extends WebPage
{


	public HomePage(final PageParameters parameters)
	{
		super(parameters);

		//Shows user what he did wrong if he makes a mistake
        add(new FeedbackPanel("feedback"));

        //Textfield that has "matchid" as id in html file. User input will be saved here.
        final TextField<String> matchID = new TextField<String>("matchid",
                Model.of(""));

        //MatchID can't be empty
        matchID.setRequired(true);

        //Checks if user input was correct.
        matchID.add(new SearchValidator());

        //Creates a form logic
        Form<?> form = new Form<Void>("searchForm")
        {
            //Notes that parent class method is overwritten. Doesn't have to be added. Method itself is supposed to do something when input is submitted to form.
            @Override
            protected void onSubmit()
            {
                //Gets matchid from "matchID" variable that was typed in textfield.
                final String matchidValue = matchID.getModelObject();

                //Create PageParameters object and add matchid there.
                PageParameters pageParameters = new PageParameters();
                pageParameters.add("matchid", matchidValue);

                //New html page will be loaded which logic is in MatchPage.class with PageParameter object that contains matchID input.
                setResponsePage(MatchPage.class, pageParameters);

            }

        };

        //Form gets added to html and textfield gets added to that form.
        add(form);
        form.add(matchID);

    }
}
