package edu.sjsu.cmpe.voting.repository;

import static com.google.common.base.Preconditions.checkNotNull;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import edu.sjsu.cmpe.voting.api.Choice;
import edu.sjsu.cmpe.voting.api.Poll;

public class PollsDBRepository implements PollsRepositoryInterface{
	MongoClient mongoClient;
	DB db;
	DBCollection collection;
	
	public PollsDBRepository() throws UnknownHostException
	{
		mongoClient = new MongoClient();
		db = mongoClient.getDB("sms-voting");
		collection = db.getCollection("polls");
	}
	
	public static String createID()
	{
		return UUID.randomUUID().toString().substring(0, 4);
	}
	

	/* (non-Javadoc)
	 * @see edu.sjsu.cmpe.voting.repository.PollsRepositoryInterface#savePoll(edu.sjsu.cmpe.voting.Poll)
	 */
	@Override
	public Poll savePoll(Poll newPoll) throws Exception {
		checkNotNull(newPoll,"newPoll must not be null to add to PollRepository");
		try {
			//Generate New Unique id for the Poll
			String key = createID();
			newPoll.setId(key);
			System.out.println("(from the repository) Question is : " + newPoll.getQuestion());
			System.out.println("(from the repository-savePoll) Start date is : " + newPoll.getStartDate());
			// add the new poll to the DB
		
			BasicDBObject pollDoc = new BasicDBObject();
			List<BasicDBObject> choiceList = new ArrayList<BasicDBObject>();
			pollDoc.put("_id", newPoll.getId());
			pollDoc.put("question", newPoll.getQuestion());
			Iterator<Choice> it = newPoll.getChoices().iterator();
			while(it.hasNext())
			{
				Choice choice = it.next();
				choiceList.add(new BasicDBObject("option", choice.getOption()).append("count", choice.getCount()));
			}
			pollDoc.put("Choices", choiceList);
			pollDoc.put("StartDate", newPoll.getStartDate());
			pollDoc.put("EndDate", newPoll.getEndDate());
			collection.insert(pollDoc);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return newPoll;
	}
	
	/* (non-Javadoc)
	 * @see edu.sjsu.cmpe.voting.repository.PollsRepositoryInterface#getPolls()
	 */
	@Override
	public List<Poll> getPolls()
	{
		List<Poll> polls = new ArrayList<Poll>();
		List<Choice> choices = new ArrayList<Choice>();
	    
	    // Retrieve all polls from the DB
	    
	    DBCursor cursor = collection.find();
	    try {
		    while(cursor.hasNext())
		    {
		    	DBObject pollObject = cursor.next();
		    	Poll copyPoll = new Poll();
		    	copyPoll.setId((String) pollObject.get("_id"));
		    	copyPoll.setQuestion((String) pollObject.get("question"));
		    	copyPoll.setStartDate((Date) pollObject.get("startDate"));
		    	copyPoll.setEndDate((Date) pollObject.get("endDate"));
		    	choices = (List<Choice>) pollObject.get("Choices");
		    	copyPoll.setChoices(choices);
		    	// add the current poll to list of polls for display
		    	polls.add(copyPoll);
		    }
	    } finally {
	    	cursor.close();
	    }
	    return polls;
	}

	/* (non-Javadoc)
	 * @see edu.sjsu.cmpe.voting.repository.PollsRepositoryInterface#iterateHashMap()
	 */
	@Override
	public HashMap<Long, Object> iterateHashMap() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.sjsu.cmpe.voting.repository.PollsRepositoryInterface#removePoll(java.lang.String)
	 */
	@Override
	public void removePoll(String id) {
		// TODO Auto-generated method stub
		if(collection.count()!=0)
			collection.remove(new BasicDBObject("_id",id));
			System.out.println("removed");
	}

	/* (non-Javadoc)
	 * @see edu.sjsu.cmpe.voting.repository.PollsRepositoryInterface#getPollById(java.lang.String)
	 */
	@Override
	public Poll getPollById(String id) {
		Poll poll = new Poll();
		List<Choice> choices = new ArrayList<Choice>();
		BasicDBObject query = new BasicDBObject("_id", id);
		DBCursor cursor = collection.find(query);
		try {
			while(cursor.hasNext())
			{
				DBObject pollObj = cursor.next();
				poll.setId((String) pollObj.get("_id"));
				poll.setQuestion((String) pollObj.get("question"));
				poll.setStartDate((Date) pollObj.get("startDate"));
				poll.setEndDate((Date) pollObj.get("endDate"));
				choices = (List<Choice>) pollObj.get("Choices");
				poll.setChoices(choices);
			}
		} finally {
			cursor.close();
		}
		return poll;
	}

	/* (non-Javadoc)
	 * @see edu.sjsu.cmpe.voting.repository.PollsRepositoryInterface#setCountForOption(java.lang.String, java.lang.String)
	 */
	@Override
	public void setCountForOption(String id, String option) {
		BasicDBObject query = new BasicDBObject("_id", id);
		DBCursor cursor = collection.find(query);
		try {
			if(cursor.hasNext())
			{
				DBObject pollDoc = cursor.next();
				System.out.println("the document is: "+pollDoc);
				BasicDBList choiceList = (BasicDBList) pollDoc.get("Choices");
				System.out.println("the option is: "+choiceList);
				 for(int i=0 ; i<choiceList.size() ; i++)
				 {
					 BasicDBObject choice = (BasicDBObject) choiceList.get(i);
					 if(option.equals(choice.get("option")))
					 {
						 	long count = (Long) choice.get("count");
							System.out.println("the count is: "+count);
							long increment = count+1;
							choice.put("count", increment);
							collection.save(pollDoc);
							break;
					 }
				 }
			}
		} finally {
			cursor.close();
		}
	}

	/* (non-Javadoc)
	 * @see edu.sjsu.cmpe.voting.repository.PollsRepositoryInterface#updatePollDate(java.lang.String, java.util.Date)
	 */
	@Override
	public void updatePollDate(String id, Date endDate) {
		BasicDBObject query = new BasicDBObject("_id", id);
		DBCursor cursor = collection.find(query);
		try {
			if(cursor.hasNext())
			{
				DBObject pollDoc = cursor.next();
				pollDoc.put("EndDate", endDate);
				collection.save(pollDoc);
			}
		} finally {
			cursor.close();
		}
	}

	/* (non-Javadoc)
	 * @see edu.sjsu.cmpe.voting.repository.PollsRepositoryInterface#getPollByQue(java.lang.String)
	 */
	@Override
	public List<Poll> getPollByQue(String que) {
		List<Poll> polls = new ArrayList<Poll>();
		List<Choice> choices = new ArrayList<Choice>();
		String pattern = "^.*"+que+".*$";
		Pattern match = Pattern.compile(pattern, java.util.regex.Pattern.MULTILINE);
		BasicDBObject query = new BasicDBObject("question", match);
		DBCursor cursor = collection.find(query);
		try {
			while(cursor.hasNext())
			{
				DBObject pollDoc = cursor.next();
				Poll copyPoll = new Poll();
				copyPoll.setId((String) pollDoc.get("_id"));
		    	copyPoll.setQuestion((String) pollDoc.get("question"));
		    	copyPoll.setStartDate((Date) pollDoc.get("startDate"));
		    	copyPoll.setEndDate((Date) pollDoc.get("endDate"));
		    	choices = (List<Choice>) pollDoc.get("Choices");
		    	copyPoll.setChoices(choices);
		    	// add the current poll to list of polls for display
		    	polls.add(copyPoll);
			}
		} finally {
			cursor.close();
		}
		return polls;
	}
}
