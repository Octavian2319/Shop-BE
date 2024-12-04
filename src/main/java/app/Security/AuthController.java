package app.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {
        try {
            String username = request.get("username");
            String password = request.get("password");

            System.out.println("Attempting authentication for user: " + username);

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            System.out.println("Authentication successful for user: " + username);

            String secretKey = "CheieSecretaMaiMareDe256DeBitiPentruAIndepliniMinimulDeLaSpringSecurity";
            String token = Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                    .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                    .compact();

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return response;

        } catch (AuthenticationException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid username or password");
        }

    }
}
