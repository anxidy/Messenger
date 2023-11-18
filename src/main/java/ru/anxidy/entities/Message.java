package ru.anxidy.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue
    private long id;

    @Column(length = 500)
    private String messageBody;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Account sender;

    @ManyToOne (optional = false, fetch = FetchType.EAGER)
    private Chat chat;

    public Message() {
    }

    public Message(String messageBody, Date time, Account sender, Chat chat) {
        this.messageBody = messageBody;
        this.time = time;
        this.sender = sender;
        this.chat = chat;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}

