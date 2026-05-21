package org.tw.continental.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoggingFilter implements Filter {
  private final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);


  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    logger.info("<- {}: {}", httpRequest.getMethod(), httpRequest.getRequestURI());
    try{
      chain.doFilter(request, response);
    }finally {
      HttpServletResponse httpResponse = (HttpServletResponse) response;
      logger.info("-> {}: {} [{}]", httpRequest.getMethod(), httpRequest.getRequestURI(),httpResponse.getStatus() );
    }

  }
}
