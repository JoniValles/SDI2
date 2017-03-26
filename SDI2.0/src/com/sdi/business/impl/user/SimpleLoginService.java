package com.sdi.business.impl.user;

import com.sdi.business.LoginService;
import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.command.CommandExecutor;
import com.sdi.business.impl.user.command.FindLoggableUSerCommand;
import com.sdi.dto.User;

public class SimpleLoginService implements LoginService {
	@Override
	public User verify(String login, String password) throws BusinessException {
		User user= validLogin(login, password);
		if (user==null)
			return null;
		return user;
	}

	private User validLogin(String login, String password) throws BusinessException {
		return new CommandExecutor<User>().execute( 
				new FindLoggableUSerCommand<User>(login, password) 
		);
		
	}
}