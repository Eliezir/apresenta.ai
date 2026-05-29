package com.dev.apresenta_ia.DAO.Provedor;


import com.dev.apresenta_ia.Models.Entity.Provedor.Provedor;
import com.dev.apresenta_ia.Models.Entity.Provedor.TipoProvedor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;



public interface ProvedorDAO extends JpaRepository<Provedor, Long> {

   Optional<Provedor> findByTipo(TipoProvedor tipo);

}