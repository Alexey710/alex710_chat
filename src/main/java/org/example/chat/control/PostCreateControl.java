package org.example.chat.control;

import org.example.chat.model.Post;
import org.example.chat.service.PostService;
import org.example.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostCreateControl {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        long id = userService.getAuthenticatedUser().getId();
        model.addAttribute("users", userService.getAllUsersExceptCurrent(id));
        return "post/create";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute Post post) {
        postService.create(post);
        return "redirect:/";
    }
}
