package com.example.API;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class PuntajeController {
    private static AccesoDB accesoDB = new AccesoDB("puntajesExpo", Arrays.asList("Juego", "Usuario", "PuntajeJuego"), "alumno", "alumnoipm");
    @GetMapping("/hola")
    @CrossOrigin(origins = "*")
    public String test(){
        return "chau";
    }
    @GetMapping("/getJuegos")
    @CrossOrigin(origins = "*")
    public String getJuegos(){
        return Sistema.convertStringArrayListToJsonArray(accesoDB.getJuegos());
    }
    @GetMapping("/getCountPuntajesTotales")
    @CrossOrigin(origins = "*")
    public int getCountPuntajesTotales(){
        return accesoDB.getCountPuntajesTotales();
    }
    @GetMapping("/getCountPuntajesPorJuego")
    @CrossOrigin(origins = "*")
    public int getCountPuntajesPorJuego(@RequestParam(value = "nombreJuego", defaultValue = "") String nombreJuego){
        return accesoDB.getCountPuntajesPorJuego(nombreJuego);
    }
    @GetMapping("/getPuntajesTotalesOrdenados")
    @CrossOrigin(origins = "*")
    public String getPuntajesTotalesOrdenados(){
        return Sistema.convertPuntajeArrayListToJsonArray(Sistema.puntajes(accesoDB.getPuntajesTotalesOrdenados()));
    }
    @GetMapping("/getPuntajesPorJuegoOrdenados")
    @CrossOrigin(origins = "*")
    public String getPuntajesPorJuegoOrdenados(@RequestParam(value = "nombreJuego", defaultValue = "") String nombreJuego){
        return Sistema.convertPuntajeArrayListToJsonArray(Sistema.puntajes(accesoDB.getPuntajesPorJuegoOrdenados(nombreJuego)));
    }
    @GetMapping("/getPuntajesTotalesOrdenadosLimit")
    @CrossOrigin(origins = "*")
    public String getPuntajesTotalesOrdenadosLimit(@RequestParam(value = "limite", defaultValue = "") int limite){
        return Sistema.convertPuntajeArrayListToJsonArray(Sistema.puntajes(accesoDB.getPuntajesTotalesOrdenadosLimit(limite)));
    }
    @GetMapping("/getPuntajesPorJuegoOrdenadosLimit")
    @CrossOrigin(origins = "*")
    public String getPuntajesPorJuegoOrdenadosLimit(@RequestParam(value = "nombreJuego", defaultValue = "") String nombreJuego, @RequestParam(value = "limite", defaultValue = "") int limite){
        return Sistema.convertPuntajeArrayListToJsonArray(Sistema.puntajes(accesoDB.getPuntajesPorJuegoOrdenadosLimit(nombreJuego, limite)));
    }
    @GetMapping("/getPuntajesTotalesOrdenadosLimitOffset")
    @CrossOrigin(origins = "*")
    public String getPuntajesTotalesOrdenadosLimitOffset(@RequestParam(value = "limite", defaultValue = "") int limite, @RequestParam(value = "cantDescartados", defaultValue = "") int cantDescartados){
        return Sistema.convertPuntajeArrayListToJsonArray(Sistema.puntajes(accesoDB.getPuntajesTotalesOrdenadosLimitOffset(limite, cantDescartados)));
    }
    @GetMapping("/getPuntajesPorJuegoOrdenadosLimitOffset")
    @CrossOrigin(origins = "*")
    public String getPuntajesPorJuegoOrdenadosLimitOffset(@RequestParam(value = "nombreJuego", defaultValue = "") String nombreJuego, @RequestParam(value = "limite", defaultValue = "") int limite, @RequestParam(value = "cantDescartados", defaultValue = "") int cantDescartados){
        return Sistema.convertPuntajeArrayListToJsonArray(Sistema.puntajes(accesoDB.getPuntajesPorJuegoOrdenadosLimitOffset(nombreJuego, limite, cantDescartados)));
    }
}
