package com.dev.apresenta_ia.DAO;

import com.dev.apresenta_ia.Models.Entity.ExemploEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemploDAO //extends JpaRepository<ExemploEntity, Long>
{
    // O repository cria automaticamente querys como findAll(), e você pode
    // criar variações apenas com nomeclatura.
}
