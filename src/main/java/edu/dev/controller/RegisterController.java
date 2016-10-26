package edu.dev.controller;

import edu.dev.entity.User;
import edu.dev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by tengzhao on 10/25/16.
 */
@Controller
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String loginGet(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String loginPost(@ModelAttribute User user) {
        user.setRole(User.Role.STUDENT);
        userRepository.save(user);
        return "login";
    }
}
