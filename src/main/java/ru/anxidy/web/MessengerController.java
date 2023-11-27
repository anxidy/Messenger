package ru.anxidy.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.anxidy.entities.Chat;

import java.util.List;

@Controller
public class MessengerController {

    private final MessengerService messenger;

    public MessengerController(MessengerService messenger) {
        this.messenger = messenger;
    }

    @GetMapping(path = "/messenger")
    public String messenger(HttpSession session, ModelMap model) {
        try {
            long accountId = (long) session.getAttribute("accountId");
            List<Chat> chats = messenger.getChats(accountId);

            model.addAttribute("chats", chats);

            return "messenger";
        } catch (Exception e) {
            return "mainPage";
        }
    }
}