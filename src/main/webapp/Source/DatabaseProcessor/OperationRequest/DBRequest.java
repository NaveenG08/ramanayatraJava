package DatabaseProcessor.OperationRequest;

import DatabaseProcessor.Constants.RequestConstants;

import java.util.Map;

public abstract class DBRequest
{
    public static Object operate(int entity, int requestType, Object data, Map requestMap) throws Exception {
        DBRequest processor = (DBRequest) getOperationProcessor(requestType);
        return processor.processDB(entity, data, requestMap);
    }
    private static Object getOperationProcessor(int requestType)
    {
        DBRequest processor = null;
        switch (requestType)
        {
            case RequestConstants.READ:
                processor = new ReadRequest();
                break;
            case RequestConstants.WRITE:
                processor = new WriteRequest();
                break;
            case RequestConstants.DELETE:
                processor = new DeleteRequest();
                break;
            case RequestConstants.UPDATE:
                processor = new UpdateRequest();
                break;
        }
        return processor;
    }

    public abstract Object processDB(int entity, Object object, Map requestMap) throws Exception;
}
