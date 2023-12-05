package com.douglas.proftechdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.douglas.proftechdesk.domain.dtos.CredentialsDTO;
import com.douglas.proftechdesk.domain.dtos.LoginResponseDTO;
import com.douglas.proftechdesk.security.TokenService;
import com.douglas.proftechdesk.security.UserSS;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationResource {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid CredentialsDTO credentialDTO) {

		var usernamePassword = new UsernamePasswordAuthenticationToken(credentialDTO.email(), credentialDTO.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);

		var token = tokenService.generateToken((UserSS) auth.getPrincipal());
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
}
