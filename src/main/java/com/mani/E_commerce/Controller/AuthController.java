package com.mani.E_commerce.Controller;


import com.mani.E_commerce.dto.AuthenticationRequest;
import com.mani.E_commerce.entity.User;
import com.mani.E_commerce.repository.UserRepository.UserRepository;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mani.E_commerce.utils.jwtUtil;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;

   private final jwtUtil jwtUtil;
   public static final String TOKEN_PREFIX="Bearer";
   public static final String HEADER_STRING="Authorization";

    @PostMapping("/authenticate")
public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException, JSONException {

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),authenticationRequest.getPassword()))

        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password");

        }
        final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
        Optional<User>optionalUser=userRepository.findFirstByEmail(userDetails.getUsername());
      final String jwt=jwtUtil.generateToken(userDetails.getUsername());
   if(optionalUser.isPresent(){
       response.getWriter().write(new JSONObject()
               .put("userID",optionalUser.get().getId())
               .put("role",optionalUser.get().getRole())
               .toString()
               );
       response.addHeader(HEADER_STRING,TOKEN_PREFIX + jwt);
   }

    }

}
