package ca.hccis.coffee.security;

import org.springframework.stereotype.Component;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Set;

/**
 * Unsure if this is needed, keeping for now (as of S2)
 */

@Component("securityHelper")
public class SecurityHelper {

    private static final Set<String> ALLOWED_EMAILS = Set.of(
            "bj.maclean@gmail.com",
            "developer@gmail.com"
    );

    public boolean hasAllowedEmail(Jwt jwt) {
        String email = jwt.getClaimAsString("email");
        return email != null && email.endsWith("gmail.com");
        // return email != null && ALLOWED_EMAILS.contains(email);
    }
}