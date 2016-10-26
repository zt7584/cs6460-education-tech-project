package edu.dev.controller;

import edu.dev.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by tengzhao on 10/25/16.
 */
@Controller
public class StudentProfileController {

    @GetMapping("/profile")
    public String loginGet(Model model) {
        System.out.println(model.asMap().get("email"));
        return "profile";
    }
}
