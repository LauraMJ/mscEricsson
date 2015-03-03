package com.ericsson.msc.group5.users;

import java.util.Collection;
import javax.ejb.Local;

@Local
public interface UserService {

	public Collection <User> getUserss();

	public void addUsers(Collection <User> users);
}
