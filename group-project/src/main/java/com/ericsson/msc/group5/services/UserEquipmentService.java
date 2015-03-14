package com.ericsson.msc.group5.services;

import java.util.Collection;
import javax.ejb.Local;
import com.ericsson.msc.group5.entities.UserEquipment;
/**
 * UserEquipment service EJB interface.
 */
@Local
public interface UserEquipmentService {

	public Collection <UserEquipment> getUserEquipment();
	
	public void addUserEquipment(Collection <UserEquipment> userEquipments);
		
	
}
