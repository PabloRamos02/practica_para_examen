package com.example.Modelo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SlotMachine {
    private Random rand = new Random();

    private int numeroEntreUnoYCuatro(){
        return rand.nextInt(4) + 1;
    }

    private float verSiGano(int a, int b, int c){
        if (a == b && b == c) return 2.5f;
        if (a == b || a == c || b == c) return 1.5f;
        return 0f;
    }

    private String figurasToString(int a, int b, int c){
        List<Integer> figuras = Arrays.asList(a,b,c);
        if (figuras.equals(Arrays.asList(1,1,1))) return "🍒🍒🍒";
        if (figuras.equals(Arrays.asList(2,2,2))) return "🍋🍋🍋";
        if (figuras.equals(Arrays.asList(3,3,3))) return "🔔🔔🔔";
        if (figuras.equals(Arrays.asList(4,4,4))) return "⭐⭐⭐";
        // para otras combinaciones devolvemos una representación simple
        StringBuilder sb = new StringBuilder();
        sb.append(symbolFor(a));
        sb.append(symbolFor(b));
        sb.append(symbolFor(c));
        return sb.toString();
    }

    private String symbolFor(int n){
        switch (n){
            case 1: return "🍒";
            case 2: return "🍋";
            case 3: return "🔔";
            case 4: return "⭐";
            default: return "?";
        }
    }

    public PlayResult play(int apuesta){
        int a = numeroEntreUnoYCuatro();
        int b = numeroEntreUnoYCuatro();
        int c = numeroEntreUnoYCuatro();
        String figuras = figurasToString(a,b,c);
        float multi = verSiGano(a,b,c);
        float ganancia = apuesta * multi;
        return new PlayResult(multi, ganancia, figuras);
    }

    public static class PlayResult{
        public final float multiplicador;
        public final float ganancia;
        public final String figuras;

        public PlayResult(float multiplicador, float ganancia, String figuras){
            this.multiplicador = multiplicador;
            this.ganancia = ganancia;
            this.figuras = figuras;
        }
    }
}

