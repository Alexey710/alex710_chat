package org.example.chat.repository;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.example.chat.model.Message;
import org.example.chat.model.Post;
import org.example.chat.model.User;
import org.example.chat.service.PostService;
import org.example.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocalStore {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    private String sender;

    private String senderColor;

    private final List<Message> messages = new CopyOnWriteArrayList<>();

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void save(Message message) {
        messages.add(message);
    }

    public String getSenderColor() {
        return senderColor;
    }

    public void setSenderColor(String senderColor) {
        this.senderColor = senderColor;
    }

    public void saveAllMessagesToDataBase(long id) {
        System.out.println("start saveAllMessagesToDataBase ==========>");
        User userAuth = userService.getAuthenticatedUser();
        Post post = postService.findById(id);
        for (Message mes : messages) {
            mes.setUser(userAuth);
        }

        post.getMessages().addAll(messages);
        postService.create(post);
        messages.clear();
    }

}
