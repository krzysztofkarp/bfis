package com.bfis.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bfis.common.BackendMappings;
import com.bfis.common.response.ItemResponse;
import com.bfis.common.response.Response;
import com.bfis.common.response.ResponseUtil;
import com.bfis.user.model.SystemUser;
import com.bfis.user.service.UserService;

@RestController
public class UserController {
	
	
	@Autowired
	private UserService service;
	
	@Autowired
	private BCryptPasswordEncoder crypter;
	
	
	
	 @RequestMapping(value = BackendMappings.User.SIGN_UP, method = RequestMethod.POST)
	 public ItemResponse<SystemUser> signUp(@RequestBody SystemUser user) {
	        user.setPassword(crypter.encode(user.getPassword()));
	        return ResponseUtil.runInItemTemplate(() -> service.save(user));
	 }
	 
	 @RequestMapping(value = BackendMappings.User.CHANGE_PASSWORD, method = RequestMethod.GET)
	 public Response changePassword(@RequestParam String email, @RequestParam String newPassword) {;
	        return ResponseUtil.runInVoidTemplate(() -> service.updatePassword(email, crypter.encode(newPassword)));
	 }
	 
	 @RequestMapping(value = BackendMappings.User.RESET_PASSWORD, method = RequestMethod.GET)
	 public Response changePassword(@RequestParam String email) {
	        return ResponseUtil.runInVoidTemplate(() -> service.resetPassword(email));
	 }
	 
	

}
