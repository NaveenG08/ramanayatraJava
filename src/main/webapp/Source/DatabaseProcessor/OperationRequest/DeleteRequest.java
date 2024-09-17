package DatabaseProcessor.OperationRequest;

import DatabaseProcessor.Constants.QueryConstants;
import DatabaseProcessor.Utils.Criteria;
import DatabaseProcessor.Utils.Query;

import java.util.Map;

public class DeleteRequest extends DBRequest
{

    @Override
    public Object processDB(int entity, Object object, Map requestMap) throws Exception
    {
        String query = getQueryString((Query) object);
        DBAction.deleteDatainDB(query);
        return null;
    }
    public String getQueryString(Query query)
    {
        StringBuilder queryStr = new StringBuilder();
        queryStr.append(getDeleteStatement(query));
        queryStr.append(getCriteria(query.getCriteria()));
        return queryStr.toString();
    }
    public String getDeleteStatement(Query query)
    {
        StringBuilder queryStr = new StringBuilder();
        queryStr.append(QueryConstants.DELETE);
        queryStr.append(" ");
        queryStr.append(QueryConstants.FROM);
        queryStr.append(" ");
        queryStr.append(query.getTable());
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
