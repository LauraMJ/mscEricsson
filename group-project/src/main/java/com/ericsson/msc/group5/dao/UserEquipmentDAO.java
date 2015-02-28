package com.ericsson.msc.group5.dao;

import java.util.List;
import com.ericsson.msc.group5.entities.UserEquipment;

/**
 * A Data Access Object interface for the UserEquipment entity. Defines common DAO methods.
 */
public interface UserEquipmentDAO {

	/**
	 * Retrieve all UserEquipment objects present in the data store.
	 * 
	 * @return a List of UserEquipment objects; empty list if no UserEquipment objects are present in the data store.
	 */
	public List <UserEquipment> getAllUserEquipment();

	/**
	 * Retrieve the UserEquipment associated with the unique id passed in as a parameter.
	 * 
	 * @param typeAllocationCode
	 *            a unique id of the UserEquipment to be retrieved.
	 * @return UserEquipment with the provided typeAllocationCode iff present in the data store; otherwise null.
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
	 * Update a UserEquipment object that exists in the data store. Object comparison is based on the unique typeAllocationCode.
	 * 
	 * @param userEquipment
	 *            A UserEquipment entity that exists in the data store. An exception will be thrown the provided UserEquipment is not in the data store.
	 */
	public void updateUserEquipment(UserEquipment userEquipment);

	/**
	 * Delete an existing UserEquipment object from the data store. Object comparison is based on the unique failureClassId.
	 * 
	 * @param userEquipment
	 *            A UserEquipment entity that exists in the data store. An exception will be thrown the provided UserEquipment is not in the data store.
	 */
	public void deleteUserEquipment(UserEquipment userEquipment);

	/**
	 * Batch insert a list of UserEquipment objects into the data store. Optimized for handling large volumes of data.
	 * 
	 * @param userEquipmentList
	 *            A list of new UserEquipment objects.
	 */
	public void batchInsertUserEquipment(List <UserEquipment> userEquipmentList);
}
