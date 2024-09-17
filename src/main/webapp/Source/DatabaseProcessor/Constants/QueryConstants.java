package DatabaseProcessor.Constants;

public enum QueryConstants
{
    EQUAL("="),
    NOT_EQUAL("!="),
    LESS_THAN("<"),
    GREATER_THAN(">"),
    LESS_THAN_OR_EQUAL("<="),
    GREATER_THAN_OR_EQUAL(">="),
    AND("AND"),
    OR("OR"),
    INNER_JOIN("INNER JOIN"),
    LEFT_JOIN("LEFT JOIN"),
    RIGHT_JOIN("RIGHT JOIN"),
    CROSS_JOIN("CROSS JOIN"),
    ON("ON"),
    COUNT("COUNT"),
    AS("AS"),
    ASCENDING("ASC"),
    DESCENDING("DEC"),
    ORDER_BY("ORDER BY"),
    LIMIT("LIMIT"),
    OFFSET("OFFSET"),
        SELECT("SELECT"),
    FROM("FROM"),
    WHERE("WHERE"),
    INSERT_INTO("INSERT INTO"),
    VALUES("VALUES"),
    DELETE("DELETE"),
    UPDATE("UPDATE"),
    SET("SET"),
    IN("IN");


    public String value;
    QueryConstants(String value)
    {
        this.value = value;
    }
}
