package com.agro.wallet.utils;

import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenUtil {

    private static final Gson gson = new Gson();

    public String generateJWT(Object obj) {

        return Jwts.builder().setSubject(gson.toJson(obj))
            .setExpiration(new Date(System.currentTimeMillis() + TokenUtils.EXPIRATIONTIME))
            .signWith(SignatureAlgorithm.HS512, TokenUtils.SECRET).compact();

    }

}
