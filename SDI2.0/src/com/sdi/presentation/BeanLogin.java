package com.sdi.presentation;

import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sdi.business.LoginService;
import com.sdi.business.Services;
import com.sdi.business.exception.BusinessException;
import com.sdi.dto.User;

@ManagedBean(name = "login")
@SessionScoped
public class BeanLogin implements Serializable {
	private static final long serialVersionUID = 6L;
	private String name = "";
	private String password = "";
	
	@ManagedProperty(value = "#{controller}")
	BeanUser user = new BeanUser();


	private String result = "login_form_result_valid";

	public BeanLogin() {
		System.out.println("BeanLogin - No existia");
	}

	
	public String verify() {
		LoginService login = Services.getLoginService();
		User user;
		try {
			user = login.verify(name, password);
			this.user.setUser(user);
			this.user.validar();
		} catch (BusinessException e) {
			setResult("login_form_result_error");
			e.printStackTrace();
			this.user.mostrarError("login_form_result_error");
			return "fallo";
			
		}
		if (user != null) {
			putUserInSession(user);
			if(user.getIsAdmin())
				return "admin";
			else return "user";
		}
		
		setResult("login_form_result_error");
		return "err";
	}
	

	private void putUserInSession(User user) {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		session.put("LOGGEDIN_USER", user);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public BeanUser getUser() {
		return user;
	}


	public void setUser(BeanUser user) {
		this.user = user;
	}
}