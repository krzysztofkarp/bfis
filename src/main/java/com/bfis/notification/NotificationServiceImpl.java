package com.bfis.notification;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bfis.email.EmailService;
import com.bfis.email.TemplateParam;
import com.bfis.user.model.SystemUser;


@Service
public class NotificationServiceImpl implements NotificationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);

	@Autowired
	private BCryptPasswordEncoder crypter;
	
	
	@Autowired
	private EmailService emailService;

	@Override
	public void notifyAfterCreation(SystemUser u) {
		try {
			emailService.send(u.getEmail(),TemplateParam.Names.GREETING_EMAIL, getPropertiesForUser(u, true));
		} catch (Exception e) {
			LOGGER.error("Notification not sent!");
			LOGGER.error(e.getMessage());
		}

	}
	
	@Override
	public void notifyAfterReset(SystemUser u) {
		try {
			emailService.send(u.getEmail(),TemplateParam.Names.RESET, getPropertiesForUser(u, false));
		} catch (Exception e) {
			LOGGER.error("Notification not sent!");
			LOGGER.error(e.getMessage());
		}
	}
	
	private Map<String, Object> getPropertiesForUser(SystemUser u, boolean decrypt){
		if(decrypt)
			u.setPassword(crypter.encode(u.getPassword()));
		
		Map<String, Object> props = new HashMap<>();
		props.put(TemplateParam.LOGIN, u.getUsername());
		props.put(TemplateParam.LINK, "https://bfis.kacperkurek.com/reset-password?email="+u.getEmail());
		props.put(TemplateParam.ADMIN, "Administrator");
		return props;
	}

}
