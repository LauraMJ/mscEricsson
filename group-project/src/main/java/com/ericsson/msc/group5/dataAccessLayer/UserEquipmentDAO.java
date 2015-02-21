package com.ericsson.msc.group5.dataAccessLayer;

import com.ericsson.msc.group5.entities.UserEquipment;

public interface UserEquipmentDAO {

	public UserEquipment getManagedUserEquipment(int tac);
}
