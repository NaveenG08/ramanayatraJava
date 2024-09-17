package ServletProcessor;

import Util.Constants;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class RequestProcessMapping
{
    public static Map RequestProcessMap = new HashMap<String ,Object>(){};
    static {
        {
            JsonObject json = new JsonObject();
            json.addProperty(Constants.REQUEST_METHOD_STR, Constants.POST_STR);
            json.add(Constants.REQUEST_DATA_STR, StreamDataMap.RequestDataMap.get("createUser"));
            json.addProperty(Constants.API_CLASS_STR, "Users.UserAPI");
            RequestProcessMap.put("/users/create", json);

            json = new JsonObject();
            json.addProperty(Constants.REQUEST_METHOD_STR, Constants.PUT_STR);
            json.add(Constants.REQUEST_DATA_STR, StreamDataMap.RequestDataMap.get("Entity"));
            json.addProperty(Constants.CLASS_STR, "Users.UserUtil");
            json.addProperty(Constants.METHOD_STR,"changeUserRole");
            json.addProperty(Constants.PERMISSION_RESTRICTED, true);
            RequestProcessMap.put("/users/role", json);

            json = new JsonObject();
            json.addProperty(Constants.REQUEST_METHOD_STR, Constants.PUT_STR);
            json.add(Constants.REQUEST_DATA_STR, StreamDataMap.RequestDataMap.get("Entity"));
            json.addProperty(Constants.CLASS_STR, "Users.UserUtil");
            json.addProperty(Constants.METHOD_STR,"changeUserStatus");
            json.addProperty(Constants.PERMISSION_RESTRICTED, true);
            RequestProcessMap.put("/users/status", json);

            json = new JsonObject();
            json.addProperty(Constants.REQUEST_METHOD_STR, Constants.PUT_STR);
            json.add(Constants.REQUEST_DATA_STR, StreamDataMap.RequestDataMap.get("updateUserPassword"));
            json.addProperty(Constants.CLASS_STR, "Users.UserUtil");
            json.addProperty(Constants.METHOD_STR,"changeUserPassword");
            json.addProperty(Constants.PERMISSION_RESTRICTED, true);
            RequestProcessMap.put("/users/password", json);

            json = new JsonObject();
            json.addProperty(Constants.REQUEST_METHOD_STR, Constants.POST_STR);
            json.add(Constants.REQUEST_DATA_STR, StreamDataMap.RequestDataMap.get("signInUser"));
            json.addProperty(Constants.API_CLASS_STR, "Users.LoginAPI");
            RequestProcessMap.put("/signin", json);

            json = new JsonObject();
            json.addProperty(Constants.REQUEST_METHOD_STR, Constants.POST_STR);
            json.add(Constants.REQUEST_DATA_STR, null);
            json.addProperty(Constants.CLASS_STR, "Users.UserUtil");
            json.addProperty(Constants.METHOD_STR,"signout");
            RequestProcessMap.put("/signout", json);

            json = new JsonObject();
            json.addProperty(Constants.REQUEST_METHOD_STR, Constants.GET_STR);
            json.add(Constants.REQUEST_DATA_STR, null);
            json.addProperty(Constants.CLASS_STR, "Users.UserUtil");
            json.addProperty(Constants.METHOD_STR,"getUsersList");
            RequestProcessMap.put("/users/list", json);

            json = new JsonObject();
            json.addProperty(Constants.REQUEST_METHOD_STR, Constants.POST_STR);
            json.add(Constants.REQUEST_DATA_STR, StreamDataMap.RequestDataMap.get("createTour"));
            json.addProperty(Constants.CLASS_STR, "Tours.TourUtil");
            json.addProperty(Constants.METHOD_STR,"createTour");
            RequestProcessMap.put("/tours/create", json);

            json = new JsonObject();
            json.addProperty(Constants.REQUEST_METHOD_STR, Constants.PUT_STR);
            json.add(Constants.REQUEST_DATA_STR, StreamDataMap.RequestDataMap.get("updateTour"));
            json.addProperty(Constants.CLASS_STR, "Tours.TourUtil");
            json.addProperty(Constants.METHOD_STR,"updateTour");
            RequestProcessMap.put("/tours/update", json);

            json = new JsonObject();
            json.addProperty(Constants.REQUEST_METHOD_STR, Constants.DELETE_STR);
            json.add(Constants.REQUEST_DATA_STR, StreamDataMap.RequestDataMap.get("entity"));
            json.addProperty(Constants.CLASS_STR, "Tours.TourUtil");
            json.addProperty(Constants.METHOD_STR,"deleteTour");
            RequestProcessMap.put("/tours/delete", json);

            json = new JsonObject();
            json.addProperty(Constants.REQUEST_METHOD_STR, Constants.GET_STR);
            json.add(Constants.REQUEST_DATA_STR, null);
            json.addProperty(Constants.CLASS_STR, "Tours.TourUtil");
            json.addProperty(Constants.METHOD_STR,"getTourList");
            RequestProcessMap.put("/tours/list", json);

            json = new JsonObject();
            json.addProperty(Constants.REQUEST_METHOD_STR, Constants.POST_STR);
            json.add(Constants.REQUEST_DATA_STR, StreamDataMap.RequestDataMap.get("createTrip"));
            json.addProperty(Constants.API_CLASS_STR, "Trips.TripAPI");
            RequestProcessMap.put("/trips/create", json);

            json = new JsonObject();
            json.addProperty(Constants.REQUEST_METHOD_STR, Constants.PUT_STR);
            json.add(Constants.REQUEST_DATA_STR, StreamDataMap.RequestDataMap.get("updateTrip"));
            json.addProperty(Constants.CLASS_STR, "Trips.TripUtil");
            json.addProperty(Constants.METHOD_STR,"updateTrip");
            RequestProcessMap.put("/trips/update", json);

            json = new JsonObject();
            json.addProperty(Constants.REQUEST_METHOD_STR, Constants.DELETE_STR);
            json.add(Constants.REQUEST_DATA_STR, StreamDataMap.RequestDataMap.get("entity"));
            json.addProperty(Constants.CLASS_STR, "Trips.TripUtil");
            json.addProperty(Constants.METHOD_STR,"deleteTrip");
            RequestProcessMap.put("/trips/delete", json);

            json = new JsonObject();
            json.addProperty(Constants.REQUEST_METHOD_STR, Constants.GET_STR);
            json.add(Constants.REQUEST_DATA_STR, null);
            json.addProperty(Constants.CLASS_STR, "Trips.TripUtil");
            json.addProperty(Constants.METHOD_STR,"getTripList");
            RequestProcessMap.put("/trips/list", json);

            json = new JsonObject();
            json.addProperty(Constants.REQUEST_METHOD_STR, Constants.POST_STR);
            json.add(Constants.REQUEST_DATA_STR, StreamDataMap.RequestDataMap.get("createCustomer"));
            json.addProperty(Constants.CLASS_STR, "Customer.CustomerUtil");
            json.addProperty(Constants.METHOD_STR,"createCustomer");
            RequestProcessMap.put("/customer/create", json);

            json = new JsonObject();
            json.addProperty(Constants.REQUEST_METHOD_STR, Constants.POST_STR);
            json.add(Constants.REQUEST_DATA_STR, null);
            json.addProperty(Constants.CLASS_STR, "Customer.CustomerUtil");
            json.addProperty(Constants.METHOD_STR,"importCustomer");
            RequestProcessMap.put("/customer/import", json);

            json = new JsonObject();
            json.addProperty(Constants.REQUEST_METHOD_STR, Constants.PUT_STR);
            json.add(Constants.REQUEST_DATA_STR, StreamDataMap.RequestDataMap.get("updateCustomer"));
            json.addProperty(Constants.CLASS_STR, "Customer.CustomerUtil");
            json.addProperty(Constants.METHOD_STR,"updateCustomer");
            RequestProcessMap.put("/customer/update", json);

            json = new JsonObject();
            json.addProperty(Constants.REQUEST_METHOD_STR, Constants.DELETE_STR);
            json.add(Constants.REQUEST_DATA_STR, StreamDataMap.RequestDataMap.get("entity"));
            json.addProperty(Constants.CLASS_STR, "Customer.CustomerUtil");
            json.addProperty(Constants.METHOD_STR,"deleteCustomer");
            RequestProcessMap.put("/customer/delete", json);

            json = new JsonObject();
            json.addProperty(Constants.REQUEST_METHOD_STR, Constants.GET_STR);
            json.add(Constants.REQUEST_DATA_STR, null);
            json.addProperty(Constants.CLASS_STR, "Customer.CustomerUtil");
            json.addProperty(Constants.METHOD_STR,"getCustomerList");
            RequestProcessMap.put("/customer/list", json);

        }
    }


}
