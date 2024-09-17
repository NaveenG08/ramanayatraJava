package DatabaseProcessor.OperationRequest;

import DatabaseProcessor.Constants.QueryConstants;
import DatabaseProcessor.Utils.Column;
import DatabaseProcessor.Utils.Criteria;
import DatabaseProcessor.Utils.Join;
import DatabaseProcessor.Utils.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class    ReadRequest extends DBRequest
{
    public Object processDB(int entity, Object query, Map requestMap) throws SQLException, ClassNotFoundException
    {
        String queryString = getQueryString(((Query)query).getTable(), ((Query)query).getSelectColumns(), ((Query)query).getCountColumn(), ((Query)query).getCriteria(), ((Query)query).getJoin(), ((Query)query).getOrderbyColumns(), ((Query)query).getOrderby(), ((Query)query).getLimit(), ((Query)query).getOffset());
        return DBAction.getDataFromDB(queryString);
    }
    private String getQueryString(String tableName, List<Column> selectColumns, Column countColumn, Criteria criteria, Join[] joins, List<Column> orderByColumns, QueryConstants orderby, Integer limit, Integer offset)
    {
        StringBuilder query = new StringBuilder();
        query.append(getSelectStatement(tableName, selectColumns, countColumn));
        query.append(getJoinString(joins));
        query.append(getCriteriaString(criteria));
        query.append(getOrderByString(orderByColumns, orderby));
        query.append(getLimitString(limit));
        query.append(getOffsetString(offset));
        return query.toString();
    }

    private String getSelectStatement(String tableName, List<Column> selectColumns, Column countColumn)
    {
        StringBuilder query = new StringBuilder("");
        query.append(QueryConstants.SELECT.value);
        query.append(" ");
        if (countColumn != null)
        {
            query.append(QueryConstants.COUNT.value);
            query.append("( ");
            query.append(countColumn.getColumnString());
            query.append(" ) ");
        }
        else if (selectColumns == null)
        {
            query.append("* ");
        }
        else
        {
           query.append(Column.getColumnListString(selectColumns));
        }
        query.append(QueryConstants.FROM.value);
        query.append(" ");
        query.append(tableName);
        query.append(" ");
        return query.toString();
    }

    private String getJoinString(Join[] joins)
    {
        StringBuilder joinString = new StringBuilder("");
        if (joins != null)
        {
            for( Join join : joins)
            {
                joinString.append(join.getJoinStatement());
                joinString.append(" ");
            }
            joinString.append(" ");
        }
        return joinString.toString();
    }

    private String getCriteriaString(Criteria criteria)
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

    private String getOrderByString(List<Column> orderBy, QueryConstants order)
    {
        StringBuilder orderByString = new StringBuilder("");
        if (orderBy != null)
        {
            orderByString.append(QueryConstants.ORDER_BY.value);
            orderByString.append(" ");
            orderByString.append(Column.getColumnListString(orderBy));
            orderByString.append(" ");
            orderByString.append(order.value);
            orderByString.append(" ");
        }
        return orderByString.toString();
    }

    private String getLimitString(Integer limit)
    {
        StringBuilder limitString = new StringBuilder("");
        if (limit != null)
        {
            limitString.append(QueryConstants.LIMIT.value);
            limitString.append(" ");
            limitString.append(limit);
            limitString.append(" ");
        }
        return limitString.toString();
    }

    private String getOffsetString(Integer offset)
    {
        StringBuilder offsetString = new StringBuilder("");
        if (offset != null)
        {
            offsetString.append(QueryConstants.OFFSET.value);
            offsetString.append(" ");
            offsetString.append(offset);
            offsetString.append(" ");
        }
        return offsetString.toString();
    }

}
