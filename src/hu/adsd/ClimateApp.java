package hu.adsd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClimateApp
{
    private Project project;
    private List<Material> availableMaterials;
    private Scanner scanner;

    public ClimateApp()
    {
        // Initialisation
        this.project = new Project();
        this.availableMaterials = new ArrayList<>();
        this.scanner = new Scanner( System.in );

        Material material1 = new Material( 1, "Wasbak", 30, RecycleType.RECYCLED, 30 );
        Material material2 = new Material( 2, "Wasbak", 0, RecycleType.REUSED, 10 );
        Material material3 = new Material( 3, "Wasbak", 100, RecycleType.NEW, 1000 );

        availableMaterials.add( material1 );
        availableMaterials.add( material2 );
        availableMaterials.add( material3 );

        getHomePage();
    }

    private void getHomePage()
    {
        while ( true )
        {
            // Option menu
            System.out.println( "\nOptions:");
            System.out.println( "1. Add item to project");
            System.out.println("2. Edit project list");
            System.out.println("3. Quit program");
            String answer = scanner.next();

            if ( answer.equals( "1" ))
            {
                System.out.println( "\nChoose product:");

                // Display available products
                for ( Material material : availableMaterials )
                {
                    System.out.println( material.toString() );
                }

                String materialSelected = scanner.next();
                project.addProjectMaterial( availableMaterials.get( Integer.parseInt( materialSelected ) ) );
            }
            else if ( answer.equals( "2" ))
            {
                // Check project material not empty
                if ( project.getProjectMaterials().size() == 0 )
                {
                    System.out.println( "\nThere are no products in project" );
                }
                else
                {
                    System.out.println( "\nThe following product are used" );

                    for ( Material material : project.getProjectMaterials() )
                    {
                        System.out.println( material.toString() );
                    }

                    System.out.println( "\nChoose item to edit:" );
                    String itemSelected = scanner.next();

                    System.out.println("\nEdit quantity of product:");
                    String quantity = scanner.next();

                    if ( quantity.equals( "0" ) )
                    {
                        project.removeProjectMaterial( Integer.parseInt( itemSelected ) );
                    }
                    else
                    {
                        int index = Integer.parseInt( itemSelected );

                        Material material = project.getProjectMaterials().get( index );
                        material.setQuantity( Integer.parseInt( quantity ) );

                        project.getProjectMaterials().set( index, material );
                    }
                }
            }
            else
            {
                return;
            }
        }
    }
}
