package edu.sjsu.cmpe.voting.dto;

public class PollDto {
	private String rel = "self"; // default is 'self'
    private String href = "#"; // default is '#'
    private String method = "GET"; // default is 'GET'

    /**
     * @param rel
     * @param href
     * @param method
     */
    public PollDto(String rel, String href, String method) {
	this.rel = rel;
	this.href = href;
	this.method = method;
    }

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
    
    
}
