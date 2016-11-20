package edu.dev.controller;

import edu.dev.entity.*;
import edu.dev.repository.GradingRubricRepository;
import edu.dev.repository.ProposalRepository;
import edu.dev.repository.UserProposalRelationshipRepository;
import edu.dev.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tengzhao on 10/25/16.
 */
@Controller
public class ProfileController {

    @Autowired
    ProposalRepository proposalRepository;

    @Autowired
    UserProposalRelationshipRepository userProposalRelationshipRepository;

    @Autowired
    GradingRubricRepository gradingRubricRepository;

    @GetMapping("/profile")
    public String profileGet(HttpSession session, Model model) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        if (user == null) {
            return "redirect:/login";
        }
        setupPage(session, model);
        return "profile";
    }

    @RequestMapping(value="/profile/{id}", method= RequestMethod.POST)
    @Transactional
    public String approveProposal(HttpSession session, Model model, @PathVariable("id") int id) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        if (user == null) {
            return "redirect:/login";
        }
        proposalRepository.updateProposalStatus(Proposal.Status.APPROVED, id);
        setupPage(session, model);
        return "redirect:/profile";
    }

    private void setupPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        boolean isStudent = user.getRole() == User.Role.STUDENT;
        List<Proposal> proposals = new ArrayList<>();
        List<ProposalResponse> pendingProposals = new ArrayList<>();
        List<ProposalResponse> approvedProposals = new ArrayList<>();
        List<GradingRubric> gradingRubrics = new ArrayList<>();
        if (isStudent) {
            // Pull all the submitted proposals
            List<UserProposalRelationship> userProposalRelationshipList = userProposalRelationshipRepository.findByUserId(user.getId());
            if (userProposalRelationshipList != null) {
                for (UserProposalRelationship userProposalRelationship : userProposalRelationshipList) {
                    List<Proposal> tempProposalList = proposalRepository.findById(userProposalRelationship.getPid());
                    if (tempProposalList != null) {
                        proposals.addAll(tempProposalList);
                    }
                }
            }
            model.addAttribute("proposals", proposals);
        }
        else {
            // Pull all the proposals from all students
            Iterable<UserProposalRelationship> all = userProposalRelationshipRepository.findAll();
            for (UserProposalRelationship userProposalRelationship : all) {
                List<Proposal> tempProposalList = proposalRepository.findById(userProposalRelationship.getPid());
                if (tempProposalList != null) {
                    for (Proposal proposal : tempProposalList) {
                        if (proposal.getStatus() == Proposal.Status.PENDING) {
                            pendingProposals.add(new ProposalResponse(userProposalRelationship.getUid(), proposal));
                        }
                        else if (proposal.getStatus() == Proposal.Status.APPROVED) {
                            approvedProposals.add(new ProposalResponse(userProposalRelationship.getUid(), proposal));
                        }
                    }
                }
            }
            List<GradingRubric> temp = gradingRubricRepository.findAllGradingRubric();
            if (temp != null) {
                gradingRubrics.addAll(temp);
            }
            model.addAttribute("pendingProposals", pendingProposals);
            model.addAttribute("approvedProposals", approvedProposals);
            model.addAttribute("gradingRubrics", gradingRubrics);
        }
        model.addAttribute("sessionUserName", user.getName());
        model.addAttribute("sessionUser", user);
        model.addAttribute("isStudent", isStudent);
    }

    @RequestMapping(value="/profile/delete_grading_rubric/{id}", method= RequestMethod.POST)
    @Transactional
    public String deleteGradingRubric(HttpSession session, Model model, @PathVariable("id") Integer id) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        if (user == null) {
            return "redirect:/login";
        }
        gradingRubricRepository.delete(id);
        setupPage(session, model);
        return "redirect:/profile";
    }
}
