package com.sdi.dto.util;

import com.sdi.dto.Category;
import com.sdi.dto.Task;
import com.sdi.dto.User;


public class Cloner {

	public static User clone(User u) {
		return new User(u.getId(),
				u.getLogin(),
				u.getEmail(),
				u.getPassword(),
				u.getIsAdmin(),
				u.getStatus() );
	}
	
	public static Task clone(Task t) {
		return new Task(t.getId(),
				t.getTitle(),
				t.getComments(),
				t.getCreated(),
				t.getPlanned(),
				t.getFinished(),
				t.getCategoryId(),
				t.getUserId());
	}

	public static Category clone(Category c) {
		return new Category()
				.setName( 	c.getName() )
				.setUserId( c.getUserId() );
	}

}
