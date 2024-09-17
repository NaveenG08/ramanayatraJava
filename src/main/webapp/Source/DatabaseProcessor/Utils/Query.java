package DatabaseProcessor.Utils;

import DatabaseProcessor.Constants.QueryConstants;

import java.util.ArrayList;
import java.util.List;

public class Query
{
    private String table = null;
    private List<Column> selectColumns = null;
    private Column countColumn = null;
    private Criteria criteria = null;
    private Join[] join = null;
    private List<Column> orderbyColumns = null;
    private QueryConstants orderby = null;
    private Integer limit  = null;
    private Integer offset = null;

    public Query(String table) {
        this.table = table;
    }

    public String getTable() {
        return table;
    }

    public List<Column> getSelectColumns() {
        return selectColumns;
    }

    public Column getCountColumn() {
        return countColumn;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public Join[] getJoin() {
        return join;
    }

    public List<Column> getOrderbyColumns() {
        return orderbyColumns;
    }

    public QueryConstants getOrderby() {
        return orderby;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void setSelectColumn(Column selectColumns) {
        List<Column> columns = new ArrayList<>();
        columns.add(selectColumns);
        this.selectColumns = columns;
    }
    public void setSelectColumns(List<Column> selectColumns){
        this.selectColumns = selectColumns;
    }
    public void addSelectedColumn(Column selectColumns)
    {
        if (this.selectColumns == null)
        {
            this.setSelectColumn(selectColumns);
        }
        else
        {
            this.selectColumns.add(selectColumns);
        }
    }


    public void setCountColumn(Column countColumn) {
        this.countColumn = countColumn;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public void setJoin(Join[] join) {
        this.join = join;
    }

    public void setOrderbyColumns(List<Column> orderbyColumns) {
        this.orderbyColumns = orderbyColumns;
    }

    public void setOrderby(QueryConstants orderby) {
        this.orderby = orderby;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
