package com.tukorea.ciwa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tukorea.ciwa.domain.FileVO;
import com.tukorea.ciwa.domain.UserVO;
import com.tukorea.ciwa.service.FileService;

@Controller
@RequestMapping(value = "/file")
public class FileController {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired(required = true)
	private FileService fileService;

	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String fileListGet(Model model, HttpServletRequest request) throws Exception {
		logger.info("/file/list URL에 GET 함수 호출 됨.");
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		List<FileVO> fileList = fileService.readFileListByUserid(user.getId());
		model.addAttribute("fileList", fileList);
		session.setAttribute("file", null);
		return "file/file_list";
	}
	
	@RequestMapping(value = { "/add" }, method = RequestMethod.GET)
	public String fileAddGet(Model model) throws Exception {
		logger.info("/file/add URL에 GET 함수 호출 됨.");
		return "file/file_info";
	}
}
