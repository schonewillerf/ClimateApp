package hu.adsd;

import java.util.List;
import java.util.Scanner;

public class ClimateApp
{
    private final Project project;
    private final List<Material> availableMaterials;
    private final Scanner scanner;

    public ClimateApp()
    {
        // Initialisation
        this.project = new Project();
        this.availableMaterials = new MaterialDatabaseParser().getMaterialList();
        this.scanner = new Scanner( System.in );

        // Show UI in Console
        showUI();
    }

    private void showUI()
    {
        // println welkom tekst
        while ( true )
        {
            // Option menu
            System.out.println( "\nOptions:" );
            System.out.println( "1. Show available products" );
            System.out.println( "2. Show products used in project" );
            System.out.println( "3. Quit program" );
            String answer = scanner.next();

            if ( answer.equals( "1" ) )
            {
                System.out.println( "\nAvailable products:" );

                // Display available products
                for ( int i = 0; i < availableMaterials.size(); i++ )
                {
                    System.out.printf( "%s. %s\n", i + 1, availableMaterials.get( i ).toString() );
                }

                System.out.println( "\nChoose product to add to project or press 0 to return:" );

                String materialSelected = scanner.next();

                if ( !materialSelected.equals( "0" ) )
                {
                    Material material = availableMaterials.get( Integer.parseInt( materialSelected ) - 1 );

                    System.out.println( "\nEnter amount of product:" );

                    String amountOfProduct = scanner.next();
                    int amountOfProductAsInteger = Integer.parseInt( amountOfProduct );

                    // Lower inventory by amount
                    material.setQuantity( material.getQuantity() - amountOfProductAsInteger );

                    // Create a new material object
                    Material projectMaterial = new Material(
                            material.getId(),
                            material.getName(),
                            material.getCarbonAmount(),
                            material.getCirculationType(),
                            amountOfProductAsInteger
                    );

                    project.addProjectMaterial( projectMaterial );
                }
            }
            else if ( answer.equals( "2" ) )
            {
                // Check project material not empty
                if ( project.getProjectMaterials().size() == 0 )
                {
                    System.out.println( "\nThere are no products used in project" );
                }
                else
                {
                    // while (boolean) loop, wanneer quantity gewijzigd word. Word 0 (terug) ingevoerd? boolean op false
                    System.out.println( "\nThe following product are used in project" );

                    int totalAmountOfCarbon = 0;

                    for ( int i = 0; i < project.getProjectMaterials().size(); i++ )
                    {
                        System.out.printf( "%s. %s\n", i + 1, project.getProjectMaterial( i ).toString() );

                        totalAmountOfCarbon += project.getProjectMaterial( i ).getTotalCarbonAmount();
                    }

                    System.out.printf( "The total carbon amount in the project is: %s\n", totalAmountOfCarbon );

                    System.out.println( "\nChoose item to editor or press 0 to return:" );
                    String itemSelected = scanner.next();

                    // niet alleen checken op 0 (return) maar ook producten lijst controleren of dit getal erin past
                    if ( !itemSelected.equals( "0" ) )
                    {
                        System.out.println( "\nEdit quantity of product:" );
                        String quantity = scanner.next();

                        int index = Integer.parseInt( itemSelected ) - 1;

                        Material material = project.getProjectMaterial( index );

                        if ( quantity.equals( "0" ) )
                        {
                            for ( Material materialLoop : availableMaterials )
                            {
                                if ( materialLoop.getId() == material.getId() )
                                {
                                    materialLoop.setQuantity( materialLoop.getQuantity() + material.getQuantity() );
                                }
                            }
                            project.removeProjectMaterial( index );
                        }
                        else
                        {
                            // Loop through availableMaterials to find id match with material
                            for ( Material materialLoop : availableMaterials )
                            {
                                if ( materialLoop.getId() == material.getId() )
                                {
                                    // If new quantity is smaller add diffrence to material in availablematerials
                                    if ( material.getQuantity() > Integer.parseInt( quantity ) )
                                    {
                                        materialLoop.setQuantity( materialLoop.getQuantity() +
                                                                  ( material.getQuantity() - Integer.parseInt( quantity ) ) );
                                    }
                                    // If new quantity is bigger subtract difference from material in availablematerials
                                    else if ( material.getQuantity() < Integer.parseInt( quantity ) )
                                    {
                                        materialLoop.setQuantity( materialLoop.getQuantity() -
                                                                  ( Integer.parseInt( quantity ) - material.getQuantity() ) );
                                    }
                                }
                            }

                            material.setQuantity( Integer.parseInt( quantity ) );

                            project.updateProjectMaterial( index, material );
                        }
                    }
                    // einde while loop
                }
            }
            else if ( answer.equals( "3" ) )
            {
                // println gemaakt door codeninjas en succesvol afgesloten melding
                return;
            }
            else
            {
                // println melding verkeerd ingevoerd probeer opnieuw
            }
        }
    }
}
