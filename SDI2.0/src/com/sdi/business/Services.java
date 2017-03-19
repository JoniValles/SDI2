package com.sdi.business;

import com.sdi.business.impl.admin.AdminServiceImpl;
import com.sdi.business.impl.task.TaskServiceImpl;
import com.sdi.business.impl.user.UserServiceImpl;

public class Services {

	public static AdminService getAdminService() {
		return new AdminServiceImpl();
	}

	public static UserService getUserService() {
		return new UserServiceImpl();
	}

	public static TaskService getTaskService() {
		return new TaskServiceImpl();
	}

}
