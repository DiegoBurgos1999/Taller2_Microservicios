package javeriana.ms.calculadora;

import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

//json imports
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@RestController
public class CalculadoraController {
    @Autowired
    RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/calculadora/suma")
    public String calculadoraSuma(@RequestParam int a, @RequestParam int b, @RequestParam String user) {
        String response = restTemplate.getForObject("http://sumador/suma?a={a}&b={b}&user={user}", String.class, a, b,
                user);

        persistirDatos("suma", user);
        return response;
    }

    @GetMapping("/calculadora/resta")
    public String calculadoraResta(@RequestParam int a, @RequestParam int b, @RequestParam String user) {
        String response = restTemplate.getForObject("http://restador/resta?a={a}&b={b}&user={user}", String.class, a, b,
                user);
        persistirDatos("resta", user);
        return response;
    }

    @GetMapping("/calculadora/multiplicacion")
    public String calculadoraMultiplicacion(@RequestParam int a, @RequestParam int b, @RequestParam String user) {
        String response = restTemplate.getForObject("http://multiplicador/multiplicacion?a={a}&b={b}&user={user}",
                String.class, a, b, user);
        persistirDatos("multiplicacion", user);
        return response;
    }

    @GetMapping("/calculadora/division")
    public String calculadoraDivision(@RequestParam int a, @RequestParam int b, @RequestParam String user) {
        String response = restTemplate.getForObject("http://divisor/division?a={a}&b={b}&user={user}", String.class, a,
                b, user);
        persistirDatos("division", user);
        return response;
    }

    @GetMapping("/calculadora/historial")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Operacion> calculadoraHistorial(@RequestParam String operacion) {
        String response = restTemplate.getForObject("http://consultor/historial?operacion={operacion}", String.class, operacion);

        ArrayList<Operacion> operaciones = new ArrayList<Operacion>();

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("src/main/resources/Operaciones.json"));

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray operacionesJSON = (JSONArray) jsonObject.get("operaciones");

            for (int i = 0; i < operacionesJSON.size(); i++) {
                JSONObject operacionJSON = (JSONObject) operacionesJSON.get(i);
                String nombreJS = (String) operacionJSON.get("nombre");
                if(nombreJS.equals(operacion)){
                    String fecha = (String) operacionJSON.get("fecha");
                    String username = (String) operacionJSON.get("user");
                    Operacion operacionEnviar = new Operacion();
                    operacionEnviar.setNombre(nombreJS);
                    operacionEnviar.setFecha(fecha);
                    operacionEnviar.setUser(username);
                    
                    operaciones.add(operacionEnviar);
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }



        return operaciones;
    }

    public void persistirDatos(String nombre, String user){

        ArrayList<Operacion> operaciones = new ArrayList<Operacion>();

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("src/main/resources/Operaciones.json"));

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray operacionesJSON = (JSONArray) jsonObject.get("operaciones");

            for (int i = 0; i < operacionesJSON.size(); i++) {
                JSONObject operacionJSON = (JSONObject) operacionesJSON.get(i);
                String nombreJS = (String) operacionJSON.get("nombre");
                String fecha = (String) operacionJSON.get("fecha");
                String username = (String) operacionJSON.get("user");

                Operacion operacion = new Operacion();
                operacion.setNombre(nombreJS);
                operacion.setFecha(fecha);
                operacion.setUser(username);

                operaciones.add(operacion);
            }
            Operacion operacion = new Operacion();
            operacion.setNombre(nombre);
            java.util.Date date = new Date();
            operacion.setFecha(date.toString());
            operacion.setUser(user);
            operaciones.add(operacion);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String json = "{ \"operaciones\": ";
        json += new Gson().toJson(operaciones);
        json += "}";

        try {

            FileWriter file = new FileWriter("src/main/resources/Operaciones.json");
            file.write(json);
            file.flush();
            file.close();

        } catch (IOException e) {
            //manejar error
        }

    }

}
