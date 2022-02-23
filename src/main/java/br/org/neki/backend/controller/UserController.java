package br.org.neki.backend.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.neki.backend.dto.ChangeUserDTO;
import br.org.neki.backend.dto.CreateUserDTO;
import br.org.neki.backend.dto.UserDTO;
import br.org.neki.backend.exception.RecursoBadRequestException;
import br.org.neki.backend.model.User;
import br.org.neki.backend.repository.UserRepository;
import br.org.neki.backend.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usuarios")
public class UserController 
{
	private UserService service;
	
	@Autowired
	public UserController(UserService service) 
	{
		super();
		this.service = service;
		
	}

	@Autowired
	UserRepository userRepository;


	@PostMapping
	@ApiOperation(value = "Cadastrar um usuario", notes = "Cadastro de usuario")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Cadastra um usuario"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	@ResponseStatus(HttpStatus.CREATED)

	public ResponseEntity <Object> inserir(@Valid @RequestBody CreateUserDTO createUserDTO) 
	{
		try 
		{
			UserDTO userDTO = service.criarUsuario(createUserDTO);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(userDTO.getName()).toUri();
			return ResponseEntity.created(uri).body(userDTO);
		} catch (RecursoBadRequestException recursoBadRequestException)
		{
			return ResponseEntity.badRequest().body(recursoBadRequestException.getMessage());
		}
	}

	@GetMapping
	@ApiOperation(value = "Listar usuários", notes = "Retorna uma lista de usuários")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma lista de usuários"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity <List <UserDTO>> listar() 
	{
		return ResponseEntity.ok(service.listar());
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Buscar um usuario por id", notes = "Busca um usuario")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um usuario"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	public ResponseEntity <User> buscarPorId(@PathVariable Long id) 
	{
		Optional <User> user = userRepository.findById(id);
		if (!user.isPresent()) 
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(service.buscar(id));
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Alterar usuario", notes = "Alteração de um usuario")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Altera um usuario"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity <Object> alterar(@PathVariable Long id,
			@Valid @RequestBody ChangeUserDTO changeUserDTO) throws RecursoBadRequestException 
	{

		if (service.alterar(id, changeUserDTO) != null) 
			return ResponseEntity.ok(changeUserDTO);
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/alterar-senha/{id}")
	@ApiOperation(value = "Alterar usuario", notes = "Alteração de um usuario")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Altera um usuario"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity <UserDTO> alterarSenha(@PathVariable Long id,
			@Valid @RequestBody String senha) throws RecursoBadRequestException
	{

		if (userRepository.existsById(id)) 
			return ResponseEntity.ok(service.alterarSenha(id, senha));
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar um usuario", notes = "Deleta usuario")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Exclui um usuario"),
			@ApiResponse(code = 204, message = "Exclui um usuario e retorna vazio"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro de servidor") })

	public ResponseEntity <Void> excluir(@PathVariable Long id) 
	{
		service.deletar(id);
		return ResponseEntity.ok().build();
	}
}
