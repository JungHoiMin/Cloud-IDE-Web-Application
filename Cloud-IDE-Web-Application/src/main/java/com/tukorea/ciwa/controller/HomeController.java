package com.tukorea.ciwa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tukorea.ciwa.domain.UserVO;
import com.tukorea.ciwa.service.FileService;
import com.tukorea.ciwa.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired(required = true)
	private UserService userService;

	@Autowired(required = true)
	private FileService fileService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "redirect:/user/signin";
	}

	@RequestMapping(value = { "/userDelete" }, method = RequestMethod.GET)
	public String userDeleteGet(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		fileService.deleteAllFile(user.getId());
		userService.deleteUser(user.getId());
		return "redirect:/user/signin";
	}
}
