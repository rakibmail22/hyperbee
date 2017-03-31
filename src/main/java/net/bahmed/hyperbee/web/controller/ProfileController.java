package net.bahmed.hyperbee.web.controller;

import net.bahmed.hyperbee.domain.Profile;
import net.bahmed.hyperbee.domain.User;
import net.bahmed.hyperbee.domain.enums.DisplayStatus;
import net.bahmed.hyperbee.service.ActivityService;
import net.bahmed.hyperbee.service.ProfileService;
import net.bahmed.hyperbee.service.UserService;
import net.bahmed.hyperbee.utils.constant.Messages;
import net.bahmed.hyperbee.web.helper.ImageUploader;
import net.bahmed.hyperbee.web.helper.SessionHelper;
import net.bahmed.hyperbee.web.security.AuthUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static net.bahmed.hyperbee.utils.constant.Constant.PROFILE_ATTRIBUTE;
import static net.bahmed.hyperbee.utils.constant.Constant.USER_ATTRIBUTE;
import static net.bahmed.hyperbee.utils.constant.Url.*;


/**
 * @author duity
 * @since 11/22/16.
 */
@Controller
@RequestMapping(value = PROFILE_URL)
public class ProfileController {

    private static final Logger log = LogManager.getLogger(SimpleLogger.class);

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageUploader imageUploader;

    @Autowired
    private ActivityService activityService;

    @GetMapping(value = PROFILE_EDIT_URL)
    public String getProfile(Model model) {
        model.addAttribute("page", "profile");
        AuthUser authUser = sessionHelper.getAuthUserFromSession();
        int id = authUser.getId();
        User user = userService.findById(id);

        if (user.getProfile() == null) {
            model.addAttribute(PROFILE_ATTRIBUTE, new Profile());
            model.addAttribute(USER_ATTRIBUTE, user);
        } else {
            Profile profile = user.getProfile();
            model.addAttribute(PROFILE_ATTRIBUTE, profile);
            model.addAttribute(USER_ATTRIBUTE, user);
        }
        activityService.archive(Messages.VISIT_EDIT_PROFILE_ACTIVITY);

        log.debug("AuthUser ID:", authUser.getId());
        log.debug("AuthUser Name:", authUser.getUsername());

        return CREATE_PROFILE_URL;
    }

    @PostMapping
    public String postProfile(@ModelAttribute Profile profile, Model model,
                              @RequestParam MultipartFile file,
                              @RequestParam MultipartFile coverFile) {
        model.addAttribute("page", "profile");
        AuthUser authUser = sessionHelper.getAuthUserFromSession();
        int userId = authUser.getId();
        User user = userService.findById(userId);
        String oldProfileImage = user.getProfile().getImagePath();
        String oldCoverImage = user.getProfile().getCoverImage();

        if (file.getSize() == 0) {
            profile.setImagePath(oldProfileImage);
        } else {
            String filename = user.getUsername().replaceAll(" ", "") + file.getOriginalFilename();
            profile.setImagePath(filename);
            if (!file.isEmpty()) {
                imageUploader.createImagesDirIfNeeded();
                model.addAttribute("message2", imageUploader.createImage(filename, file));
            }
        }

        if (coverFile.getSize() == 0) {
            profile.setCoverImage(oldCoverImage);
        } else {
            String coverImageName = user.getUsername().replaceAll(" ", "") + coverFile.getOriginalFilename();
            profile.setCoverImage(coverImageName);
            if (!coverFile.isEmpty()) {
                imageUploader.createImagesDirIfNeeded();
                model.addAttribute("message3", imageUploader.createImage(coverImageName, coverFile));
            }
        }

        String message = profileService.saveProfileForUser(profile, userId);
        model.addAttribute("message", message);
        model.addAttribute(USER_ATTRIBUTE, user);
        activityService.archive(Messages.EDITED_PROFILE_ACTIVITY);

        log.debug("Edited User Profile:", user.getId() + ":" + user.getUsername());

        return CREATE_PROFILE_URL;
    }

    @GetMapping(value = USER_PROFILE_URL)
    public String getViewProfile(Model model) {
        model.addAttribute("page", "profile");
        AuthUser authUser = sessionHelper.getAuthUserFromSession();
        String username = authUser.getUsername();
        User user = userService.findByUsername(username);
        Profile profile = user.getProfile();
        if (!model.containsAttribute(PROFILE_ATTRIBUTE)) {
            model.addAttribute(PROFILE_ATTRIBUTE, profile);
        }
        model.addAttribute(USER_ATTRIBUTE, user);
        activityService.archive(Messages.VISITED_PROFILE_ACTIVITY);

        return VIEW_PROFILE_URL;
    }

    @PostMapping(value = USER_PROFILE_URL)
    public String viewProfile(Model model) {
        model.addAttribute("page", "profile");
        AuthUser authUser = sessionHelper.getAuthUserFromSession();
        String username = authUser.getUsername();
        User user = userService.findByUsername(username);
        Profile profile = user.getProfile();
        model.addAttribute(PROFILE_ATTRIBUTE, profile);
        model.addAttribute(USER_ATTRIBUTE, user);

        return VIEW_PROFILE_URL;
    }

    @GetMapping(value = SEARCH_URL)
    public String searchProfilePage(Model model) {
        model.addAttribute("page", "stalk");
        List<User> userList;

        if (sessionHelper.getAuthUserFromSession().isAdmin()) {
            userList = userService.findAll();
        } else {
            userList = userService.findActiveUsers();
        }
        model.addAttribute("userList", userList);
        activityService.archive(Messages.VISIT_STALKUSER_ACTIVITY);

        return PROFILE_SEARCH_URL;
    }

    @PostMapping(value = SEARCH_URL)
    public String searchProfile(@RequestParam("search") String username, Model model) {
        model.addAttribute("page", "stalk");
        AuthUser authUser = sessionHelper.getAuthUserFromSession();
        User user = userService.findByUsername(username);
        List<User> userList;

        if (authUser.isAdmin()) {
            if (user == null) {
                model.addAttribute("message", Messages.NO_USER_FOUND);
            } else {
                Profile profile = user.getProfile();
                model.addAttribute(PROFILE_ATTRIBUTE, profile);
                model.addAttribute(USER_ATTRIBUTE, user);
            }
        } else {
            if (user == null || user.getDisplayStatus() == DisplayStatus.INACTIVE) {
                model.addAttribute("message", Messages.NO_USER_FOUND);
            } else {
                Profile profile = user.getProfile();
                model.addAttribute(PROFILE_ATTRIBUTE, profile);
                model.addAttribute(USER_ATTRIBUTE, user);
            }
        }

        if (authUser.isAdmin()) {
            userList = userService.findAll();
        } else {
            userList = userService.findActiveUsers();
        }
        model.addAttribute("userList", userList);
        activityService.archive(Messages.STALK_PROFILE_ACTIVITY);

        return PROFILE_SEARCH_URL;
    }

    @GetMapping(value = STALK_PROFILE_URL)
    public String stalkProfile(Model model, @PathVariable String username, RedirectAttributes redirectAttributes) {
        model.addAttribute("page", "stalk");
        User user = userService.findByUsername(username);

        if (user == null) {
            redirectAttributes.addFlashAttribute("message", Messages.NO_USER_FOUND);
            redirectAttributes.addFlashAttribute("messageStyle", "alert alert-success");
            return "redirect:" + DONE_URL;
        }
        Profile profile = user.getProfile();
        model.addAttribute(PROFILE_ATTRIBUTE, profile);
        model.addAttribute(USER_ATTRIBUTE, user);
        activityService.archive(Messages.STALK_USER_PROFILE_ACTIVITY);

        return PROFILE_STALK_URL;
    }

    @RequestMapping(value = PROFILE_IMAGE_URL)
    @ResponseBody
    public byte[] getImage(@PathVariable(value = "imagePath") String imageName) throws IOException {
        imageUploader.createImagesDirIfNeeded();
        File serverFile = new File(imageUploader.getImagesDirAbsolutePath() + imageName + ".png");

        return Files.readAllBytes(serverFile.toPath());
    }

    @RequestMapping(value = COVER_IMAGE_URL)
    @ResponseBody
    public byte[] getCoverImage(@PathVariable(value = "coverImage") String imageName) throws IOException {
        imageUploader.createImagesDirIfNeeded();
        File serverFile = new File(imageUploader.getImagesDirAbsolutePath() + imageName + ".png");

        return Files.readAllBytes(serverFile.toPath());
    }
}
