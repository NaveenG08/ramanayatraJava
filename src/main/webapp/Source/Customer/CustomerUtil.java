package Customer;

import DatabaseProcessor.Constants.EntityConstants;
import DatabaseProcessor.Constants.QueryConstants;
import DatabaseProcessor.Constants.RequestConstants;
import DatabaseProcessor.OperationRequest.DBRequest;
import DatabaseProcessor.Utils.Column;
import DatabaseProcessor.Utils.Criteria;
import DatabaseProcessor.Utils.Query;
import Entity.Customer;
import Tables.CustomerTable;
import Tables.TourTable;
import Util.UtilMethods;
import com.mysql.cj.jdbc.result.ResultSetImpl;
import jakarta.servlet.http.Part;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class CustomerUtil
{
    public static Object deleteCustomer(Map requestMap) throws Exception
    {
        Query query = new Query(TourTable.TableName);
        query.setCriteria(new Criteria(new Column(TourTable.TableName, TourTable.id), requestMap.get("entity_id"), QueryConstants.EQUAL));
        DBRequest.operate(EntityConstants.TOUR, RequestConstants.DELETE, query, requestMap);
        return null;
    }

    public static Object createCustomer(Map requestMap) throws Exception
    {
        String name = (String) requestMap.get("name");
        String description = (String) requestMap.get("description");
        String dateOfBirth = (String) requestMap.get("dateofbirth");
        String dateOfMarriage = (String) requestMap.get("dateofmarriage");
        String aadharNum = String.valueOf(requestMap.get("aadharnum"));
        String passportNum = (String) requestMap.get("passportnum");
        String passportExp = (String) requestMap.get("passportexp");
        String address = (String) requestMap.get("address");
        String mobileNum = String.valueOf(requestMap.get("mobilenum"));
        String emailId = (String) requestMap.get("emailid");
        String occupation = (String) requestMap.get("occupation");
        int gender = Integer.parseInt((String) requestMap.get("gender"));

        Customer customer = new Customer(name, description, dateOfBirth, dateOfMarriage, address, mobileNum, emailId, aadharNum, passportNum, passportExp, occupation, gender);
        DBRequest.operate(EntityConstants.CUSTOMER, RequestConstants.WRITE, customer, requestMap);
        return customer.toJsonObject();
    }

    public static Object importCustomer(Map requestMap) throws Exception
    {
        String[] customerColumnList = {"name", "dateofbirth", "dateofmarriage", "aadharnum", "passportnum", "passportexp", "address", "mobilenum", "emailid", "occupation", "gender"};
        String[] columnList = {"Customer Name", "DOB", "Married Date", "Aadhaar", "Passport", "Passport Expiry", "Address", "Mobile No", "Email", "Occupation", "Gender"};
        Part filePart = (Part) requestMap.get("file");
        Workbook workbook = new XSSFWorkbook(UtilMethods.convertToXls(filePart.getInputStream()));
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        Map<String, Integer> matchingIndexes = getCellIndexes(rowIterator.next());
        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            for (int i = 0, j = 0 ; i < customerColumnList.length; i++)
            {
                Object value;
                switch (customerColumnList[i])
                {
                    case "aadharnum":
                    case "passportnum":
                        if (matchingIndexes.get(columnList[i]) != null)
                        {
                             value = UtilMethods.getValueFromCellInExcel(row , matchingIndexes.get(columnList[i]));
                        }
                        else
                        {
                            value = ((String)UtilMethods.getValueFromCellInExcel(row , matchingIndexes.get("Proof Type"))).contains(columnList[i]) ? UtilMethods.getValueFromCellInExcel(row, matchingIndexes.get("Proof No")) : "";
                        }
                        break;
                    case "gender":
                        value = UtilMethods.getValueFromCellInExcel(row, matchingIndexes.get(columnList[i]));
                        value = (((String) value).contains("female") || ((String) value).contains("Female")) ? "1" : "0";
                        break;
                    case "address":
                        value = UtilMethods.getValueFromCellInExcel(row, matchingIndexes.get("Address")) + " ";
                        value += UtilMethods.getValueFromCellInExcel(row, matchingIndexes.get("City")) + " ";
                        value += UtilMethods.getValueFromCellInExcel(row, matchingIndexes.get("State")) + " ";
                        value += UtilMethods.getValueFromCellInExcel(row, matchingIndexes.get("Country")) + " ";
                        value += UtilMethods.getValueFromCellInExcel(row, matchingIndexes.get("Pincode")) + " ";
                        break;
                    default:
                        value = UtilMethods.getValueFromCellInExcel(row , matchingIndexes.get(columnList[i]));
                        break;
                }
                requestMap.put(customerColumnList[i], value);
                j++;
            }
            createCustomer(requestMap);
        }
        return null;
    }

    private static Map getCellIndexes(Row row)
    {
        String[] columnList = {"Customer Name", "DOB", "Married Date", "Aadhaar","Proof Type","Proof No", "Passport", "Passport Expiry", "Address", "City", "State", "Country", "Pincode", "Mobile No", "Email", "Occupation", "Gender"};
        Map matchingIndexes = new HashMap();
        int index = 0;
        Iterator<Cell> cellIterator = row.iterator();
        while (cellIterator.hasNext())
        {
            Cell cell = cellIterator.next();
            for(int i = 0; i < columnList.length; i++)
            {
                String head = cell.getStringCellValue();
                if (head.contains(columnList[i]) && !matchingIndexes.containsKey(columnList[i]))
                {
                    matchingIndexes.put(columnList[i], index);
                }
            }
            index++;
        }
        return matchingIndexes;
    }

    public static Object updateCustomer(Map requestMap) throws Exception
    {
        int entityId = (Integer) requestMap.get("entityid");
        String name = (String) requestMap.get("name");
        String description = (String) requestMap.get("description");
        String dateOfBirth = (String) requestMap.get("dateofbirth");
        String dateOfMarriage = (String) requestMap.get("dateofmarriage");
        String aadharNum = (String) requestMap.get("aadharnum");
        String passportNum = (String) requestMap.get("passportnum");
        String passportExp = (String) requestMap.get("passportexp");
        String address = (String) requestMap.get("address");
        String mobileNum = (String) requestMap.get("mobilenum");
        String emailId = (String) requestMap.get("emailid");

        Query query = new Query(CustomerTable.TableName);
        query.setCriteria(new Criteria(new Column(CustomerTable.TableName, CustomerTable.id), entityId, QueryConstants.EQUAL));
        ResultSetImpl result = (ResultSetImpl) DBRequest.operate(EntityConstants.CUSTOMER, RequestConstants.READ, query, requestMap);
        result.next();
        if (result == null)
        {
            throw new Exception("Invalid TripId");
        }
        Customer customer = new Customer(result);
        customer.setName(name);
        customer.setDescription(description);
        customer.setDateOfBirth(dateOfBirth);
        customer.setDateOfMarriage(dateOfMarriage);
        customer.setAadharNum(aadharNum);
        customer.setPassportNum(passportNum);
        customer.setPassportExp(passportExp);
        customer.setAddress(address);
        customer.setMobileNum(mobileNum);
        customer.setEmailID(emailId);

        DBRequest.operate(EntityConstants.CUSTOMER, RequestConstants.UPDATE, customer, requestMap);
        return customer.toJsonObject();
    }
    public static Object getCustomerList(Map requestMap) throws Exception
    {
        Query query = new Query(CustomerTable.TableName);
        ResultSetImpl result = (ResultSetImpl) DBRequest.operate(EntityConstants.CUSTOMER, RequestConstants.READ, query, requestMap);
        return UtilMethods.convertResultToJson(result);
    }
}
