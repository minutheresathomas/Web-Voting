package edu.sjsu.cmpe.voting.ui.views;

import java.util.List;

import com.yammer.dropwizard.views.View;

import edu.sjsu.cmpe.voting.api.Poll;

public class PollView extends View{
			private final Poll poll;
			
		    public PollView(Poll poll) {
			super("individualpoll.mustache");
			this.poll = poll;
		    }

		    public Poll getPoll() {
			return poll;
		    }
}
