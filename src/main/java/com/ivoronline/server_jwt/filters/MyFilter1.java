package com.ivoronline.server_jwt.filters;

import com.ivoronline.server_jwt.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyFilter1 extends OncePerRequestFilter {

  //PROPERTIES
  @Autowired JWTUtil jwtUtil;

  //==================================================================================
  // DO FILTER
  //==================================================================================
  @Override
  public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
    throws IOException, ServletException {

    //GET AUTHORIZATION HEADER
    String authorization = request.getHeader("authorization");
    String jwt           = jwtUtil.getJWTFromAuthorizationHeader(authorization);

    //CREATE AUTHENTICATION OBJECT
    Authentication authentication = jwtUtil.createAuthenticationObjectFromJWT(jwt);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    //CALL NEXT FILTER
    chain.doFilter(request, response);

  }

  //===================================================================
  // FILTER REGISTRATION BEAN
  //===================================================================
  @Bean
  public FilterRegistrationBean<MyFilter1> regFilter1(MyFilter1 myfilter){

    //CREATE REGISTRATION BEAN
    FilterRegistrationBean<MyFilter1> registrationBean = new FilterRegistrationBean<>();
                                      registrationBean.setFilter(myfilter);
                                      registrationBean.addUrlPatterns("/Hello");
                                      registrationBean.setOrder(1);

    //RETURN REGISTRATION BEAN
    return registrationBean;

  }

}
