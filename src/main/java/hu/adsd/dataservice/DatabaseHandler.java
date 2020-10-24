package hu.adsd.dataservice;

import hu.adsd.products.CirculationType;
import hu.adsd.products.Product;
import hu.adsd.products.ProductSort;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.sqlite.SQLiteDataSource;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Removed all unused code from this class in accordance with clean code guidelines
 *
 * In order to review old code checkout the tag v0.3.0
 *
 * Lastly this class is package private,meaning only classes from within this package should use it
 * In order to use any methods use DataServiceImpl from within other classes
 */
class DatabaseHandler
{
    public List<Product> getProductsFilteredSortedAndByRoom( String filter, ProductSort productsort, String room )
    {
        List<Product> productsList = new ArrayList<>();

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl( "jdbc:sqlite:src/main/resources/database_sqlite.db" );

        // Placeholder ? can only be replaced by values but not column and sort order
        // So we have to do String.format
        String SQL = String.format( "SELECT * FROM products WHERE name LIKE ? AND room = ? ORDER BY %S", productsort );

        try ( Connection connection = dataSource.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement( SQL ) )
        {
            // The % sign before and after filter term will match any name containing the criteria
            preparedStatement.setString( 1, "%" + filter + "%" );
            preparedStatement.setString( 2, room );

            try ( ResultSet resultSet = preparedStatement.executeQuery() )
            {
                while ( resultSet.next() )
                {
                    productsList.add( new Product(
                            resultSet.getInt( "id" ),
                            resultSet.getString( "name" ),
                            100,
                            CirculationType.LINEAR,
                            resultSet.getDouble( "carbon" ),
                            resultSet.getDouble( "energy" ),
                            resultSet.getDouble( "refCarbon" ),
                            resultSet.getDouble( "refEnergy" ),
                            resultSet.getString( "room" )
                    ) );
                }
            }
        }
        catch ( SQLException throwables )
        {
            throwables.printStackTrace();
        }

        return productsList;
    }
    
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
                double carbon = (Double) producObject.get( "carbon" );
                double energy = (Double) producObject.get( "energy" );
                double refCarbon = (Double) producObject.get( "refCarbon" );
                double refEnergy = (Double) producObject.get( "refEnergy" );
                String roomAPI = (String) producObject.get( "room" );

                Product product = new Product( id.intValue(), name, 100, CirculationType.LINEAR, carbon, energy, refCarbon, refEnergy, roomAPI );

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