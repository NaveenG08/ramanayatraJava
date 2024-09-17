package DatabaseProcessor.Constants;

import Tables.*;

import java.util.HashMap;
import java.util.Map;

public class EntityConstants
{
    public static int USER = 1;
    public static int SESSION = 2;
    public static int TOUR = 3;
    public static int TRIP = 4;
    public static int CUSTOMER = 5;
    public static Map<Integer , Class> ENTITY_TABLE_MAP = new HashMap();
    static
    {
        ENTITY_TABLE_MAP.put(USER, UserTable.class);
        ENTITY_TABLE_MAP.put(SESSION, SessionTable.class);
        ENTITY_TABLE_MAP.put(TRIP, TripTable.class);
        ENTITY_TABLE_MAP.put(TOUR, TourTable.class);
        ENTITY_TABLE_MAP.put(CUSTOMER, CustomerTable.class);
    }
}
