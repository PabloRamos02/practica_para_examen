package com.example.Launcher;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Main {
    private List<String> historial = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        //generar aqui una lista vacia tipos string para el historial
        System.out.println("Hello world!");
        new Main().menu(); // <- cambio: crear instancia y llamar a menu()


    }
    public void menu(){
        while (true) {
            mostrarMenuDeSeleccion();
            int opcion = escanerNumero();
            switch (opcion){
                case 1:
                    apostar();
                    break;
                case 2:
                    Historial();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
    public void mostrarMenuDeSeleccion(){
        System.out.println("Menu de seleccion");
        System.out.println("1- Apostar");
        System.out.println("2- Ver mis apuestas");
        System.out.println("3- Salir");
    }
    public int escanerNumero(){
        System.out.print("Seleccione un numero:");
        int numero = scanner.nextInt();
        return numero;

    }
    
    public void apostar(){
        //aqui la lista de historial

        //ingreso de monto
        int montoApostado = ingresarMonto();
        // ver si gano
        //guardar resultado
        float multiplicador = juego();
        //calculador de ganancias monto apostado por multiplicador
        float ganancia = calculadorDeGanancia(montoApostado, multiplicador);
        System.out.println("Ganancia obtenida: " + ganancia);
        guardarHistorial(ganancia);



    }
    public int ingresarMonto(){
        System.out.println("Ingrese monto a apostar");
        int monto = escanerNumero();
        System.out.println("Se aposto:" + monto);
        return monto;
    }
    public float juego(){
        int primeraFigura = numeroEntreUnoYCuatro();
        int segundaFigura = numeroEntreUnoYCuatro();
        int terceraFigura = numeroEntreUnoYCuatro();
        mostrarFiguras(primeraFigura, segundaFigura, terceraFigura);
        float casoGanado = verSiGano(primeraFigura, segundaFigura, terceraFigura);
        return casoGanado;
    }
    public float verSiGano(int primeraFigura, int segundaFigura, int terceraFigura){

        //si dos son iguales gana *1.5(lo que se considera caso 1), si tres son iguales gana *2.5(lo que se considera caso 2), si no, no gana nada (caso 3)
        if (primeraFigura == segundaFigura && segundaFigura == terceraFigura) {
        // Tres iguales - multiplicador 2.5 (caso 2)
        return 2.5f;
        } else if (primeraFigura == segundaFigura || primeraFigura == terceraFigura || segundaFigura == terceraFigura) {
            // Dos iguales - multiplicador 1.5 (caso 1)
            return 1.5f;
        } else {
            // Ninguna igual - no gana (caso 3)
            return 0f;
        }
    }
    public int numeroEntreUnoYCuatro(){
        Random rand = new Random();
        return rand.nextInt(4) + 1;
    }
    public void mostrarFiguras(int primeraFigura, int segundaFigura, int terceraFigura){
        //1🍒 (Cereza),2🍋 (Limón),3🔔 (Campana),4⭐ (Estrella)
        System.out.println("resultado:");
        //primero crear una lista con los numeros
        List<Integer> figuras = Arrays.asList(primeraFigura, segundaFigura, terceraFigura);
        //una serie de comparaciones para transformar los numeros en figuras, con las 12 posibles combinaciones
        if (figuras.equals(Arrays.asList(1,1,1))){
        System.out.println("🍒🍒🍒");
        } else if (figuras.equals(Arrays.asList(2,2,2))){
            System.out.println("🍋🍋🍋");
        } else if (figuras.equals(Arrays.asList(3,3,3))){
            System.out.println("🔔🔔🔔");
        } else if (figuras.equals(Arrays.asList(4,4,4))){
            System.out.println("⭐⭐⭐");
        } else if (figuras.equals(Arrays.asList(1,1,2)) || figuras.equals(Arrays.asList(1,2,1)) || figuras.equals(Arrays.asList(2,1,1))){
            System.out.println("🍒🍒🍋");
        } else if (figuras.equals(Arrays.asList(1,1,3)) || figuras.equals(Arrays.asList(1,3,1)) || figuras.equals(Arrays.asList(3,1,1))){
            System.out.println("🍒🍒🔔");
        } else if (figuras.equals(Arrays.asList(1,1,4)) || figuras.equals(Arrays.asList(1,4,1)) || figuras.equals(Arrays.asList(4,1,1))){
            System.out.println("🍒🍒⭐");
        } else if (figuras.equals(Arrays.asList(2,2,1)) || figuras.equals(Arrays.asList(2,1,2)) || figuras.equals(Arrays.asList(1,2,2))){
            System.out.println("🍋🍋🍒");
        } else if (figuras.equals(Arrays.asList(2,2,3)) || figuras.equals(Arrays.asList(2,3,2)) || figuras.equals(Arrays.asList(3,2,2))){
            System.out.println("🍋🍋🔔");
        } else if (figuras.equals(Arrays.asList(2,2,4)) || figuras.equals(Arrays.asList(2,4,2)) || figuras.equals(Arrays.asList(4,2,2))){
            System.out.println("🍋🍋⭐");
        } else if (figuras.equals(Arrays.asList(3,3,1)) || figuras.equals(Arrays.asList(3,1,3)) || figuras.equals(Arrays.asList(1,3,3))){
            System.out.println("🔔🔔🍒");
        } else if (figuras.equals(Arrays.asList(3,3,2)) || figuras.equals(Arrays.asList(3,2,3)) || figuras.equals(Arrays.asList(2,3,3))){
            System.out.println("🔔🔔🍋");
        } else if (figuras.equals(Arrays.asList(3,3,4)) || figuras.equals(Arrays.asList(3,4,3)) || figuras.equals(Arrays.asList(4,3,3))){
            System.out.println("🔔🔔⭐");
        } else if (figuras.equals(Arrays.asList(4,4,1)) || figuras.equals(Arrays.asList(4,1,4)) || figuras.equals(Arrays.asList(1,4,4))){
            System.out.println("⭐⭐🍒");
        } else if (figuras.equals(Arrays.asList(4,4,2)) || figuras.equals(Arrays.asList(4,2,4)) || figuras.equals(Arrays.asList(2,4,4))){
            System.out.println("⭐⭐🍋");
        } else if (figuras.equals(Arrays.asList(4,4,3)) || figuras.equals(Arrays.asList(4,3,4)) || figuras.equals(Arrays.asList(3,4,4))){
            System.out.println("⭐⭐🔔");
        } else if (figuras.equals(Arrays.asList(1,2,3)) || figuras.equals(Arrays.asList(1,3,2)) || figuras.equals(Arrays.asList(2,1,3)) || 
                figuras.equals(Arrays.asList(2,3,1)) || figuras.equals(Arrays.asList(3,1,2)) || figuras.equals(Arrays.asList(3,2,1))){
            System.out.println("🍒🍋🔔");
        } else if (figuras.equals(Arrays.asList(1,2,4)) || figuras.equals(Arrays.asList(1,4,2)) || figuras.equals(Arrays.asList(2,1,4)) || 
                figuras.equals(Arrays.asList(2,4,1)) || figuras.equals(Arrays.asList(4,1,2)) || figuras.equals(Arrays.asList(4,2,1))){
            System.out.println("🍒🍋⭐");
        } else if (figuras.equals(Arrays.asList(1,3,4)) || figuras.equals(Arrays.asList(1,4,3)) || figuras.equals(Arrays.asList(3,1,4)) || 
                figuras.equals(Arrays.asList(3,4,1)) || figuras.equals(Arrays.asList(4,1,3)) || figuras.equals(Arrays.asList(4,3,1))){
            System.out.println("🍒🔔⭐");
        } else if (figuras.equals(Arrays.asList(2,3,4)) || figuras.equals(Arrays.asList(2,4,3)) || figuras.equals(Arrays.asList(3,2,4)) || 
                figuras.equals(Arrays.asList(3,4,2)) || figuras.equals(Arrays.asList(4,2,3)) || figuras.equals(Arrays.asList(4,3,2))){
            System.out.println("🍋🔔⭐");
        } else {
            System.out.println("Combinación no válida");
        }

    }
    //calculador de ganancias monto apostado por multiplicador
    public float calculadorDeGanancia(int apostado, float multi){
        float resultadoA = apostado * multi;
        return resultadoA;
    }
    public List<String> guardarHistorial (float ganado){
        //generar una lista con los resultados
        //transforma ganado a string
        String registro = "Ganancia: " + ganado;
        historial.add(registro);
        return historial;

    }
    public void Historial (){
        //un metodo que muestra la lista de historial en fila
        if (historial.isEmpty()){
            System.out.println("No hay historial de apuestas.");
            return;
        }
        System.out.println("Historial de apuestas:");
        for (int i = 0; i < historial.size(); i++){
            System.out.println((i+1) + ". " + historial.get(i));
        }

    }
}