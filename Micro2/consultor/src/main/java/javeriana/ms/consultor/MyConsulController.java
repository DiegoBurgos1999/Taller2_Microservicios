package javeriana.ms.consultor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MyConsulController {

    @Autowired
    Environment environment;

    @GetMapping("/historial")
    public String historial(@RequestParam String operacion){
        String port = environment.getProperty("local.server.port");
        String response = "Consultar "+operacion+" desde: "+port;
        return response;
    }
    
}
