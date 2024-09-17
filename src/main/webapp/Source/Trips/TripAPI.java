package Trips;

import DatabaseProcessor.Constants.EntityConstants;
import DatabaseProcessor.Constants.QueryConstants;
import DatabaseProcessor.Constants.RequestConstants;
import DatabaseProcessor.OperationRequest.DBRequest;
import DatabaseProcessor.Utils.Column;
import DatabaseProcessor.Utils.Criteria;
import DatabaseProcessor.Utils.Query;
import Entity.Trip;
import ServletProcessor.API;
import Tables.CustomerTable;
import com.mysql.cj.protocol.Resultset;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TripAPI extends API
{
    @Override
    public Object handleProcess(HttpServletRequest req, HttpServletResponse resp, Map requestMap) throws Exception
    {
        int tourId =  Integer.parseInt((String) requestMap.get("tourid"));
        int price =  Integer.parseInt((String) requestMap.get("price"));
        String description = (String) requestMap.get("description");
        String startDate = (String) requestMap.get("startdate");
        String endDate = (String) requestMap.get("enddate");
        String custList = (String) requestMap.get("customerlist");

        Trip trip = new Trip(tourId, custList, description, price, startDate, endDate);
        DBRequest.operate(EntityConstants.TRIP, RequestConstants.WRITE, trip, requestMap);
        return TripUtil.getTripList(requestMap);
    }

    public void validateCustomerList(Map requestMap) throws Exception
    {
        String custList = (String) requestMap.get("customerlist");
        List customerList = new ArrayList<>();
        for (String cust : custList.split(","))
        {
            if(!customerList.contains(Integer.valueOf(cust)))
            {
                customerList.add(Integer.valueOf(cust));
            }
        }
        Query query = new Query(CustomerTable.TableName);
        query.setCriteria(new Criteria(new Column(CustomerTable.TableName, CustomerTable.id), customerList, QueryConstants.IN));
        Resultset result = (Resultset) DBRequest.operate(EntityConstants.CUSTOMER, RequestConstants.READ, query, requestMap);
        if (result.getRows().size() != customerList.size())
        {
            throw new Exception("Kindly pass only valid customer list.");
        }
    }
}
