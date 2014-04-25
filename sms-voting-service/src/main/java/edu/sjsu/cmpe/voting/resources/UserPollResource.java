package edu.sjsu.cmpe.voting.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

//import edu.sjsu.cmpe.voting.api.Choice;
import edu.sjsu.cmpe.voting.api.Poll;
import edu.sjsu.cmpe.voting.dto.PollDto;
import edu.sjsu.cmpe.voting.repository.PollsRepositoryInterface;

@Path("/v1/user/polls")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserPollResource {
	
	private final PollsRepositoryInterface pollsRepository;

	/**
	 * User Polls resource
	 * @param pollsRepository
	 */
	public UserPollResource(PollsRepositoryInterface pollsRepository) {
		this.pollsRepository = pollsRepository;
	}
	
	/**
	 * 1. Get the Polls
	 * 		Resource : GET - /polls/id
	 * 		Description : get an existing Poll from the repository
	 * 
	 */
	@GET
	@Timed(name="view-all-polls")	
	public HashMap<String, Object> viewAllPolls()
	{
		List<Poll> allPolls = new ArrayList<Poll>();
		allPolls = pollsRepository.getPolls();
		HashMap<String, Object> pollsMap = new HashMap<String, Object>();
		List<PollDto> pollLinks = new ArrayList<PollDto>();
		for(Poll p : allPolls)
		{
			pollsMap.put(p.getId(), p.getQuestion());
			pollLinks.add(new PollDto("view-poll", "/polls/"+p.getId(), "GET"));
		}
		HashMap<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("Questions", pollsMap);
		responseMap.put("Links", pollLinks);
		return responseMap;
	}
	
	/**
	 * 2. Get the Poll by Id
	 * 		Resource : GET - /polls/id
	 * 		Description : get an existing Poll from the repository
	 * 
	 */
	@GET
	@Timed(name="view-poll")
	@Path("/{id}")
	public HashMap<String, Object> viewPollById(@PathParam("id") String id) 
	{
		Poll pollById = pollsRepository.getPollById(id);
		HashMap<String, Object> myMap = new HashMap<String, Object>();
		myMap.put("Poll", pollById);
		return myMap;
	}
	
	/**
	 * 3. choose an option
	 * 		Resource : PUT - /polls/{id}/?option={option}
	 */
	@PUT
	@Timed(name="participate-poll")
	@Path("/{id}")
	public HashMap<String, Object> participatePoll(@PathParam("id") String id, 
							@QueryParam("option") String option)
	{
		pollsRepository.setCountForOption(id, option);
		Poll pollById = pollsRepository.getPollById(id);
		HashMap<String, Object> myMap = new HashMap<String, Object>();
		myMap.put("Poll", pollById);
		return myMap;
	}
	
	/**
	 * 4. Search with sub string
	 * 		Resource : GET - /polls/?question={que}
	 */
	@GET
	@Timed(name="search-poll")
	@Path("/searchPolls")
	public HashMap<String, Object> searchPoll(@QueryParam("question") String que)
	{
		List<Poll> polls = pollsRepository.getPollByQue(que);
		HashMap<String, Object> myMap = new HashMap<String, Object>();
		List<PollDto> pollLinks = new ArrayList<PollDto>();
		for(Poll p : polls)
		{
			myMap.put(p.getId(), p.getQuestion());
			pollLinks.add(new PollDto("view-poll", "/polls/"+p.getId(), "GET"));
		}
		HashMap<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("Questions", myMap);
		responseMap.put("Links", pollLinks);
		return responseMap;
	}
}
