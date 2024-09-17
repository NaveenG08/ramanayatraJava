package Tables;

import java.lang.reflect.Field;

public class Table
{
    public String getColumnsForInsertQuery() throws NoSuchFieldException, IllegalAccessException
    {
        Field[] fields = this.getClass().getDeclaredFields();
        StringBuilder query = new StringBuilder();
        query.append(this.getClass().getDeclaredField("TableName").get(this));
        query.append(" ( ");
        for (int i = 0 ; i < fields.length ; i++)
        {
            if (!(fields[i].getName().equalsIgnoreCase("ID") || fields[i].getName().equalsIgnoreCase("TableName")))
            {
                query.append(fields[i].getName());
                query.append((i != fields.length -1)? ", " : " ");
            }
        }
        query.append(") ");
        return query.toString();
    }
}
