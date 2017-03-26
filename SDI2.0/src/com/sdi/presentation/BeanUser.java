 package com.sdi.presentation;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import alb.util.log.Log;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

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
	
	private Task task=null;
	private String pass = "";

	
	@ManagedProperty("#{category}")
	private BeanCategory category;
	
	private String categoryName;
	
	//String usado para informar al usuario
	private String msg="";




	
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

//	public List<Task> getFinishedTask() {
//		return finishedTask;
//	}
//
//	public void setFinishedTask(List<Task> finishedTask) {
//		this.finishedTask = finishedTask;
//	}
//
//	public List<Task> getWeekTask() {
//		return weekTask;
//	}
//
//	public void setWeekTask(List<Task> weekTask) {
//		this.weekTask = weekTask;
//	}
//
//	public List<Task> getTodayTask() {
//		return todayTask;
//	}
//
//	public void setTodayTask(List<Task> todayTask) {
//		this.todayTask = todayTask;
//	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public BeanCategory getCategory() {
		return category;
	}

	public void setCategory(BeanCategory category) {
		this.category = category;
	}

	public String validar() {
		UserService userService;

		try {
		
			userService = Services.getUserService();
			user = userService.findLoggableUser(user.getLogin(),
					user.getPassword());
			
//			Map<String, Object> session = FacesContext.getCurrentInstance()
//					.getExternalContext().getSessionMap();
//			session.put("LOGGEDIN_USER", user);
			if (user.getIsAdmin()) { 
				listadoUsuarios();
				return "admin";
			}

//			listadoTareas();

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
			Log.debug("Estado del usuario [%s] cambiado con exito a [%s]",
					user.getLogin(), user.getStatus().toString());
			return "exito"; 
		}catch(BusinessException b){
			Log.error("Se ha producido un error al intentar cambiar el"
					+ "estado del usuario");
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
			Log.debug("Se ha eliminado el usuario [%s] con exito",
					user.getLogin());
			return "exito"; 
		} catch (BusinessException b){
			Log.error("Se ha producido algun error al intentar eliminar el"
					+ "usuario");
			return "error";
		}
	}

	private void listadoUsuarios() throws BusinessException {
		users =  Services.getAdminService().findAllUsers();
	}

	public String listadoTareas() throws BusinessException{
//		
//			tasks = new ArrayList<Task>();
//			finishedTask=Services.getTaskService().findFinishedInboxTasksByUserId(user.getId());
//			weekTask=Services.getTaskService().findWeekTasksByUserId(user.getId());
//			todayTask=Services.getTaskService().findTodayTasksByUserId(user.getId());
//		
//			if(finishedTask!=null)
//				tasks.addAll(finishedTask);
//			if(todayTask!=null)
//				tasks.addAll(todayTask);
//			if(weekTask!=null)
//				tasks.addAll(weekTask);
		return "true";
	}
//	

	
	public String registro(){
		return "true";
	}

	public String registrar() {
		UserService userService;

		
		//HABRIA QUE PONER AVISOS EN EL FRONTEND
		//Comprobar email
		if (!user.getEmail().matches("[-\\w\\.]+@\\w+\\.\\w+")) {
			Log.error("Email invalido: [%s]", user.getEmail());
			return "false";
		}
		
		//Comprobar contraseña
		if (!pass.equals(user.getPassword())) {	
			Log.error("Las contraseñas no coinciden: [%s] -- [%s]", pass,
					user.getPassword());
			return "false";
		}
		//Comprobar longitud de contraseña
		else {
			if (pass.length() < 8) {
				Log.error("Las contraseñas deben tener una "
						+ "longitud de al menos 8 caracteres [%s]", pass);
				return "false";
			}
		}
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
		task=null;
		return "crearTarea";
	}
	
	public String irACrearCategoria(){
		return "crearCategoria";
	}
	/**Boton atrás
	 * 
	 * @return
	 */
	public String atras() {
		System.out.println("Atrás.");
		return "true";
	}
	
	public String crearTarea() {
		TaskService taskService;
		task.setUserId(user.getId());
		try {
			taskService = Services.getTaskService();
		
			taskService.createTask(task);

		} catch (BusinessException b) { 
			return "error"; 
		}
		return "true"; 
	}
	public String crearCategoria() {
		TaskService taskService;
		Category cat = new Category();
		cat.setName(categoryName);
		cat.setUserId(user.getId());
		try {
			taskService = Services.getTaskService();
			taskService.createCategory(cat);

		} catch (BusinessException b) { 
			return "error"; 
		}
		return "true";
	}
	
	public List<Category> getCategorys(){
		TaskService taskService;
		taskService = Services.getTaskService ();
		List<Category> cat=null;
		try {
			
			cat = taskService.findCategoriesByUserId(user.getId());
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return cat;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String listadoCategorias(){
		return "true";
		
	}
	public String irACasa(){
		return "home";
	}
	
	public String cerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("userSession", null);
		Log.debug("Se cierra la sesión correctamente");
		return "EXITO";
	}
	
	public Date getHoy(){
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		return date;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}