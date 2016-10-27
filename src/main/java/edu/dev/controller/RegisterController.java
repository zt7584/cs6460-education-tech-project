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

import javax.servlet.http.HttpSession;

/**
 * Created by tengzhao on 10/25/16.
 */
@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String registerGet(Model model, HttpSession session) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        if (user != null) {
            return "redirect:/profile";
        }
        model.addAttribute("registerUser", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute User user, HttpSession session) {
        try {
        	userService.saveStudentUser(user);
            session.setAttribute(Constant.SESSION_USER, user);
            return "redirect:/profile";
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        return "register";
    }
}
