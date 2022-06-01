package com.tukorea.ciwa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tukorea.ciwa.domain.FileVO;
import com.tukorea.ciwa.persistence.FileDAO;

@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private FileDAO fileDAO;

	@Override
	public List<FileVO> readFileListByUserid(String id) throws Exception {
		return fileDAO.readListByUserid(id);
	}

}
