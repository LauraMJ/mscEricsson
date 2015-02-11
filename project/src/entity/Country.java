package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country {
	@Id
	@Column
	private int mcc; //PK
	@Column(length=45)
	private String country;
	
	public Country() {
		//
	}
	
	public Country(int mcc, String country){
		this.mcc = mcc;
		this.country = country;
	}

}