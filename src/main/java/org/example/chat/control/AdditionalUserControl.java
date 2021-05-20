package org.example.chat.control;

import org.example.chat.model.Post;
import org.example.chat.model.User;
import org.example.chat.service.PostService;
import org.example.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdditionalUserControl {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public String getAdditionalPage(
            @ModelAttribute Post post, Model model) {
        long idPost = post.getId();

        model.addAttribute("users", userService.getUsersOffChat(idPost));

        model.addAttribute("id", idPost);
        return "post/additional";
    }

    @PostMapping("/add")
    public String addUserToPost(@RequestParam(value = "idUser", required = false) String idUser,
                                @RequestParam(value = "idPost", required = false) String idPost,
                                Model model) {
        Post post = postService.findById(Long.parseLong(idPost));
        User user = userService.findUserById(idUser);
        post.getUsers().add(user);
        postService.create(post);

        model.addAttribute("posts", user.getPosts());

        return "redirect:/";
    }
}
