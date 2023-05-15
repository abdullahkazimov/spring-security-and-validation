package abdkzmv.wm2.assignment2.controller;

import abdkzmv.wm2.assignment2.model.dto.SignupDto;
import abdkzmv.wm2.assignment2.model.entity.User;
import abdkzmv.wm2.assignment2.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String signUpPage(Model model) {
        model.addAttribute("signupDto", new SignupDto());
        return "signup";
    }

    @PostMapping
    public String postSignUp(@ModelAttribute("signupDto") SignupDto signupDto) {

        User user = signupDto.toUser(passwordEncoder);
        userService.add(user);

        return "redirect:/login";
    }

}
