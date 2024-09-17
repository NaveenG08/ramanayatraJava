package ServletProcessor;

import Entity.Session;
import Util.Constants;
import Util.ParamHandler;
import Util.UtilMethods;
import com.google.gson.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 15
)
public class ServletAPI extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try
        {
            handleRequest(req, resp);
        }
        catch (Exception e)
        {
            JsonObject json = new JsonObject();
            json.addProperty("status", 400);
            json.addProperty("message", e.getMessage());

            PrintWriter pwriter = resp.getWriter();
            pwriter.println(json);
            pwriter.close();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try
        {
            handleRequest(req, resp);
        }
        catch (Exception e)
        {
            JsonObject json = new JsonObject();
            json.addProperty("status", 400);
            json.addProperty("message", e.getMessage());

            PrintWriter pwriter = resp.getWriter();
            pwriter.println(json);
            pwriter.close();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try
        {
            handleRequest(req, resp);
        }
        catch (Exception e)
        {
            JsonObject json = new JsonObject();
            json.addProperty("status", 400);
            json.addProperty("message", e.getMessage());

            PrintWriter pwriter = resp.getWriter();
            pwriter.println(json);
            pwriter.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try
        {
            handleRequest(req, resp);
        }
        catch (Exception e)
        {
            JsonObject json = new JsonObject();
            json.addProperty("status", 400);
            json.addProperty("message", e.getMessage());

            PrintWriter pwriter = resp.getWriter();
            pwriter.println(json);
            pwriter.close();
        }
    }
    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Object object = null;

        JsonObject requestProcess = (JsonObject) RequestProcessMapping.RequestProcessMap.get(req.getRequestURI());
        validateRequest(req, resp, requestProcess);

        if (requestProcess.has(Constants.API_CLASS_STR))
        {
            String processclass = requestProcess.get(Constants.API_CLASS_STR).getAsString();
            API classobj = (API) Class.forName(processclass).newInstance();
            Map requestMap = (Map) req.getAttribute("requestMap");
            classobj.handleValidation(requestMap);
            object = classobj.handleProcess(req, resp, requestMap);
        }
        else if (requestProcess.has(Constants.CLASS_STR))
        {
            String processclass = requestProcess.get(Constants.CLASS_STR).getAsString();
            String method = requestProcess.get(Constants.METHOD_STR).getAsString();

            Class classobj = Class.forName(processclass);
            Method objMethod = classobj.getMethod(method, Class.forName("java.util.Map"));
            Map requestMap = (Map) req.getAttribute("requestMap");

            object = objMethod.invoke(classobj.newInstance(), requestMap);
        }
        handleResponse(object, resp);
    }
    private void validateRequest(HttpServletRequest req,HttpServletResponse resp, JsonObject requestProcess) throws Exception
    {
        if (!requestProcess.get(Constants.REQUEST_METHOD_STR).getAsString().equalsIgnoreCase(req.getMethod()))
        {
            log("Request Method not supported for the Request.");
            throw new Exception("Invalid Request");
        }

        Session session = (Session) req.getAttribute(Constants.SESSION);
        if (requestProcess.has(Constants.PERMISSION_RESTRICTED) && !(requestProcess.get(Constants.PERMISSION_RESTRICTED).getAsBoolean() && session.isAdmin()))
        {
            throw new Exception("Permission Denied");
        }
        Map inputData = new HashMap<String,Object>();

        if (UtilMethods.isImport(req))
        {
            Part filePart = req.getPart("file");
            inputData.put("file", filePart);
        }
        else
        {
            JsonParser jsonParser = new JsonParser();
            StringBuilder sb = new StringBuilder();

            BufferedReader reader = req.getReader();
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }
            catch (IOException e)
            {

            }

            JsonObject inputJson = sb.isEmpty() ? new JsonObject() : (JsonObject) jsonParser.parse(sb.toString());
            Map paramMap = req.getParameterMap();


            JsonArray streamDataArray = (!requestProcess.get("request-data").isJsonNull()) ? jsonParser.parse(requestProcess.get("request-data").getAsString()).getAsJsonArray() : new JsonArray();

            for (JsonElement element : streamDataArray)
            {
                JsonObject object = element.getAsJsonObject();
                String name = object.get("name").getAsString();
                String from = object.get("from").getAsString();
                String regex = object.get("regex").getAsString();
                int maxLen = object.get("max-len").getAsInt();
                int minLen = object.get("min-len").getAsInt();
                boolean isMandatory = object.has("isMandatory") ? object.get("isMandatory").getAsBoolean() : true;
                boolean isHandlingRequired = object.has("isHandlingRequired") ? object.get("isHandlingRequired").getAsBoolean() : false;

                String value = "";
                if (from.equals("param"))
                {
                    if (paramMap.containsKey(name))
                    {
                        value = ((String[]) paramMap.get(name))[0];
                    }
                    else
                    {
                        if (!isMandatory)
                        {
                            continue;
                        }
                        throw new Exception("Invalid Data Provided In the Request");
                    }
                }
                else if (from.equals("stream"))
                {
                    if (inputJson.has(name))
                    {
                        value = inputJson.get(name).getAsString();
                    }
                    else
                    {
                        if (!isMandatory)
                        {
                            continue;
                        }
                        throw new Exception("Invalid Data Provided In the Request");
                    }
                }

                if (value.length() < minLen || value.length() > maxLen)
                {
                    throw new Exception("Invalid Data Provided In the Request");
                }

                Pattern regexPattern = Pattern.compile(regex);
                if (!regexPattern.matcher(value).matches())
                {
                    throw new Exception("Invalid Data Provided In the Request");
                }
                if (isHandlingRequired)
                {
                     value = ParamHandler.handleParam(name, value);
                }
                inputData.put(name,value);
            }
        }

        inputData.put(Constants.SESSION, session);
        inputData.put(Constants.REQUEST_STR, req);
        inputData.put(Constants.RESPONSE_STR, resp);
        req.setAttribute("requestMap",inputData);
    }

    private void handleResponse(Object obj, HttpServletResponse response) throws IOException
    {
        JsonObject json = new JsonObject();
        json.addProperty("status",200);
        json.addProperty("message","success");

        if (obj != null)
        {
            json.addProperty("Object", obj.toString());
        }

        PrintWriter pwriter = response.getWriter();
        pwriter.println(json);
        pwriter.close();
    }
}
