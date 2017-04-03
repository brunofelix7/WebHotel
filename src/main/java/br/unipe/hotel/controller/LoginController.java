package br.unipe.hotel.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	private static final String LOGIN_VIEW = "/login/Login";
	
	@RequestMapping(value = "/login")
	public String autenticar(@AuthenticationPrincipal User user){
		if (user != null) {
			return "redirect:hospedes";
		}
		return LOGIN_VIEW;
	}
	
}
