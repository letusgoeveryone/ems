package com.letusgo.dto;


public class DCollege {

    private Integer id;
    private String name;
    private String currterm;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCurrterm() {
		return currterm;
	}
	public void setCurrterm(String currterm) {
		this.currterm = currterm;
	}
	public DCollege(Integer id, String name, String currterm) {
		super();
		this.id = id;
		this.name = name;
		this.currterm = currterm;
	}
	public DCollege() {
		super();
	}
	@Override
	public String toString() {
		return "DCollege [id=" + id + ", name=" + name + ", currterm=" + currterm + "]";
	}
    
    
}
