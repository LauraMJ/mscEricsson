package project;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class MCC_MNCCK implements Serializable{
	private static final long serialVersionUID = 1L;
	private int mcc;
	private int mnc;

	public MCC_MNCCK() {
		//
	}
	
	public MCC_MNCCK(int mcc, int mnc){
		super();
		this.mcc = mcc;
		this.mnc = mnc;
	}
	
	
	
	//required equals method
	public boolean equals(Object object){
		//need to add more code
		return false;
	}
	//required hashcode method
	public int hashCode(){
		int hashCode = 0; //temp to remove errors
		//generate hash for both fields together
		return hashCode;
	}
	
	
	

	public int getMcc() {
		return mcc;
	}

	public void setMcc(int mcc) {
		this.mcc = mcc;
	}

	public int getMnc() {
		return mnc;
	}

	public void setMnc(int mnc) {
		this.mnc = mnc;
	}
	

}
