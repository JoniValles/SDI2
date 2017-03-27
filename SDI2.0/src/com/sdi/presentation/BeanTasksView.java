package com.sdi.presentation;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;





import com.sdi.business.Services;
import com.sdi.business.TaskService;
import com.sdi.business.exception.BusinessException;
import com.sdi.dto.Category;
import com.sdi.dto.Task;

@ManagedBean( name = "dtTableView")
@SessionScoped
public class BeanTasksView implements Serializable {
	
		private static final long serialVersionUID = 1012715922860875459L;

		public List<Task> tasks;
	    public List<Task> filteredTasks ;

	    public Task selectedTask;
	    
	    private boolean showFinished;
	    
		private List<Task> finishedTask;
		private List<Task> weekTask;
		private List<Task> todayTask;
	    private List<Task> inbox;
		private List<Task> all;
	    
		private String[] strCategorys;
		
	     
	    @ManagedProperty("#{controller}")
	    private BeanUser user;
	 
	    @PostConstruct
	    public void init() {
	        tasks=user.getTasks() ;
	        cargarCategoriasStr();
	      
	    }
	    @PostConstruct
	    public List<Category> getCategorys() {
	        return user.getCategorys();
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
	    
		private String[] conversorCategoriasString(List<Category> listaCategorias){
	    	String[] lista =new String[listaCategorias.size()];
	    	for(int i=0; i<listaCategorias.size(); i++)
	    		 lista[i]=listaCategorias.get(i).getName();
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
			cargarFinishedTask();
			return finishedTask;
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
	
	    public void setTasks(List<Task> tsk){
	    	tasks=tsk;
	    }
	    public List<Task> cargarInbox(){
	    	TaskService taskService;
			taskService = Services.getTaskService ();
			List<Task> lista = null;
			try {
				
				lista = taskService.findInboxTasksByUserId(user.getUser().getId());
				if(showFinished) lista.addAll(getFinishedTask());
				inbox = lista;
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return inbox;
	    }
	    
		public List<Task> cargarTodayTask(){
			TaskService taskService;
			taskService = Services.getTaskService ();
			List<Task> lista = null;
			try {
				
				lista = taskService.findTodayTasksByUserId(user.getUser().getId());
				 todayTask=lista;
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return todayTask;
		}
		public List<Task> cargarAllTask(){
			List<Task> lista = null;
			lista = getFinishedTask();
			lista.addAll(getTodayTask());
			lista.addAll(getWeekTask());
			all= lista;
			return lista;
		}
		
		 public List<Task> cargarFinishedTask() {
		    	TaskService taskService;
				taskService = Services.getTaskService ();
				List<Task> lista = null;
				try {
					
					lista = taskService.findFinishedInboxTasksByUserId(user.getUser().getId());
					finishedTask=lista;
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
				return finishedTask;
			}
		 public List<Task> cargarWeekTask() {
		    	TaskService taskService;
				taskService = Services.getTaskService ();
				List<Task> lista = null;
				try {
					
					lista = taskService.findWeekTasksByUserId(user.getUser().getId());
					weekTask=lista;
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
				return weekTask;
			}
		 
		 
		public boolean isShowFinished() {
			return showFinished;
		}
		public void setShowFinished(boolean showFinished) {
			this.showFinished = showFinished;
		}
		public List<Task> getAll() {
			cargarAllTask();
			return all;
		}
		public void setAll(List<Task> all) {
			this.all = all;
		}
		public String[] getStrCategorys() {
			return strCategorys;
		}
		public void setStrCategorys(String[] strCategorys) {
			this.strCategorys = strCategorys;
		}
		
		
		
	}

