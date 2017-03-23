package com.sdi.presentation;

import java.io.Serializable;



import javax.faces.bean.*;

import com.sdi.business.Services;
import com.sdi.business.TaskService;
import com.sdi.business.exception.BusinessException;
import com.sdi.dto.Category;



@ManagedBean(name = "categoria")
@SessionScoped
public class BeanCategory extends Category implements Serializable {
	private static final long serialVersionUID = 6L;
	
	
	@ManagedProperty(value = "#{controller}")
	private BeanUser user;

	private Long id;
	private String nombre;
	

	public BeanCategory() {
		System.out.println("BeanCategory - No existia. Creada");
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String crearCategoria() {
		TaskService taskService;

		try {
			taskService = Services.getTaskService();
			Category newCategory = new Category();
			newCategory.setName(nombre);
			newCategory.setId(user.getUser().getId());
			taskService.createCategory(new Category());

		} catch (BusinessException b) {
			return "error"; 
		}
		return "true"; 
	}
	
	public Long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}