package edu.dev.controller;

import edu.dev.entity.GradingRubric;
import edu.dev.entity.StatisticEntry;
import edu.dev.entity.User;
import edu.dev.service.GradingRubricService;
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
 * Created by tengzhao on 11/19/16.
 */
@Controller
public class AddGradingRubricController {

    @Autowired
    GradingRubricService gradingRubricService;

    @GetMapping("/addgradingrubric")
    public String addGradingRubricGet(Model model, HttpSession session) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("gradingRubric", new GradingRubric());
        model.addAttribute("statisticEntryNames", StatisticEntry.STATISTIC_ENTRY_NAMES);
        return "addgradingrubric";
    }

    @PostMapping("/addgradingrubric")
    public String addGradingRubricPost(@ModelAttribute GradingRubric gradingRubric, HttpSession session) {
        try {
            gradingRubric.setOperator(Constant.OPERATORS[Integer.parseInt(gradingRubric.getOperator())]);
            gradingRubricService.saveGradingRubric(gradingRubric);
            return "redirect:/profile";
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        return "addgradingrubric";
    }
}
