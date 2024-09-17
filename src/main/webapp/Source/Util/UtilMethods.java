package Util;

import java.io.*;
import java.sql.ResultSet;
import java.util.Objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class UtilMethods
{
    public static JsonArray convertResultToJson(ResultSet resultSet) throws Exception {

        JsonArray jsonArray = new JsonArray();

        while (resultSet.next()) {

            int columns = resultSet.getMetaData().getColumnCount();
            JsonObject obj = new JsonObject();

            for (int i = 0; i < columns; i++)
            {
                String key = resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase();
                String value = String.valueOf(resultSet.getObject(i + 1));
                if (key.startsWith("is"))
                {
                    value = (value.equals("1"))? "Yes": "No";
                }
                obj.addProperty(key, value);
            }

            jsonArray.add(obj);
        }
        return jsonArray;
    }

    public static boolean isImport(HttpServletRequest request)
    {
        return request.getRequestURI().contains("import");
    }

    public static FileInputStream convertToXls(InputStream inputStream) throws IOException
    {

        File tempFile = File.createTempFile("tempfile", ".xlsx");

        try (FileOutputStream out = new FileOutputStream(tempFile))
        {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1)
            {
                out.write(buffer, 0, bytesRead);
            }
        }
        return new FileInputStream(tempFile);
    }

    public static Object getValueFromCellInExcel(Row row, Integer columnIndex)
    {
        Object value = "";
        if (columnIndex != null && row.getCell(columnIndex) != null)
        {
            Cell cell = row.getCell(columnIndex);
            switch (cell.getCellType())
            {
                case STRING:
                    value = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    value = (long) cell.getNumericCellValue();
                    break;
                default:
                    value = "";
                    break;
            }
        }
        return value;
    }
}
