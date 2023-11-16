package ru.anxidy.web;

import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.anxidy.dao.AccountsDAO;
import ru.anxidy.entities.Account;

@Controller
public class LoginController {
    private final AccountsDAO accountsDAO;

    @Autowired
    public LoginController(AccountsDAO accountsDAO) {
        this.accountsDAO = accountsDAO;
    }

    @PostMapping(path = "/login")
    protected String doLogin(HttpSession session,
                             @RequestParam String login,
                             @RequestParam String password,
                             ModelMap map) {
        try {
            Account found = accountsDAO.findByLoginAndPassword(login, password);
            session.setAttribute("accountId", found.getId());

            return "redirect:/messenger";
        } catch (NoResultException notFound){
            map.addAttribute("login", "login");
            return "mainPage";
        }
    }
}