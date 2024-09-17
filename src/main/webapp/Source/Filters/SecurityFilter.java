package Filters;

import ServletProcessor.RequestProcessMapping;
import com.google.gson.JsonObject;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;

public class SecurityFilter implements Filter
{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        String requestURI = ((HttpServletRequest)servletRequest).getRequestURI();
        try
        {

            ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin","*");
            ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Headers","*");

            if (!RequestProcessMapping.RequestProcessMap.containsKey(requestURI))
            {
                throw new Exception("Invalid Request");
            }
                filterChain.doFilter(servletRequest,servletResponse);
        }
        catch (Exception e)
        {
            JsonObject json = new JsonObject();
            json.addProperty("status", 400);
            json.addProperty("message", e.getMessage());

            PrintWriter pwriter = servletResponse.getWriter();
            pwriter.println(json);
            pwriter.close();
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
