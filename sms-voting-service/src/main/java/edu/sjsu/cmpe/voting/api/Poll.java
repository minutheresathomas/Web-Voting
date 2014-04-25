package edu.sjsu.cmpe.voting.api;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import edu.sjsu.cmpe.voting.api.Choice;

public class Poll {
	@JsonProperty
	@JsonInclude(Include.NON_DEFAULT)
	private String id;
	
	@JsonProperty("question")
	private String question;
	
	@Valid
	@JsonProperty
	private List<Choice> choices;
	
	@JsonProperty("creation date")
	private Date startDate;
	
	@JsonProperty("expiry date")
	private Date endDate;	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

//	public Set<Choice> getChoices() {
//		return choices;
//	}
//	public void setChoices(Set<Choice> choices) {
//		this.choices = choices;
//	}
	
	public List<Choice> getChoices() {
		return choices;
	}
	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}
	
	//----------
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
