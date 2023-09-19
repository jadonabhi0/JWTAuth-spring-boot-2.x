package com.abhi.auth.config;/*
    @author jadon
*/

import com.abhi.auth.service.CustomUserDetailService;
import com.abhi.auth.utils.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // getting the authorization header
        String authorizedHeader = request.getHeader("Authorization");
        logger.info("Header : {}", authorizedHeader);

        String userName = null;
        String jwtToken = null;

        // verifying the authorization header
        if(authorizedHeader != null && authorizedHeader.startsWith("Bearer ")){

            // getting the jwt-token
            jwtToken = authorizedHeader.substring(7);
            try {
                // getting the username from token
                userName = jwtUtils.extractUsername(jwtToken);
            } catch (IllegalArgumentException e) {
                logger.info("Illegal Argument while fetching the username !!");
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                logger.info("Given jwt token is expired !!");
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                logger.info("Some changed has done in token !! Invalid Token");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

            }

        }else{
            logger.info("Invalid Header Value !! ");
        }



        // checking the security context holder
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails = this.customUserDetailService.loadUserByUsername(userName);

            if (!jwtUtils.validateToken(jwtToken, userDetails)){
                logger.info("Not a valid token ");
                return;
            }

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        }else {
            logger.info("Validation fails !!");
        }


        // if everything is alright then allow access
        filterChain.doFilter(request, response);
    }
}
