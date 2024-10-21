package uz.pdp.testservice;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PreAuthorize("hasAnyAuthority('user','ROLE_user')")
    @GetMapping("/test")
    public String get(Authentication authentication) {

        System.out.println(authentication);
        return "test";
    }

    @GetMapping
    public String ip(HttpServletRequest request) {
        return request.getRemoteAddr();
    }
}
