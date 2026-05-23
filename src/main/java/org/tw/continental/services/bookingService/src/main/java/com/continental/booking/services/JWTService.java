package com.continental.booking.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import com.continental.booking.modles.User;

import javax.crypto.SecretKey;

@Service
public class JWTService {
  private static final String secret = "ContinentalIsAVeryLargeHotelChainInAcquireAndItIsALargeGame";

  public String generateToken(User user) {
    return Jwts.builder()
        .subject(user.getUsername())
        .signWith(getSignKey())
        .compact();
  }

  private SecretKey getSignKey() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
  }

  public String extractUsername(String token) {
    Claims claims = Jwts
        .parser()
        .verifyWith(getSignKey())
        .build().
        parseSignedClaims(token).
        getPayload();

		return claims.getSubject();
  }

  public boolean isValidToken(String token, String username) {
    return username.equals(extractUsername(token));
  }
}
