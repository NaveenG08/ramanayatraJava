package Users;

import DatabaseProcessor.Constants.EntityConstants;
import DatabaseProcessor.Constants.QueryConstants;
import DatabaseProcessor.Constants.RequestConstants;
import DatabaseProcessor.OperationRequest.DBRequest;
import DatabaseProcessor.Utils.Column;
import DatabaseProcessor.Utils.Criteria;
import DatabaseProcessor.Utils.Query;
import Entity.Session;
import ServletProcessor.API;
import Tables.UserTable;
import Util.Constants;
import com.mysql.cj.jdbc.result.ResultSetImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class LoginAPI extends API
{
    @Override
    public Object handleProcess(HttpServletRequest req, HttpServletResponse resp, Map requestMap) throws Exception
    {
        HttpSession ses = req.getSession(true);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        Session session = new Session((int) requestMap.get("UserID"),  (int) requestMap.get("isAdmin") == 1, ses.getId(), ft.format(new Date(ses.getCreationTime())).toString(), ft.format(new Date(ses.getCreationTime()+86400000L)).toString(), req.getRemoteAddr());
        try
        {
            DBRequest.operate(EntityConstants.SESSION, RequestConstants.WRITE, session, requestMap);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return session.toJsonObject();
    }

    public static void validateUserCredentials(Map requestMap) throws Exception
    {
        String username = (String) requestMap.get("username");
        String password = (String) requestMap.get("password");

        Query query = new Query(UserTable.TableName);
        query.addSelectedColumn(new Column(UserTable.TableName, UserTable.id));
        query.addSelectedColumn(new Column(UserTable.TableName, UserTable.isAdmin));
        Criteria criteria = new Criteria(new Column(UserTable.TableName, UserTable.username), username, QueryConstants.EQUAL);
        criteria.and(new Criteria(new Column(UserTable.TableName, UserTable.password), password, QueryConstants.EQUAL));
        criteria.and(new Criteria(new Column(UserTable.TableName, UserTable.isActive), Constants.ACTIVE, QueryConstants.EQUAL));
        query.setCriteria(criteria);
        ResultSetImpl result = (ResultSetImpl) DBRequest.operate(EntityConstants.USER, RequestConstants.READ, query, requestMap);
        result.next();
        if (result.getRows().size() == 0)
        {
            throw new Exception("Invalid username and Password");
        }
        requestMap.put("UserID",result.getInt(UserTable.id));
        requestMap.put("isAdmin", result.getInt(UserTable.isAdmin));
    }

}
