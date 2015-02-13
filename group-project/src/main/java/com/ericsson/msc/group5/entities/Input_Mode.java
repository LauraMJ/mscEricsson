package com.ericsson.msc.group5.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Input Mode")
public class Input_Mode {
	@Id
	@Column(name="input mode id")
	private Integer input_mode_id; 
	@Column(length=45)
	private String input_mode;
	
	public Input_Mode() {
		//
	}
	
	public Input_Mode(Integer input_mode_id, String input_mode){
		this.input_mode_id = input_mode_id;
		this.input_mode = input_mode;
	}

	public Integer getInput_mode_id() {
		return input_mode_id;
	}

	public void setInput_mode_id(Integer input_mode_id) {
		this.input_mode_id = input_mode_id;
	}

	public String getInput_mode() {
		return input_mode;
	}

	public void setInput_mode(String input_mode) {
		this.input_mode = input_mode;
	}

	

}