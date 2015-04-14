package com.ericsson.msc.group5.dao;

import java.util.Collection;
import com.ericsson.msc.group5.entities.UserEquipment;

/**
 * A Data Access Object interface for the UserEquipment entity. Defines common
 * DAO methods.
 */
public interface UserEquipmentDAO {

	/**
	 * Retrieve all UserEquipment objects present in the data store.
	 * 
	 * @return a Collection of UserEquipment objects; empty collection if no
	 *         UserEquipment objects are present in the data store.
	 */
	public Collection <UserEquipment> getAllUserEquipment();

	/**
	 * Retrieve the UserEquipment associated with the unique id passed in as a
	 * parameter.
	 * 
	 * @param typeAllocationCode
	 *            a unique id of the UserEquipment to be retrieved.
	 * @return UserEquipment with the provided typeAllocationCode iff present in
	 *         the data store; otherwise null.
	 */
	public UserEquipment getUserEquipment(int typeAllocationCode);

	/**
	 * Insert a new UserEquipment object into the data store.
	 * 
	 * @param userEquipment
	 *            A new UserEquipment object.
	 */
	public void insertUserEquipment(UserEquipment userEquipment);

	/**
	 * Batch insert a Collection of UserEquipment objects into the data store.
	 * Optimized for handling large volumes of data.
	 * 
	 * @param userEquipmentList
	 *            A Collection of new UserEquipment objects.
	 */
	public void batchInsertUserEquipment(Collection <UserEquipment> userEquipmentList);
}
