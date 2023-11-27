package com.chamado.demo.repository;

import com.chamado.demo.model.Chamado;
import com.chamado.demo.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

    @Query("select c from Chamado c where c.usuario.id = :id")
    List<Chamado> selectAllFromChamadoByUserId(Long id);

    @Query("select c from Chamado c where c.departamento = :departamento")
    List<Chamado> selectAllFromChamadoByAtendenteDepartamento(Departamento departamento);
}
