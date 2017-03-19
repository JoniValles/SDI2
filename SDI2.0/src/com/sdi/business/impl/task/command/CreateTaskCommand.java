package com.sdi.business.impl.task.command;

import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.command.Command;
import com.sdi.business.impl.util.TaskCheck;
import com.sdi.dto.Task;
import com.sdi.persistence.Persistence;

import alb.util.date.DateUtil;

public class CreateTaskCommand implements Command<Long> {

	private Task task;

	public CreateTaskCommand(Task task) {
		this.task = task;
	}

	@Override
	public Long execute() throws BusinessException {
		TaskCheck.userExists( task );
		TaskCheck.userIsNotDisabled( task );
		TaskCheck.userIsNotAdmin( task );
		TaskCheck.titleIsNotNull( task );
		TaskCheck.titleIsNotEmpty( task );
		if ( task.getCategoryId() != null ) {
			TaskCheck.categoryExists( task );
		}
		
		task.setCreated( DateUtil.today() );
		task.setFinished( null );
		return Persistence.getTaskDao().save( task );
	}

}
