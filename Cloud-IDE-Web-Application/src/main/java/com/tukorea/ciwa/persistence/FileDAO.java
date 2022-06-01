package com.tukorea.ciwa.persistence;

import java.util.List;

import com.tukorea.ciwa.domain.FileVO;

public interface FileDAO {

	List<FileVO> readListByUserid(String id) throws Exception;

}
