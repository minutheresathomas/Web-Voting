package edu.sjsu.cmpe.voting.ui.views;

import java.util.List;

import com.yammer.dropwizard.views.View;

import edu.sjsu.cmpe.voting.api.Poll;

public class CreatePollView extends View{
	
    public CreatePollView() {
		super("createpoll.mustache");
	}
}
