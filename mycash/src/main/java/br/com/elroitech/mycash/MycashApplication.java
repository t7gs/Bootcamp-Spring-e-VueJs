package br.com.elroitech.mycash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RestController;
import br.com.elroitech.mycash.service.UsuarioService;

@SpringBootApplication
@RestController
public class MycashApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context
		= SpringApplication.run(MycashApplication.class, args);
		

		UsuarioService service = context.getBean(UsuarioService.class);
		service.registraUsuarioAdmin("admin@mycash.com", "admin");
	}
	
	
	//testar = http://localhost:8080/ola?nome=tiago
//	@GetMapping("/ola")
//	public String hello(@RequestParam("nome") String nome) {
//		return "Olá " + nome;
//	}

}
