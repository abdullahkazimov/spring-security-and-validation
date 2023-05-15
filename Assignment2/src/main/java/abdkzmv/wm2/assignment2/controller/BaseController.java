package abdkzmv.wm2.assignment2.controller;

import abdkzmv.wm2.assignment2.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("greetText")
    private String welcomeMessage;

    @Autowired
    @Qualifier("byeText")
    private String farewellMessage;

    @GetMapping({"/","/welcome","/welcome/"})
    public String welcomePage(Model model){
        model.addAttribute("message", welcomeMessage);
        if(userDetailsService.loadUserByUsername(SecurityConfig.currentUser()).getAuthorities().toString().contains("ROLE_ADMIN")) {
            SecurityConfig.nullUser();
            return "admin/home";
        }
        SecurityConfig.nullUser();
        return "user/home";
    }

    @GetMapping("/bye")
    public String byePage(Model model){
        model.addAttribute("message", farewellMessage);
        return "user/home";
    }
}
