package lv.ctd.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainResource {

    @RequestMapping("/")
    public String welcome() {
        return "index";
    }
}
