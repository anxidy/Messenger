package ru.anxidy.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.anxidy.entities.Account;
import ru.anxidy.entities.Chat;
import ru.anxidy.entities.Message;

import java.net.URL;
import java.util.Date;

@Controller
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping(path = "/chat_")
    public String chat(URL url) {
        try {
            StringBuilder str = new StringBuilder(url.getPath());
            int chatId = Integer.parseInt(str.substring(str.indexOf("_")));
            chatService.getChatMessages(chatId);

            return "chat";
        } catch (Exception e) {
            return "mainPage";
        }
    }

    @PostMapping(path = "/post_message")
    public String postMessage(HttpSession session,
                              @RequestParam String messageBody,
                              URL url) {
        try {
            StringBuilder str = new StringBuilder(url.getPath());
            int chatId = Integer.parseInt(str.substring(str.indexOf("_")));
            long accountId = (long) session.getAttribute("accountId");
            Account sender = chatService.getSender(accountId);
            Chat chat = chatService.findChat(chatId);
            Message message = new Message(messageBody, new Date(), sender, chat);

            chatService.postMessage(message);
            return "chat";
        } catch (Exception e) {
            return "mainPage";
        }
    }
}
