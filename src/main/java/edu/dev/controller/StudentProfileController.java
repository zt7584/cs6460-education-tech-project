package edu.dev.controller;

import edu.dev.entity.Proposal;
import edu.dev.entity.User;
import edu.dev.entity.UserProposalRelationship;
import edu.dev.repository.ProposalRepository;
import edu.dev.repository.UserProposalRelationshipRepository;
import edu.dev.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tengzhao on 10/25/16.
 */
@Controller
public class StudentProfileController {

    @Autowired
    ProposalRepository proposalRepository;

    @Autowired
    UserProposalRelationshipRepository userProposalRelationshipRepository;

    @GetMapping("/profile")
    public String profileGet(HttpSession session, Model model) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        if (user == null) {
            return "redirect:/login";
        }
        List<UserProposalRelationship> userProposalRelationshipList = userProposalRelationshipRepository.findByUserId(user.getId());
        List<Proposal> proposals = new ArrayList<>();
        if (userProposalRelationshipList != null) {
            for (UserProposalRelationship userProposalRelationship : userProposalRelationshipList) {
                List<Proposal> tempProposalList = proposalRepository.findById(userProposalRelationship.getPid());
                if (tempProposalList != null) {
                    proposals.addAll(tempProposalList);
                }
            }
        }
        model.addAttribute("sessionUserName", user.getName());
        model.addAttribute("proposals", proposals);
        return "profile";
    }
}
