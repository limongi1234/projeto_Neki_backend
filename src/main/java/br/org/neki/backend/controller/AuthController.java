package br.org.neki.backend.controller;

import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.org.neki.backend.config.security.jwt.JwtUtils;
import br.org.neki.backend.config.security.service.UserDetailsImpl;
import br.org.neki.backend.dto.CreateUserDTO;
import br.org.neki.backend.model.JwtResponse;
import br.org.neki.backend.model.LoginRequest;
import br.org.neki.backend.model.MessageResponse;
import br.org.neki.backend.model.User;
import br.org.neki.backend.repository.UserRepository;
import br.org.neki.backend.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController 
{
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository repository;

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity <?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) 
	{
		System.out.println(loginRequest.getEmail());
		System.out.println(loginRequest.getPassword());

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());	
		Optional <User> user = repository.findByEmail(loginRequest.getEmail());
		userDetails.setId(user.get().getId());
		userDetails.setEmail(user.get().getEmail());
		return ResponseEntity
				.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getEmail(), user.get().getImg(), user.get().getName(), user.get().getLevel().name()));
	}

	@PostMapping("/signup")
	public ResponseEntity <MessageResponse> registerUser(@Valid @PathVariable String email, @RequestBody CreateUserDTO request)
	{
		if (repository.existsByEmail(request.getEmail(email))) 
			return ResponseEntity.badRequest().body(new MessageResponse("Erro: Email já está sendo usado!"));
		

		// Create new user's account
		userService.criarUsuario(request);

		return ResponseEntity.ok(new MessageResponse("Usuário registrado com sucesso!"));
	}
}

