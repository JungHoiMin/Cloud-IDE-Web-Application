package com.tukorea.ciwa.persistence;

import com.tukorea.ciwa.domain.UserVO;

public interface UserDAO {

	UserVO read(String id) throws Exception;

	void add(UserVO user) throws Exception;

}
