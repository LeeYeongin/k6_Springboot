package edu.pnu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	@GetMapping("/") public String index() { return "index"; }
	@GetMapping("/public") public String webpublic() { return "public"; }
	@GetMapping("/intra/marketing") public String market() { return "market"; }
	@GetMapping("/intra/develop") public String develop() { return "develop"; }
	@GetMapping("/intra/finance") public String finance() { return "finance"; }
	@GetMapping("/admin") public String admin() { return "admin"; }
}
