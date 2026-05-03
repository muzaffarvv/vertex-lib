package uz.vv.vertexlib.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.vv.vertexlib.entities.User;
import uz.vv.vertexlib.exceptions.BadRequestException;

@Component
public class SecurityUtils {

    public static String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BadRequestException("Foydalanuvchi tizimga kirmagan");
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof User user) {
            return user.getId();
        }

        throw new BadRequestException("Foydalanuvchi identifikatsiyasi topilmadi");
    }
}