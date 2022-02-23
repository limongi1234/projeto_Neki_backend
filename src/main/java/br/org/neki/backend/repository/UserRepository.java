package br.org.neki.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.neki.backend.dto.UserDTO;
import br.org.neki.backend.model.User;

import java.util.Date;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> 
{
    Optional <User> findByNome(String nome);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    
    Optional <User> findByEmail(String email);
    
    Optional <User> findByDataContratacao(Date data);
    
    Optional <User> findByUsername(UserDTO userDTO);
}
