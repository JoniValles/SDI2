package com.sdi.dto;

import com.sdi.dto.types.UserStatus;

/**
 * An implementation of the DTO pattern
 * 
 * @author alb
 */
public class User {
	private Long id;

	private String login;
	private String email;
	private String password;
	private Boolean isAdmin = false;
	private UserStatus status = UserStatus.ENABLED;
	
	public User(){
		
	}
	
	
	
	public User(Long id, String login, String email, String password,
			Boolean isAdmin, UserStatus status) {
		super();
		this.id = id;
		this.login = login;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
		this.status = status;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id 
				+ ", login=" + login 
				+ ", email=" + email 
				+ ", password=" + password 
				+ ", isAdmin=" + isAdmin + "]";
	}

	public UserStatus getStatus() {
		return status;
	}

	public User setStatus(UserStatus status) {
		this.status = status;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isAdmin == null) ? 0 : isAdmin.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isAdmin == null) {
			if (other.isAdmin != null)
				return false;
		} else if (!isAdmin.equals(other.isAdmin))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (status != other.status)
			return false;
		return true;
	}	
	
}
