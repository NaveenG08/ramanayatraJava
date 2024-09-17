package DatabaseProcessor.Utils;

import DatabaseProcessor.Constants.QueryConstants;

import java.util.List;

public class Column
{
    String table;
    String column;
    String columnAlias = null;

    public Column(String table, String column)
    {
        this.table = table;
        this.column = column;
    }

    public Column(String table, String column, String columnAlias) {
        this.table = table;
        this.column = column;
        this.columnAlias = columnAlias;
    }
    public void setColumnAlias(String columnAlias)
    {
        this.columnAlias = columnAlias;
    }

    public String getColumnString()
    {
        StringBuilder columnString = new StringBuilder();
        if (this.table != null)
        {
            columnString.append(this.table);
            columnString.append(".");
        }
        columnString.append(this.column);
        if (this.columnAlias != null)
        {
            columnString.append(" ");
            columnString.append(QueryConstants.AS.value);
            columnString.append(" ");
            columnString.append(this.columnAlias);
        }
        return  columnString.toString();
    }
    public static String getColumnListString(List<Column> columns)
    {
        StringBuilder columnsString = new StringBuilder();
        for(int i = 0 ; i < columns.size() ; i++)
        {
            columnsString.append(columns.get(i).getColumnString());
            columnsString.append((i != columns.size() - 1) ? ", " : " ");
        }
        return columnsString.toString();
    }
}
