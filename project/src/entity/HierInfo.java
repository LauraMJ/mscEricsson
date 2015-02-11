package entity;

import javax.persistence.Column;
import javax.persistence.OneToOne;

public class HierInfo {
	@OneToOne
	private CellInfo cell;

	@Column(name="hier3_id", length=20)
	private String hier3Id;
	
	@Column(name="hier32_id", length=20)
	private String hier32Id;
	
	@Column(name="hier321_id", length=20)
	private String hier321Id;
	
	public HierInfo() {
		
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

	public CellInfo getCell() {
		return cell;
	}

	public void setCell(CellInfo cell) {
		this.cell = cell;
	}
}
