package hu.adsd;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class MaterialDatabaseParser
{
    public ArrayList<Material> getMaterialList()
    {
        try
        {
            return parseDatabase();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private ArrayList<Material> parseDatabase() throws Exception
    {
        ArrayList<Material> materialArrayList = new ArrayList<>();

        try
        {
            JSONParser jsonParser = new JSONParser();
            FileReader in = new FileReader( "src/main/resources/MaterialDatabase.JSON" );
            JSONObject jsonObject = (JSONObject) jsonParser.parse( in );
            JSONArray jsonArray = (JSONArray) jsonObject.get("materials");

            for (Object object : jsonArray)
            {
                JSONObject materialObject = (JSONObject) object;

                String materialId = (String) materialObject.get("id");

                String materialName = (String) materialObject.get("name");

                String materialCarbon = (String) materialObject.get("carbon");

                String materialCirculationType = (String) materialObject.get("circulationtype");

                String materialQuantity = (String) materialObject.get("quantity");

                materialArrayList.add(new Material(Integer.valueOf(materialId), materialName, Integer.valueOf(materialCarbon), CirculationType.valueOf(materialCirculationType), Integer.valueOf(materialQuantity)));
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return materialArrayList;
    }
}
