package ru.anxidy.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.anxidy.entities.Account;
import ru.anxidy.entities.Chat;
import ru.anxidy.entities.Message;

import java.util.Date;
import java.util.List;

@Controller
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping(path = "/chat")
    public String chat(@RequestParam int ch,
                       ModelMap model) {
        try {
            Chat chat = chatService.findChat(ch);
            List<Message> messages = chatService.getChatMessages(ch);

            model.addAttribute("messages", messages);
            model.addAttribute("chat", chat);

            return "chat";
        } catch (Exception e) {
            return "mainPage";
        }
    }

    @PostMapping(path = "/chat")
    public String postMessage(HttpSession session,
                              @RequestParam String messageBody,
                              @RequestParam int ch,
                              ModelMap model
    ) {
        try {
            long accountId = (long) session.getAttribute("accountId");
            Account sender = chatService.getSender(accountId);
            Chat chat = chatService.findChat(ch);
            Message message = new Message(messageBody, new Date(), sender, chat);

            chatService.postMessage(message);

            List<Message> messages = chatService.getChatMessages(ch);

            model.addAttribute("messages", messages);
            model.addAttribute("chat", chat);

            return "chat";
        } catch (Exception e) {
            return "mainPage";
        }
    }
}
