package com.sdi.presentation;
import java.io.Serializable;
import java.util.ArrayList;
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

		public List<Task> tasks=null;
	    public List<Task> filteredTasks ;
	     
	    public Task selectedTask;
	     
	    @ManagedProperty("#{controller}")
	    private BeanUser user;
	 
	    @PostConstruct
	    public void init() {
	        tasks=user.getTasks() ;
	      
	    }
	    @PostConstruct
	    public List<Category> getCategorys() {
	        return user.getCategorys();
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
	 
	    public void setFilteredCars(List<Task> filteredTask) {
	        this.filteredTasks=filteredTask;
	    }
	    @PostConstruct
	    public void setUser(BeanUser user) {
	        this.user=user;
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
	}

