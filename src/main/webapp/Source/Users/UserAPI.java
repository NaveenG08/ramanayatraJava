package Users;

import DatabaseProcessor.Constants.QueryConstants;
import DatabaseProcessor.Constants.RequestConstants;
import DatabaseProcessor.OperationRequest.DBRequest;
import DatabaseProcessor.Utils.Column;
import DatabaseProcessor.Utils.Criteria;
import DatabaseProcessor.Utils.Query;
import DatabaseProcessor.Constants.EntityConstants;
import Entity.User;
import ServletProcessor.API;
import Tables.UserTable;
import com.mysql.cj.jdbc.result.ResultSetImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.regex.Pattern;

public class UserAPI extends API
{
    @Override
    public Object handleProcess(HttpServletRequest req, HttpServletResponse resp, Map requestMap) throws Exception
    {
        String name = (String) requestMap.get("name");
        String username = (String) requestMap.get("username");
        String password = (String) requestMap.get("password");
        User user = new User(name, username, password);
        try
        {
            DBRequest.operate(EntityConstants.USER, RequestConstants.WRITE, user, requestMap);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return UserUtil.getUsersList(requestMap);
    }
    public void validatePassword(Map requestMap) throws Exception
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
    public void validateUsername(Map requestMap) throws Exception
    {
        String username = (String) requestMap.get("username");
        Query query = new Query(UserTable.TableName);
        query.setCountColumn(new Column(UserTable.TableName, UserTable.id));
        query.setCriteria(new Criteria(new Column(UserTable.TableName, UserTable.username), username, QueryConstants.EQUAL));
        ResultSetImpl result = (ResultSetImpl) DBRequest.operate(EntityConstants.USER, RequestConstants.READ, query, requestMap);
        result.next();
        if (result.getInt(1) != 0)
        {
            throw new Exception("Username already exists. Choose another username");
        }
    }
}
