package Util;

import DatabaseProcessor.Constants.EntityConstants;
import DatabaseProcessor.Constants.QueryConstants;
import DatabaseProcessor.Constants.RequestConstants;
import DatabaseProcessor.OperationRequest.DBRequest;
import DatabaseProcessor.Utils.Column;
import DatabaseProcessor.Utils.Criteria;
import DatabaseProcessor.Utils.Query;
import Entity.Session;
import Tables.SessionTable;
import com.mysql.cj.jdbc.result.ResultSetImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FilterUtil
{
    public static boolean isSessionValidationRequired(HttpServletRequest request)
    {
        return !request.getRequestURI().contains("/signin") && !request.getMethod().equalsIgnoreCase("OPTIONS");
    }
    public static void validateSession(HttpServletRequest request) throws Exception
    {
        String sessionId = request.getHeader("JSESSIONID");
        if (sessionId == null || sessionId.isBlank())
        {
            throw new Exception("Invalid Session Passed");
        }

        Query query = new Query(SessionTable.TableName);
        query.setCriteria(new Criteria(new Column(SessionTable.TableName, SessionTable.sessionKey), sessionId, QueryConstants.EQUAL));
        ResultSetImpl result = (ResultSetImpl) DBRequest.operate(EntityConstants.SESSION, RequestConstants.READ, query,null);

        if (!result.hasRows())
        {
            throw new Exception("Invalid Session Passed");
        }
        result.next();
        String endtime = result.getString(SessionTable.endTime);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        if (ft.parse(endtime).getTime() - System.currentTimeMillis() < 0)
        {
            throw new Exception("Invalid Session Passed");
        }

        Session session = new Session(result.getInt(SessionTable.id), result.getInt(SessionTable.userId), result.getBoolean(SessionTable.isAdmin), result.getString(SessionTable.sessionKey), result.getString(SessionTable.startTime), result.getString(SessionTable.endTime), result.getString(SessionTable.ip));
        request.setAttribute(Constants.SESSION, session);
    }
}
