package Entity;

import Tables.CustomerTable;

import java.sql.ResultSet;

public class Customer extends Entity
{
    int id;
    String name;
    String occupation;
    int gender;
    String description;
    String dateOfBirth;
    String dateOfMarriage;
    String aadharNum;
    String passportNum;
    String passportExp;
    String address;
    String mobileNum;
    String emailID;
    Integer createdBy = null;
    Integer lastModifiedBy = null;
    String createdTime = null;
    String lastModifiedTime = null;

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfMarriage() {
        return dateOfMarriage;
    }

    public void setDateOfMarriage(String dateOfMarriage) {
        this.dateOfMarriage = dateOfMarriage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getAadharNum() {
        return aadharNum;
    }

    public void setAadharNum(String aadharNum) {
        this.aadharNum = aadharNum;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    public String getPassportExp() {
        return passportExp;
    }

    public void setPassportExp(String passportExp) {
        this.passportExp = passportExp;
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

    public Customer(int id, String name, String description, String dateOfBirth, String dateOfMarriage, String address, String mobileNum, String emailID, String aadharNum, String passportNum, String passportExp, Integer createdBy, Integer lastModifiedBy, String createdTime, String lastModifiedTime, String occupation, int gender) throws Exception
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.dateOfMarriage = dateOfMarriage;
        this.address = address;
        this.mobileNum = mobileNum;
        this.emailID = emailID;
        this.aadharNum = aadharNum;
        this.passportNum = passportNum;
        this.passportExp = passportExp;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
        this.createdTime = createdTime;
        this.lastModifiedTime = lastModifiedTime;
        this.occupation = occupation;
        this.gender = gender;
        setActualValues();
    }

    public Customer(String name, String description, String dateOfBirth, String dateOfMarriage, String address, String mobileNum, String emailID, String aadharNum, String passportNum, String passportExp, String occupation, int gender) throws Exception
    {
        this.name = name;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.dateOfMarriage = dateOfMarriage;
        this.address = address;
        this.mobileNum = mobileNum;
        this.emailID = emailID;
        this.aadharNum = aadharNum;
        this.passportNum = passportNum;
        this.passportExp = passportExp;
        this.occupation = occupation;
        this.gender = gender;
        setActualValues();
    }
    public Customer(ResultSet result) throws Exception
    {
        this.id = result.getInt(CustomerTable.id);
        this.name = result.getString(CustomerTable.name);
        this.description = result.getString(CustomerTable.description);
        this.dateOfBirth = result.getString(CustomerTable.dateOfBirth);
        this.dateOfMarriage = result.getString(CustomerTable.dateOfMarriage);
        this.address = result.getString(CustomerTable.address);
        this.mobileNum = result.getString(CustomerTable.mobileNum);
        this.emailID = result.getString(CustomerTable.emailID);
        this.aadharNum = result.getString(CustomerTable.aadharNum);
        this.passportNum = result.getString(CustomerTable.passportNum);
        this.passportExp = result.getString(CustomerTable.passportExp);
        this.createdBy = result.getInt(CustomerTable.createdBy);
        this.lastModifiedBy = result.getInt(CustomerTable.lastModifiedBy);
        this.createdTime = result.getString(CustomerTable.createdTime);
        this.lastModifiedTime = result.getString(CustomerTable.lastModifiedTime);
        this.occupation = result.getString(CustomerTable.occupation);
        this.gender = result.getInt(CustomerTable.gender);
        setActualValues();
    }
}
