package edu.dev.controller;

import edu.dev.entity.GradingRubric;
import edu.dev.entity.StatisticEntry;
import edu.dev.entity.User;
import edu.dev.service.GradingRubricService;
import edu.dev.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by tengzhao on 11/19/16.
 */
@Controller
public class GradingController {

    @Autowired
    GradingRubricService gradingRubricService;

    @GetMapping("/grading")
    public String gradingGet(Model model, HttpSession session) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        if (user == null) {
            return "redirect:/login";
        }
        return "grading";
    }
}
