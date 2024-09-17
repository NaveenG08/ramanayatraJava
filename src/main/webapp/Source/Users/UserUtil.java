package Users;

import DatabaseProcessor.Constants.EntityConstants;
import DatabaseProcessor.Constants.QueryConstants;
import DatabaseProcessor.Constants.RequestConstants;
import DatabaseProcessor.OperationRequest.DBRequest;
import DatabaseProcessor.Utils.Column;
import DatabaseProcessor.Utils.Criteria;
import DatabaseProcessor.Utils.Query;
import Entity.Session;
import Entity.User;
import Tables.SessionTable;
import Tables.UserTable;
import Util.Constants;
import Util.UtilMethods;
import com.google.gson.JsonArray;
import com.mysql.cj.jdbc.result.ResultSetImpl;

import java.sql.ResultSet;
import java.util.Map;
import java.util.regex.Pattern;

public class UserUtil
{
    public static Object signout(Map requestMap) throws Exception
    {
        String id = ((Session) requestMap.get(Constants.SESSION)).getSessionKey();
        Query query = new Query(SessionTable.TableName);
        query.setCriteria(new Criteria(new Column(SessionTable.TableName, SessionTable.sessionKey), id, QueryConstants.EQUAL));
        DBRequest.operate(EntityConstants.SESSION, RequestConstants.DELETE, query, requestMap);
        return null;
    }

    public static Object changeUserRole(Map requestMap) throws Exception
    {
        Query query = new Query(UserTable.TableName);
        query.setCriteria(new Criteria(new Column(UserTable.TableName, UserTable.id), requestMap.get("user_id"), QueryConstants.EQUAL));
        ResultSetImpl result = (ResultSetImpl) DBRequest.operate(EntityConstants.USER, RequestConstants.READ, query, requestMap);
        result.next();
        if (result == null)
        {
            throw new Exception("Invalid userid");
        }
        User user = new User(result);
        int isAdmin = (user.isAdmin() == 0) ? 1 : 0;
        user.setAdmin(isAdmin);
        DBRequest.operate(EntityConstants.USER, RequestConstants.UPDATE, user, requestMap);
        return user.toJsonObject();
    }

    public static Object changeUserStatus(Map requestMap) throws Exception
    {
        Query query = new Query(UserTable.TableName);
        query.setCriteria(new Criteria(new Column(UserTable.TableName, UserTable.id), requestMap.get("user_id"), QueryConstants.EQUAL));
        ResultSetImpl result = (ResultSetImpl) DBRequest.operate(EntityConstants.USER, RequestConstants.READ, query, requestMap);
        result.next();
        if (result == null)
        {
            throw new Exception("Invalid userid");
        }
        User user = new User(result);
        int isActive = (user.isActive() == 0) ? 1 : 0;
        user.setActive(isActive);
        DBRequest.operate(EntityConstants.USER, RequestConstants.UPDATE, user, requestMap);
        return user.toJsonObject();
    }
    public static Object changeUserPassword(Map requestMap) throws Exception
    {
        validatePassword(requestMap);
        Query query = new Query(UserTable.TableName);
        query.setCriteria(new Criteria(new Column(UserTable.TableName, UserTable.id), requestMap.get("user_id"), QueryConstants.EQUAL));
        ResultSetImpl result = (ResultSetImpl) DBRequest.operate(EntityConstants.USER, RequestConstants.READ, query, requestMap);
        result.next();
        if (result == null)
        {
            throw new Exception("Invalid userid");
        }
        User user = new User(result);
        user.setPassword((String) requestMap.get("password"));
        DBRequest.operate(EntityConstants.USER, RequestConstants.UPDATE, user, requestMap);
        return user.toJsonObject();
    }

    public static void validatePassword(Map requestMap) throws Exception
    {
        String password = (String) requestMap.get("password");
        Pattern smallCase = Pattern.compile("[a-z]+");
        Pattern capticalCase = Pattern.compile("[A-Z]+");
        Pattern digits = Pattern.compile("[0-9]+");
        if (!smallCase.asPredicate().test(password) || !capticalCase.asPredicate().test(password) || !digits.asPredicate().test(password))
        {
            throw new Exception("A password must contain a small case, capital case and a digit to provide a secure auth");
        }
    }

    public static Object getUsersList(Map requestMap) throws Exception
    {
        Query query = new Query(UserTable.TableName);
        ResultSetImpl result = (ResultSetImpl) DBRequest.operate(EntityConstants.USER, RequestConstants.READ, query, requestMap);
        return UtilMethods.convertResultToJson(result);
    }
}
