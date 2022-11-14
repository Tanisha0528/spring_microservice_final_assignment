package com.shop.management.service.shopManagementService.configuration;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration

public class JwtFilter extends GenericFilterBean {


    private TokenConsumer tokenConsumer;

    @Autowired
    public JwtFilter(TokenConsumer tokenConsumer) {
        this.tokenConsumer = tokenConsumer;
    }
    @GetMapping("/get-token/{token}")
    public String getIdFromToken(@PathVariable("token") String token){
        return tokenConsumer.getIdFromToken(token);
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) res;

        String token = httpServletRequest.getHeader("Authorization");
        if("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod()))
        {
            httpServletResponse.sendError(HttpServletResponse.SC_OK,"success");
            return;
        }
        if(allowRequestWithoutToken(httpServletRequest))
        {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(req,res);
                      }
        else
        {
            ObjectId user_id = new ObjectId(tokenConsumer.getIdFromToken(token));
            httpServletRequest.setAttribute("user_id",user_id);
            filterChain.doFilter(req,res);
        }
    }
    public boolean allowRequestWithoutToken(HttpServletRequest request)
    {
        System.out.println(request.getRequestURI());
        if(request.getRequestURI().contains("/add-category")||
            request.getRequestURI().contains("/delete-category")||
            request.getRequestURI().contains("/update-category")||
            request.getRequestURI().contains("/add-product")||
            request.getRequestURI().contains("/product-with-category")||
            request.getRequestURI().contains("/update-product")||
            request.getRequestURI().contains("/delete-product")



        )
            return false;

        return true;
    }
}
