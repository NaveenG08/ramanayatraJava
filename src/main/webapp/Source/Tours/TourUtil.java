package Tours;

import DatabaseProcessor.Constants.EntityConstants;
import DatabaseProcessor.Constants.QueryConstants;
import DatabaseProcessor.Constants.RequestConstants;
import DatabaseProcessor.OperationRequest.DBRequest;
import DatabaseProcessor.Utils.Column;
import DatabaseProcessor.Utils.Criteria;
import DatabaseProcessor.Utils.Query;
import Entity.Tour;
import Tables.TourTable;
import Util.UtilMethods;
import com.google.gson.JsonArray;
import com.mysql.cj.jdbc.result.ResultSetImpl;

import java.util.Map;

public class TourUtil
{
    public static Object createTour(Map requestMap) throws Exception
    {
        String name = (String) requestMap.get("name");
        String startingPoint = (String) requestMap.get("startingpoint");
        String destination = (String) requestMap.get("destination");
        String description = (String) requestMap.get("description");
        Tour tour = new Tour(name, startingPoint, destination, description);
        DBRequest.operate(EntityConstants.TOUR, RequestConstants.WRITE, tour, requestMap);
        return tour.toJsonObject();
    }

    public static Object updateTour(Map requestMap) throws Exception
    {
        Query query = new Query(TourTable.TableName);
        query.setCriteria(new Criteria(new Column(TourTable.TableName, TourTable.id), requestMap.get("user_id"), QueryConstants.EQUAL));
        ResultSetImpl result = (ResultSetImpl) DBRequest.operate(EntityConstants.TOUR, RequestConstants.READ, query, requestMap);
        result.next();
        if (result == null)
        {
            throw new Exception("Invalid tourid");
        }
        Tour tour = new Tour(result);
        tour.setDescription((String) requestMap.get("description"));
        tour.setDestination((String) requestMap.get("destination"));
        tour.setStartingpoint((String) requestMap.get("starting_point"));
        tour.setName((String) requestMap.get("name"));
        DBRequest.operate(EntityConstants.TOUR, RequestConstants.UPDATE, tour, requestMap);
        return tour.toJsonObject();
    }

    public static Object deleteTour(Map requestMap) throws Exception
    {
        Query query = new Query(TourTable.TableName);
        query.setCriteria(new Criteria(new Column(TourTable.TableName, TourTable.id), requestMap.get("entity_id"), QueryConstants.EQUAL));
        DBRequest.operate(EntityConstants.TOUR, RequestConstants.DELETE, query, requestMap);
        return null;
    }
    public static JsonArray getTourList(Map requestMap) throws Exception
    {
        Query query = new Query(TourTable.TableName);
        ResultSetImpl result = (ResultSetImpl) DBRequest.operate(EntityConstants.TOUR, RequestConstants.READ, query, requestMap);
        return UtilMethods.convertResultToJson(result);
    }
}
