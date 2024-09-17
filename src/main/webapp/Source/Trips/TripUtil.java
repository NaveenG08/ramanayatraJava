package Trips;


import DatabaseProcessor.Constants.EntityConstants;
import DatabaseProcessor.Constants.QueryConstants;
import DatabaseProcessor.Constants.RequestConstants;
import DatabaseProcessor.OperationRequest.DBRequest;
import DatabaseProcessor.Utils.Column;
import DatabaseProcessor.Utils.Criteria;
import DatabaseProcessor.Utils.Query;
import Entity.Trip;
import Tables.TourTable;
import Tables.TripTable;
import Util.UtilMethods;
import com.mysql.cj.jdbc.result.ResultSetImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TripUtil
{
    public static Object deleteTrip(Map requestMap) throws Exception
    {
        Query query = new Query(TourTable.TableName);
        query.setCriteria(new Criteria(new Column(TripTable.TableName, TripTable.id), requestMap.get("entity_id"), QueryConstants.EQUAL));
        DBRequest.operate(EntityConstants.TOUR, RequestConstants.DELETE, query, requestMap);
        return null;
    }
    public static Object updateTrip(Map requestMap) throws Exception
    {
        int entityId = (Integer) requestMap.get("entity_id");
        int tourId =  (Integer) requestMap.get("tour_id");
        int price =  (Integer) requestMap.get("price");
        String description = (String) requestMap.get("description");
        String startDate = (String) requestMap.get("start_date");
        String endDate = (String) requestMap.get("end_date");
        String custList = (String) requestMap.get("customer_list");
        List customerList = new ArrayList<>();
        for (String cust : custList.split(","))
        {
            customerList.add(Integer.valueOf(cust));
        }

        Query query = new Query(TripTable.TableName);
        query.setCriteria(new Criteria(new Column(TripTable.TableName, TripTable.id), entityId, QueryConstants.EQUAL));
        ResultSetImpl result = (ResultSetImpl) DBRequest.operate(EntityConstants.TRIP, RequestConstants.READ, query, requestMap);
        result.next();
        if (result == null)
        {
            throw new Exception("Invalid TripId");
        }

        Trip trip = new Trip(result);
        trip.setTourId(tourId);
        trip.setPrice(price);
        trip.setDescription(description);
        trip.setStartDate(startDate);
        trip.setEndDate(endDate);
        DBRequest.operate(EntityConstants.TRIP, RequestConstants.UPDATE, trip, requestMap);
        return trip.toJsonObject();
    }
    public static Object getTripList(Map requestMap) throws Exception
    {
        Query query = new Query(TripTable.TableName);
        ResultSetImpl result = (ResultSetImpl) DBRequest.operate(EntityConstants.TRIP, RequestConstants.READ, query, requestMap);
        return UtilMethods.convertResultToJson(result);
    }
}
