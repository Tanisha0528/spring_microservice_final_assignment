package com.authentication.service.authenticationService.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import org.bson.types.ObjectId;

@Service
public class TokenService {
    public static final String token_secret = "oiotiuhfunhsabxsxf2332xfsax";

    public String createToken(ObjectId id){
        try {
            // Random generating String using with Token Secret.
            // We are using HMAC256 Algorithms to generate the token
            Algorithm algorithm = Algorithm.HMAC256(token_secret);

            // We are using claims of id and create Date using Date() object
            String token =  JWT.
                                create().
                                withClaim("id",id.toString()).
                                withClaim("createdAt", new Date()).
                                sign(algorithm);
            return token;

        } catch (UnsupportedEncodingException | JWTCreationException e){
            e.printStackTrace();
        }


        return null;
    }

    // Decoding the created token
    public String getIdFromToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(token_secret);
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();

            DecodedJWT decodedJWT = jwtVerifier.verify(token);

            return decodedJWT.getClaim("id").asString();

        } catch (UnsupportedEncodingException | JWTCreationException e){
            e.printStackTrace();
        }
        return null;

    }

    public boolean isTokenValid(String token){
        String id = this.getIdFromToken(token);
        return  id != null;

    }
}
