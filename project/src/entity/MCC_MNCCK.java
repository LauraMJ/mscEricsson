//test commit
package entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class MCC_MNCCK implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer mcc;
	private Integer mnc;

	public MCC_MNCCK() {
		//
	}
	
	public MCC_MNCCK(Integer mcc, Integer mnc){
		super();
		this.mcc = mcc;
		this.mnc = mnc;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MCC_MNCCK other = (MCC_MNCCK) obj;
		if (mcc != other.mcc)
			return false;
		if (mnc != other.mnc)
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mcc;
		result = prime * result + mnc;
		return result;
	}
	
	
	

	public Integer getMcc() {
		return mcc;
	}

	public void setMcc(Integer mcc) {
		this.mcc = mcc;
	}

	public Integer getMnc() {
		return mnc;
	}

	public void setMnc(Integer mnc) {
		this.mnc = mnc;
	}
	

}
