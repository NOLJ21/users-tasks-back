package com.mc4.user_tasks.repository;

import com.mc4.user_tasks.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    Optional<Usuario> findByEmail(String email);

    Usuario findByNombre(String nombre);

}
