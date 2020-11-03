package hu.adsd.dataservice;

import hu.adsd.products.CirculationType;
import hu.adsd.products.Product;
import hu.adsd.products.ProductSort;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ApiHandler
{
    public List<Product> getProductsFilteredSortedAndByRoomFromAPI( String filter, ProductSort productsort, String room )
    {
        List<Product> productsList = new ArrayList<>();

        JSONParser parser = new JSONParser();

        try {
            URL url = new URL( "http://localhost:8080/data" );
            URLConnection urlConnection = url.openConnection();
            JSONArray array = (JSONArray) parser.parse( new InputStreamReader(urlConnection.getInputStream()) );

            for ( Object object : array )
            {
                JSONObject producObject = (JSONObject) object;

                Long id = (Long) producObject.get( "id" );
                String name = (String) producObject.get( "name" );
                String circulation = (String) producObject.get( "circulation" );
                Number carbon = (Number) producObject.get( "carbon" );
                Number energy = (Number) producObject.get( "energy" );
                Number refCarbon = (Number) producObject.get( "refCarbon" );
                Number refEnergy = (Number) producObject.get( "refEnergy" );
                String roomAPI = (String) producObject.get( "room" );

                Product product = new Product(
                        id.intValue(),
                        name,
                        100,
                        CirculationType.valueOf( circulation ),
                        carbon.doubleValue(),
                        energy.doubleValue(),
                        refCarbon.doubleValue(),
                        refEnergy.doubleValue(),
                        roomAPI
                );

                if ( product.getBuildingPart().equals(room) )
                {
                    productsList.add(product);
                }
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        return productsList;
    }
}
