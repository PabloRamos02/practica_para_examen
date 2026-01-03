package com.example.Modelo;

import java.util.Scanner;

public class Juego {
    private final Scanner scanner;
    private final Historial historial;
    private final SlotMachine slot;
    private final BaseDatos db;
    private Usuario usuario;

    public Juego(Scanner scanner){
        this.scanner = scanner;
        this.historial = new Historial();
        this.slot = new SlotMachine();
        this.db = new BaseDatos();
    }

    public void start(){
        while (true){
            mostrarMenuDeSeleccion();
            int opcion = escanerNumero();
            switch (opcion){
                case 1:
                    seleccionarYJugar();
                    break;
                case 2:
                    historial.imprimir();
                    break;
                case 3:
                    System.out.println("Guardando y saliendo...");
                    if (usuario != null) db.guardarUsuario(usuario);
                    return;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void mostrarMenuDeSeleccion(){
        System.out.println("Menu de seleccion");
        System.out.println("1- Apostar");
        System.out.println("2- Ver mis apuestas");
        System.out.println("3- Salir");
    }

    private int escanerNumero(){
        System.out.print("Seleccione un numero: ");
        while (!scanner.hasNextInt()){
            System.out.println("Entrada inválida.");
            scanner.nextLine();
            System.out.print("Seleccione un numero: ");
        }
        int numero = scanner.nextInt();
        scanner.nextLine();
        return numero;
    }

    private void seleccionarYJugar(){
        if (usuario == null){
            SeleccionarUsuario su = new SeleccionarUsuario();
            usuario = su.crearUsuarioInteractivo(scanner);
            if (usuario == null) return;
            System.out.println("Usuario creado: " + usuario);
        }
        System.out.print("Ingrese monto a apostar: ");
        while (!scanner.hasNextDouble()){
            System.out.println("Monto inválido.");
            scanner.nextLine();
            System.out.print("Ingrese monto a apostar: ");
        }
        double monto = scanner.nextDouble();
        scanner.nextLine();
        if (monto <= 0){
            System.out.println("Monto debe ser positivo.");
            return;
        }
        if (monto > usuario.getSaldo()){
            System.out.println("Saldo insuficiente.");
            return;
        }
        usuario.retirar(monto);
        SlotMachine.PlayResult res = slot.play((int)monto);
        System.out.println("Resultado: " + res.figuras + " | Multiplicador: " + res.multiplicador + " | Ganancia: " + res.ganancia);
        if (res.ganancia > 0){
            usuario.depositar(res.ganancia);
        }
        String registro = "Apostó " + monto + ", resultado: " + res.figuras + ", ganancia: " + res.ganancia + ", saldo: " + usuario.getSaldo();
        historial.agregar(registro);
    }
}
