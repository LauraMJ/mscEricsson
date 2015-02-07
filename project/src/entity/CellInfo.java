package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CellInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cellInfoId;

	private int cellId;
	//TODO I'm pretty sure that neVersion is a string thing, check later
	private int neVersion;
	private String hier3Id, hier32Id, hier321Id;

	public CellInfo() {
	}

	public CellInfo(int cellId, int neVersion, String hier3Id, String hier32Id, String hier321Id) {
		this.cellId = cellId;
		this.neVersion = neVersion;
		this.hier3Id = hier3Id;
		this.hier32Id = hier32Id;
		this.hier321Id = hier321Id;
	}

	public int getCellInfoId() {
		return cellInfoId;
	}

	public void setCellInfoId(int cellInfoId) {
		this.cellInfoId = cellInfoId;
	}

	public int getCellId() {
		return cellId;
	}

	public void setCellId(int cellId) {
		this.cellId = cellId;
	}

	public int getNeVersion() {
		return neVersion;
	}

	public void setNeVersion(int neVersion) {
		this.neVersion = neVersion;
	}

	public String getHier3Id() {
		return hier3Id;
	}

	public void setHier3Id(String hier3Id) {
		this.hier3Id = hier3Id;
	}

	public String getHier32Id() {
		return hier32Id;
	}

	public void setHier32Id(String hier32Id) {
		this.hier32Id = hier32Id;
	}

	public String getHier321Id() {
		return hier321Id;
	}

	public void setHier321Id(String hier321Id) {
		this.hier321Id = hier321Id;
	}
}
