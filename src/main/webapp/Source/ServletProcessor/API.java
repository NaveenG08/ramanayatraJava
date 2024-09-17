package ServletProcessor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;
import java.util.Map;

public class API
{
    public Object handleProcess(HttpServletRequest req, HttpServletResponse resp, Map requestMap) throws Exception {
        return null;
    }
    public void handleValidation(Map requestMap) throws Exception
    {
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method : methods)
        {
            if (method.getName().contains("validate"))
            {
                method.invoke(this.getClass().newInstance() , requestMap);
            }
        }
    }
}
