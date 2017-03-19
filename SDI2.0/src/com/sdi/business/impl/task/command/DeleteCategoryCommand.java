package com.sdi.business.impl.task.command;

import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.command.Command;
import com.sdi.persistence.Persistence;

public class DeleteCategoryCommand implements Command<Void> {

	private Long catId;

	public DeleteCategoryCommand(Long catId) {
		this.catId = catId;
	}

	@Override
	public Void execute() throws BusinessException {
		Persistence.getTaskDao().deleteAllFromCategory( catId );
		Persistence.getCategoryDao().delete( catId );
		return null;
	}

}
