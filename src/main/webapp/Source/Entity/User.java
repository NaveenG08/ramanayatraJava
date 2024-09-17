package Entity;

import Tables.UserTable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends Entity
{
    int id;
    String name = null;
    String username = null;
    String password = null;
    Integer createdBy = null;
    Integer lastModifiedBy = null;
    String createdTime = null;
    String lastModifiedTime = null;
    int isAdmin = 0;
    int isActive = 1;

    public User(int id, String name, String username, String password, int createdBy, int lastModifiedBy, String createdTime, String lastModifiedTime, int isAdmin, int isActive) throws IllegalAccessException
    {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
        this.createdTime = createdTime;
        this.lastModifiedTime = lastModifiedTime;
        this.isAdmin = isAdmin;
        this.isActive = isActive;
        setActualValues();
    }

    public User(ResultSet resultSet) throws Exception
    {
        this.id = resultSet.getInt(UserTable.id);
        this.name = resultSet.getString(UserTable.name);
        this.username = resultSet.getString(UserTable.username);
        this.password = resultSet.getString(UserTable.password);
        this.createdBy = resultSet.getInt(UserTable.createdBy);
        this.lastModifiedBy = resultSet.getInt(UserTable.lastModifiedBy);
        this.createdTime = resultSet.getString(UserTable.createdTime);
        this.lastModifiedTime = resultSet.getString(UserTable.lastModifiedTime);
        this.isAdmin = resultSet.getInt(UserTable.isAdmin);
        this.isActive = resultSet.getInt(UserTable.isActive);
        setActualValues();
    }

    public User(String name, String username, String password) throws IllegalAccessException
    {
        this.name = name;
        this.username = username;
        this.password = password;
        setActualValues();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(int lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(String lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public int isAdmin() {
        return isAdmin;
    }

    public void setAdmin(int admin) {
        isAdmin = admin;
    }

    public int isActive() { return isActive;}

    public void setActive(int active) { isActive = active;}

}
