package com.sdi.persistence;

import com.sdi.persistence.impl.CategoryDaoJdbcImpl;
import com.sdi.persistence.impl.TaskDaoJdbcImpl;
import com.sdi.persistence.impl.TransactionJdbcImpl;
import com.sdi.persistence.impl.UserDaoJdbcImpl;

public class Persistence {
	
	public static Transaction newTransaction() {
		return new TransactionJdbcImpl();
	}
	
	public static UserDao getUserDao() {
		return new UserDaoJdbcImpl();
	}

	public static TaskDao getTaskDao() {
		return new TaskDaoJdbcImpl();
	}

	public static CategoryDao getCategoryDao() {
		return new CategoryDaoJdbcImpl();
	}

}
