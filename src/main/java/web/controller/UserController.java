package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/index")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("index", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/show")
    public String show(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/index";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/update")
    public String update(@ModelAttribute("user")User user, @RequestParam("id") long id) {
        userService.updateUser(id, user);
        return "redirect:/index";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") long id) {
        userService.deleteUser(id);
        return "redirect:/index";
    }
}
