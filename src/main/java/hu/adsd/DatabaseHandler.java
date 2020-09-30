package hu.adsd;

import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler
{
    //Returns a list of all products in database
    // Should be used in the productsListView
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

    public ArrayList<Product> getProjectProductsList()
    {
        // Should fetch product from prejectproduct table with quantity
        try
        {
            return parseGetProjectProductsList();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    //Returns product with given id from database
    // Maybe applicable later for product-detail view
    public Product getProductById( int id )
    {
        try
        {
            return parseGetProductById( id );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            return null;
        }
    }

    //Updates quantity of given product in database
    // Future Admin method
    public void updateProduct( Product product )
    {
        try
        {
            parseUpdateProduct( product );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    //Removes product with given id from database
    // Future admin task
    public void removeProductById( int id )
    {
        try
        {
            parseRemoveProductById( id );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    // Should be executed from projectView Edit -> Save Button
    public void updateProjectProduct( Product product )
    {
        // Update product based on id in projectproducts table
        try
        {
            parseUpdateProjectProduct(product);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Should be executed from projectView Delete Button
    public void removeProjectProduct( int id )
    {
        // Remove product based on id in projectproducts table
        try
        {
               parseRemoveProjectProduct( id );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    // Should be executed from productCard add Button
    public void addProductToProjectById( int id )
    {
        // Add product by id to projectproducts table
        // Default quantity is 1??
        try
        {
            parseAddProductToProjectById( id );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    //Returns a list of all products in database
    private ArrayList<Product> parseDatabase() throws SQLException
    {
        ArrayList<Product> productArrayList = new ArrayList<>();

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl( "jdbc:sqlite:src/main/resources/database_sqlite.db" );

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
                    double minCarbon = rs.getDouble( "co2_min" );
                    double maxCarbon = rs.getDouble("co2_max");
                    String circulationType = rs.getString( "materialtype" );
                    int quantity = rs.getInt( "units" );


                    Product product = new Product( id, name, minCarbon, maxCarbon, CirculationType.valueOf( circulationType ), quantity );
                    productArrayList.add( product );
                }
            }
        }

        return productArrayList;
    }

    private ArrayList<Product> parseGetProjectProductsList() throws SQLException
    {
        ArrayList<Product> productList = new ArrayList<>();

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl( "jdbc:sqlite:src/main/resources/database_sqlite.db" );

        try ( Connection c = dataSource.getConnection() )
        {
            String sql = "SELECT products.*, project.units FROM project " +
                    "INNER JOIN products ON project.productid = products.id";

            try
                    (
                            Statement stmt = c.createStatement();
                            ResultSet rs = stmt.executeQuery(sql)
                    )
            {
                while ( rs.next() )
                {
                    int dataId = rs.getInt( "id" );
                    String name = rs.getString( "name" );
                    double minCarbon = rs.getDouble( "co2_min" );
                    double maxCarbon = rs.getDouble("co2_max");
                    String circulationType = rs.getString( "materialtype" );
                    int quantity = rs.getInt( "units" );


                    Product product = new Product( dataId, name, minCarbon, maxCarbon, CirculationType.valueOf( circulationType ), quantity );
                    productList.add( product );
                }
            }
        }

        return productList;
    }

    //Returns product with given id from database
    private Product parseGetProductById( int id ) throws SQLException
    {
        Product product = null;

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl( "jdbc:sqlite:src/main/resources/database_sqlite.db" );

        //Connection with database started, closes automatically at end of code block
        try ( Connection c = dataSource.getConnection() )
        {
            String sql = "SELECT * FROM products WHERE id=" + id + ";";

            //Run SQL query and get the results, closes automatically at end of code block
            try
                    (
                            Statement stmt = c.createStatement();
                            ResultSet rs = stmt.executeQuery(sql)
                    )
            {
                //Result is put into new Product
                while ( rs.next() )
                {
                    int dataId = rs.getInt( "id" );
                    String name = rs.getString( "name" );
                    double minCarbon = rs.getDouble( "co2_min" );
                    double maxCarbon = rs.getDouble("co2_max");
                    String circulationType = rs.getString( "materialtype" );


                    product = new Product( dataId, name, minCarbon, maxCarbon, CirculationType.valueOf( circulationType ), 1 );
                }
            }
        }

        return product;
    }

    //Updates quantity of given product in database
    private void parseUpdateProduct( Product product ) throws SQLException
    {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl( "jdbc:sqlite:src/main/resources/database_sqlite.db" );

        String SQL = "UPDATE products SET units= ? WHERE id = ?";

        try
                (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement( SQL )
                )
        {
            preparedStatement.setInt( 1, product.getQuantity() );
            preparedStatement.setInt( 2, product.getId() );


            int betrokkenRijen = preparedStatement.executeUpdate();

            if ( betrokkenRijen != 1 )
            {
                throw new SQLException(
                        String.format( "Updaten van Product gefaald." )
                );
            }
        }
    }

    //Removes product with given id from database
    private void parseRemoveProductById( int id ) throws SQLException
    {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl( "jdbc:sqlite:src/main/resources/database_sqlite.db" );

        String SQL = "DELETE from products WHERE id = ?";

        try
                (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement( SQL )
                )
        {
            preparedStatement.setInt( 1, id );

            int betrokkenRijen = preparedStatement.executeUpdate();

            if ( betrokkenRijen != 1 )
            {
                throw new SQLException(
                        String.format( "Verwijderen van product gefaald" )
                );
            }
        }
    }

    private void parseUpdateProjectProduct(Product product) throws SQLException
    {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl( "jdbc:sqlite:src/main/resources/database_sqlite.db" );

        String SQL = "UPDATE project SET units= ? WHERE productid = ?";

        try
                (
                        Connection connection = dataSource.getConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement( SQL )
                )
        {
            preparedStatement.setInt( 1, product.getQuantity() );
            preparedStatement.setInt( 2, product.getId() );


            int betrokkenRijen = preparedStatement.executeUpdate();

            if ( betrokkenRijen != 1 )
            {
                throw new SQLException(
                        String.format( "Updaten van Product gefaald." )
                );
            }
        }
    }

    private void parseRemoveProjectProduct( int id ) throws SQLException
    {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl( "jdbc:sqlite:src/main/resources/database_sqlite.db" );

        String SQL = "DELETE from project WHERE id = ?";

        try
                (
                        Connection connection = dataSource.getConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement( SQL )
                )
        {
            preparedStatement.setInt( 1, id );

            int betrokkenRijen = preparedStatement.executeUpdate();

            if ( betrokkenRijen != 1 )
            {
                throw new SQLException(
                        String.format( "Verwijderen van product gefaald" )
                );
            }
        }
    }

    private void parseAddProductToProjectById( int id ) throws SQLException
    {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl( "jdbc:sqlite:src/main/resources/database_sqlite.db" );

        String SQL = "INSERT INTO project (productid, units) VALUES (?, 1) ON CONFLICT(productid) DO UPDATE SET units = units + 1;";

        try
                (
                        Connection connection = dataSource.getConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement( SQL )
                )
        {
            preparedStatement.setInt( 1, id );

            int betrokkenRijen = preparedStatement.executeUpdate();

            if ( betrokkenRijen != 1 )
            {
                throw new SQLException(
                        String.format( "Toevoegen van Product gefaald." )
                );
            }
        }
    }
}
