package org.example.chat.service;

import org.example.chat.model.Post;
import org.example.chat.model.User;
import org.example.chat.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PostService postService;

    public UserService(UserRepository userRepository, PostService postService) {
        this.userRepository = userRepository;
        this.postService = postService;
    }

    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        return userRepository.findByUsername(userDetail.getUsername());
    }

    public User findUserById(String id) {
        Optional<User> userOpt = userRepository.findById(Long.parseLong(id));
        return userOpt.orElse(null);
    }

    public void saveUser(User user) {
        List<String> colors = new ArrayList<>();
        colors.add("#DC143C");
        colors.add("#FF0000");
        colors.add("#FF1493");
        colors.add("#00FF00");
        colors.add("#0000FF");
        colors.add("#000000");
        String color = colors.get(new Random().nextInt(colors.size()));
        user.setColorCSS(color);
        userRepository.save(user);
    }

    public List<User> getAllUsersExceptCurrent(long id) {
        return userRepository.findAllUsersExceptCurrent(id);
    }

    public List<User> getUsersOffChat(long id) {
        List<User> list =  userRepository.findAllUsersExceptCurrent(id);
        Post post = postService.findById(id);
        List<User> list2 = post.getUsers();
        list.removeAll(list2);
        return list;
    }

}
