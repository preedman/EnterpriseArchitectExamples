/*
 * 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
 * @author Paul Reedman
 */
public class CreateItems {

    /**
     * @param args the command line arguments
     */
    private static Repository rep;
    private static String rootPackageID;
    private static String viewPackageGUID;
    private static String packageGUID;
    private static short i;
    private static String VIEWNAME = "MyView";
    private static String PACKAGENAME = "MyPackage";

    public static void main(String[] args) {
        // TODO code application logic here

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
            
                     
            
            createView(VIEWNAME);
            createPackage(viewPackageGUID);
            
            System.out.println("Finished successfully");

        } catch (Exception e) {
            System.out.println(e.toString());
            
        } finally {
            rep.CloseFile();
        }
    }
    

    private static void createView(String viewName) {

        try {
            i = 0;

            System.out.println(rep.GetModels().GetAt(i).GetName());

            rootPackageID = rep.GetModels().GetAt(i).GetPackageGUID();  // get the root package key

            org.sparx.Package root = rep.GetPackageByGuid(rootPackageID);  // get the root 

            Collection<org.sparx.Package> subPackages = root.GetPackages(); // get the packages in the root

            org.sparx.Package theView = subPackages.AddNew(viewName, ""); // create a view under the root 

            theView.SetFlags("VICON=4;");   // This is a flag which indicates that the package is a view.
                                            // The different numbers indicate a specific icon.
                                            // The values are from 0 to 5.

            theView.Update();  // update the packages

            viewPackageGUID = theView.GetPackageGUID();   // store the GUID for the view, use it later

            rep.GetModels().Refresh();  // refresh the repo
            
        } catch (Exception e) {
            throw e;
        }

    }
    
    private static void createPackage(String aGUID) {
        
        org.sparx.Package thePackage = rep.GetPackageByGuid(aGUID);  // everything is a package
                                                                     // so find the starting point
        
        Collection<org.sparx.Package> subPackages = thePackage.GetPackages();  // geth the list of packages
        
        org.sparx.Package newPackage = subPackages.AddNew(PACKAGENAME, "");  // add a new package to list
        
        newPackage.Update();  // update
        
        packageGUID = newPackage.GetPackageGUID();  // get the GUID for the package just created, used later
        
        newPackage.GetPackages().Refresh();   // refresh the packages
     
        rep.GetModels().Refresh();  // refresh the repo
    }

}
