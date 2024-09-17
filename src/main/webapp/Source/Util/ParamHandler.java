package Util;

public class ParamHandler
{
    public static String handleParam(String param, String value)
    {
        switch (param)
        {
            case "gender":
                value = value.equals("Male") ? "0" : "1";
        }
        return value;
    }
}
