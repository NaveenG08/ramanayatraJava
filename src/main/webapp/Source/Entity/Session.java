package Entity;

import Tables.SessionTable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Session extends Entity
{
    long id;
    int userId;
    boolean isAdmin;
    String sessionKey;
    String startTime;
    String endTime;
    String ip;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Session(int userId, boolean isAdmin, String sessionKey, String startTime, String endTime, String ip) throws IllegalAccessException
    {
        this.userId = userId;
        this.isAdmin = isAdmin;
        this.sessionKey = sessionKey;
        this.startTime = startTime;
        this.endTime = endTime;
        this.ip = ip;
        setActualValues();
    }

    public Session(ResultSet resultSet) throws Exception
    {
        this.id = id;
        this.userId = resultSet.getInt(SessionTable.userId);
        this.isAdmin = resultSet.getInt(SessionTable.isAdmin) == 1;
        this.sessionKey = resultSet.getString(SessionTable.sessionKey);
        this.startTime = resultSet.getString(SessionTable.startTime);
        this.endTime = resultSet.getString(SessionTable.endTime);
        this.ip = resultSet.getString(SessionTable.ip);
        setActualValues();
    }

    public Session(int id, int userId, boolean isAdmin, String sessionKey, String startTime, String endTime, String ip) throws IllegalAccessException
    {
        this.id = id;
        this.userId = userId;
        this.sessionKey = sessionKey;
        this.startTime = startTime;
        this.endTime = endTime;
        this.ip = ip;
        this.isAdmin = isAdmin;
        setActualValues();
    }
}
