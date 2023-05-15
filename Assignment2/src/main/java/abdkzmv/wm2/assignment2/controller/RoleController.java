package abdkzmv.wm2.assignment2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoleController {

    @GetMapping({"/admin","/admin/"})
    public String adminPanel(Model model) {
        model.addAttribute("message", "Admin panel");
        return "admin/panel";
    }

    @GetMapping({"/user","/user/"})
    public String userPanel(Model model) {
        model.addAttribute("message", "User panel");
        return "user/panel";
    }
}
