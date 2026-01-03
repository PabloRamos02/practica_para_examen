package com.example.Launcher;

import com.example.Modelo.Juego;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Launcher: iniciando juego...");
        Scanner scanner = new Scanner(System.in);
        Juego juego = new Juego(scanner);
        juego.start();
        scanner.close();
    }
} 