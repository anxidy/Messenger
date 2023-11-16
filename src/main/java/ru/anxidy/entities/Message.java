package ru.anxidy.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue
    private long id;

    @Column(length = 511)
    private String messageBody;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Account sender;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Account receiver;

    public Message() {
    }

    public Message(String messageBody, Date time, Account sender, Account receiver) {
        this.messageBody = messageBody;
        this.time = time;
        this.sender = sender;
        this.receiver = receiver;
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

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }
}

