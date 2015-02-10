package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="OS")
public class OS{
	@Id
	@Column(name="os id")
	private int os_id; 
	@Column(length=45)
	private String os;
	
	public OS() {
		
	}
	
	public OS(int os_id, String os){
		this.os_id = os_id;
		this.os = os;
	}

	public int getOs_id() {
		return os_id;
	}

	public void setOs_id(int os_id) {
		this.os_id = os_id;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	

}