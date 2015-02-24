package com.ericsson.msc.group5.dataAccessLayer;

import javax.ejb.Local;
import com.ericsson.msc.group5.entities.UserEquipment;

@Local
public interface UserEquipmentDAO {

	public UserEquipment getManagedUserEquipment(int tac);
}
