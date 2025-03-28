package com.mc4.user_tasks.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mc4.user_tasks.entity.Usuario;
import com.mc4.user_tasks.repository.UsuarioRepository;
import com.mc4.user_tasks.dto.UsuarioDto;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void save(UsuarioDto usuarioDto) {
        Usuario usuario = usuarioDtoToEntity(usuarioDto);

        if (usuarioRepository.findByEmail(usuarioDto.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }

        usuario = usuarioRepository.save(usuario);
    }

    @Transactional
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public UsuarioDto findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            return null;
        }
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNombre(usuario.getNombre());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setPassword(usuario.getPassword());
        usuarioDto.setRol(usuario.getRol().name());
        return usuarioDto;
    }

    @Transactional
    public UsuarioDto update(UsuarioDto usuarioDto) {
        Usuario usuario = usuarioRepository.findByEmail(usuarioDto.getEmail()).orElse(null);
        if (usuario == null) {
            return null;
        }
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setRol(Usuario.Rol.valueOf(usuarioDto.getRol()));
        usuario = usuarioRepository.save(usuario);
        return usuarioDto;
    }

    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public UsuarioDto login(UsuarioDto usuarioDto) {
        Usuario usuario = usuarioRepository.findByEmail(usuarioDto.getEmail()).orElse(null);
        System.out.println("Usuario: " + usuario);
        if (usuario == null || !usuario.getPassword().equals(usuarioDto.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        UsuarioDto loggedInUser = new UsuarioDto();
        loggedInUser.setNombre(usuario.getNombre());
        loggedInUser.setEmail(usuario.getEmail());
        loggedInUser.setRol(usuario.getRol().name());
        return loggedInUser;
    }

    public Usuario usuarioDtoToEntity(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setRol(Usuario.Rol.valueOf(usuarioDto.getRol()));
        return usuario;
    }

}
