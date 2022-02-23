package br.org.neki.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.org.neki.backend.config.MailConfig;
import br.org.neki.backend.dto.ChangeUserDTO;
import br.org.neki.backend.dto.CreateUserDTO;
import br.org.neki.backend.dto.UserDTO;
import br.org.neki.backend.exception.RecursoBadRequestException;
import br.org.neki.backend.exception.RecursoNotFoundException;
import br.org.neki.backend.model.User;
import br.org.neki.backend.repository.UserRepository;

@Service
public class UserService 
{
    @Autowired
	private UserRepository userRepository;

	@Autowired
	MailConfig mailConfig;

	@Autowired
	PasswordEncoder encoder;

	/**
	 * MÉTODO PARA CRIAR UM NOVO USUÁRIO
	 * 
	 * @param createUserDTO
	 * @return UM NOVO USUÁRIO
	 * @throws RecursoBadRequestException
	 */

	public UserDTO criarUsuario(@Valid CreateUserDTO createUserDTO) throws RecursoBadRequestException
	{

		if (userRepository.findByUsername(createUserDTO.getUserDTO()).isPresent()) 
			throw new RecursoBadRequestException("Nome de usuário já cadastrado!");
		
        String password = null;
		String senhaUnd = createUserDTO.getPassword(password);
		User user = new User(createUserDTO);
		
		
		user.setPassword(encoder.encode(createUserDTO.getPassword(password)));
		userRepository.save(user);
		

		String texto = "Usuario cadastrado com sucesso!\nSeu Login: %s \nSua Senha: %s\nAo acessar pela primeira vez mude a sua senha!\nAcesse pelo Link para mudar: %s";
		texto = String.format(texto, senhaUnd, "http://localhost:3000/alterar-senha");
		

		return new UserDTO(user);

	}

	
	public List <UserDTO> listar()  
	{
		List <User> users = userRepository.findAll();
		
		return users.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
	}

	
	public User buscar(Long id) throws RecursoBadRequestException
	{
		User user = userRepository.getById(id);
		if (user != null) 
			return user;
		else 
			throw new RecursoNotFoundException("Usuario não encontrado");
		
	}

	public ResponseEntity <UserDTO> deletar(Long id) 
	{
		if (!userRepository.existsById(id))
			return ResponseEntity.notFound().build();

		userRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	

	public UserDTO alterar(Long id, @Valid ChangeUserDTO changeUserDTO) throws RecursoBadRequestException
	{
		if (userRepository.existsById(id)) 
		{
			User user = new User(changeUserDTO);
			
			user.setName(changeUserDTO.getName());
			user.setPassword(encoder.encode(changeUserDTO.getPassword()));
			user.setEmail(changeUserDTO.getEmail());
			user.setLevel(changeUserDTO.getLevel());
			user.setImg(changeUserDTO.getImg());
			String texto = "Usuario alterado com sucesso!\nSeu Login: %s \nSua Senha: %s";
			texto = String.format(texto, user.getEmail(), user.getPassword());
			mailConfig.enviarEmail(user.getEmail(), "Alteração de Usuário Concluída", texto);

			userRepository.save(user);
			return new UserDTO(user);
		}
		throw new RecursoBadRequestException("Usuario não encontrado");
	}
	
	public UserDTO alterarSenha(Long id, String senha) 
	{
		
		if (userRepository.existsById(id)) 
		{
			User user = userRepository.getById(id);
			user.setPassword(encoder.encode(senha));
			userRepository.save(user);
			return new UserDTO(user);
		}
		throw new RecursoBadRequestException ("Usuario não encontrado");
	}

	public void enviaEmails() 
	{
		
	}
}