package app.Security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        String jwt = generateToken(authentication);
        Map<String, String> response = new HashMap<>();
        response.put("token", jwt);
        return response;
    }

    private String generateToken(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();

        // Înlocuiește cu o cheie mai puternică
        String SECRET_KEY = "secret123";
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Valabil 10 ore
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
@Getter
@Setter
class AuthRequest {
    private String username;
    private String password;
}
