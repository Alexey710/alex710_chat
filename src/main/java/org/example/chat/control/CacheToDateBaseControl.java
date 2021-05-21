package org.example.chat.control;

import org.example.chat.model.Post;
import org.example.chat.model.User;
import org.example.chat.repository.LocalStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheToDateBaseControl {
    @Autowired
    private LocalStore localStore;
    
    @PostMapping(value = "/cache")
    public Post addAllToDataBase(@RequestBody Post post) {
        localStore.saveAllMessagesToDataBase(post.getId());
      
        return post;
    }
    
}
