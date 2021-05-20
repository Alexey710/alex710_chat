package org.example.chat.control;

import org.example.chat.model.User;
import org.example.chat.service.PostService;
import org.example.chat.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControl {

    private final UserService userService;
    private final PostService postService;

    public IndexControl(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        User user = userService.getAuthenticatedUser();

        model.addAttribute("posts", postService.findAllPostByUserId(user.getId()));
        model.addAttribute("user", user.getUsername());
        return "index";
    }
}
