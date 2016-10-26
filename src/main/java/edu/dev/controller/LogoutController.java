package edu.dev.controller;

import edu.dev.entity.User;
import edu.dev.util.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by tengzhao on 10/26/16.
 */
@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String loginGet(HttpSession session) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        if (user == null) {
            return "redirect:/login";
        }
        session.removeAttribute(Constant.SESSION_USER);
        return "logout";
    }
}
