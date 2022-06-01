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
		logger.info("/user/signin URL�� GET �Լ� ȣ�� ��.");
		HttpSession session = request.getSession();
		session.setAttribute("user", null);
		return "sign/signin";
	}

	@RequestMapping(value = { "/signin" }, method = RequestMethod.POST)
	public String signInPost(@ModelAttribute("user") UserVO user, Model model, HttpServletRequest request)
			throws Exception {
		logger.info("/user/signin URL�� POST �Լ� ȣ�� ��.");
		HttpSession session = request.getSession();
		UserVO check = userService.readUser(user.getId());
		if (!user.getPasswd().equals(check.getPasswd())) {
			model.addAttribute("msg", "�߸��� ��й�ȣ �Դϴ�.");
			model.addAttribute("user", null);
			return "error/error";
		}
		session.setAttribute("user", check);
		return "redirect:/file/list";
	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.GET)
	public String signUPGet() throws Exception {
		logger.info("/user/signup URL�� GET �Լ� ȣ�� ��.");
		return "sign/signup";
	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	public String signUPPost(@ModelAttribute("user") UserVO user, HttpServletRequest request) throws Exception {
		logger.info("/user/signup URL�� POST �Լ� ȣ�� ��.");
		userService.addUser(user);

		return "sign/signin";
	}

	@RequestMapping(value = { "/userDelete" }, method = RequestMethod.GET)
	public String userDeleteGet(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		userService.deleteUser(user.getId());
		return "redirect:/user/signin";
	}
}
