package ru.anxidy.entities;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 16, unique = true, nullable = false)
    private String login;

    @Column(length = 32, nullable = false)
    private String password;

    @ManyToMany(cascade = ALL)
    private final Set<Chat> chats = new HashSet<>();

    public Account() {
    }

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Chat> getChats() {
        return Collections.unmodifiableSet(chats);
    }

    public void addChat(@NonNull Chat chat, Boolean calledFromAddReceiver) {
        if (!calledFromAddReceiver) chat.addReceiver(this, true);
        chats.add(chat);
    }
}
