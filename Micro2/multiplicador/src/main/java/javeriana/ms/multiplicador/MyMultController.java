package javeriana.ms.multiplicador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MyMultController {

    @Autowired
    Environment environment;

    @GetMapping("/multiplicacion")
    public String multi(@RequestParam int a,@RequestParam int b,@RequestParam String user){
        String port = environment.getProperty("local.server.port");
        String response = "Resultado: " + String.valueOf(a*b)+ " respuesta desde puerto: "+port+" user: "+user;
        return response;
    }
    
}
