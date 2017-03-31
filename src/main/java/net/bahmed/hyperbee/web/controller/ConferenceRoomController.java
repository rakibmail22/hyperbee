package net.bahmed.hyperbee.web.controller;

import net.bahmed.hyperbee.domain.ConferenceRoom;
import net.bahmed.hyperbee.service.ConferenceRoomService;
import net.bahmed.hyperbee.web.validator.ConferenceRoomValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static net.bahmed.hyperbee.utils.constant.Url.*;
import static net.bahmed.hyperbee.utils.constant.Messages.*;



/**
 * @author rumman
 * @since 11/27/16
 */
@Controller
@RequestMapping(value = CONFERENCE_ROOM_BASE_URL)
public class ConferenceRoomController {

    private static final Logger log = LogManager.getLogger(SimpleLogger.class);

    @Autowired
    private ConferenceRoomService conferenceRoomService;


    @Autowired
    private ConferenceRoomValidator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
        binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
    }

    @GetMapping(value = CONFERENCE_ROOM_LIST_URL)
    public String showConferenceRoomList(ModelMap modelMap) {

        modelMap.addAttribute("page", CONFERENCE_HTML_PAGE_ACTIVE_KEY)
                .addAttribute("conferenceRoomList", conferenceRoomService.findAllConferenceRoom())
                .addAttribute("conferenceRoomAddUrl", CONFERENCE_ROOM_BASE_URL)
                .addAttribute("deleteUrl", CONFERENCE_ROOM_BASE_URL + CONFERENCE_ROOM_DELETE_URL);

        log.debug(CONFERENCE_LIST_VIEWED);

        return CONFERENCE_LIST_VIEW;
    }

    @GetMapping
    public String showAddConferenceRoomForm(ModelMap modelMap) {
        if (!modelMap.containsAttribute("conferenceRoom")) {
            modelMap.addAttribute("conferenceRoom", new ConferenceRoom());
        }

        modelMap.addAttribute("page", CONFERENCE_HTML_PAGE_ACTIVE_KEY)
                .addAttribute("pageHeader", CONFERENCE_PAGE_ADD_HEADER)
                .addAttribute("action", CONFERENCE_ROOM_BASE_URL + CONFERENCE_ROOM_ADD_URL);

        log.debug(CONFERENCE_ADD_VIEWED);

        return CONFERENCE_FORM_VIEW;
    }


    @PostMapping(value = CONFERENCE_ROOM_ADD_URL)
    public String AddConferenceRoom(@ModelAttribute("conferenceRoom") @Validated ConferenceRoom conferenceRoom,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "conferenceRoom", bindingResult)
                    .addFlashAttribute("conferenceRoom", conferenceRoom);

            log.debug(CONFERENCE_SAVE_ERROR);

            return "redirect:" + CONFERENCE_ROOM_BASE_URL;
        }

        conferenceRoomService.saveConferenceRoom(conferenceRoom);
        log.debug(CONFERENCE_SAVED);

        return "redirect:" + CONFERENCE_ROOM_BASE_URL + CONFERENCE_ROOM_LIST_URL;
    }

    @GetMapping(value = CONFERENCE_ROOM_UPDATE_VIEW_URL)
    public String showEditConferenceRoomForm(@PathVariable("id") int id, ModelMap modelMap) {
        if (!modelMap.containsAttribute("conferenceRoom")) {
            modelMap.addAttribute("conferenceRoom", conferenceRoomService.findConferenceRoomById(id));
        }

        modelMap.addAttribute("page", CONFERENCE_HTML_PAGE_ACTIVE_KEY)
                .addAttribute("action", CONFERENCE_ROOM_BASE_URL + CONFERENCE_ROOM_UPDATE_URL)
                .addAttribute("pageHeader", CONFERENCE_PAGE_EDIT_HEADER);

        log.debug(CONFERENCE_EDIT_VIEWED);

        return CONFERENCE_FORM_VIEW;
    }


    @PostMapping(value = CONFERENCE_ROOM_UPDATE_URL)
    public String editAddConferenceRoom(@ModelAttribute("conferenceRoom") @Validated ConferenceRoom conferenceRoom,
                                        BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "conferenceRoom", bindingResult)
                    .addFlashAttribute("conferenceRoom", conferenceRoom);

            log.debug(CONFERENCE_SAVE_ERROR);

            return "redirect:" + CONFERENCE_ROOM_BASE_URL + "/" + conferenceRoom.getId();
        }

        conferenceRoomService.saveConferenceRoom(conferenceRoom);
        log.debug(CONFERENCE_SAVED);

        return "redirect:" + CONFERENCE_ROOM_BASE_URL + CONFERENCE_ROOM_LIST_URL;
    }

    @PostMapping(value = CONFERENCE_ROOM_DELETE_URL)
    public String deleteConferenceRoom(@RequestParam("id") int conferenceRoomId) {
        conferenceRoomService.delete(conferenceRoomId);

        log.debug(CONFERENCE_DELETED);

        return "redirect:" + CONFERENCE_ROOM_BASE_URL + CONFERENCE_ROOM_LIST_URL;
    }
}
