package com.example.Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación ligera de persistencia basada en archivos CSV.
 * Notas:
 * - Para un proyecto real se recomienda usar SQLite (JDBC) o una base de datos embebida.
 * - Aquí se guarda/lee usuarios de un archivo simple `usuarios.csv` en el directorio del proyecto.
 */
public class BaseDatos {
    private final File usuariosFile = new File("usuarios.csv");

    public void guardarUsuario(Usuario u){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(usuariosFile, true))){
            // formato: nombre,edad,contrasena,saldo
            String line = escape(u.getNombre()) + "," + u.getEdad() + "," + escape(u.getContrasena()) + "," + u.getSaldo();
            bw.write(line);
            bw.newLine();
        } catch (IOException e){
            System.out.println("Error guardando usuario: " + e.getMessage());
        }
    }

    public List<Usuario> cargarUsuarios(){
        List<Usuario> out = new ArrayList<>();
        if (!usuariosFile.exists()) return out;
        try (BufferedReader br = new BufferedReader(new FileReader(usuariosFile))){
            String l;
            while ((l = br.readLine()) != null){
                String[] parts = l.split(",");
                if (parts.length < 4) continue;
                String nombre = unescape(parts[0]);
                int edad = Integer.parseInt(parts[1]);
                String pass = unescape(parts[2]);
                double saldo = Double.parseDouble(parts[3]);
                out.add(new Usuario(nombre, edad, pass, saldo));
            }
        } catch (IOException e){
            System.out.println("Error leyendo usuarios: " + e.getMessage());
        }
        return out;
    }

    private String escape(String s){
        if (s == null) return "";
        return s.replace("\n","\\n").replace(",","\\,");
    }

    private String unescape(String s){
        if (s == null) return "";
        return s.replace("\\,",",").replace("\\n","\n");
    }
}

