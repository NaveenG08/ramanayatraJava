package Entity;

import Util.Constants;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Field;
import java.util.*;

public class Entity
{
    private Map<Field, Object> actualValues;

    protected void setActualValues() throws IllegalAccessException
    {
        Field[] fields = this.getClass().getDeclaredFields();
        this.actualValues = new HashMap<>();
        for (Field field : fields)
        {
            if (!field.getName().equalsIgnoreCase("actualValues"))
            {
                this.actualValues.put(field,field.get(this));
            }
        }
    }


    public String getValuesForInsertQuery(Map requestMap) throws IllegalAccessException
    {
        Field[] fields = this.getClass().getDeclaredFields();
        StringBuilder query = new StringBuilder();
        query.append(" ( ");
        for (int i = 0 ; i < fields.length ; i++)
        {
            if (!fields[i].getName().equalsIgnoreCase("ID") && !fields[i].getName().equalsIgnoreCase("actualValues"))
            {
                Object value = fields[i].get(this);
                if (fields[i].getName().equalsIgnoreCase("createdTime") || fields[i].getName().equalsIgnoreCase("LastModifiedTime"))
                {
                    long millis = System.currentTimeMillis();
                    value = new Date(millis).toString();
                    fields[i].set(this, value);
                }
                else if (fields[i].getName().equalsIgnoreCase("createdBy") || fields[i].getName().equalsIgnoreCase("LastModifiedBy"))
                {
                    value = ((Session)requestMap.get(Constants.SESSION)).getUserId();
                    fields[i].set(this, value);
                }
                if (value instanceof String)
                {
                    query.append("'");
                    query.append(value);
                    query.append("'");
                }
                else
                {
                    query.append(value);
                }
                query.append((i != fields.length -1)? ", " : " ");
            }
        }
        query.append(") ");
        return query.toString();
    }

    public String getSetQueryForUpdate(Map requestMap) throws IllegalAccessException
    {
        Field[] fields = this.getClass().getDeclaredFields();

        long millis = System.currentTimeMillis();
        String date = new Date(millis).toString();

        StringBuilder query = new StringBuilder();
        for (int i = 0 ; i < fields.length ; i++)
        {
            if (!canIgnoreField(fields[i]))
            {
                query.append((query.length() > 3)? ", " : " ");
                Object value = fields[i].get(this);
                if (fields[i].getName().equalsIgnoreCase("LastModifiedTime"))
                {
                    value = date;
                    fields[i].set(this, value);
                }
                else if (fields[i].getName().equalsIgnoreCase("LastModifiedBy"))
                {
                    value = ((Session)requestMap.get(Constants.SESSION)).getUserId();
                    fields[i].set(this, value);
                }

                query.append(fields[i].getName());
                query.append(" = ");
                if (value instanceof String)
                {
                    query.append("'");
                    query.append(value);
                    query.append("'");
                }
                else
                {
                    query.append(value);
                }
            }
        }
        query.append(" ");
        return query.toString();
    }
    private boolean canIgnoreField(Field field) throws IllegalAccessException
    {
        String[] fieldNames = {"id", "createdTime", "createdBy", "actualValues"};
        String[] fieldLists = {"lastModifiedTime", "lastModifiedBy"};
        String name = field.getName();
        return (Arrays.stream(fieldNames).toList().contains(name) || Objects.deepEquals(actualValues.get(field), field.get(this))) && !Arrays.stream(fieldLists).toList().contains(name);
    }
    public Integer getPrimaryKey() throws Exception
    {
        return (Integer) this.getClass().getDeclaredField("id").get(this);
    }

    public JsonObject toJsonObject() throws IllegalAccessException
    {
        JsonObject json = new JsonObject();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields)
        {
            json.addProperty(field.getName(), String.valueOf(field.get(this)));
        }
        return json;
    }
}
