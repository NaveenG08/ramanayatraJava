package Entity;

import Tables.TourTable;

import java.sql.ResultSet;

public class Tour extends Entity
{
    int id;
    String name;
    String startingpoint;
    String destination;
    String description;
    Integer createdBy = null;
    Integer lastModifiedBy = null;
    String createdTime = null;
    String lastModifiedTime = null;

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Integer lastModifiedBy) {
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

    public String getStartingpoint() {
        return startingpoint;
    }

    public void setStartingpoint(String startingpoint) {
        this.startingpoint = startingpoint;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Tour(int id, String name, String startingpoint, String destination, String description, Integer createdBy, Integer lastModifiedBy, String createdTime, String lastModifiedTime) throws IllegalAccessException
    {
        this.id = id;
        this.name = name;
        this.startingpoint = startingpoint;
        this.destination = destination;
        this.description = description;
        setActualValues();
    }
    public Tour(ResultSet result) throws Exception
    {
        this.id = result.getInt(TourTable.id);
        this.name = result.getString(TourTable.name);
        this.startingpoint = result.getString(TourTable.startingPoint);
        this.destination = result.getString(TourTable.destination);
        this.description = result.getString(TourTable.description);
        setActualValues();
    }

    public Tour(String name, String startingpoint, String destination, String description) throws IllegalAccessException
    {
        this.name = name;
        this.startingpoint = startingpoint;
        this.destination = destination;
        this.description = description;
        setActualValues();
    }
}
