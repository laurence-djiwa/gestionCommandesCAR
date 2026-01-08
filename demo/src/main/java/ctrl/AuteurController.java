package ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuteurController {
	
	private AuteurService auteurService;
	
	public AuteurController (AuteurService auteurService ) {
		this.auteurService = auteurService;
	}
	
	@GetMapping("/home")
	public String home() {
		return "auteurs";
	}
}
