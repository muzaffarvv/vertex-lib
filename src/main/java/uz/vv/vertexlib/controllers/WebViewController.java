package uz.vv.vertexlib.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import uz.vv.vertexlib.dtos.requests.LoginRequest;
import uz.vv.vertexlib.dtos.requests.RegisterRequest;
import uz.vv.vertexlib.dtos.responses.AuthResponse;
import uz.vv.vertexlib.services.AuthService;

@Controller
@RequiredArgsConstructor
public class WebViewController {

    private final AuthService authService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginRequest", new LoginRequest("", ""));
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginProcess(@Valid @ModelAttribute("loginRequest") LoginRequest loginRequest,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "auth/login";
        }
        try {
            AuthResponse response = authService.login(loginRequest);
            model.addAttribute("token", response.token());
            return "auth/login-success";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "auth/login";
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest("", "", ""));
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerProcess(@Valid @ModelAttribute("registerRequest") RegisterRequest registerRequest,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }
        try {
            AuthResponse response = authService.register(registerRequest);
            model.addAttribute("token", response.token());
            return "auth/login-success";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "auth/register";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/movies")
    public String movies() {
        return "movies";
    }

    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @GetMapping("/genres")
    public String genres() {
        return "genres";
    }

    @GetMapping("/loans")
    public String loans() {
        return "loans";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }
}