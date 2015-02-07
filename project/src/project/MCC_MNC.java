package project;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MCC_MNC {
	@Id
	private MCC_MNCCK mccAndMnc; //Composite key of MCC and MNC
	private String operator;

	public MCC_MNC() {
		//
	}
	
	public MCC_MNC(MCC_MNCCK mccAndMnc, String operator){
		this.mccAndMnc = mccAndMnc;
		this.operator = operator;
	}

}
