package com.ericsson.msc.group5.dataAccessLayer;

import javax.ejb.Local;
import com.ericsson.msc.group5.entities.UserEquipmentType;

@Local
public interface UserEquipmentTypeDAO {

	public UserEquipmentType getManagedUserEquipmentType(String ueType);
}
