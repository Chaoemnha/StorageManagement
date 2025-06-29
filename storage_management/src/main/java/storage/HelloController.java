package storage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	//http://lh:8080/sr/hello
	@GetMapping("/hello")
	public String index() {
		return "index";
	}
}
