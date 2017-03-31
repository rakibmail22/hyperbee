package net.bahmed.hyperbee.web.controller;

import net.bahmed.hyperbee.domain.Hive;
import net.bahmed.hyperbee.domain.Notice;
import net.bahmed.hyperbee.domain.enums.DisplayStatus;
import net.bahmed.hyperbee.service.ActivityService;
import net.bahmed.hyperbee.service.HiveService;
import net.bahmed.hyperbee.service.NoticeService;
import net.bahmed.hyperbee.service.UserService;
import net.bahmed.hyperbee.web.helper.SessionHelper;
import net.bahmed.hyperbee.web.validator.NoticeValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;

import static net.bahmed.hyperbee.utils.constant.Messages.*;
import static net.bahmed.hyperbee.utils.constant.Url.*;


/**
 * @author rumman
 * @since 11/22/16
 */
@Controller
@RequestMapping(value = NOTICE_BASE_URL)
public class NoticeController {

    private static final Logger log = LogManager.getLogger(SimpleLogger.class);

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private UserService userService;

    @Autowired
    private HiveService hiveService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private NoticeValidator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);

        binder.registerCustomEditor(Hive.class, "hiveList", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Hive hive = hiveService.retrieveHiveById(Integer.parseInt(text));
                setValue(hive);
            }
        });
    }


    @RequestMapping(value = NOTICE_LIST_URL, method = RequestMethod.GET)
    public String showNoticeList(ModelMap modelMap) {
        modelMap.addAttribute("page", NOTICE_HTML_PAGE_ACTIVE_KEY)
                .addAttribute("noticeList", noticeService.findAllNotice())
                .addAttribute("noticeAddUrl", NOTICE_BASE_URL)
                .addAttribute("isAdmin", sessionHelper.getAuthUserFromSession().isAdmin())
                .addAttribute("deleteUrl", NOTICE_BASE_URL + NOTICE_DELETE_URL);

        log.debug(NOTICE_VIEWED);
        activityService.archive(NOTICE_LIST_VIEWED);

        return NOTICE_LIST_VIEW;
    }

    @GetMapping
    public String showAddNoticeForm(ModelMap modelMap) {
        if (!modelMap.containsAttribute("notice")) {
            modelMap.addAttribute("notice", new Notice());
        }

        modelMap.addAttribute("page", NOTICE_HTML_PAGE_ACTIVE_KEY)
                .addAttribute("noticeHeader", NOTICE_PAGE_ADD_HEADER)
                .addAttribute("action", NOTICE_BASE_URL + NOTICE_ADD_URL)
                .addAttribute("hiveList", hiveService.getAllHive())
                .addAttribute("displayStatusOptions", DisplayStatus.values());

        log.debug(NOTICE_ADD_VIEWED);

        return NOTICE_FORM_VIEW;
    }

    @PostMapping(value = NOTICE_ADD_URL)
    public String addNotice(@ModelAttribute("notice") Notice notice,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        int sessionUserId = (sessionHelper.getAuthUserFromSession()).getId();
        notice.setUser(userService.findById(sessionUserId));

        validator.validate(notice, bindingResult);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "notice", bindingResult)
                    .addFlashAttribute("notice", notice);

            log.debug(NOTICE_SAVE_ERROR);

            return "redirect:" + NOTICE_BASE_URL;
        }

        noticeService.saveNotice(notice);
        activityService.archive(NOTICE_SAVED);
        log.debug(NOTICE_SAVED);

        redirectAttributes.addFlashAttribute("message", NOTICE_SUCCESS)
                .addFlashAttribute("htmlTitle", NOTICE_SAVED)
                .addFlashAttribute("messageStyle", SUCCESS_HTML_CLASS);

        return "redirect:" + DONE_URL;
    }

    @GetMapping(value = "/{id}/**")
    public String showEditNoticeForm(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("page", NOTICE_HTML_PAGE_ACTIVE_KEY)
                .addAttribute("action", NOTICE_BASE_URL + NOTICE_UPDATE_URL)
                .addAttribute("noticeHeader", NOTICE_PAGE_EDIT_HEADER)
                .addAttribute("hiveList", hiveService.getAllHive())
                .addAttribute("notice", noticeService.findNoticeById(id));

        log.debug(NOTICE_EDIT_VIEWED);

        return NOTICE_FORM_VIEW;
    }

    @PostMapping(value = NOTICE_UPDATE_URL)
    public String editNotice(@ModelAttribute("notice") Notice notice,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        int sessionUserId = (sessionHelper.getAuthUserFromSession()).getId();
        notice.setUser(userService.findById(sessionUserId));

        validator.validate(notice, bindingResult);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "notice", bindingResult)
                    .addFlashAttribute("notice", notice);

            log.debug(NOTICE_SAVE_ERROR);

            return "redirect:" + NOTICE_BASE_URL;
        }

        noticeService.saveNotice(notice);
        activityService.archive(NOTICE_MODIFIED);
        log.debug(NOTICE_MODIFIED);

        redirectAttributes.addFlashAttribute("message", NOTICE_SUCCESS)
                .addFlashAttribute("htmlTitle", NOTICE_MODIFIED)
                .addFlashAttribute("messageStyle", SUCCESS_HTML_CLASS);

        return "redirect:" + DONE_URL;
    }

    @PostMapping(value = NOTICE_DELETE_URL)
    public String deleteNotice(@RequestParam("id") int noticeId) {
        noticeService.delete(noticeId);
        activityService.archive(NOTICE_DELETED);
        log.debug(NOTICE_DELETED);

        return "redirect:" + NOTICE_BASE_URL + NOTICE_LIST_URL;
    }
}