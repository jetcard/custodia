/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio.util;

import java.util.Scanner;


public class Prueba {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
 
        do {
            System.out.println("Escribe un número entero: ");
            String n = s.nextLine();
            System.out.println(NumeroLetra.cantidadConLetra(n));
        } while (true);
    }
}