package com.ericsson.msc.group5.services;

import java.util.Collection;
import javax.ejb.Local;
import com.ericsson.msc.group5.entities.UserEquipment;

/**
 * UserEquipment service EJB interface.
 */
@Local
public interface UserEquipmentService {

	public Collection <UserEquipment> getAllUserEquipments();

	public void addUserEquipments(Collection <UserEquipment> userEquipments);

	public void addUserEquipment(UserEquipment userEquipment);

}
