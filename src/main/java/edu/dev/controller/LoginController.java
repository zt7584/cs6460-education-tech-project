package edu.dev.controller;

import edu.dev.entity.User;
import edu.dev.repository.UserRepository;
import edu.dev.service.UserService;
import edu.dev.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by tengzhao on 9/18/16.
 */
@Controller
public class LoginController {

	@Autowired
	UserService userService;

	@GetMapping("/")
	public String rootGet(HttpSession session) {
		User user = (User) session.getAttribute(Constant.SESSION_USER);
		if (user == null) {
			return "redirect:/login";
		}
		return "redirect:/profile";
	}

	@GetMapping("/login")
	public String loginGet(Model model, HttpSession session) {
		User user = (User) session.getAttribute(Constant.SESSION_USER);
		if (user != null) {
			return "redirect:/profile";
		}
		model.addAttribute("studentUser", new User());
		model.addAttribute("instructorUser", new User());
		return "login";
	}

	@PostMapping("/login")
	public String loginPost(@ModelAttribute User user, HttpSession session) {
		if (userService.authenticate(user)) {
			session.setAttribute(Constant.SESSION_USER, user);
			return "redirect:/profile";
		}
		return "error";
	}
}
