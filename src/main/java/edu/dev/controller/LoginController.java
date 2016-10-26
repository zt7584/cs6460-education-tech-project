package edu.dev.controller;

import edu.dev.entity.User;
import edu.dev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Created by tengzhao on 9/18/16.
 */
@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String loginGet(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(Model model, @ModelAttribute User user) {
        List<User> result = userRepository.findByUsername(user.getEmail());
        if (result != null && result.size() == 1) {
            if (user.getPassword().equals(result.get(0).getPassword())) {
                model.addAttribute("email", user.getEmail());
                return "profile";
            }
        }
        return "error";
    }
}
