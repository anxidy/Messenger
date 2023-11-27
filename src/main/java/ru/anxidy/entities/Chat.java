package ru.anxidy.entities;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue
    int id;

    @Column(length = 16)
    String name;

    @ManyToMany
    List<Account> receivers;

    @OneToMany(cascade = ALL)
    final
    List<Message> messages = new ArrayList<>();

    public Chat() {}

    public Chat(String name, List<Account> receivers) {
        this.name = name;
        this.receivers = receivers;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Message> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    public void addMessage(@NonNull Message message) {
        message.setChat(this);
        messages.add(message);
    }

    public List<Account> getReceivers() {
        return Collections.unmodifiableList(receivers);
    }

    public void addReceiver(@NonNull Account account, Boolean calledFromAddChat) {
        if (!calledFromAddChat) account.addChat(this, true);
        receivers.add(account);
    }
}
