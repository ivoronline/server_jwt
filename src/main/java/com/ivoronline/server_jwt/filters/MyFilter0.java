package com.ivoronline.server_jwt.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Order(0)
@Component
@Configuration
public class MyFilter0 extends OncePerRequestFilter {

  //==================================================================
  // DO FILTER
  //==================================================================
  @Override
  public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
    throws IOException, ServletException  {

    try {
      System.out.println("MyFilter0");
      chain.doFilter(request, response);
    }
    catch (Exception exception) {
      System.out.println(response.getWriter());
      exception.printStackTrace();
      response.getWriter().write(exception.getMessage());
    }

  }

  //===================================================================
  // FILTER REGISTRATION BEAN
  //===================================================================
  @Bean
  public FilterRegistrationBean<MyFilter0> regFilter0(){

    //CREATE REGISTRATION BEAN
    FilterRegistrationBean<MyFilter0> registrationBean = new FilterRegistrationBean<>();
                                      registrationBean.setFilter(new MyFilter0());
                                      registrationBean.addUrlPatterns("/Hello");
                                      registrationBean.setOrder(0);

    //RETURN REGISTRATION BEAN
    return registrationBean;

  }

}

