package hu.adsd;

import java.util.ArrayList;

public class Tester
{

    public static void test1()
    {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Product product;

        product = databaseHandler.getProductById(5);

        System.out.println(product.getName());
    }

    public static void test2()
    {
       DatabaseHandler databaseHandler = new DatabaseHandler();
        ArrayList<Product> productArrayList = new ArrayList<>();

        productArrayList = databaseHandler.getMaterialList();

        Product product = productArrayList.get(3);

        product.setQuantity(23);

        try
        {
            databaseHandler.updateProduct(product);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void test3()
    {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        try
        {
            databaseHandler.removeProductById(4);
        }
        catch (Exception e)
        {

        }
    }

    public static void main(String[] args)
    {
        test1();
    }
}
