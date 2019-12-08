/*
 * Small example demonstrating how to iterate through a Sparx Model.
 */
package com.reedmanit.ea.examples;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import org.sparx.Collection;
import org.sparx.Repository;

/**
 *
 * @author paul reedman
 */
public class ListPackages {

    /**
     * @param args the command line arguments
     */
    private static Repository rep;

    public static void main(String[] args) {

        try {

            Scanner input = new Scanner(System.in);

            System.out.print("Enter file name for sparx model: ");
            String repoLocation = input.next();
            System.out.println("File entered = " + repoLocation);
            
            Path path = Paths.get(repoLocation);
            
            if (Files.notExists(path)) {
                throw new Exception("File does not exist");
            }

            rep = new Repository();  // get a repository

            
            rep.OpenFile(repoLocation);  //open the repository file
            
            // Get the first Model node Iterate through all views in the model
            
            org.sparx.Package myModel = rep.GetModels().GetAt((short) 0);

            Collection<org.sparx.Package> views = myModel.GetPackages();

            for (short i = 0; i < views.GetCount(); i++) {
                org.sparx.Package currentView = views.GetAt(i);

                System.out.println("View: " + currentView.GetName());
            }

        } catch (Exception e) {
            System.out.println(e.toString());   
        }

    }

}
