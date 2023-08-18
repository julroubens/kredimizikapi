package com.julroubens.kredimizik.kredi_mizik_rest_api.repos;

import com.julroubens.kredimizik.kredi_mizik_rest_api.domain.EntityType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EntityTypeRepository extends JpaRepository<EntityType, Long> {
}
