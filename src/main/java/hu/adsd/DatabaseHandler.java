package hu.adsd;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseHandler
{
    //Returns a list of all products in database
    public ArrayList<Product> getMaterialList()
    {
        try
        {
            return parseDatabase();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Product getProductById( int id )
    {
        return null;
    }

    public void updateProduct( Product product )
    {
        //
    }

    public void removeProductById( int id )
    {
        //
    }

    //Returns a list of all products in database
    private ArrayList<Product> parseDatabase()
    {
        ArrayList<Product> productArrayList = new ArrayList<>();

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:src/main/resources/database_sqlite.db");

        //Connection with database started, closes automatically at end of code block
        try ( Connection c = dataSource.getConnection() )
        {
            String sql = "SELECT * FROM products;";

            //Run SQL query and get the results, closes automatically at end of code block
            try
                    (
                            Statement stmt = c.createStatement();
                            ResultSet rs = stmt.executeQuery(sql)
                    )
            {
                //each item in database initialized as a Product and put into ArrayList
                while ( rs.next() )
                {
                    int id = rs.getInt( "id" );
                    String name = rs.getString( "name" );
                    String carbon = rs.getString( "co2" );
                    String circulationType = rs.getString( "materialtype" );
                    int quantity = rs.getInt( "units" );


                    Product product = new Product( id, name, carbon, CirculationType.valueOf( circulationType ), quantity );
                    productArrayList.add( product );
                }
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }

        return productArrayList;
    }
}
