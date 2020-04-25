package com.bfis.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bfis.common.Utils;
import com.bfis.user.model.SystemUser;
import com.bfis.user.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository repo;
	
	 
	@Autowired
	public UserServiceImpl(UserRepository repo) {
		this.repo = repo;
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
	
}
