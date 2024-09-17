package DatabaseProcessor.Utils;

import DatabaseProcessor.Constants.QueryConstants;

public class Join
{
    private QueryConstants joinType;
    private String joinTable;
    private Criteria joinCriteria;

    public Join(QueryConstants joinType, String joinTable)
    {
        this.joinType = joinType;
        this.joinTable = joinTable;
    }
    public void setCriteria(Column baseColumn, Column joinColumn)
    {
        this.joinCriteria =  new Criteria(baseColumn, joinColumn, QueryConstants.EQUAL);
    }
    public String getJoinStatement()
    {
        StringBuilder joinStatement = new StringBuilder();
        joinStatement.append(this.joinType.value);
        joinStatement.append(" ");
        joinStatement.append(this.joinTable);
        joinStatement.append(" ");
        joinStatement.append(QueryConstants.ON.value);
        joinStatement.append(" ");
        joinStatement.append(this.joinCriteria.getCriteriaString());
        return  joinStatement.toString();
    }
}
