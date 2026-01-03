package com.example.Modelo;

import java.util.Scanner;

public class SeleccionarUsuario {
    // crea un usuario solicitando datos por consola
    public Usuario crearUsuarioInteractivo(Scanner scanner){
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        int edad = 0;
        while (true){
            System.out.print("Ingrese edad: ");
            String linea = scanner.nextLine();
            try{
                edad = Integer.parseInt(linea.trim());
                break;
            } catch(NumberFormatException e){
                System.out.println("Edad inválida, ingrese un número.");
            }
        }
        if (edad < 18){
            System.out.println("Debes ser mayor de 18 años para jugar.");
            return null;
        }
        System.out.print("Ingrese contraseña: ");
        String pass = scanner.nextLine();
        double saldo = 0;
        while (true){
            System.out.print("Saldo inicial (ej. 100): ");
            String linea = scanner.nextLine();
            try{
                saldo = Double.parseDouble(linea.trim());
                if (saldo < 0) { System.out.println("Saldo no puede ser negativo."); continue; }
                break;
            } catch(NumberFormatException e){
                System.out.println("Saldo inválido, ingrese un número.");
            }
        }
        return new Usuario(nombre, edad, pass, saldo);
    }
}
