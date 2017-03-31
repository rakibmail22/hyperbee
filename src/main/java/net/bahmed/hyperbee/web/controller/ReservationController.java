package net.bahmed.hyperbee.web.controller;

import net.bahmed.hyperbee.domain.ConferenceRoom;
import net.bahmed.hyperbee.domain.Reservation;
import net.bahmed.hyperbee.domain.enums.ReservationStatus;
import net.bahmed.hyperbee.service.ConferenceRoomService;
import net.bahmed.hyperbee.service.ReservationService;
import net.bahmed.hyperbee.service.UserService;
import net.bahmed.hyperbee.utils.constant.Messages;
import net.bahmed.hyperbee.web.helper.ReservationHelper;
import net.bahmed.hyperbee.web.helper.SessionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;

import static net.bahmed.hyperbee.utils.constant.Url.*;


/**
 * @author rumman
 * @since 11/22/16
 */
@Controller
@RequestMapping(value = RESERVATION_BASE_URL)
public class ReservationController {

    private static final Logger log = LogManager.getLogger(SimpleLogger.class);

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ConferenceRoomService conferenceRoomService;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private ReservationHelper reservationHelper;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(ConferenceRoom.class, "conferenceRoom", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                ConferenceRoom conferenceRoom = conferenceRoomService.findConferenceRoomById(Integer.parseInt(text));
                setValue(conferenceRoom);
            }
        });
    }


    @GetMapping(value = RESERVATION_LIST_URL)
    public String showReservationList(ModelMap modelMap) {

        modelMap.addAttribute("page", Messages.RESERVATION_HTML_PAGE_ACTIVE_KEY)
                .addAttribute("reservationList", reservationService.findAllReservation())
                .addAttribute("actionUrl", RESERVATION_BASE_URL)
                .addAttribute("deleteUrl", RESERVATION_BASE_URL + RESERVATION_DELETE_URL);

        log.debug(Messages.RESERVATION_LIST_VIEWED);

        return RESERVATION_LIST_VIEW;
    }

    @GetMapping
    public String showAddReservationForm(ModelMap modelMap) {

        modelMap.addAttribute("page", Messages.RESERVATION_HTML_PAGE_ACTIVE_KEY)
                .addAttribute("reservation", new Reservation())
                .addAttribute("pageHeader", Messages.RESERVATION_PAGE_ADD_HEADER)
                .addAttribute("action", RESERVATION_BASE_URL + RESERVATION_ADD_URL)
                .addAttribute("roomList", conferenceRoomService.findAllConferenceRoom())
                .addAttribute("reservationStatusOptions", ReservationStatus.values());

        log.debug(Messages.RESERVATION_ADD_VIEWED);

        return RESERVATION_FORM_VIEW;
    }

    @PostMapping(value = RESERVATION_ADD_URL)
    public String addReservation(@ModelAttribute("reservation") Reservation reservation,
                                 BindingResult bindingResult,
                                 @RequestParam("reservationFrom") String reservationFrom,
                                 @RequestParam("reservationTo") String reservationTo) {

        int sessionUserId = (sessionHelper.getAuthUserFromSession()).getId();

        reservation.setReservationFrom(reservationHelper.getCalendarFromString(reservationFrom));
        reservation.setUser(userService.findById(sessionUserId));
        reservation.setReservationTo(reservationHelper.getCalendarFromString(reservationTo));

        reservationService.saveReservation(reservation);
        log.debug(Messages.RESERVATION_SAVED);

        return "redirect:" + RESERVATION_BASE_URL + RESERVATION_LIST_URL;
    }

    @GetMapping(value = RESERVATION_ROOM_UPDATE_VIEW_URL)
    public String showEditReservationForm(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("page", Messages.RESERVATION_HTML_PAGE_ACTIVE_KEY)
                .addAttribute("pageHeader", Messages.RESERVATION_PAGE_EDIT_HEADER)
                .addAttribute("action", RESERVATION_BASE_URL + RESERVATION_UPDATE_URL)
                .addAttribute("reservation", reservationService.findReservationById(id))
                .addAttribute("roomList", conferenceRoomService.findAllConferenceRoom())
                .addAttribute("reservationStatusOptions", ReservationStatus.values());

        log.debug(Messages.RESERVATION_EDIT_VIEWED);

        return RESERVATION_FORM_VIEW;
    }

    @PostMapping(value = RESERVATION_UPDATE_URL)
    public String editReservation(@ModelAttribute("reservation") Reservation reservation,
                                  BindingResult bindingResult,
                                  @RequestParam("reservationFrom") String reservationFrom,
                                  @RequestParam("reservationTo") String reservationTo) {

        int sessionUserId = (sessionHelper.getAuthUserFromSession()).getId();
        reservation.setUser(userService.findById(sessionUserId));
        reservation.setReservationFrom(reservationHelper.getCalendarFromString(reservationFrom));
        reservation.setReservationTo(reservationHelper.getCalendarFromString(reservationTo));
        reservationService.saveReservation(reservation);

        log.debug(Messages.RESERVATION_SAVED);

        return "redirect:" + RESERVATION_BASE_URL + RESERVATION_LIST_URL;
    }

    @PostMapping(value = RESERVATION_DELETE_URL)
    public String deleteReservation(@RequestParam("id") int reservationId) {
        reservationService.delete(reservationId);

        log.debug(Messages.RESERVATION_DELETED);

        return "redirect:" + RESERVATION_BASE_URL + RESERVATION_LIST_URL;
    }
}
