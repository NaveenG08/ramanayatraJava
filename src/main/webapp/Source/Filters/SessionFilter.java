package Filters;

import Util.FilterUtil;
import com.google.gson.JsonObject;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.PrintWriter;

public class SessionFilter implements Filter
{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException
    {

        try
        {
            if (FilterUtil.isSessionValidationRequired((HttpServletRequest) request))
            {
                FilterUtil.validateSession((HttpServletRequest) request);
            }
            filterChain.doFilter(request,response);
        }
        catch (Exception e)
        {
            JsonObject json = new JsonObject();
            json.addProperty("status", 400);
            json.addProperty("message", e.getMessage());

            PrintWriter pwriter = response.getWriter();
            pwriter.println(json);
            pwriter.close();
        }

    }

    @Override
    public void destroy()
    {
        Filter.super.destroy();
    }
}
