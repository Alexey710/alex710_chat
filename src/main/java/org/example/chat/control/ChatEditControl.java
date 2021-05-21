package org.example.chat.control;

import org.example.chat.model.Message;
import org.example.chat.model.Post;
import org.example.chat.model.User;
import org.example.chat.repository.LocalStore;
import org.example.chat.service.PostService;
import org.example.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatEditControl {
    @Autowired
    private LocalStore localStore;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/chat")
    public String getChat(@RequestParam(value = "id", required = false) String id,
                              @RequestParam(value = "name", required = false) String name,
                              Model model) {
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        Post post = postService.findById(Long.parseLong(id));
        model.addAttribute("chatName", post.getName());
        model.addAttribute("chat", post.getMessages());
        return "post/chat";
    }

    @MessageMapping("/chat")
    @SendTo("/topic/greetings")
    public Message addMessage(Message message) throws Exception {
        Message current = Message.of(
                message.getContent(), localStore.getSender(), localStore.getSenderColor());
        System.out.println("Message current =>" + current);
        localStore.save(current);
        return current;
    }
}
