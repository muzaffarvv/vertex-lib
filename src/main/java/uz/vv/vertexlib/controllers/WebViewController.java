package uz.vv.vertexlib.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebViewController {

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }

    @GetMapping("/books")
    public String booksList() {
        return "books/list";
    }
    
    // Redirect root to dashboard (or login)
    @GetMapping("/")
    public String index() {
        return "redirect:/dashboard";
    }
}
