package org.example.chat.control;

import org.example.chat.model.Post;
import org.example.chat.model.User;
import org.example.chat.service.PostService;
import org.example.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostEditControl {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/edit")
    public String getEditPage(@RequestParam(value = "id", required = false) String id,
                              @RequestParam(value = "name", required = false) String name,
                              Model model) {
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        return "post/edit";
    }

    @PostMapping("/edit")
    public String updateName(
                             @RequestParam(value = "name_form", required = false) String name,
                             @RequestParam(value = "id", required = false) String id,
                              Model model) {

        Post post = Post.of(name);
        User user = userService.findUserById(id);
        post.getUsers().add(user);
        User current = userService.getAuthenticatedUser();
        post.getUsers().add(current);

        postService.create(post);

        model.addAttribute("posts", user.getPosts());

        return "redirect:/";
    }
}

