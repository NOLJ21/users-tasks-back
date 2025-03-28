package com.mc4.user_tasks.repository;


import com.mc4.user_tasks.entity.Tarea;
import com.mc4.user_tasks.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    @Query("SELECT t FROM Tarea t WHERE t.usuarioAsignado = :usuario")
    List<Tarea> findByUsuarioAsignado(Usuario usuario);
}
