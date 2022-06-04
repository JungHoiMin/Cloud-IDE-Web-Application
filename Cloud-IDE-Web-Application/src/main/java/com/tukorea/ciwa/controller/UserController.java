package com.tukorea.ciwa.controller;

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

import com.tukorea.ciwa.domain.UserVO;
import com.tukorea.ciwa.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired(required = true)
	private UserService userService;

	@RequestMapping(value = { "/signin" }, method = RequestMethod.GET)
	public String signInGet(HttpServletRequest request) throws Exception {
		logger.info("/user/signin URL에 GET 함수 호출 됨.");
		HttpSession session = request.getSession();
		session.setAttribute("user", null);
		return "sign/signin";
	}

	@RequestMapping(value = { "/signin" }, method = RequestMethod.POST)
	public String signInPost(@ModelAttribute("user") UserVO user, Model model, HttpServletRequest request)
			throws Exception {
		logger.info("/user/signin URL에 POST 함수 호출 됨.");
		HttpSession session = request.getSession();
		UserVO check = userService.readUser(user.getId());
		if (!user.getPasswd().equals(check.getPasswd())) {
			model.addAttribute("msg", "잘못된 비밀번호 입니다.");
			model.addAttribute("user", null);
			return "error/error";
		}
		session.setAttribute("user", check);
		return "redirect:/file/list";
	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.GET)
	public String signUPGet() throws Exception {
		logger.info("/user/signup URL에 GET 함수 호출 됨.");
		return "sign/signup";
	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	public String signUPPost(@ModelAttribute("user") UserVO user, HttpServletRequest request) throws Exception {
		logger.info("/user/signup URL에 POST 함수 호출 됨.");
		userService.addUser(user);

		return "sign/signin";
	}
}
