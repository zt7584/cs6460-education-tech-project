package edu.dev.controller;

import edu.dev.entity.*;
import edu.dev.object.ApprovedProposal;
import edu.dev.object.ProposalWithUser;
import edu.dev.object.RequestWrapper;
import edu.dev.object.UserWithRole;
import edu.dev.repository.GradingRubricRepository;
import edu.dev.repository.ProposalRepository;
import edu.dev.repository.UserProposalRelationshipRepository;
import edu.dev.repository.UserRepository;
import edu.dev.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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
    UserRepository userRepository;

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
        List<ProposalWithUser> otherStudentsProposals = new ArrayList<>();
        List<ProposalWithUser> pendingProposals = new ArrayList<>();
        List<RequestWrapper> approvedProposals = new ArrayList<>();
        List<GradingRubric> gradingRubrics = new ArrayList<>();
        if (isStudent) {
            // Pull all the submitted proposals
            boolean ownApprovedProposal = false;
            Proposal finalProposal = null;
            List<UserProposalRelationship> userProposalRelationshipList
                    = userProposalRelationshipRepository.findProposalsOwnedByUserId(user.getId());
            if (userProposalRelationshipList != null) {
                for (UserProposalRelationship userProposalRelationship : userProposalRelationshipList) {
                    List<Proposal> tempProposalList = proposalRepository.findById(userProposalRelationship.getPid());
                    if (tempProposalList != null) {
                        for (Proposal tProposal : tempProposalList) {
                            proposals.add(tProposal);
                            if (tProposal.getStatus() == Proposal.Status.APPROVED) {
                                ownApprovedProposal = true;
                                finalProposal = tProposal;
                            }
                        }
                    }
                }
            }
            List<UserProposalRelationship> requestUserProposalRelationshipList = userProposalRelationshipRepository.findRequestsMadeByUserId(user.getId());
            if (requestUserProposalRelationshipList == null) {
                requestUserProposalRelationshipList = new ArrayList<>();
            }
            for (UserProposalRelationship request : requestUserProposalRelationshipList) {
                if (request.getStatus() == UserProposalRelationship.Status.APPROVED) {
                    List<Proposal> tempProposalList = proposalRepository.findById(request.getPid());
                    if (tempProposalList != null && tempProposalList.size() > 0) {
                        finalProposal = tempProposalList.get(0);
                        break;
                    }
                }
            }
            List<UserProposalRelationship> otherUserProposalRelationshipList
                    = userProposalRelationshipRepository.findProposalsNotOwnedByUserId(user.getId());
            if (otherUserProposalRelationshipList != null) {
                for (UserProposalRelationship userProposalRelationship : otherUserProposalRelationshipList) {
                    List<Proposal> tempProposalList = proposalRepository.findById(userProposalRelationship.getPid());
                    List<User> tempUserList = userRepository.findById((int) userProposalRelationship.getUid());
                    if (tempProposalList != null && tempProposalList.size() > 0 && tempUserList != null && tempUserList.size() > 0) {
                        // Only approved proposal can be requested to join
                        if (tempProposalList.get(0).getStatus() == Proposal.Status.APPROVED) {
                            boolean isAlreadyRequested = false;
                            for (UserProposalRelationship request : requestUserProposalRelationshipList) {
                                if (request.getUid() == user.getId() && request.getPid() == tempProposalList.get(0).getId()
                                    && (request.getStatus() == UserProposalRelationship.Status.PENDING ||
                                        request.getStatus() == UserProposalRelationship.Status.APPROVED)) {
                                    isAlreadyRequested = true;
                                    break;
                                }
                            }
                            if (false == isAlreadyRequested) {
                                otherStudentsProposals.add(new ProposalWithUser(tempProposalList.get(0), tempUserList.get(0)));
                            }
                        }
                    }
                }
            }
            List<UserProposalRelationship> tempOtherRequestUserProposalRelationshipList = userProposalRelationshipRepository.findRequestsNotMadeByUserId(user.getId());
            if (tempOtherRequestUserProposalRelationshipList == null) {
                tempOtherRequestUserProposalRelationshipList = new ArrayList<>();
            }
            List<UserProposalRelationship> otherRequestUserProposalRelationshipList = new ArrayList<>();
            for (UserProposalRelationship request : tempOtherRequestUserProposalRelationshipList) {
                List<UserProposalRelationship> tempList = userProposalRelationshipRepository.findOwnerOfApprovedProposal((int) request.getPid());
                if (tempList != null & tempList.size() > 0) {
                    if (tempList.get(0).getUid() == user.getId()) {
                        otherRequestUserProposalRelationshipList.add(request);
                    }
                }
            }

            model.addAttribute("proposals", proposals);
            model.addAttribute("otherStudentsProposals", otherStudentsProposals);
            model.addAttribute("requestsByMe", constructRequestWrappers(requestUserProposalRelationshipList));
            model.addAttribute("requestsByOthers", constructRequestWrappers(otherRequestUserProposalRelationshipList));
            model.addAttribute("ownApprovedProposal", ownApprovedProposal);
            model.addAttribute("finalProposal", finalProposal);
        }
        else {
            // Pull all the proposals from all students
            Iterable<UserProposalRelationship> all = userProposalRelationshipRepository.findAll();
            for (UserProposalRelationship userProposalRelationship : all) {
                List<Proposal> tempProposalList = proposalRepository.findById(userProposalRelationship.getPid());
                if (tempProposalList != null) {
                    for (Proposal proposal : tempProposalList) {
                        if (proposal.getStatus() == Proposal.Status.PENDING) {
                            pendingProposals.add(new ProposalWithUser(proposal, userRepository.findById((int) userProposalRelationship.getUid()).get(0)));
                        }
                        else if (proposal.getStatus() == Proposal.Status.APPROVED) {
                            approvedProposals.add(new RequestWrapper(userRepository.findById((int) userProposalRelationship.getUid()).get(0), proposal, userProposalRelationship));
                        }
                    }
                }
            }
            List<GradingRubric> temp = gradingRubricRepository.findAllGradingRubric();
            if (temp != null) {
                gradingRubrics.addAll(temp);
            }
            model.addAttribute("pendingProposals", pendingProposals);
            model.addAttribute("approvedProposals", constructApprovedProposals(approvedProposals));
            model.addAttribute("gradingRubrics", gradingRubrics);
        }
        model.addAttribute("sessionUserName", user.getName());
        model.addAttribute("sessionUser", user);
        model.addAttribute("isStudent", isStudent);
    }

    private List<RequestWrapper> constructRequestWrappers(List<UserProposalRelationship> relationships) {
        List<RequestWrapper> requestWrappers = new ArrayList<>();
        for (UserProposalRelationship relationship : relationships) {
            requestWrappers.add(new RequestWrapper(
                    userRepository.findById((int) relationship.getUid()).get(0),
                    proposalRepository.findById((int) relationship.getPid()).get(0),
                    relationship));
        }
        return requestWrappers;
    }

    private List<ApprovedProposal> constructApprovedProposals(List<RequestWrapper> approvedRequestWrappers) {
        Map<Integer, ApprovedProposal> hash = new HashMap<>();
        for (RequestWrapper requestWrapper : approvedRequestWrappers) {
            if (hash.containsKey(requestWrapper.getProposal().getId())) {
                ApprovedProposal approvedProposal = hash.get(requestWrapper.getProposal().getId());
                if (requestWrapper.getRelationship().getStatus() == UserProposalRelationship.Status.APPROVED) {
                    approvedProposal.addMember(new UserWithRole(requestWrapper.getUser(), (int) requestWrapper.getRelationship().getRelationship()));
                }
                hash.put(requestWrapper.getProposal().getId(), approvedProposal);
            }
            else {
                ApprovedProposal approvedProposal = new ApprovedProposal(requestWrapper.getProposal());
                if (requestWrapper.getRelationship().getStatus() == UserProposalRelationship.Status.APPROVED) {
                    approvedProposal.addMember(new UserWithRole(requestWrapper.getUser(), (int) requestWrapper.getRelationship().getRelationship()));
                }
                hash.put(requestWrapper.getProposal().getId(), approvedProposal);
            }
        }
        List<ApprovedProposal> approvedProposals = new ArrayList<>();
        for (ApprovedProposal approvedProposal : hash.values()) {
            approvedProposals.add(approvedProposal);
        }
        return approvedProposals;
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

    @RequestMapping(value="/profile/request_to_join/{id}", method= RequestMethod.POST)
    @Transactional
    public String requestToJoin(HttpSession session, Model model, @PathVariable("id") Integer proposalId) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        if (user == null) {
            return "redirect:/login";
        }
        UserProposalRelationship userProposalRelationship = new UserProposalRelationship();
        userProposalRelationship.setRelationship(UserProposalRelationship.Relationship.MEMBER);
        userProposalRelationship.setPid(proposalId);
        userProposalRelationship.setUid(user.getId());
        userProposalRelationship.setStatus(UserProposalRelationship.Status.PENDING);
        userProposalRelationshipRepository.save(userProposalRelationship);
        setupPage(session, model);
        return "redirect:/profile";
    }

    @RequestMapping(value="/profile/withdraw_proposal/{id}", method= RequestMethod.POST)
    @Transactional
    public String withDrawProposal(HttpSession session, Model model, @PathVariable("id") Integer proposalId) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        if (user == null) {
            return "redirect:/login";
        }
        proposalRepository.updateProposalStatus(Proposal.Status.WITHDRAWN, proposalId);
        userProposalRelationshipRepository.withdrawProposalRelationshipStatus(UserProposalRelationship.Status.WITHDRAWN, proposalId);
        setupPage(session, model);
        return "redirect:/profile";
    }

    @RequestMapping(value="/profile/decline_proposal/{id}", method= RequestMethod.POST)
    @Transactional
    public String declineProposal(HttpSession session, Model model, @PathVariable("id") Integer proposalId) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        if (user == null) {
            return "redirect:/login";
        }
        proposalRepository.updateProposalStatus(Proposal.Status.DECLINED, proposalId);
        setupPage(session, model);
        return "redirect:/profile";
    }

    @RequestMapping(value="/profile/withdraw_join_request/{uid}/{pid}/{relationship}", method= RequestMethod.POST)
    @Transactional
    public String withDrawProposal(HttpSession session, Model model, @PathVariable("uid") Integer uid, @PathVariable("pid") Integer pid
            , @PathVariable("relationship") Integer relationship) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        if (user == null) {
            return "redirect:/login";
        }
        userProposalRelationshipRepository.updateUserProposalRelationshipStatus(Proposal.Status.WITHDRAWN, uid, pid, relationship);
        setupPage(session, model);
        return "redirect:/profile";
    }

    @RequestMapping(value="/profile/approve_join_request/{uid}/{pid}/{relationship}", method= RequestMethod.POST)
    @Transactional
    public String approveProposal(HttpSession session, Model model, @PathVariable("uid") Integer uid, @PathVariable("pid") Integer pid
            , @PathVariable("relationship") Integer relationship) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        if (user == null) {
            return "redirect:/login";
        }
        userProposalRelationshipRepository.updateUserProposalRelationshipStatus(Proposal.Status.APPROVED, uid, pid, relationship);
        setupPage(session, model);
        return "redirect:/profile";
    }

    @RequestMapping(value="/profile/decline_join_request/{uid}/{pid}/{relationship}", method= RequestMethod.POST)
    @Transactional
    public String declineProposal(HttpSession session, Model model, @PathVariable("uid") Integer uid, @PathVariable("pid") Integer pid
            , @PathVariable("relationship") Integer relationship) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        if (user == null) {
            return "redirect:/login";
        }
        userProposalRelationshipRepository.updateUserProposalRelationshipStatus(Proposal.Status.DECLINED, uid, pid, relationship);
        setupPage(session, model);
        return "redirect:/profile";
    }
}
