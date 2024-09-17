package ServletProcessor;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class StreamDataMap
{
    public static JsonObject RequestDataMap = new JsonObject();
    static{

        JsonArray dataMap = new JsonArray();
        JsonObject data =  new JsonObject();
        data.addProperty("name","username");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z0-9@]+");
        data.addProperty("max-len",32);
        data.addProperty("min-len", 7);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","password");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z0-9@]+");
        data.addProperty("max-len",32);
        data.addProperty("min-len", 8);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","name");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z ]+");
        data.addProperty("max-len",50);
        data.addProperty("min-len", 3);
        dataMap.add(data);

        RequestDataMap.addProperty("createUser", dataMap.toString());

        dataMap = new JsonArray();
        data =  new JsonObject();
        data.addProperty("name","username");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z0-9@]+");
        data.addProperty("max-len",32);
        data.addProperty("min-len", 4);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","password");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z0-9@]+");
        data.addProperty("max-len",32);
        data.addProperty("min-len", 8);
        dataMap.add(data);
        RequestDataMap.addProperty("signInUser", dataMap.toString());

        dataMap = new JsonArray();

        data = new JsonObject();
        data.addProperty("name","name");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z ]+");
        data.addProperty("max-len",50);
        data.addProperty("min-len", 3);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","startingpoint");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z ]+");
        data.addProperty("max-len",50);
        data.addProperty("min-len", 3);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","destination");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z ]+");
        data.addProperty("max-len",50);
        data.addProperty("min-len", 3);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","description");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z0-9 ]+");
        data.addProperty("max-len",50);
        data.addProperty("min-len", 3);
        dataMap.add(data);
        RequestDataMap.addProperty("createTour", dataMap.toString());

        dataMap = new JsonArray();
        data = new JsonObject();
        data.addProperty("name","name");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z ]+");
        data.addProperty("max-len",50);
        data.addProperty("min-len", 3);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","startingpoint");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z ]+");
        data.addProperty("max-len",50);
        data.addProperty("min-len", 3);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","destination");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z ]+");
        data.addProperty("max-len",50);
        data.addProperty("min-len", 3);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","description");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z ]+");
        data.addProperty("max-len",50);
        data.addProperty("min-len", 3);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","tourid");
        data.addProperty("from","param");
        data.addProperty("regex","[0-9]+");
        data.addProperty("max-len",10);
        data.addProperty("min-len", 1);
        dataMap.add(data);
        RequestDataMap.addProperty("updateTour", dataMap.toString());

        dataMap = new JsonArray();
        data = new JsonObject();
        data.addProperty("name","entityid");
        data.addProperty("from","param");
        data.addProperty("regex","[0-9]+");
        data.addProperty("max-len",10);
        data.addProperty("min-len", 1);
        dataMap.add(data);
        RequestDataMap.addProperty("entity", dataMap.toString());

        dataMap = new JsonArray();
        data = new JsonObject();
        data.addProperty("name","tourid");
        data.addProperty("from","stream");
        data.addProperty("regex","[0-9]+");
        data.addProperty("max-len",10);
        data.addProperty("min-len", 1);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","customerlist");
        data.addProperty("from","stream");
        data.addProperty("regex","[0-9,]+");
        data.addProperty("max-len",50);
        data.addProperty("min-len", 1);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","price");
        data.addProperty("from","stream");
        data.addProperty("regex","[0-9]+");
        data.addProperty("max-len",8);
        data.addProperty("min-len", 1);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","description");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z0-9 ]+");
        data.addProperty("max-len",50);
        data.addProperty("min-len", 3);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","startdate");
        data.addProperty("from","stream");
        data.addProperty("regex","[0-9 -]+");
        data.addProperty("max-len",10);
        data.addProperty("min-len", 10);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","enddate");
        data.addProperty("from","stream");
        data.addProperty("regex","[0-9 -]+");
        data.addProperty("max-len",10);
        data.addProperty("min-len", 10);
        dataMap.add(data);
        RequestDataMap.addProperty("createTrip", dataMap.toString());

        dataMap = new JsonArray();
        data = new JsonObject();
        data.addProperty("name","tourid");
        data.addProperty("from","stream");
        data.addProperty("regex","[0-9]+");
        data.addProperty("max-len",10);
        data.addProperty("min-len", 1);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","customerlist");
        data.addProperty("from","stream");
        data.addProperty("regex","[0-9,]+");
        data.addProperty("max-len",50);
        data.addProperty("min-len", 3);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","price");
        data.addProperty("from","stream");
        data.addProperty("regex","[0-9]+");
        data.addProperty("max-len",8);
        data.addProperty("min-len", 1);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","description");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z0-9 ]+");
        data.addProperty("max-len",50);
        data.addProperty("min-len", 3);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","startdate");
        data.addProperty("from","stream");
        data.addProperty("regex","[0-9 -]+");
        data.addProperty("max-len",8);
        data.addProperty("min-len", 1);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","enddate");
        data.addProperty("from","stream");
        data.addProperty("regex","[0-9 -]+");
        data.addProperty("max-len",50);
        data.addProperty("min-len", 3);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","tripid");
        data.addProperty("from","param");
        data.addProperty("regex","[0-9]+");
        data.addProperty("max-len",10);
        data.addProperty("min-len", 1);
        RequestDataMap.addProperty("updateTrip", dataMap.toString());

        dataMap = new JsonArray();
        data = new JsonObject();
        data.addProperty("name","name");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z ]+");
        data.addProperty("max-len",50);
        data.addProperty("min-len", 5);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","description");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z0-9 ]+");
        data.addProperty("isMandatory", false);
        data.addProperty("max-len",50);
        data.addProperty("min-len", 3);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","occupation");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z ]+");
        data.addProperty("isMandatory", false);
        data.addProperty("max-len",50);
        data.addProperty("min-len", 3);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","dateofbirth");
        data.addProperty("from","stream");
        data.addProperty("regex","[0-9 -]+");
        data.addProperty("isMandatory", false);
        data.addProperty("max-len",15);
        data.addProperty("min-len", 6);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","dateofmarriage");
        data.addProperty("from","stream");
        data.addProperty("regex","[0-9 -]+");
        data.addProperty("isMandatory", false);
        data.addProperty("max-len",8);
        data.addProperty("min-len", 1);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","aadharnum");
        data.addProperty("from","stream");
        data.addProperty("regex","[0-9]+");
        data.addProperty("max-len",12);
        data.addProperty("min-len", 12);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","passportnum");
        data.addProperty("from","stream");
        data.addProperty("regex","[0-9]+");
        data.addProperty("isMandatory", false);
        data.addProperty("max-len",30);
        data.addProperty("min-len", 10);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","passportexp");
        data.addProperty("from","stream");
        data.addProperty("regex","[0-9/]+");
        data.addProperty("isMandatory", false);
        data.addProperty("max-len",20);
        data.addProperty("min-len", 5);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","address");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z0-9 ]+");
        data.addProperty("max-len",50);
        data.addProperty("min-len", 5);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","mobilenum");
        data.addProperty("from","stream");
        data.addProperty("regex","[0-9]+");
        data.addProperty("max-len",15);
        data.addProperty("min-len", 9);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","emailid");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z0-9@.]+");
        data.addProperty("max-len",50);
        data.addProperty("min-len", 5);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","gender");
        data.addProperty("from","stream");
        data.addProperty("regex","[Male|Female]{4,6}");
        data.addProperty("isHandlingRequired", true);
        data.addProperty("max-len",6);
        data.addProperty("min-len", 4);
        dataMap.add(data);
        RequestDataMap.addProperty("createCustomer", dataMap.toString());

        dataMap = new JsonArray();
        data = new JsonObject();
        data.addProperty("name","entityid");
        data.addProperty("from","stream");
        data.addProperty("regex","[0-9]+");
        data.addProperty("max-len",10);
        data.addProperty("min-len", 1);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","name");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z ]+");
        data.addProperty("max-len",50);
        data.addProperty("min-len", 5);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","description");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z0-9 ]+");
        data.addProperty("max-len",50);
        data.addProperty("min-len", 3);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","dateofbirth");
        data.addProperty("from","stream");
        data.addProperty("regex","[0-9 -]+");
        data.addProperty("max-len",15);
        data.addProperty("min-len", 6);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","dateofmarriage");
        data.addProperty("from","stream");
        data.addProperty("regex","[0-9 -]+");
        data.addProperty("max-len",8);
        data.addProperty("min-len", 1);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","aadharnum");
        data.addProperty("from","stream");
        data.addProperty("regex","[0-9]+");
        data.addProperty("max-len",30);
        data.addProperty("min-len", 16);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","passportnum");
        data.addProperty("from","stream");
        data.addProperty("regex","[0-9]+");
        data.addProperty("max-len",30);
        data.addProperty("min-len", 10);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","passportexp");
        data.addProperty("from","stream");
        data.addProperty("regex","[0-9/]+");
        data.addProperty("max-len",20);
        data.addProperty("min-len", 5);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","address");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z]+");
        data.addProperty("max-len",50);
        data.addProperty("min-len", 5);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","mobilenum");
        data.addProperty("from","stream");
        data.addProperty("regex","[0-9]+");
        data.addProperty("max-len",15);
        data.addProperty("min-len", 9);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","emailid");
        data.addProperty("from","stream");
        data.addProperty("regex","[a-zA-Z0-9@]+");
        data.addProperty("max-len",50);
        data.addProperty("min-len", 5);
        dataMap.add(data);

        data = new JsonObject();
        data.addProperty("name","gender");
        data.addProperty("from","stream");
        data.addProperty("regex","[Male|Female]{4,6}");
        data.addProperty("isHandlingRequired", true);
        data.addProperty("max-len",6);
        data.addProperty("min-len", 4);
        dataMap.add(data);
        RequestDataMap.addProperty("updateCustomer", dataMap.toString());

        data = new JsonObject();


    }
}
