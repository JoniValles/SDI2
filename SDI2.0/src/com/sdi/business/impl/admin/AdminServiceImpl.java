package com.sdi.business.impl.admin;

import java.util.List;

import com.sdi.business.AdminService;
import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.admin.command.DeepDeleteUserCommand;
import com.sdi.business.impl.admin.command.DisableUserCommand;
import com.sdi.business.impl.admin.command.EnableUserCommand;
import com.sdi.business.impl.command.Command;
import com.sdi.business.impl.command.CommandExecutor;
import com.sdi.dto.User;
import com.sdi.persistence.Persistence;

public class AdminServiceImpl implements AdminService {
	
	@Override
	public void deepDeleteUser(Long id) throws BusinessException {
		new CommandExecutor<Void>().execute( new DeepDeleteUserCommand( id ) );
	}

	@Override
	public void disableUser(Long id) throws BusinessException {
		new CommandExecutor<Void>().execute( new DisableUserCommand( id ) );
	}

	@Override
	public void enableUser(Long id) throws BusinessException {
		new CommandExecutor<Void>().execute( new EnableUserCommand( id ) );
	}

	@Override
	public List<User> findAllUsers() throws BusinessException {
		return new CommandExecutor<List<User>>().execute( 
				new Command<List<User>>() {
			@Override public List<User> execute() throws BusinessException {
				return Persistence.getUserDao().findAll();
			}
		});
	}

	@Override
	public User findUserById(final Long id) throws BusinessException {
		return new CommandExecutor<User>().execute( new Command<User>() {
			@Override public User execute() throws BusinessException {
				return Persistence.getUserDao().findById(id);
			}
		});
	}

}
