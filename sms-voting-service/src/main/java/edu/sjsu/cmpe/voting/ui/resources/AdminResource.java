package edu.sjsu.cmpe.voting.ui.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.sjsu.cmpe.voting.api.Poll;
import edu.sjsu.cmpe.voting.repository.PollsRepositoryInterface;
import edu.sjsu.cmpe.voting.ui.views.AdminView;
import edu.sjsu.cmpe.voting.ui.views.CreatePollView;
import edu.sjsu.cmpe.voting.ui.views.PollView;
import edu.sjsu.cmpe.voting.ui.views.RenewPollView;
import edu.sjsu.cmpe.voting.ui.views.UserView;

@Path("/admin/polls/")
@Produces(MediaType.TEXT_HTML)
public class AdminResource {
	private final PollsRepositoryInterface pollRepository;

    public AdminResource(PollsRepositoryInterface pollRepository) {
	this.pollRepository = pollRepository;
    }

    @GET
    public AdminView getUserHome() {
		System.out.println("getting the polls!...");
    	return new AdminView(pollRepository.getPolls());
    }
    
    @GET
    @Path("/createPoll")
    public CreatePollView getCreatePoll() {
    	CreatePollView a = new CreatePollView();
    	return a;
    }
    
    @GET
    @Path("/renewPolls")
    public RenewPollView getRenewPollsPage() {
		System.out.println("getting the polls!...");
    	return new RenewPollView(pollRepository.getPolls());
    } 
    
//    @POST
//    @Path("/{id}")
//    public void getIndividualPoll(Poll poll) {
//    	System.out.println("Create the poll...");
//    }
}
