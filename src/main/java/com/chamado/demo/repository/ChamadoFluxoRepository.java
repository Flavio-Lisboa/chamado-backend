package com.chamado.demo.repository;

import com.chamado.demo.model.ChamadoFluxo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChamadoFluxoRepository extends JpaRepository<ChamadoFluxo, Long> {

    @Query("select f from ChamadoFluxo f where f.chamado.id = :chamadoId order by f.id")
    List<ChamadoFluxo> selectAllComentarios(Long chamadoId);
}
