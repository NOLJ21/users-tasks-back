package com.mc4.user_tasks.controller;

import com.mc4.user_tasks.dto.UsuarioDto;
import com.mc4.user_tasks.entity.Usuario;
import com.mc4.user_tasks.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UsuarioDto user) {
        try {
            System.out.println("Usuario: " + user);
            userService.save(user);
            System.out.println("Usuario guardado: " + user);
            return ResponseEntity.ok("Usuario creado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Usuario> getAllUsers() {
        return userService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<UsuarioDto> login(@RequestBody UsuarioDto user) {
        try{
            return ResponseEntity.ok(userService.login(user));
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
