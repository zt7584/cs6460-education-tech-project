package edu.dev.controller;

import edu.dev.entity.User;
import edu.dev.util.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by tengzhao on 10/25/16.
 */
@Controller
public class StudentProfileController {

    @GetMapping("/profile")
    public String profileGet(HttpSession session, Model model) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("sessionUserName", user.getName());
        return "profile";
    }
}
