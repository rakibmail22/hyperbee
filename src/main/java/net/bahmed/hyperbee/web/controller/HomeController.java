package net.bahmed.hyperbee.web.controller;

import net.bahmed.hyperbee.utils.constant.Url;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author rumman
 * @since 11/30/16
 */
@Controller
public class HomeController {
    @GetMapping(value = Url.DONE_URL)
    public String noticeAccessDenied(Model model) {
        return Url.DONE_VIEW;
    }
}
