package hu.adsd.csvgenerator;

import hu.adsd.products.Product;
import hu.adsd.projects.BuildingPart;
import hu.adsd.projects.ProductsConfiguration;

import java.io.FileWriter;
import java.io.IOException;

public class CSVGenerator
{
    public void exportConfiguration( ProductsConfiguration productsConfiguration ) throws IOException
    {
        FileWriter bomWriter = new FileWriter( "BOM.csv" );

        bomWriter.append( "Bouwmateriaal, aantal, id\n" );

        for ( BuildingPart buildingPart : productsConfiguration.getBuildingParts() )
        {
            for ( Product product : buildingPart.getProducts() )
            {
                bomWriter.append(
                        String.format(
                                "%s, %s, %s%n",
                                product.getName(),
                                product.getQuantity(),
                                product.getId()
                        )
                );
            }
        }

        bomWriter.flush();
        bomWriter.close();
    }
}
