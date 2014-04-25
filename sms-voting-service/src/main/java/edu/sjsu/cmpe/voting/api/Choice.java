package edu.sjsu.cmpe.voting.api;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import edu.sjsu.cmpe.voting.utils.SmsVotingUtils;

public class Choice {
	@JsonIgnore
	//private String id;
	
	@JsonProperty("option")
	@NotNull
	private String option;
	
	@JsonProperty("count")
	private long count;
	
//	public String getId() {
//		return id;
//	}
//	public void setId() {
//		this.id = UUID.randomUUID().toString();
//	}
	
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}	
}
