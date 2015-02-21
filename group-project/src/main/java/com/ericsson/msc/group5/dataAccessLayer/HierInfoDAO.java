package com.ericsson.msc.group5.dataAccessLayer;

import com.ericsson.msc.group5.entities.HierInfo;

public interface HierInfoDAO {

	public HierInfo getManagedHierInfo(long hier3Id, long hier32Id,
			long hier321Id);
}
