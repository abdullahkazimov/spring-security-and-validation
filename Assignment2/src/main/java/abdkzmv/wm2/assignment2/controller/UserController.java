package abdkzmv.wm2.assignment2.controller;

import abdkzmv.wm2.assignment2.model.dto.UserDto;
import abdkzmv.wm2.assignment2.model.entity.Role;
import abdkzmv.wm2.assignment2.model.mapper.UserMapper;
import abdkzmv.wm2.assignment2.service.inter.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
@SessionAttributes({"user", "roles"})
public class UserController {
    static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("admin/users")
    public String allUsers(Model model) {

        var users = UserMapper.instance.userListToUserDtoList(userService.getAllUsers());
        LOGGER.info("allUsers()");

        model.addAttribute("users", users);
        return "admin/main";
    }

    @GetMapping("admin/users/{id}")
    public String allUserRoles(Model model, @PathVariable Long id) {
        var userDto = UserMapper.instance.userToUserDto(userService.getById(id));
        LOGGER.info("allUserRoles()");

        List<Role> userRoles = new ArrayList<>();
        List<String> userRolesString = Arrays.stream(userDto.getRoles().split(";")).collect(Collectors.toList());

        for (Role role : userDto.getRolesList()) {
            for (String userRoleString : userRolesString) {
                if (role.getName().equals(userRoleString))
                    userRoles.add(role);
            }
        }

        List<Role> availableRoles = new ArrayList<>();
        for (Role role : userDto.getRolesList()) {
            if (!userRoles.contains(role))
                availableRoles.add(role);
        }

        model.addAttribute("user", userDto);
        model.addAttribute("roles", userRoles);
        model.addAttribute("availableRoles", availableRoles);

        return "user/update";
    }

    @PostMapping("admin/users/addRole/{id}")
    public String addUserRoles(Model model,
                               @RequestParam("selectedRole") Role role,
                               @SessionAttribute("user") UserDto userDto,
                               @PathVariable("id") Long id) {

        Role currentRole = (role != null) ? role : null;

        if (currentRole != null) {
            userDto.setRoles(userDto.getRoles() + ";" + currentRole.getName());
        }

        model.addAttribute("user", userDto);
        userService.saveRoleToUser(userDto.getRoles(), userDto.getId());


        LOGGER.info("addUserRoles()");

        return "redirect:/admin/users/"+id;
    }

    @GetMapping("admin/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        LOGGER.info("deleteUser()");
        return "redirect:/admin/users";
    }

    @GetMapping("admin/users/{id}/delete/{roleId}")
    public String deleteUserRole(@PathVariable Long id, @PathVariable Long roleId, @SessionAttribute List<Role> roles, Model model) {

        LOGGER.info("deleteUserRole()");


        var userDto = UserMapper.instance.userToUserDto(userService.getById(id));

        String currentRole = null;


        for (Role role : roles) {

            if (role.getId().equals(roleId)) {

                currentRole = role.toString();

            }

        }

        if (currentRole != null) {

            if (!currentRole.equals("ROLE_USER")) {



                String currentRoles = userDto.getRoles();


                if (currentRoles.endsWith(currentRole)) {
                    currentRoles = currentRoles.replace(";" + currentRole, "");
                }


                else if (currentRoles.startsWith(currentRole)) {
                    currentRoles = currentRoles.replace(currentRole + ";", "");
                }

                else {
                    currentRoles = currentRoles.replace(currentRole, "");
                }

                userDto.setRoles(currentRoles);
            }
        }

        userService.saveRoleToUser(userDto.getRoles(), id);


        model.addAttribute("user", userDto);

        return "redirect:/admin/users/" + id;
    }
}