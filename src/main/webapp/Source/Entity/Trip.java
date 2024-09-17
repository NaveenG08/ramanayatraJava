package Entity;

import Tables.TripTable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Trip extends Entity
{
    int id;
    int tourId;
    String customerList;
    String description;
    int price;
    String startDate;
    String endDate;
    Integer createdBy = null;
    Integer lastModifiedBy = null;
    String createdTime = null;
    String lastModifiedTime = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public String getCustomerList() {
        return customerList;
    }

    public void setCustomerList(String customerList) {
        this.customerList = customerList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

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

    public Trip(int id, int tourId, String customerList, String description, int price, String startDate, String endDate, Integer createdBy, Integer lastModifiedBy, String createdTime, String lastModifiedTime) throws IllegalAccessException
    {
        this.id = id;
        this.tourId = tourId;
        this.customerList = customerList;
        this.description = description;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
        this.createdTime = createdTime;
        this.lastModifiedTime = lastModifiedTime;
        setActualValues();
    }

    public Trip(int tourId, String customerList, String description, int price, String startDate, String endDate) throws IllegalAccessException
    {
        this.tourId = tourId;
        this.customerList = customerList;
        this.description = description;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        setActualValues();
    }
    public Trip(ResultSet resultSet) throws Exception
    {
        this.id = resultSet.getInt(TripTable.id);
        this.tourId = resultSet.getInt(TripTable.tourId);
        this.customerList = resultSet.getString(TripTable.customerList);
        this.description = resultSet.getString(TripTable.description);
        this.price = resultSet.getInt(TripTable.price);
        this.startDate = resultSet.getString(TripTable.startDate);
        this.endDate = resultSet.getString(TripTable.endDate);
        this.createdBy = resultSet.getInt(TripTable.createdBy);
        this.lastModifiedBy = resultSet.getInt(TripTable.lastModifiedBy);
        this.createdTime = resultSet.getString(TripTable.createdTime);
        this.lastModifiedTime = resultSet.getString(TripTable.lastModifiedTime);
        setActualValues();
    }
}
