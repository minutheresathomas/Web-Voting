package edu.sjsu.cmpe.voting.ui.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.sjsu.cmpe.voting.api.Poll;
import edu.sjsu.cmpe.voting.repository.PollsRepositoryInterface;
import edu.sjsu.cmpe.voting.ui.views.PollView;
import edu.sjsu.cmpe.voting.ui.views.UserView;

@Path("/user/polls/")
@Produces(MediaType.TEXT_HTML)
public class UserResource {
	private final PollsRepositoryInterface pollRepository;

    public UserResource(PollsRepositoryInterface pollRepository) {
	this.pollRepository = pollRepository;
    }

    @GET
    public UserView getUserHome() {
	//return new HomeView(bookRepository.getBookByISBN(1L));
    	System.out.println("getting the polls!...");
    	return new UserView(pollRepository.getPolls());
    }
    
    @GET
    @Path("/{id}")
    public PollView getIndividualPoll(@PathParam("id") String id) {
    	System.out.println("getting the poll based on id!...");
    	Poll myPoll = pollRepository.getPollById(id);
    	System.out.println("My poll is : question : "+myPoll.getQuestion());
    	return new PollView(myPoll);
    }
    
    @GET
    @Path("/searchPolls")
    public UserView getSearchPoll(@QueryParam("question") String que) {
    	System.out.println("getting the poll based on search!...");
    	List<Poll> myPolls = pollRepository.getPollByQue(que);
    	return new UserView(myPolls);
    }
}
