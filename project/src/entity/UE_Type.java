package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="UE Type")
public class UE_Type {
	@Id
	@Column(name="ue type id")
	private int ue_type_id; 
	@Column(length=45)
	private String ue_type;
	
	public UE_Type() {
		
	}
	
	public UE_Type(int ue_type_id, String ue_type){
		this.ue_type_id = ue_type_id;
		this.ue_type = ue_type;
	}

	public int getUe_type_id() {
		return ue_type_id;
	}

	public void setUe_type_id(int ue_type_id) {
		this.ue_type_id = ue_type_id;
	}

	public String getUe_type() {
		return ue_type;
	}

	public void setUe_type(String ue_type) {
		this.ue_type = ue_type;
	}

}