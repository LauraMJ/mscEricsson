package com.ericsson.msc.group5.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Input_Mode")
public class InputMode {
	@Id
	@Column(name="input_mode_id")
	private Integer inputModeId; 
	@Column(name="input_mode", length=45)
	private String inputMode;
	
	public InputMode() {
		//
	}
	
	public InputMode(Integer inputModeId, String inputMode){
		this.inputModeId = inputModeId;
		this.inputMode = inputMode;
	}

	public Integer getInputModeId() {
		return inputModeId;
	}

	public void setInputModeId(Integer inputModeId) {
		this.inputModeId = inputModeId;
	}

	public String getInputMode() {
		return inputMode;
	}

	public void setInputMode(String inputMode) {
		this.inputMode = inputMode;
	}
}