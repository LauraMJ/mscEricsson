package com.ericsson.msc.group5.entities;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "input_mode")
public class InputMode {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "input_mode_id")
	private Integer inputModeId;
	@Column(name = "input_mode", length = 45)
	private String inputMode;

	@OneToMany(mappedBy = "inputModeClass")
	private Collection <UserEquipment> userEquipment;

	public InputMode() {
	}

	public InputMode(String inputMode) {
		this.inputMode = inputMode;
	}

	public Integer getInputModeId() {
		return inputModeId;
	}

	public String getInputMode() {
		return inputMode;
	}

	public void setInputMode(String inputMode) {
		this.inputMode = inputMode;
	}
}