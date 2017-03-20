package com.sdi.presentation;

import java.util.List;
import javax.faces.event.ActionEvent;
import com.sdi.business.AdminService;
import com.sdi.business.Services;
import com.sdi.business.UserService;
import com.sdi.business.exception.BusinessException;
import com.sdi.dto.Task;
import com.sdi.dto.User;
import com.sdi.dto.types.UserStatus;


public class BeanUser {


	private User user = new User();
	private List<User> users = null;
	private List<Task> tasks = null;

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

	private void listadoTareas() throws BusinessException{
		tasks = Services.getTaskService().findInboxTasksByUserId(user.getId());
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


	/**Boton atrás
	 * 
	 * @return
	 */
	public String atras() {
		System.out.println("Atrás.");
		return "true";
	}
}