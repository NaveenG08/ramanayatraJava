package DatabaseProcessor.Utils;

import DatabaseProcessor.Constants.QueryConstants;

import java.util.Iterator;
import java.util.List;

public class Criteria
{
    private Object leftOperant;
    private Object rightOperant;
    private QueryConstants comparator;
    private QueryConstants joinOperant = null;
    private Criteria joinCriteria = null;
    public Criteria(Object leftOperant, Object rightOperant, QueryConstants criteriaConstants)
    {
        this.leftOperant = leftOperant;
        this.rightOperant = rightOperant;
        this.comparator = criteriaConstants;
    }

    public void and(Criteria criteria)
    {
        joinOperant = QueryConstants.AND;
        joinCriteria = criteria;
    }
    public void or(Criteria criteria)
    {
        joinOperant = QueryConstants.OR;
        joinCriteria = criteria;
    }

    public String getValue(Object operant)
    {
        if (operant instanceof String)
        {
            return "'" + operant + "'";
        }
        else if (operant instanceof Column)
        {
            return ((Column) operant).getColumnString();
        }
        else if (operant instanceof List)
        {
            StringBuilder str = new StringBuilder("(");
            for(int i =0 ;i < ((List) operant).size() ; i++)
            {
                str.append(getValue(((List) operant).get(i)));
                str.append((i != ((List)operant).size() - 1) ? ", " : ")");
            }
            return str.toString();
        }
        else
        {
            return String.valueOf(operant);
        }
    }

    public String getCriteriaString()
    {
        StringBuilder criteriaString = new StringBuilder();
        criteriaString.append("( ");
        criteriaString.append(getValue(this.leftOperant));
        criteriaString.append(" ");
        criteriaString.append(this.comparator.value);
        criteriaString.append(" ");
        criteriaString.append(getValue(this.rightOperant));
        criteriaString.append(" ");
        if (this.joinCriteria != null)
        {
            criteriaString.append( this.joinOperant + " " + this.joinCriteria.getCriteriaString());
        }
        criteriaString.append(" )");
        return  criteriaString.toString();
    }
}
