package com.example.API;

import java.util.HashMap;

public class Puntaje {
    String nombre;
    int puntaje;
    String direccionFoto;

    public Puntaje(String nombre, int puntaje, String direccionFoto) {
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.direccionFoto = direccionFoto;
    }
    public Puntaje() {
        this.nombre = "";
        this.puntaje = 0;
        this.direccionFoto = "";
    }

    public Puntaje(HashMap<String, Object> datos) {
        this.nombre = (String) datos.get("nombre");
        this.puntaje = (int) datos.get("puntaje");
        this.direccionFoto = (String) datos.get("direccionFoto");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getDireccionFoto() {
        return direccionFoto;
    }

    public void setDireccionFoto(String direccionFoto) {
        this.direccionFoto = direccionFoto;
    }

    public String toJSON() {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        jsonBuilder.append("\"Nombre\":\"").append(nombre).append("\",");
        jsonBuilder.append("\"Puntaje\":").append(puntaje).append(",");
        jsonBuilder.append("\"DireccionFoto\":\"").append(direccionFoto).append("\"");
        jsonBuilder.append("}");

        return jsonBuilder.toString();
    }
}
