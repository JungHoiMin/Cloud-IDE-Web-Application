package com.tukorea.ciwa.service;

import com.tukorea.ciwa.domain.UserVO;

public interface UserService {

	UserVO readUser(String id) throws Exception;

	void addUser(UserVO user) throws Exception;

	void deleteUser(String id) throws Exception;

}
