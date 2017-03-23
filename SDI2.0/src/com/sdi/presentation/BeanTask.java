package com.sdi.presentation;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.sdi.business.AdminService;
import com.sdi.business.Services;
import com.sdi.business.TaskService;
import com.sdi.business.exception.BusinessException;
import com.sdi.dto.Task;






import alb.util.date.DateUtil;


@ManagedBean(name = "task")
@SessionScoped
public class BeanTask implements Serializable {
	
	
	
	public BeanTask(){
		
	}
	private static final long serialVersionUID = 6L;
	private String name="";
	private Long id;

	private String title;
	private String comments;

	private Date creacion;
	private Date planned;
	private Date finished;
	
	private Long categoryId;
	private Long userId;
//	
//	@ManagedProperty(value = "#{categoria}")
//	private BeanCategory categoria;
	

	@PostConstruct
	public void init() {
//		try {
		System.out.println("BeanSettings - PostConstruct");
//		TaskService taskService =  Services.getTaskService();
//		
//			categoria = (BeanCategory) taskService.findCategoryById(categoryId);
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public Date getPlanned() {
		return planned;
	}


	public void setPlanned(Date planned) {
		this.planned = planned;
	}


	public Date getFinished() {
		return finished;
	}


	public void setFinished(Date finished) {
		this.finished = finished;
	}


	public Long getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
//	public BeanCategory getCategoria() {
//		return categoria;
//	}
//	public void setCategoria(BeanCategory categoria) {
//		this.categoria = categoria;
//	}


	public Date getCreacion() {
		return creacion;
	}

	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}

	public String crear() {
		TaskService taskService;

		try {
			taskService = Services.getTaskService();
			taskService.createTask( new Task(id, title, " ",  DateUtil.today(), null, null, categoryId, userId));

		} catch (BusinessException b) {
			return "error"; 
		}
		return "true"; 
	}

}