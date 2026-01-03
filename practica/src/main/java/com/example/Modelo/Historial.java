package com.example.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Historial {
	private List<String> entradas = new ArrayList<>();

	public void agregar(String entrada) {
		entradas.add(entrada);
	}

	public List<String> obtenerEntradas() {
		return entradas;
	}

	public boolean estaVacio(){
		return entradas.isEmpty();
	}

	public void imprimir() {
		if (entradas.isEmpty()){
			System.out.println("No hay historial de apuestas.");
			return;
		}
		System.out.println("Historial de apuestas:");
		for (int i = 0; i < entradas.size(); i++){
			System.out.println((i+1) + ". " + entradas.get(i));
		}
	}
}
