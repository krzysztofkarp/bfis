package com.bfis.user.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bfis.common.Utils;
import com.bfis.notification.NotificationService;
import com.bfis.user.model.SystemUser;
import com.bfis.user.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository repo;
	
	private NotificationService service;
	
	 
	@Autowired
	public UserServiceImpl(UserRepository repo, NotificationService service) {
		this.repo = repo;
		this.service = service;
	}


	@Override
	public SystemUser save(SystemUser u) {
		return repo.save(u);
	}


	@Override
	public List<SystemUser> getAll() {
		return Utils.asList(repo.findAll());
	}


	@Override
	public void remove(SystemUser u) {
		repo.delete(u);
	}


	@Override
	public void updatePassword(String email, String newPassword) throws Exception {
		SystemUser su = repo.findByEmail(email);
		
		if(Utils.notNullAndNotEmpty(su) && Utils.notNullAndNotEmpty(su.getResetHash())) {
			su.setPassword(newPassword);
			su.setResetHash(null);
			repo.save(su);
		} else {
			throw new Exception("Reset hash does not exists!");
		}
		
	}


	@Override
	public void resetPassword(String email) throws Exception{
		SystemUser su = repo.findByEmail(email);
		
		if(Utils.nullOrEmpty(su.getResetHash())) {
			su.setResetHash(UUID.randomUUID().toString());
			repo.save(su);
			service.notifyAfterReset(su);
		} else {
			throw new Exception("Reset email already sent!");
		}
	}
	
}
