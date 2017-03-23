package com.sdi.presentation;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;

import alb.util.console.Console;
import alb.util.date.DateUtil;

import com.sdi.business.AdminService;
import com.sdi.business.Services;
import com.sdi.business.TaskService;
import com.sdi.business.UserService;
import com.sdi.business.exception.BusinessException;
import com.sdi.dto.Category;
import com.sdi.dto.Task;
import com.sdi.dto.User;
import com.sdi.dto.types.UserStatus;


public class BeanUser implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4346788724162425311L;
	
	private User user = new User();
	private List<User> users = null;
	private List<Task> tasks = null;
	private List<Category> categorys= null;
	private Task task=null;
	
	private List<Task> finishedTask=null;
	private List<Task> weekTask=null;
	private List<Task> todayTask=null;
	

	
	public BeanUser() {
		iniciarUser(null);
	}

	public void iniciarUser(ActionEvent event) {
		user.setId(null);
		user.setLogin("login");
		user.setPassword("password");
		user.setIsAdmin(false);
		user.setEmail("email");
		user.setStatus(UserStatus.DISABLED);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User alumno) {
		this.user = alumno;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Task> getFinishedTask() {
		return finishedTask;
	}

	public void setFinishedTask(List<Task> finishedTask) {
		this.finishedTask = finishedTask;
	}

	public List<Task> getWeekTask() {
		return weekTask;
	}

	public void setWeekTask(List<Task> weekTask) {
		this.weekTask = weekTask;
	}

	public List<Task> getTodayTask() {
		return todayTask;
	}

	public void setTodayTask(List<Task> todayTask) {
		this.todayTask = todayTask;
	}

	public String validar() {
		UserService userService;

		try {
			userService = Services.getUserService();
			user = userService.findLoggableUser(user.getLogin(),
					user.getPassword());
			if (user.getIsAdmin()) { 
				listadoUsuarios();
				return "admin";
			}

			listadoTareas();

		} catch (BusinessException b) {
			return "error"; 
		}

		return "user"; 
	}

	public String cambiarEstado(User user){
		AdminService adminService;
		if(user == null)
			return "error";
		try{
			adminService= Services.getAdminService();
			if(user !=null && user.getStatus().equals(UserStatus.ENABLED)){
				adminService.disableUser(user.getId());
			}else{
				adminService.enableUser(user.getId());
			}
			listadoUsuarios(); 
			return "exito"; 
		}catch(BusinessException b){
			return "error";
		}
	}

	public String eliminar(User user){
		AdminService adminService;
		if(user == null)
			return "error";
		try{
			adminService = Services.getAdminService();
			adminService.deepDeleteUser(user.getId());
			listadoUsuarios();
			return "exito"; 
		} catch (BusinessException b){
			return "error";
		}
	}

	private void listadoUsuarios() throws BusinessException {
		users =  Services.getAdminService().findAllUsers();
	}

	public String listadoTareas() throws BusinessException{
		if(user.getId()!=null){
		
			finishedTask=Services.getTaskService().findFinishedInboxTasksByUserId(user.getId());
			weekTask=Services.getTaskService().findWeekTasksByUserId(user.getId());
			todayTask=Services.getTaskService().findTodayTasksByUserId(user.getId());
//			if(finishedTask!=null)
//				tasks.addAll(finishedTask);
//			if(todayTask!=null)
//				tasks.addAll(todayTask);
//			if(weekTask!=null)
//				tasks.addAll(weekTask);
		}
			return "true";
//		
//		else return "error";
		
	}

	
	public String registro(){
		return "true";
	}

	public String registrar() {
		UserService userService;

		try {
			userService = Services.getUserService();
			user.setIsAdmin(false);
			user.setStatus(UserStatus.ENABLED);
			userService.registerUser(user);

		} catch (BusinessException b) {
			return "error"; 
		}
		return "true"; 
	}

	public String actualizarEmail(){
		UserService userService;
		try {
			userService = Services.getUserService();
			if(user.getEmail()!=null)
				userService.updateUserDetails(user);
			
		} catch (BusinessException e) {
			e.printStackTrace();
			return "error";
			
		}
		return "true";
	}
	
	public String irACrearTarea(){
		return "crearTarea";
	}
	/**Boton atrás
	 * 
	 * @return
	 */
	public String atras() {
		System.out.println("Atrás.");
		return "true";
	}
	
	public String crear() {
		TaskService taskService;

//		try {
//			taskService = Services.getTaskService();
//		
//		//	taskService.createTask( new Task("", title, " ",  DateUtil.today(), null, null, categoryId, userId));
//
//		} catch (BusinessException b) {
//			return "error"; 
//		}
		return "true"; 
	}
	
}