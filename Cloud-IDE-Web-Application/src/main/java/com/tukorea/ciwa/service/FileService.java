package com.tukorea.ciwa.service;

import java.util.List;

import com.tukorea.ciwa.domain.FileVO;

public interface FileService {

	List<FileVO> readFileListByUserid(String id) throws Exception;

}
