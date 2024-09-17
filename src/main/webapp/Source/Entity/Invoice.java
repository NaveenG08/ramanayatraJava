package Entity;

import Tables.InvoiceTable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Invoice extends Entity
{
    int id;
    int tripId;
    int customerId;
    int invoiceTotal;
    int amountPaid;
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

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(int invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
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

    public Invoice(int id, int tripId, int customerId, int invoiceTotal, int amountPaid, Integer createdBy, Integer lastModifiedBy, String createdTime, String lastModifiedTime) throws IllegalAccessException {
        this.id = id;
        this.tripId = tripId;
        this.customerId = customerId;
        this.invoiceTotal = invoiceTotal;
        this.amountPaid = amountPaid;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
        this.createdTime = createdTime;
        this.lastModifiedTime = lastModifiedTime;
        setActualValues();
    }
    public Invoice(ResultSet resultSet) throws IllegalAccessException, SQLException {
        this.id = resultSet.getInt(InvoiceTable.id);
        this.tripId = resultSet.getInt(InvoiceTable.tripID);
        this.customerId = resultSet.getInt(InvoiceTable.customerID);
        this.invoiceTotal = resultSet.getInt(InvoiceTable.invoiceTotal);
        this.amountPaid = resultSet.getInt(InvoiceTable.amountPaid);
        this.createdBy = resultSet.getInt(InvoiceTable.createdBy);
        this.lastModifiedBy = resultSet.getInt(InvoiceTable.lastModifiedBy);
        this.createdTime = resultSet.getString(InvoiceTable.createdTime);
        this.lastModifiedTime = resultSet.getString(InvoiceTable.lastModifiedTime);
        setActualValues();
    }



    public Invoice(int tripId, int customerId, int invoiceTotal, int amountPaid, Integer createdBy, Integer lastModifiedBy, String createdTime, String lastModifiedTime) throws IllegalAccessException {
        this.tripId = tripId;
        this.customerId = customerId;
        this.invoiceTotal = invoiceTotal;
        this.amountPaid = amountPaid;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
        this.createdTime = createdTime;
        this.lastModifiedTime = lastModifiedTime;
        setActualValues();
    }
}
