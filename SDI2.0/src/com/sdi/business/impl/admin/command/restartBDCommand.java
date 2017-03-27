package com.sdi.business.impl.admin.command;

import java.util.List;

import alb.util.date.DateUtil;

import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.command.Command;
import com.sdi.dto.Category;
import com.sdi.dto.Task;
import com.sdi.dto.User;
import com.sdi.dto.types.UserStatus;
import com.sdi.persistence.CategoryDao;
import com.sdi.persistence.Persistence;
import com.sdi.persistence.TaskDao;
import com.sdi.persistence.UserDao;

public class restartBDCommand implements Command<Void>{
	
	

	@Override
	public Void execute() throws BusinessException {

		TaskDao tDao = Persistence.getTaskDao();
		CategoryDao cDao = Persistence.getCategoryDao();
		UserDao	uDao = Persistence.getUserDao();


		deleteUsers(uDao,tDao,cDao);
		createUsers(uDao,tDao,cDao);
	

		return null;

	}

	private void createUsers(UserDao uDao, TaskDao tDao, CategoryDao cDao) {
		User user;
		for (int i = 1; i == 3; i++) {
			user = new User();
			
			user.setLogin("user" + i);
			user.setPassword("user" + i);
			user.setEmail("user" + i + "@gmail.com");
			user.setIsAdmin(false);
			user.setStatus(UserStatus.ENABLED);
			uDao.save(user);
			
			createCategories(user, uDao, cDao);
			createTasks(user, uDao, tDao, cDao);
		}
	}


	private void createCategories(User user, UserDao uDao, CategoryDao cDao) {
	
		Category cat = new Category();
		
			cat.setUserId(user.getId());
			cat.setName("categoria_1");
			cDao.save(cat);
			cat.setName("categoria_2");
			cDao.save(cat);
			cat.setName("categoria_3");
			cDao.save(cat);
			
		
	}

	private void deleteUsers(UserDao uDao, TaskDao tDao, CategoryDao cDao) {
		List<User> users = uDao.findAll();
		
		for (User user : users) {
			if (!user.getIsAdmin()) {
				tDao.deleteAllFromUserId(user.getId());
				cDao.deleteAllFromUserId(user.getId());
				uDao.delete(user.getId());
			}
		}
	}

	private void createTasks(User user, UserDao uDao, TaskDao tDao, CategoryDao cDao) {
				todayTasks(user, tDao);
//				weekTasks(u);
//				categoryTasks(u);
			
		
	}


	private void todayTasks(User u, TaskDao tDao) {
		Task task = new Task();
		task.setUserId(u.getId());
		task.setCategoryId(null);
		task.setCreated(DateUtil.today());
		task.setPlanned(DateUtil.today());
		task.setFinished(null);
		for (int i = 1; i <= 10; i++) {
			task.setTitle("Hoy:" + i);
			tDao.save(task);
		}
	}

	

}
