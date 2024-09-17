package DatabaseProcessor.OperationRequest;

import DatabaseProcessor.Constants.QueryConstants;
import Entity.Entity;
import Tables.Table;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

import static DatabaseProcessor.Constants.EntityConstants.ENTITY_TABLE_MAP;

public class WriteRequest extends DBRequest
{
    public Object processDB(int entity, Object entityValue, Map requestMap) throws Exception
    {
        String insertQuery = getQueryString((Entity) entityValue,entity, requestMap);
        return DBAction.putDataInDB(insertQuery);
    }
    private String getQueryString(Entity entityValue, int entity, Map requestMap) throws InstantiationException, IllegalAccessException, NoSuchFieldException, InvocationTargetException
    {
        StringBuilder query = new StringBuilder();
        query.append(QueryConstants.INSERT_INTO.value);
        query.append(" ");
        Table classname = (Table) ENTITY_TABLE_MAP.get(entity).newInstance();
        query.append(classname.getColumnsForInsertQuery());
        query.append(QueryConstants.VALUES.value);
        query.append(" ");
        query.append(entityValue.getValuesForInsertQuery(requestMap));
        query.append(";");
        return query.toString();
    }
}
