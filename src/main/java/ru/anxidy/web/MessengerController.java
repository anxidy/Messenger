package ru.anxidy.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.anxidy.entities.Account;

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
            int accountId = (int) session.getAttribute("accountId");
            List<Account> dialogues = messenger.getDialogues(accountId);

            model.addAttribute("dialogues", dialogues);

            return "messenger";
        } catch (Exception e) {
            return "mainPage";
        }
    }
}