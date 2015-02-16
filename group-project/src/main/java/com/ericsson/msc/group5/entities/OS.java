package com.ericsson.msc.group5.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="OS")
public class OS{
	@Id
	@Column(name="os_id")
	private Integer osId; 
	@Column(length=45)
	private String os;
	
	public OS() {
		
	}
	
	public OS(Integer osId, String os){
		this.osId = osId;
		this.os = os;
	}

	public Integer getOsId() {
		return osId;
	}

	public void setOsId(Integer osId) {
		this.osId = osId;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}
}