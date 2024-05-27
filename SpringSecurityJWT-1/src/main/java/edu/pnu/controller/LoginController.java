package edu.pnu.controller;

import java.util.Collection;
import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import edu.pnu.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@Slf4j
public class LoginController {
	private final AuthenticationConfiguration authConfig;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginProc(@RequestBody Member member){
		try {
			Authentication authToken = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
			Authentication auth = authConfig.getAuthenticationManager().authenticate(authToken);
			
			User user = (User)auth.getPrincipal();
			Collection<GrantedAuthority> cols = user.getAuthorities();
			StringBuffer role = new StringBuffer();
			
			for(GrantedAuthority ga: cols) {
				role.append(ga.getAuthority());
			}
			
			String jwtToken = JWT.create()
					.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*10))
					.withClaim("username", user.getUsername())
					.sign(Algorithm.HMAC256("edu.pnu.jwt"));
			
			log.info(user.toString());
			log.debug(jwtToken);
			
			return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken).build();
		} catch (Exception e) {
			log.info(e.getMessage());	// 자격 증명에 실패
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}
