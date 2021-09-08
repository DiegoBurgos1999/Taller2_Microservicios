package javeriana.ms.divisor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MyDivController {
    
    @Autowired
    Environment environment;
    
    @GetMapping("/division")
    public String divi(@RequestParam int a,@RequestParam int b,@RequestParam String user){
        String port = environment.getProperty("local.server.port");

        if(b != 0){
            String response = "Resultado: " + String.valueOf(a/b)+ " respuesta desde puerto: "+port+" user: "+user;
            return response;
        }else{
            String response = "Denominador es 0, no se puede hacer la division";
            return response;
        }
    }
}
