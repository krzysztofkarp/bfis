package com.bfis.user.service;

import java.util.List;

import com.bfis.user.model.SystemUser;

public interface UserService {
	
	
	SystemUser save(SystemUser u);
	List<SystemUser> getAll();
	void remove(SystemUser u);
	void updatePassword(String email, String newPassword) throws Exception;
	void resetPassword(String email) throws Exception;

}
