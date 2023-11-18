package ru.anxidy.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue
    int id;

    @Column(length = 31)
    String name;

    @ManyToMany
    List<Account> receivers;

    @OneToMany(cascade = CascadeType.ALL)
    List<Message> messages = new ArrayList<>();

    public Chat() {}

    public Chat(String name, List<Account> receivers) {
        this.name = name;
        this.receivers = receivers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Account> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<Account> receivers) {
        this.receivers = receivers;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
