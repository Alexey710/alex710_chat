package org.example.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private Calendar created;

    @ManyToMany
    @JoinTable (name = "post_user",
            joinColumns = @JoinColumn (name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnoreProperties("posts")
    private final List<User> users = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private final List<Message> messages = new LinkedList<>();

    public static Post of(String name) {
        Post post = new Post();
        post.name = name;
        return post;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id
                && Objects.equals(name, post.name)
                && Objects.equals(description, post.description)
                && Objects.equals(created, post.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, created);
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", created=" + "HIDDEN" + '}';
    }
}
