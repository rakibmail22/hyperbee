package net.bahmed.hyperbee.web.controller;

import net.bahmed.hyperbee.domain.Buzz;
import net.bahmed.hyperbee.service.ActivityService;
import net.bahmed.hyperbee.service.BuzzService;
import net.bahmed.hyperbee.service.UserService;
import net.bahmed.hyperbee.utils.Utils;
import net.bahmed.hyperbee.utils.constant.Messages;
import net.bahmed.hyperbee.utils.constant.Url;
import net.bahmed.hyperbee.web.helper.SessionHelper;
import net.bahmed.hyperbee.web.security.AuthUser;
import net.bahmed.hyperbee.web.validator.BuzzValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @author zoha
 * @since 11/22/16
 */
@RequestMapping(Url.BUZZ_BASE_URL)
@Controller

public class BuzzController {

    private static final Logger log = LogManager.getLogger(SimpleLogger.class);

    @Autowired
    private BuzzService buzzService;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private BuzzValidator buzzValidator;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private Utils utils;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(buzzValidator);
    }

    @GetMapping(Url.BUZZ_VIEW_URL)
    public void viewLatestBuzz(Model model) {
        model.addAttribute("pinnedBuzzList", buzzService.getPinnedBuzz());
        model.addAttribute("buzzList", buzzService.getLatestBuzz());

        log.debug("Passing Buzz Lists to view for displaying.");
    }

    @PostMapping(Url.BUZZ_CREATE_URL)
    public String sendBuzz(@ModelAttribute("newBuzz") @Validated Buzz newBuzz, BindingResult result,
                           RedirectAttributes redirectAttributes, HttpSession session, Model model) {

        if (result.hasErrors()) {
            log.debug("Encountered an error. Propagating message to view.");

            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "newBuzz", result);
            redirectAttributes.addFlashAttribute("newBuzz", newBuzz);

            return utils.redirectTo(Url.USER_DASHBOARD_URL);
        }

        AuthUser authUser = sessionHelper.getAuthUserFromSession();

        newBuzz.setUser(userService.findByUsername(authUser.getUsername()));
        buzzService.saveBuzz(newBuzz);
        log.debug("Created new buzz.");

        activityService.archive(Messages.BUZZ_SEND_SUCCESS.replaceAll("<message>", newBuzz.getMessage()));
        log.debug("Creation of buzz logged in activity log.");

        model.addAttribute("newBuzz", new Buzz());

        return utils.redirectTo(Url.USER_DASHBOARD_URL);
    }

    @GetMapping(Url.BUZZ_FLAG_URL)
    public String flagBuzz(int id) {
        Buzz tempBuzz = buzzService.flagBuzz(buzzService.getBuzzById(id));
        log.debug("Flagged buzz with id = " + id + ".");

        activityService.archive(Messages.BUZZ_FLAG_SUCCESS.replaceAll("<message>", tempBuzz.getMessage()));
        log.debug("Flagging of buzz logged in activity log.");

        return utils.redirectTo(Url.USER_DASHBOARD_URL);
    }

    @GetMapping(Url.BUZZ_DEACTIVATE_URL)
    public String deactivateBuzz(int id) {
        Buzz tempBuzz = buzzService.deactivateBuzz(buzzService.getBuzzById(id));
        log.debug("Deactivated buzz with id = " + id + ".");

        activityService.archive(Messages.BUZZ_DELETE_SUCCESS.replaceAll("<message>", tempBuzz.getMessage()));
        log.debug("Deactivation of buzz logged in activity log.");

        return utils.redirectTo(Url.USER_DASHBOARD_URL);
    }

    @GetMapping(Url.BUZZ_PIN_URL)
    public String pinBuzz(int id) {
        Buzz tempBuzz = buzzService.pinBuzz(buzzService.getBuzzById(id));
        log.debug("Pinned buzz with id = " + id + ".");

        activityService.archive(Messages.BUZZ_PINNED_SUCCESS.replaceAll("<message>", tempBuzz.getMessage()));
        log.debug("Pinning of buzz logged in activity log.");

        return utils.redirectTo(Url.USER_DASHBOARD_URL);
    }

    @GetMapping(Url.BUZZ_HISTORY_URL)
    public String viewBuzzHistory(@RequestParam("prev") int prev, @RequestParam("next") int next, Model model) {
        List<Buzz> buzzList = buzzService.getAllBuzz();

        if (next > buzzList.size()) {
            model.addAttribute("buzzList", buzzList.subList(prev, buzzList.size()));
            next = buzzList.size();
        } else {
            model.addAttribute("buzzList", buzzList.subList(prev, next));
        }

        model.addAttribute("prev", prev);
        model.addAttribute("next", next);

        model.addAttribute("page", "buzz");

        log.debug("Sending buzz list as per requirement for viewing history.");

        activityService.archive(Messages.BUZZ_HISTORY_REQUEST);
        log.debug("Retrieval of buzz history logged in activity log");

        return Url.BUZZ_BASE_URL + Url.BUZZ_HISTORY_URL;
    }
}