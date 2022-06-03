package com.tukorea.ciwa.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

	@RequestMapping(value = { "/read" }, method = RequestMethod.GET)
	public String fileReadGet(@RequestParam("title") String title, Model model, HttpServletRequest request)
			throws Exception {
		logger.info("/file/read URL에 GET 함수 호출 됨.");
		HttpSession session = request.getSession();
		FileVO file = fileService.readFile(title);
		session.setAttribute("file", file);

		String body = fileService.getBody(file);
		model.addAttribute("body", body);

		return "file/file_info";
	}

	@RequestMapping(value = { "/save" }, method = RequestMethod.POST)
	public String saveFilePost(@ModelAttribute("modified_file") FileVO modified_file,
			@ModelAttribute("body") String body, Model model, HttpServletRequest request) throws Exception {
		logger.info("/file/save URL에 POST 함수 호출 됨.");

		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		modified_file.setUserid(user.getId());
		FileVO file = (FileVO) session.getAttribute("file");

		if (file != null) {
			fileService.updateFile(file, modified_file, body);
		} else {
			fileService.addFile(modified_file, body);
		}

		return "redirect:/file/list";
	}

	@RequestMapping(value = { "/delete" }, method = RequestMethod.GET)
	public String fileDeleteGet(@RequestParam("title") String title) throws Exception {
		logger.info("/file/delete URL에 GET 함수 호출 됨.");
		fileService.deleteFile(title);
		return "redirect:/file/list";
	}

	@RequestMapping(value = { "/execute" }, method = RequestMethod.POST)
	public String excuteFilePost(@ModelAttribute("saved_file") FileVO saved_file, @ModelAttribute("body") String body,
			Model model, HttpServletRequest request) throws Exception {
		logger.info("/file/execute URL에 POST 함수 호출 됨.");

		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		saved_file.setUserid(user.getId());
		FileVO file = (FileVO) session.getAttribute("file");

		if (file != null) {
			fileService.updateFile(file, saved_file, body);
		} else {
			fileService.addFile(saved_file, body);
		}

		String result = fileService.executeBody(saved_file);
		session.setAttribute("file", saved_file);
		model.addAttribute("result", result);

		return "file/file_info";
	}
}
