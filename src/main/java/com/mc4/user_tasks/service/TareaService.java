package com.mc4.user_tasks.service;

import com.mc4.user_tasks.dto.TareaNuevaDto;
import com.mc4.user_tasks.entity.Usuario;
import com.mc4.user_tasks.repository.UsuarioRepository;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mc4.user_tasks.entity.Tarea;
import com.mc4.user_tasks.repository.TareaRepository;
import com.mc4.user_tasks.dto.TareaDto;


import java.util.List;
import java.util.Optional;



@Service
public class TareaService {

    private final TareaRepository taskRepository;
    private final UsuarioRepository userRepository;


    public TareaService(TareaRepository taskRepository, UsuarioRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Tarea createTask(TareaNuevaDto tareaNuevaDto) {

        Tarea tarea = new Tarea();
        tarea.setId(null);
        tarea.setTitulo(tareaNuevaDto.getTitulo());
        tarea.setDescripcion(tareaNuevaDto.getDescripcion());
        tarea.setEstadoTarea(Tarea.EstadoTarea.PENDIENTE);
        tarea.setEstado(true);

        Optional<Usuario> usuario = userRepository.findByEmail(tareaNuevaDto.getEmail());
        if (usuario.isPresent()) {
            tarea.setUsuarioAsignado(usuario.get());
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }

        return taskRepository.save(tarea);
    }

    public List<Tarea> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Tarea> getTasksByUser(Long userId) {
        Optional<Usuario> user = userRepository.findById(userId);
        return user.map(taskRepository::findByUsuarioAsignado).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // obtener tarea por email
    public List<Tarea> getTasksByEmail(String email) {
        Optional<Usuario> user = userRepository.findByEmail(email);
        return user.map(taskRepository::findByUsuarioAsignado).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Transactional
    public Tarea cambiarEstado(Long tareaId,String email) {
        Optional<Tarea> tareaOptional = taskRepository.findById(tareaId);
        Optional<Usuario> user = userRepository.findByEmail(email);
        if (tareaOptional.isPresent() && user.isPresent()) {
            Tarea tarea = tareaOptional.get();
            Usuario usuario = user.get();

            tarea.avanzarEstado();

        } else {
            throw new RuntimeException("Tarea o usuario no encontrado");
        }
        return taskRepository.save(tareaOptional.get());

    }

    @Transactional
    public Tarea cambiarEstadoAtras(Long id, String email) {

        Optional<Tarea> tareaOptional = taskRepository.findById(id);
        Optional<Usuario> user = userRepository.findByEmail(email);
        if (tareaOptional.isPresent() && user.isPresent()) {
            Tarea tarea = tareaOptional.get();
            Usuario usuario = user.get();

            tarea.retrocederEstado();

        } else {
            throw new RuntimeException("Tarea o usuario no encontrado");
        }
        return taskRepository.save(tareaOptional.get());
    }
}
