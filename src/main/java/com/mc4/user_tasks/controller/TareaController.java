package com.mc4.user_tasks.controller;

import com.mc4.user_tasks.dto.TareaDto;
import com.mc4.user_tasks.dto.TareaNuevaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mc4.user_tasks.entity.Tarea;
import com.mc4.user_tasks.service.TareaService;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/tareas")
@CrossOrigin(origins = "http://localhost:4200")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/create")
    public Tarea createTask(@RequestParam String email, @RequestParam String titulo,
                            @RequestParam String descripcion) {

        TareaNuevaDto nueva = new TareaNuevaDto();
        nueva.setEmail(email);
        nueva.setTitulo(titulo);
        nueva.setDescripcion(descripcion);
        System.out.println("Email: " + email);
        return tareaService.createTask(nueva);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<Tarea> getAllTasks() {
        return tareaService.getAllTasks();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{email}")
    public List<Tarea> getTasksByEmail(@PathVariable String email) {
        return tareaService.getTasksByEmail(email);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping("/{id}/estadoAdelante")
    public ResponseEntity<Tarea> cambiarEstadoAdelante(@RequestParam Long id,
                                               @RequestParam String email) {
        return ResponseEntity.ok(tareaService.cambiarEstado(id, email));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping("/{id}/estadoAtras")
    public ResponseEntity<Tarea> cambiarEstadoAtras(@RequestParam Long id,
                                               @RequestParam String email) {
        return ResponseEntity.ok(tareaService.cambiarEstadoAtras(id, email));
    }


}
