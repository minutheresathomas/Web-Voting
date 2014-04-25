package edu.sjsu.cmpe.voting.dto;

import java.util.ArrayList;
import java.util.List;

import edu.sjsu.cmpe.voting.dto.PollDto;

public class PollsDto {
	private List<PollDto> polls = new ArrayList<PollDto>();

    public void addLink(PollDto poll) {
	polls.add(poll);
    }

	public List<PollDto> getPolls() {
		return polls;
	}

	public void setPolls(List<PollDto> polls) {
		this.polls = polls;
	}

}
