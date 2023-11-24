package com.example.API;

import java.util.*;

public class Sistema {
    public static ArrayList<Puntaje> puntajes(ArrayList<HashMap<String, Object>> puntajes_){
        ArrayList<Puntaje> puntajes = new ArrayList<Puntaje>();
        for (int i = 0;i < puntajes_.size();i++) puntajes.add(new Puntaje(puntajes_.get(i)));
        return puntajes;
    }
    /*public static String convertHashSetToJsonArray(HashSet<String> jsonSet) {
        StringBuilder jsonArrayBuilder = new StringBuilder();
        jsonArrayBuilder.append("[");

        boolean first = true;
        for (String json : jsonSet) {
            if (!first) {
                jsonArrayBuilder.append(",");
            } else {
                first = false;
            }
            jsonArrayBuilder.append(json);
        }

        jsonArrayBuilder.append("]");

        return jsonArrayBuilder.toString();
    }*/

    public static String convertPuntajeArrayListToJsonArray(ArrayList<Puntaje> jsonSet) {
        StringBuilder jsonArrayBuilder = new StringBuilder();
        jsonArrayBuilder.append("[");

        boolean first = true;
        for (Puntaje jsonP : jsonSet) {
            String json = jsonP.toJSON();
            if (!first) {
                jsonArrayBuilder.append(",");
            } else {
                first = false;
            }
            jsonArrayBuilder.append(json);
        }

        jsonArrayBuilder.append("]");

        return jsonArrayBuilder.toString();
    }

    public static String convertStringArrayListToJsonArray(ArrayList<String> jsonSet) {
        StringBuilder jsonArrayBuilder = new StringBuilder();
        jsonArrayBuilder.append("[");

        String json_ = "{" + "\"Juego\":\"Total\"" + "}";
        jsonArrayBuilder.append(json_);
        for (String jsons : jsonSet) {
            String json = ",{" +
                    "\"Juego\":\"" + jsons + "\"" +
                    "}";
            jsonArrayBuilder.append(json);
        }

        jsonArrayBuilder.append("]");

        return jsonArrayBuilder.toString();
    }
}
