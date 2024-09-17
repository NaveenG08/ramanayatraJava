package DatabaseProcessor.OperationRequest;

import DatabaseProcessor.Constants.QueryConstants;
import DatabaseProcessor.Utils.Criteria;
import DatabaseProcessor.Utils.Query;
import Entity.Entity;

import java.util.Map;

import static DatabaseProcessor.Constants.EntityConstants.ENTITY_TABLE_MAP;

public class UpdateRequest extends DBRequest
{

    @Override
    public Object processDB(int entity, Object object, Map requestMap) throws Exception
    {
        String query = getQueryString(entity,(Entity) object, requestMap);
        return DBAction.putDataInDB(query);
    }

    public String getQueryString(int entity, Entity ent, Map requestMap) throws Exception
    {
        StringBuilder queryStr = new StringBuilder();
        Class clazz = ENTITY_TABLE_MAP.get(entity);
        queryStr.append(getUpdateStatement(String.valueOf(clazz.getDeclaredField("TableName").get(new Object()))));
        queryStr.append(ent.getSetQueryForUpdate(requestMap));
        Criteria criteria = new Criteria(clazz.getDeclaredField("id").get(new Object()), ent.getPrimaryKey(), QueryConstants.EQUAL);
        queryStr.append(getCriteria(criteria));
        return queryStr.toString();
    }
    public String getUpdateStatement(String tableName)
    {
        StringBuilder queryStr = new StringBuilder();
        queryStr.append(QueryConstants.UPDATE);
        queryStr.append(" ");
        queryStr.append(tableName);
        queryStr.append(" ");
        queryStr.append(QueryConstants.SET);
        queryStr.append(" ");
        return queryStr.toString();
    }

    public String getCriteria(Criteria criteria)
    {
        StringBuilder criteriaString = new StringBuilder("");
        if (criteria != null)
        {
            criteriaString.append(QueryConstants.WHERE.value);
            criteriaString.append(" ");
            criteriaString.append(criteria.getCriteriaString());
            criteriaString.append(" ");
        }
        return  criteriaString.toString();
    }
}
