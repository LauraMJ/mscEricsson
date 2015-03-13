package com.ericsson.msc.group5.services.ejb;

import java.util.Collection;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ericsson.msc.group5.dao.UserEquipmentDAO;
import com.ericsson.msc.group5.entities.UserEquipment;
import com.ericsson.msc.group5.services.UserEquipmentService;

@Stateless
@Local
public class UserEquipmentServiceEJB implements UserEquipmentService{

	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private UserEquipmentDAO dao;
	
	@Override
	public Collection<UserEquipment> getUserEquipment() {
		return dao.getAllUserEquipment();
	}
	
	@Override
	public void addUserEquipment(Collection<UserEquipment> userEquipments) {
		dao.batchInsertUserEquipment(userEquipments);
	}
	
	@Override
	public Collection<UserEquipment> getTypeAllocationCode() {
		return null;
	}

	@Override
	public void addTypeAllocationCode(Collection<UserEquipment> allocationCodes) {
		dao.batchInsertUserEquipment(allocationCodes);
		
	}

	@Override
	public Collection<UserEquipment> getMarketingName() {
		return null;
	}

	@Override
	public void addMarketingName(Collection<UserEquipment> marketingNames) {
		dao.batchInsertUserEquipment(marketingNames);
		
	}

	@Override
	public Collection<UserEquipment> getManufacturer() {
		return null;
	}

	@Override
	public void addManufacturer(Collection<UserEquipment> manufacturers) {
		dao.batchInsertUserEquipment(manufacturers);
		
	}

	@Override
	public Collection<UserEquipment> getAccessCapability() {
		return null;
	}

	@Override
	public void addAccessCapability(Collection<UserEquipment> accessCapabilities) {
		dao.batchInsertUserEquipment(accessCapabilities);
		
	}

	@Override
	public Collection<UserEquipment> getModel() {
		return null;
	}

	@Override
	public void addModel(Collection<UserEquipment> models) {
		dao.batchInsertUserEquipment(models);
		
	}

	@Override
	public Collection<UserEquipment> getVendor() {
		return null;
	}

	@Override
	public void addVendor(Collection<UserEquipment> vendors) {
		dao.batchInsertUserEquipment(vendors);		
	}

	@Override
	public Collection<UserEquipment> getUserEquipmentType() {
		return null;
	}

	@Override
	public void addUserEquipmentType(
			Collection<UserEquipment> userEquipmentTypes) {
		dao.batchInsertUserEquipment(userEquipmentTypes);
		
	}

	@Override
	public Collection<UserEquipment> getOperatingSystem() {
		return null;
	}

	@Override
	public void addOperatingSystem(Collection<UserEquipment> operatingSystems) {
		dao.batchInsertUserEquipment(operatingSystems);
		
	}

	@Override
	public Collection<UserEquipment> getInputMode() {
		return null;
	}

	@Override
	public void addInputMode(Collection<UserEquipment> inputModes) {
		dao.batchInsertUserEquipment(inputModes);
		
	}

	@Override
	public Collection<UserEquipment> getFailureTrace() {
		return null;
	}

	@Override
	public void addFailureTrace(Collection<UserEquipment> failureTraces) {
		dao.batchInsertUserEquipment(failureTraces);
	}

	

	

}
