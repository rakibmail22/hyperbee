package net.bahmed.hyperbee.web.controller;

import net.bahmed.hyperbee.domain.Buzz;
import net.bahmed.hyperbee.domain.Hive;
import net.bahmed.hyperbee.domain.User;
import net.bahmed.hyperbee.domain.enums.DisplayStatus;
import net.bahmed.hyperbee.service.*;
import net.bahmed.hyperbee.utils.Utils;
import net.bahmed.hyperbee.utils.constant.Messages;
import net.bahmed.hyperbee.web.command.SignUpInfo;
import net.bahmed.hyperbee.web.helper.NoticeHelper;
import net.bahmed.hyperbee.web.helper.ReservationHelper;
import net.bahmed.hyperbee.web.helper.SessionHelper;
import net.bahmed.hyperbee.web.validator.LoginValidator;
import net.bahmed.hyperbee.web.validator.SignUpValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import static net.bahmed.hyperbee.utils.constant.Url.*;


/**
 * @author rayed
 * @since 11/22/16 10:59 AM
 */
@Controller
public class UserController {

    @Autowired
    BuzzService buzzService;

    @Autowired
    NoteService noteService;

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    private SignUpValidator signUpValidator;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private NoticeHelper noticeHelper;

    @Autowired
    private ReservationHelper reservationHelper;

    @Autowired
    private HiveService hiveService;

    @Autowired
    private Utils utils;

    @InitBinder("login")
    private void loginValidator(WebDataBinder binder) {
        binder.setValidator(loginValidator);
    }

    @InitBinder("signUp")
    private void signUpValidator(WebDataBinder binder) {
        binder.setValidator(signUpValidator);
    }

    @GetMapping(ROOT_URL)
    public String entry() {

        return utils.redirectTo(LOGIN_URL);
    }

    @GetMapping(LOGIN_URL)
    public String login(Model model) {
        model.addAttribute("login", new User());

        return LOGIN_VIEW;
    }

    @PostMapping(LOGIN_URL)
    public String loginUser(@Validated @ModelAttribute("login") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return LOGIN_VIEW;
        }

        User retrievedUser = userService.findByUsernameAndPassword(user);

        if ((retrievedUser != null) && (retrievedUser.getDisplayStatus() == DisplayStatus.ACTIVE)) {
            sessionHelper.persistInSession(retrievedUser);
            noticeHelper.persistInSession();
            reservationHelper.persistInSession();

            activityService.archive(Messages.LOGGED_IN);

            return utils.redirectTo(USER_DASHBOARD_URL);
        }

        return LOGIN_VIEW;
    }

    @GetMapping(SIGN_UP_URL)
    public String signUp(Model model) {
        model.addAttribute("signUp", new SignUpInfo());

        return SIGN_UP_VIEW;
    }

    @PostMapping(SIGN_UP_URL)
    public String signUpDash(@Validated @ModelAttribute("signUp") SignUpInfo signUpInfo,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return SIGN_UP_VIEW;
        }

        User user = signUpInfo.getUser();

        User retrievedUser = userService.createUser(user);
        sessionHelper.persistInSession(retrievedUser);

        Hive hive = hiveService.retrieveHiveById(1);
        hiveService.insertFirstUserToHive(hive, retrievedUser.getId());

        activityService.archive(Messages.SIGNED_UP);

        return utils.redirectTo(USER_DASHBOARD_URL);
    }

    @GetMapping(LOGOUT_URL)
    public String logout() {
        sessionHelper.invalidateSession();

        return utils.redirectTo(LOGIN_URL);
    }

    @GetMapping(USER_DASHBOARD_URL)
    public String welcome(Model model) {
        if (!model.containsAttribute("newBuzz")) {
            model.addAttribute("newBuzz", new Buzz());
        }

        int userId = sessionHelper.getUserIdFromSession();

        model.addAttribute("topStickyNote",
                noteService.findTopStickyNoteByUser(userId));
        model.addAttribute("latestReminders",
                noteService.findUpcomingReminderNoteByUser(userId));

        model.addAttribute("pinnedBuzzList", buzzService.getPinnedBuzz());
        model.addAttribute("buzzList", buzzService.getLatestBuzz());

        sessionHelper.setStatInSession();

        return USER_DASHBOARD_VIEW;
    }

    @GetMapping(USER_DEACTIVATE_URL)
    public String inactivateUser(@PathVariable int userId) {
        userService.inactivate(userId);

        sessionHelper.decrementSessionAttribute("activeUsers", 1);
        sessionHelper.incrementSessionAttribute("inactiveUsers", 1);

        return utils.redirectTo(PROFILE_URL + SEARCH_URL);
    }

    @GetMapping(USER_ACTIVATE_URL)
    public String activateUser(@PathVariable int userId) {
        userService.activate(userId);

        sessionHelper.incrementSessionAttribute("activeUsers", 1);
        sessionHelper.decrementSessionAttribute("inactiveUsers", 1);

        return utils.redirectTo(PROFILE_URL + SEARCH_URL);
    }
}
