package com.PizzaBros.PizzaBroRESTBackend.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.authenticator.SavedRequest;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.util.StringUtils;



public class MySavedRequestAwareAuthenticationSuccessHandler 
extends SimpleUrlAuthenticationSuccessHandler {

  private RequestCache requestCache = new HttpSessionRequestCache();

  
  public void onAuthenticationSuccess (
    HttpServletRequest request,
    HttpServletResponse response, 
    Authentication authentication) 
    throws ServletException, IOException {

      org.springframework.security.web.savedrequest.SavedRequest savedRequest
        = requestCache.getRequest(request, response);

      if (savedRequest == null) {
          clearAuthenticationAttributes(request);
          return;
      }
      String targetUrlParam = getTargetUrlParameter();
      if (isAlwaysUseDefaultTargetUrl()
        || (targetUrlParam != null
        && StringUtils.hasText(request.getParameter(targetUrlParam)))) {
          requestCache.removeRequest(request, response);
          clearAuthenticationAttributes(request);
          return;
      }

      clearAuthenticationAttributes(request);
  }



public void setRequestCache(RequestCache requestCache) {
      this.requestCache = requestCache;
  }
}