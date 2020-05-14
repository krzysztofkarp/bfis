package com.bfis.notification;

import com.bfis.user.model.SystemUser;

public interface NotificationService {
	
	
	void notifyAfterCreation(SystemUser u);
	void notifyAfterReset(SystemUser u);

}
