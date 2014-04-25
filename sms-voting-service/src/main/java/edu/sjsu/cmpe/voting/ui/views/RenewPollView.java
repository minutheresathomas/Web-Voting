package edu.sjsu.cmpe.voting.ui.views;

import java.util.List;

import com.yammer.dropwizard.views.View;

import edu.sjsu.cmpe.voting.api.Poll;

public class RenewPollView extends View{
	private final List<Poll> polls;
	
    public RenewPollView(List<Poll> polls) {
	super("renewpolls.mustache");
	this.polls = polls;
    }

    public List<Poll> getPoll() {
	return polls;
    }
}
