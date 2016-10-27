package edu.dev.controller;

import edu.dev.entity.Proposal;
import edu.dev.entity.User;
import edu.dev.entity.UserProposalRelationship;
import edu.dev.repository.ProposalRepository;
import edu.dev.repository.UserProposalRelationshipRepository;
import edu.dev.repository.UserRepository;
import edu.dev.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by tengzhao on 10/26/16.
 */
@Controller
public class ProposalController {

    @Autowired
    ProposalRepository proposalRepository;

    @Autowired
    UserProposalRelationshipRepository userProposalRelationshipRepository;

    @GetMapping("/proposal")
    public String proposalGet(Model model, HttpSession session) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("proposal", new Proposal());
        return "proposal";
    }

    @PostMapping("/proposal")
    public String proposalPost(@ModelAttribute Proposal proposal, HttpSession session) {
        try {
            User user = (User) session.getAttribute(Constant.SESSION_USER);
            if (user == null) {
                return "redirect:/login";
            }
            proposal.setStatus(Proposal.Status.PENDING);
            proposal.setCreatedAt(new Date());
            proposal.setLastUpdatedAt(new Date());
//            proposal.addUser(user, true);
            Proposal saved = proposalRepository.save(proposal);
            UserProposalRelationship userProposalRelationship = new UserProposalRelationship();
            userProposalRelationship.setRelationship(UserProposalRelationship.Relationship.CREATOR);
            userProposalRelationship.setPid(saved.getId());
            userProposalRelationship.setUid(user.getId());
            userProposalRelationshipRepository.save(userProposalRelationship);
            return "redirect:/profile";
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        return "register";
    }
}
