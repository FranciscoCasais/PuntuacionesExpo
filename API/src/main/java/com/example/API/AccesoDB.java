package com.example.API;

import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class AccesoDB {
    private Connection conexion;
    private List<String> nombreTabla;
    private String nombreBaseDeDatos;
    public AccesoDB(String nombreBaseDeDatos, List<String> nombreTabla, String user, String password) {
        this.nombreBaseDeDatos = nombreBaseDeDatos;
        this.nombreTabla = nombreTabla;
        try {
            conectar(user, password);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public AccesoDB(String nombreBaseDeDatos, List<String> nombreTabla) {
        this.nombreBaseDeDatos = nombreBaseDeDatos;
        this.nombreTabla = nombreTabla;
    }
    public void conectar(String user, String password) throws SQLException { // Establece la conexión con la base de datos en MySQL
        String url = "jdbc:mysql://localhost:3306/"+nombreBaseDeDatos;
        try {
            conexion = DriverManager.getConnection(url, user, password);
            if (conexion != null) {
                System.out.println("Se ha conectado exitósamente a la base de datos");
            } else {
                System.out.println("No se ha podido conectar a la base de datos");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int getCountPuntajesTotales(){
        ResultSet data;
        int cant = 0;
        String consulta= "Select count(*) as cant from Usuario;";
        try {
            PreparedStatement sentenciaSQL = conexion.prepareStatement(consulta);
            data = sentenciaSQL.executeQuery(consulta);
            while (data.next()) {
                cant = data.getInt("cant");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cant;
    }

    public int getCountPuntajesPorJuego(String nombreJuego){
        ResultSet data;
        int cant = 0;
        String consulta= "Select count(*) as cant from PuntajeJuego join Juego on Juego_idJuego = idJuego join Usuario on Usuario_idUsuario = idUsuario where Juego.nombre = \"" + nombreJuego + "\";";
        try {
            PreparedStatement sentenciaSQL = conexion.prepareStatement(consulta);
            data = sentenciaSQL.executeQuery(consulta);
            while (data.next()) {
                cant = data.getInt("cant");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cant;
    }

    public ArrayList<HashMap<String, Object>> getPuntajesTotalesOrdenados(){
        ResultSet data;
        ArrayList<HashMap<String, Object>> puntajes=new ArrayList<HashMap<String, Object>>();
        String consulta= "Select idUsuario, nombre, puntajeTotal, direccionFoto from Usuario order by puntajeTotal DESC;";
        try {
            PreparedStatement sentenciaSQL = conexion.prepareStatement(consulta);
            data = sentenciaSQL.executeQuery(consulta);
            while (data.next()) {
                HashMap<String, Object> usuario = new HashMap<String, Object>();
                usuario.put("nombre", data.getString("nombre"));
                usuario.put("puntaje", data.getInt("puntajeTotal"));
                usuario.put("direccionFoto", data.getString("direccionFoto"));
                puntajes.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return puntajes;
    }

    public ArrayList<HashMap<String, Object>> getPuntajesPorJuegoOrdenados(String nombreJuego){
        ResultSet data;
        ArrayList<HashMap<String, Object>> puntajes=new ArrayList<HashMap<String, Object>>();
        String consulta= "Select idPuntaje, Usuario.nombre, PuntajeJuego.puntajeJuego, Usuario.direccionFoto from PuntajeJuego join Juego on Juego_idJuego = idJuego join Usuario on Usuario_idUsuario = idUsuario where Juego.nombre = \"" + nombreJuego + "\" order by PuntajeJuego.puntajeJuego DESC;";
        try {
            PreparedStatement sentenciaSQL = conexion.prepareStatement(consulta);
            data = sentenciaSQL.executeQuery(consulta);
            while (data.next()) {
                HashMap<String, Object> usuario = new HashMap<String, Object>();
                usuario.put("nombre", data.getString("Usuario.nombre"));
                usuario.put("puntaje", data.getInt("idPuntaje"));
                usuario.put("direccionFoto", data.getString("Usuario.direccionFoto"));
                puntajes.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return puntajes;
    }

    public ArrayList<HashMap<String, Object>> getPuntajesTotalesOrdenadosLimit(int limite){
        ResultSet data;
        ArrayList<HashMap<String, Object>> puntajes=new ArrayList<HashMap<String, Object>>();
        String consulta= "Select idUsuario, nombre, puntajeTotal, direccionFoto from Usuario order by puntajeTotal DESC limit " + limite + ";";
        try {
            PreparedStatement sentenciaSQL = conexion.prepareStatement(consulta);
            data = sentenciaSQL.executeQuery(consulta);
            while (data.next()) {
                HashMap<String, Object> usuario = new HashMap<String, Object>();
                usuario.put("nombre", data.getString("nombre"));
                usuario.put("puntaje", data.getInt("puntajeTotal"));
                usuario.put("direccionFoto", data.getString("direccionFoto"));
                puntajes.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return puntajes;
    }

    public ArrayList<HashMap<String, Object>> getPuntajesPorJuegoOrdenadosLimit(String nombreJuego, int limite){
        ResultSet data;
        ArrayList<HashMap<String, Object>> puntajes=new ArrayList<HashMap<String, Object>>();
        String consulta= "Select idPuntaje, Usuario.nombre, PuntajeJuego.puntajeJuego, Usuario.direccionFoto from PuntajeJuego join Juego on Juego_idJuego = idJuego join Usuario on Usuario_idUsuario = idUsuario where Juego.nombre = \"" + nombreJuego + "\" order by PuntajeJuego.puntajeJuego DESC limit " + limite + ";";
        try {
            PreparedStatement sentenciaSQL = conexion.prepareStatement(consulta);
            data = sentenciaSQL.executeQuery(consulta);
            while (data.next()) {
                HashMap<String, Object> usuario = new HashMap<String, Object>();
                usuario.put("nombre", data.getString("Usuario.nombre"));
                usuario.put("puntaje", data.getInt("idPuntaje"));
                usuario.put("direccionFoto", data.getString("Usuario.direccionFoto"));
                puntajes.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return puntajes;
    }

    public ArrayList<HashMap<String, Object>> getPuntajesTotalesOrdenadosLimitOffset(int limite, int cantDescartados){
        ResultSet data;
        ArrayList<HashMap<String, Object>> puntajes=new ArrayList<HashMap<String, Object>>();
        String consulta= "Select idUsuario, nombre, puntajeTotal, direccionFoto from Usuario order by puntajeTotal DESC limit " + limite + " offset " + cantDescartados + ";";
        try {
            PreparedStatement sentenciaSQL = conexion.prepareStatement(consulta);
            data = sentenciaSQL.executeQuery(consulta);
            while (data.next()) {
                HashMap<String, Object> usuario = new HashMap<String, Object>();
                usuario.put("nombre", data.getString("nombre"));
                usuario.put("puntaje", data.getInt("puntajeTotal"));
                usuario.put("direccionFoto", data.getString("direccionFoto"));
                puntajes.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return puntajes;
    }

    public ArrayList<HashMap<String, Object>> getPuntajesPorJuegoOrdenadosLimitOffset(String nombreJuego, int limite, int cantDescartados){
        ResultSet data;
        ArrayList<HashMap<String, Object>> puntajes=new ArrayList<HashMap<String, Object>>();
        String consulta= "Select idPuntaje, Usuario.nombre, PuntajeJuego.puntajeJuego, Usuario.direccionFoto from PuntajeJuego join Juego on Juego_idJuego = idJuego join Usuario on Usuario_idUsuario = idUsuario where Juego.nombre = \"" + nombreJuego + "\" order by PuntajeJuego.puntajeJuego DESC limit " + limite + " offset " + cantDescartados + ";";
        try {
            PreparedStatement sentenciaSQL = conexion.prepareStatement(consulta);
            data = sentenciaSQL.executeQuery(consulta);
            while (data.next()) {
                HashMap<String, Object> usuario = new HashMap<String, Object>();
                usuario.put("nombre", data.getString("Usuario.nombre"));
                usuario.put("puntaje", data.getInt("idPuntaje"));
                usuario.put("direccionFoto", data.getString("Usuario.direccionFoto"));
                puntajes.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return puntajes;
    }

    public ArrayList<String> getJuegos(){
        ResultSet data;
        ArrayList<String> juegos=new ArrayList<String>();
        String consulta= "Select nombre from Juego;";
        try {
            PreparedStatement sentenciaSQL = conexion.prepareStatement(consulta);
            data = sentenciaSQL.executeQuery(consulta);
            while (data.next()) {
                juegos.add(data.getString("nombre"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return juegos;
    }
}