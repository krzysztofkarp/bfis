package com.bfis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {

	@Value("${spring.datasource.username}")
	private String datasourceUsername;
	
	@Value("${spring.datasource.password}")
	private String datasourcePassword;
	
	@Value("${spring.mail.username}")
	private String email;
	
	@Value("${spring.mail.password}")
	private String emailPassword;
	
		

	public String getDatasourceUsername() {
		return datasourceUsername;
	}
	public String getDatasourcePassword() {
		return datasourcePassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailPassword() {
		return emailPassword;
	}
	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}
	public void setDatasourceUsername(String datasourceUsername) {
		this.datasourceUsername = datasourceUsername;
	}
	public void setDatasourcePassword(String datasourcePassword) {
		this.datasourcePassword = datasourcePassword;
	}
	
}
