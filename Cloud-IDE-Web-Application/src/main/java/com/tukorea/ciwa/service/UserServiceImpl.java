package com.tukorea.ciwa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tukorea.ciwa.domain.UserVO;
import com.tukorea.ciwa.exception.AlreadyExistUserException;
import com.tukorea.ciwa.exception.UserNotFoundException;
import com.tukorea.ciwa.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired(required = true)
	private UserDAO userDAO;
	
	@Override
	public UserVO readUser(String id) throws Exception {
		UserVO user = userDAO.read(id); 
		if (user == null) {
			throw new UserNotFoundException(id);
		}
		return user;
	}

	@Override
	public void addUser(UserVO user) throws Exception {
		if (userDAO.read(user.getId()) != null)
			throw new AlreadyExistUserException(user.getId());
		userDAO.add(user);
		
	}

	@Override
	public void deleteUser(String id) throws Exception {
		userDAO.delete(id);
	}

}
