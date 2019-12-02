/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reedmanit.ea.examples;

import org.sparx.Collection;
import org.sparx.Repository;

/**
 *
 * @author paul
 */
public class ListPackages {

    /**
     * @param args the command line arguments
     */
    private static Repository rep;

    public static void main(String[] args) {
        
        try {
            rep = new Repository();  // get a repository

            rep.OpenFile("C:\\SoftwareDevelopment\\EA-Projects\\EAExample.eap");  //open the repository file

            // Get the first Model node// Iterate through all views in the model
            org.sparx.Package myModel = rep.GetModels().GetAt( (short)0 );
            
            Collection<org.sparx.Package> views = myModel.GetPackages();
            
            for (short i = 0; i < views.GetCount(); i++) {
                org.sparx.Package currentView = views.GetAt(i);

                
                System.out.println("View: " + currentView.GetName());
            }

            }
            catch (Exception e) {
                System.out.println(rep.GetLastError());   // nice utiility method to print a previous error
        }

        }

    }
