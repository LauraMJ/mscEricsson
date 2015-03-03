package com.ericsson.msc.group5.dataAccessLayer;

import java.util.Collection;
import com.ericsson.msc.group5.users.User;

public interface UserDAO {

	public Collection <User> getUsers();

}
