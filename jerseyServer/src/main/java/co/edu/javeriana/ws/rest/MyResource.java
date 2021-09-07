package co.edu.javeriana.ws.rest;

import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//json imports
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    //myapp/myresource/puntouno?name=carlos
    @GET
    @Path("puntouno")
    @Produces(MediaType.TEXT_PLAIN)
    public String puntouno( @QueryParam("name") String name) {
        return "Hola "+name;
    }

    //myapp/myresource/puntodos/gabriel
    @GET
    @Path("puntodos/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String puntodos( @PathParam("name") String name) {
        return "Hola "+name;
    }


    //myapp/myresource/puntocuatro/5?op2=10
    @GET
    @Path("puntocuatro/{op1}")
    @Produces(MediaType.TEXT_PLAIN)
    public int puntocuatro( @PathParam("op1") String op1, @QueryParam("op2") int op2 ) {
        return Integer.valueOf(op1) * op2;
    }


    //Articulo
    @GET
    @Path("article")
    //@Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    public Article getArticle(){
        Article article = new Article();
        article.setAuthor("Garcia Marquez");
        article.setName("libro de garcia");
        return article;
    }

    @GET
    @Path("paseos")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Paseo> getPaseos(){

        ArrayList<Paseo> paseos = new ArrayList<Paseo>();
        JSONParser parser = new JSONParser();

        try {    
            Object obj = parser.parse(new FileReader("src/main/java/co/edu/javeriana/Recursos/Paseos.json"));

            JSONObject jsonObject =  (JSONObject) obj;
            JSONArray paseosJSON = (JSONArray) jsonObject.get("paseos");
            
            for (int i=0; i< paseosJSON.size(); i++){
                JSONObject paseoJSON = (JSONObject)paseosJSON.get(i);
                Long id = (Long) paseoJSON.get("identificador");
                String nombre = (String) paseoJSON.get("nombre");
                String lugar_salida = (String) paseoJSON.get("lugar_salida");
                String lugar_llegada = (String) paseoJSON.get("lugar_llegada");
                String fecha = (String) paseoJSON.get("fecha");

                Paseo paseo = new Paseo();
                paseo.setIdentificador(id);
                paseo.setNombre(nombre);
                paseo.setLugar_salida(lugar_salida);
                paseo.setLugar_llegada(lugar_llegada);
                paseo.setFecha(fecha);

                paseos.add(paseo);
              }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return paseos;
        
        
    }


    @DELETE
    @Path("paseos/{id}")
    public String deletePaseo(@PathParam("id") Long identificador){

        ArrayList<Paseo> paseos = new ArrayList<Paseo>();
        JSONParser parser = new JSONParser();
        boolean bandera = false;

        try {    
            Object obj = parser.parse(new FileReader("src/main/java/co/edu/javeriana/Recursos/Paseos.json"));

            JSONObject jsonObject =  (JSONObject) obj;
            JSONArray paseosJSON = (JSONArray) jsonObject.get("paseos");
            
            
            for (int i=0; i< paseosJSON.size(); i++){
                JSONObject paseoJSON = (JSONObject)paseosJSON.get(i);
                Long id = (Long) paseoJSON.get("identificador");
                if(id == identificador){
                    bandera=true;
                }
                if(id != identificador){
                    String nombre = (String) paseoJSON.get("nombre");
                    String lugar_salida = (String) paseoJSON.get("lugar_salida");
                    String lugar_llegada = (String) paseoJSON.get("lugar_llegada");
                    String fecha = (String) paseoJSON.get("fecha");

                    Paseo paseo = new Paseo();
                    paseo.setIdentificador(id);
                    paseo.setNombre(nombre);
                    paseo.setLugar_salida(lugar_salida);
                    paseo.setLugar_llegada(lugar_llegada);
                    paseo.setFecha(fecha);
    
                    paseos.add(paseo);
                }
                

              }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(bandera){
            String json = "{ \"paseos\": ";
            json += new Gson().toJson(paseos);
            json += "}";
            
            try {

                FileWriter file = new FileWriter("src/main/java/co/edu/javeriana/Recursos/Paseos.json");
                file.write(json);
                file.flush();
                file.close();

            } catch (IOException e) {
                //manejar error
            }
            return "Producto eliminado";
        }else{
            return "No existe el id";
        }


    }


}
