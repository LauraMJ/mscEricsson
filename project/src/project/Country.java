package project;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country {
	@Id
	private int mcc; //PK
	private String country;
	
	public Country() {
		//
	}
	
	public Country(int mcc, String country){
		this.mcc = mcc;
		this.country = country;
	}

}
