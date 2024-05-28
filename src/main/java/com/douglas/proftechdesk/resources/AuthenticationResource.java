package com.douglas.proftechdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.douglas.proftechdesk.domain.dtos.CredentialsDTO;
import com.douglas.proftechdesk.domain.dtos.LoginResponseDTO;
import com.douglas.proftechdesk.domain.dtos.RegisterDTO;
import com.douglas.proftechdesk.security.TokenService;
import com.douglas.proftechdesk.security.UserSS;
import com.douglas.proftechdesk.services.UserDetailsServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationResource {
	
	@Autowired
    private UserDetailsServiceImpl detailsService;

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
	
	@PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO user)  {
            this.detailsService.register(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
