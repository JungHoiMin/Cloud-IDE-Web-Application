package com.tukorea.ciwa.service;

import java.util.List;

import com.tukorea.ciwa.domain.FileVO;

public interface FileService {

	List<FileVO> readFileListByUserid(String id) throws Exception;

	void addFile(FileVO file, String body) throws Exception;

	FileVO readFile(String title) throws Exception;

	void updateFile(FileVO file, FileVO modified_file, String body) throws Exception;

	void deleteFile(String title) throws Exception;

	void deleteAllFile(String userid) throws Exception;

	String getBody(FileVO file) throws Exception;

	String executeBody(FileVO file) throws Exception;

	void copyFile(FileVO file, String new_title) throws Exception;
}
