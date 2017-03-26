package com.sdi.business;

import com.sdi.business.exception.BusinessException;
import com.sdi.dto.User;

public interface LoginService {

	public User verify(String login, String password) throws BusinessException;
}
