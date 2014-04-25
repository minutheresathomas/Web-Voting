/**
 * 
 */
package edu.sjsu.cmpe.voting.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import edu.sjsu.cmpe.voting.api.Poll;

/**
 * Poll repository Interface
 * @author minu
 *	this contains the methods
 */
public interface PollsRepositoryInterface {
	/**
	 * Save a new poll in the repository
	 * 
	 * @param newPoll
	 *            a poll instance to be create in the repository
	 * @return a newly created poll instance with auto-generated key
	 */
	Poll savePoll(Poll newPoll) throws Exception ;
	
	/**
	 * Get the polls in the repository
	 * 
	 * @return a hashmap with all the poll entries
	 */
	HashMap<Long, Object> iterateHashMap() throws Exception;
	
	/**
	 * Remove the poll in the repository based on the id
	 * 
	 * @param id
	 * 			id of the poll to be deleted
	 * 
	 */
	void removePoll(String id);
	
	/**
	 * Get the poll in the repository based on the id
	 * 
	 * @param id
	 * 			id of the poll to be deleted
	 * @return a poll instance with auto-generated key
	 */
	Poll getPollById(String id);
	
	/**
	 * Get the list of polls in the repository 
	 * 
	 * @return a list of poll instances with auto-generated key
	 */
	List<Poll> getPolls();
	
	/**
	 * Set the count of each option in the poll based on user participation of the poll 
	 * 
	 * @param id
	 * 			id of the poll
	 * @param option
	 * 			option that the user has voted for
	 */
	void setCountForOption(String id, String option);
	
	/**
	 * update the end date of each poll based on admin entry of the poll 
	 * 
	 * @param id
	 * 			id of the poll
	 * @param endDate
	 * 			end date that the admin has provided for the Poll
	 */
	void updatePollDate(String id, Date endDate);
	
	/**
	 * Get the polls based on user search 
	 * 
	 * @param que
	 * 			sub string of the poll
	 *
	 */
	List<Poll> getPollByQue(String que);
}
