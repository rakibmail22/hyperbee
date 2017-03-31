package net.bahmed.hyperbee.web.controller;

import net.bahmed.hyperbee.domain.Activity;
import net.bahmed.hyperbee.domain.User;
import net.bahmed.hyperbee.web.helper.SessionHelper;
import net.bahmed.hyperbee.service.ActivityService;
import net.bahmed.hyperbee.service.UserService;
import net.bahmed.hyperbee.web.command.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import static net.bahmed.hyperbee.utils.constant.Url.*;


/**
 * @author rayed
 * @since 11/28/16 11:07 AM
 */
@Controller
@RequestMapping(ACTIVITY_ROOT_URL)
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionHelper sessionHelper;

    @GetMapping(ACTIVITY_LOG_URL)
    public String viewActivity(Model model) {
        int userId = sessionHelper.getUserIdFromSession();

        List<Activity> activityList = activityService.findByUserId(userId);
        List<User> userList = userService.findAll();

        model.addAttribute("activityList", activityList);
        model.addAttribute("userInfo", new UserInfo(userList));
        model.addAttribute("page", "activity");

        return ACTIVITY_VIEW;
    }

    @PostMapping(ACTIVITY_LOG_URL)
    public String selectActivity(UserInfo userInfo, BindingResult bindingResult, Model model){
        int userId = userInfo.getUserId();

        User user = userService.findById(userId);
        List<Activity> activityList = activityService.findByUserId(userId);

        model.addAttribute("user", user);
        model.addAttribute("activityList", activityList);
        model.addAttribute("page", "activity");

        return ACTIVITY_ADMIN_VIEW;
    }
}
