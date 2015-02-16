package com.ericsson.msc.group5.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="OS")
public class OS{
	@Id
	@Column(name="os id")
	private Integer os_id; 
	@Column(length=45)
	private String os;
	
	public OS() {
		
	}
	
	public OS(Integer os_id, String os){
		this.os_id = os_id;
		this.os = os;
	}

	public Integer getOs_id() {
		return os_id;
	}

	public void setOs_id(Integer os_id) {
		this.os_id = os_id;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}
}