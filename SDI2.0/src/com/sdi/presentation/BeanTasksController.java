package com.sdi.presentation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import alb.util.date.DateUtil;
import alb.util.log.Log;

import com.sdi.business.Services;
import com.sdi.business.TaskService;
import com.sdi.business.exception.BusinessException;
import com.sdi.dto.Category;
import com.sdi.dto.Task;

@ManagedBean( name = "dtTasks")
@SessionScoped
public class BeanTasksController implements Serializable {
	
		private static final long serialVersionUID = 1012715922860875459L;

		public List<Task> tasks;
	    public List<Task> filteredTasks ;

	    public Task selectedTask;
	    
	    private boolean showFinished=false;
	    
	    enum tiposTabla { week, today, inbox}
	    private tiposTabla tipoTablaSeleccionado = tiposTabla.inbox;
	    
		private List<Task> finishedTask;
		private List<Task> weekTask;
		private List<Task> todayTask;
	    private List<Task> inbox;
		private List<Task> all;
	    
		private List<String> strCategorys;
		
		private List<Category> categories;
		
	    private String[] selectedCategorys;
	    private Long selectedCategoryId;
	    
	    @ManagedProperty("#{controller}")
	    private BeanUser user;
	 
		@ManagedProperty(value = "#{task}")
		private BeanTask task;
	    
	    @PostConstruct
	    public void init() {
	    	TaskService taskService= Services.getTaskService();
	    	try {
			tasks = taskService.findInboxTasksByUserId(user.getUser().getId());
			categories = Services.getTaskService().findCategoriesByUserId(
								user.getUser().getId());
			actualizarTabla();
			Log.debug("Cargando tareas y categorias para lista de tareas.");
	    	} catch (BusinessException e) {
	    		Log.error("No ha sido posible cargar tareas y categorias");
						e.printStackTrace();
					}
	        actualizarTabla();
	        cargarCategoriasStr();
	      
	    }
	    @PostConstruct
	    public List<Category> getCategorys() {
	        return user.getCategories();
	    }
	     
	    public String listadoTareas(){
	    	
	    	mostrarInbox();
	    	actualizarTabla();
	    	return "true";
	    }
	    
	     public void cargarCategoriasStr(){
	    	 
	    		TaskService taskService;
				taskService = Services.getTaskService ();
				List<Category> aux=null;
			
				try {
					
					aux=taskService.findCategoriesByUserId(user.getUser().getId());
					strCategorys= conversorCategoriasString(aux);
					
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	     }
	    
		private List<String> conversorCategoriasString(List<Category> listaCategorias){
	    	List<String> lista = new ArrayList<String>();
	    	for(Category cat : listaCategorias)
	    		 lista.add(cat.getName());
	    	return lista;
	     }
	    public List<Task> getTasks() {
	        return tasks;
	    }
	 
	    public List<Task> getFilteredTasks() {
	        return filteredTasks;
	    }
	 
	    public Task getSelectedTask() {
	        return selectedTask;
	    }
	 
	    public void setSelectedTask(Task selectedTask) {
	        this.selectedTask = selectedTask;
	    }
	 
	    public void setFilteredTasks(List<Task> filteredTask) {
	        this.filteredTasks=filteredTask;
	    }
	    @PostConstruct
	    public void setUser(BeanUser user) {
	        this.user=user;
	    }
	    
	   
		public void setFinishedTask(List<Task> finishedTask) {
			this.finishedTask = finishedTask;
		}
		public List<Task> getWeekTask() {
			cargarWeekTask();
			return weekTask;
		}
		public void setWeekTask(List<Task> weekTask) {
			this.weekTask = weekTask;
		}
		public List<Task> getTodayTask() {
			cargarTodayTask();
			return todayTask;
		}
		public void setTodayTask(List<Task> todayTask) {
			cargarCategoriasStr();
			this.todayTask=todayTask;
		}
	
		public BeanUser getUser() {
			return user;
		}
		public List<Task> getInbox() {
			cargarInbox();
			return inbox;
		}
		public void setInbox(List<Task> inbox) {
			this.inbox = inbox;
		}
		public List<Task> getFinishedTask() {
			return finishedTask;
		}
		
		public String[] getSelectedCategorys() {
			return selectedCategorys;
		}
		public void setSelectedCategorys(String[] selectedCategorys) {
			this.selectedCategorys = selectedCategorys;
		}
		
		public List<Category> getCategories() {
			categories=user.getCategories();
			return categories;
		}
		public void setCategories(List<Category> categories) {
			this.categories = categories;
		}
		public Long getSelectedCategoryId() {
			return selectedCategoryId;
		}
		public void setSelectedCategoryId(Long selectedCategoryId) {
			this.selectedCategoryId = selectedCategoryId;
		}

		public String getCategoryString(Task task){
	    	TaskService taskService;
			taskService = Services.getTaskService ();
			Category cat=null;
			try {
				
				cat = taskService.findCategoryById(task.getCategoryId());
				
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return cat.getName();
		
	    }
	
	    public tiposTabla getTipoTablaSeleccionado() {
			return tipoTablaSeleccionado;
		}
		public void setTipoTablaSeleccionado(tiposTabla tipoTablaSeleccionado) {
			this.tipoTablaSeleccionado = tipoTablaSeleccionado;
		}
		public void setTasks(List<Task> tsk){
	    	tasks=tsk;
	    }
		/***
		 * Metodo que actualiza la lista de tareas a tareas inbox.
		 * @return lista de tareas
		 */
	    public List<Task> cargarInbox(){
	    	TaskService taskService;
			taskService = Services.getTaskService ();
			List<Task> lista = null;
			try {
				
				lista = taskService.findInboxTasksByUserId(user.getUser().getId());
				if(showFinished) lista.addAll(obtenerTareasFinalizadas());
				
				tasks=lista;
				inbox = lista;
				
				System.err.print("TAMAÑO INBOX: " + inbox.size());
			
			} catch (BusinessException e) {
				Log.error("Error al cargar tareas inbox");
				e.printStackTrace();
			}
			Log.debug("Cargadas las tareas inbox");
			return tasks;
	    }
	    
	    /**
	     * Metodo que actualiza la lista de tareas a tareas de hoy
	     * @return
	     */
		public List<Task> cargarTodayTask(){
			TaskService taskService;
			taskService = Services.getTaskService ();
			List<Task> lista = null;
			try {
				
				lista = taskService.findTodayTasksByUserId(user.getUser().getId());
				
				for(Task t : lista){
					finalizada(t);
				}
				todayTask=lista;
				 tasks= lista;
				 
				 Log.debug("Cargadas las tareas de hoy");
			} catch (BusinessException e) {
				Log.error("Error al cargar las tareas de hoy");
				e.printStackTrace();
			}

			
			return tasks;
		}
		
		
//		public List<Task> cargarAllTask(){
//			List<Task> lista = null;
//			lista = getFinishedTask();
//			lista.addAll(getTodayTask());
//			lista.addAll(getWeekTask());
//			all= lista;
//			return lista;
//		}
		/**
		 * Metodo que retorna las tareas finalizadas
		 * @return
		 */
		 public List<Task> obtenerTareasFinalizadas() {
		    	TaskService taskService;
				taskService = Services.getTaskService ();
				List<Task> lista = null;
				try {
					
					lista = taskService.findFinishedInboxTasksByUserId(user.getUser().getId());
					finishedTask=lista;
					
					 Log.debug("Obteniendo tareas finalizadas");
				} catch (BusinessException e) {
					 Log.error("No ha sido posible obtener tareas finalizadas");
					e.printStackTrace();
				}
		
				return lista;
			}
		 
		 /**
		  * Metodo que actualiza la lista con las tareas de la semana
		  * @return
		  */
		 public List<Task> cargarWeekTask() {
		    	TaskService taskService;
				taskService = Services.getTaskService ();
				List<Task> lista = null;
				try {
					
					lista = taskService.findWeekTasksByUserId(user.getUser().getId());
					weekTask=lista;
					 Log.debug("Cargando tareas de la semana ");
					 
				} catch (BusinessException e) {
					 Log.error("No ha sido posible cargar las tareas de la semana");
					e.printStackTrace();
				}
		
				return weekTask;
			}
		 
		 
		public boolean isShowFinished() {
			return showFinished;
		}
		public void setShowFinished(boolean showFinished) {
			
			this.showFinished = showFinished;
			actualizarTabla();
		}
		public List<Task> getAll() {
//			cargarAllTask();
			return all;
		}
		public BeanTask getTask() {
			return task;
		}
		public void setTask(BeanTask task) {
			this.task = task;
		}
		public void setAll(List<Task> all) {
			this.all = all;
		}
		public List<String> getStrCategorys() {
			
			return strCategorys;
		}
		public void setStrCategorys(List<String> strCategorys) {
			this.strCategorys = strCategorys;
		}
		
		
		public String editarTarea() {
		 	TaskService taskService;
			taskService = Services.getTaskService ();
			try {
				taskService.updateTask(task);
				actualizarTabla();
				task.iniciaTask(null);
			} catch (BusinessException b) {
				
				return "error";
			}
			Log.info("Tarea editada " + task.getId());
			return "true";
		}

		/**
		 * Metodo que se encarga de crear las tareas
		 * @return
		 */
		@SuppressWarnings("null")
		public String crearTarea() {
			TaskService taskService;
			
			task.setUserId(user.getUser().getId());
			task.setCategoryId(selectedCategoryId);
			Task aux=null;
			try {
				taskService = Services.getTaskService();
				
				taskService.createTask(task);
				//volvemos a task por defecto
				aux=task;
				task.iniciaTask(null);
				
				actualizarTabla();
				
			} catch (BusinessException b) { 
				user.mostrarError(b.getMessage());
				 Log.error("Error al crear tarea con id [" + aux.getId() +" ] "
				 		+ "Titulo: "+ aux.getTitle());
				return "error"; 
			}
			 Log.debug("Creando tarea con titulo: " +aux.getTitle());
			return "true"; 
		}
		
		/**
		 * Metodo que actualizara la lista a mostrar
		 * con las tareas correspondientes en funcion
		 * de que informacion se desea mostrar
		 */
		public void actualizarTabla(){
			if(tipoTablaSeleccionado.equals(tiposTabla.inbox))
				cargarInbox();
			if(tipoTablaSeleccionado.equals(tiposTabla.today))
				cargarTodayTask();
			else 
				cargarWeekTask();
			 Log.debug("Tabla actualizada");
			 
		}
		
		/**
		 * Metodo que da por seleccionada una tarea para posteriormente 
		 * manejarla 
		 * @param task
		 * @return
		 */
		public String seleccionarTarea(Task task){
			selectedTask=task;
			this.task.setTask(task);
			return "true";
		}
		
		public void mostrarInbox(){
			if(!inboxSeleccionada()){
				tipoTablaSeleccionado=tiposTabla.inbox;
				showFinished=false;
				actualizarTabla();
			}
			else  Log.info("Inbox ya estan siendo mostrada");
			 Log.info("Mostrando tareas en Inbox");
		}
		public void mostrarToday(){
			if(!tipoTablaSeleccionado.equals(tiposTabla.today)){
				tipoTablaSeleccionado=tiposTabla.today;
				actualizarTabla();
			}
			else  Log.debug("Las tareas de hoy ya estan siendo mostrada");
			 Log.debug("Mostrando tareas de hoy");
		}
		public void mostrarWeek(){
			if(!tipoTablaSeleccionado.equals(tiposTabla.week)){
					tipoTablaSeleccionado=tiposTabla.week;
					actualizarTabla();
				}
			else  Log.debug("Las tareas de la semana ya estan siendo mostrada");
			 Log.debug("Mostrando tareas de la semana");
		}
		
		/**
		 * Metodo que nos valdra para saber si estamos mostrando inbox
		 * @return
		 */
		public boolean inboxSeleccionada(){
			if(tipoTablaSeleccionado.equals(tiposTabla.inbox))
				return true;
			return false;
		}
		public boolean todaySeleccionada(){
			if(tipoTablaSeleccionado.equals(tiposTabla.today))
				return true;
			return false;
		}
		
		public boolean weekSeleccionada(){
			if(tipoTablaSeleccionado.equals(tiposTabla.week))
				return true;
			return false;
		}
		
		
		/**
		 * Metodo que indica si una tarea esta finalizada o no
		 * @return
		 */
		public boolean finalizada(Task task){
			if(task.getFinished()!=null)
				if(DateUtil.isBefore(task.getFinished(), DateUtil.today()))
					return true;
			return false;
		}
		/**
		 * 
		 * @param task
		 * @return
		 */
		public boolean retrasada(Task task) {
			if(task.getPlanned()!=null)
				if (DateUtil.isBefore(task.getPlanned(), DateUtil.today())) {
					task.setRetrasada(true);
					return true;
				}
			return false;
		}
		
		/**
		 * Metodo que a partir de una id pasada como parametro 
		 * obtiene el nombre de la categoria.
		 * @param id
		 * @return
		 */
		public String obtenerNombreCategoria(Long id){
			if(id==null) return " ";
			TaskService taskService;
			taskService = Services.getTaskService();
			try {
				Category cat= taskService.findCategoryById(id);
				
				return cat.getName();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return " ";
			}
			
		}
		public void cerrarTarea(Task tarea){
			TaskService taskService;
			taskService = Services.getTaskService();
			try {
				taskService.markTaskAsFinished(tarea.getId());
				actualizarTabla();
				Log.debug("Finalizando tarea");
			} catch (BusinessException e) {
				 Log.error("Error al finalizar tarea");
				e.printStackTrace();
				
			}
		}
		public void alternarShowFinished(){
			showFinished=!showFinished;
			actualizarTabla();
		}
}


