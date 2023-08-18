package com.julroubens.kredimizik.kredi_mizik_rest_api.repos;

import com.julroubens.kredimizik.kredi_mizik_rest_api.domain.Entity;
import com.julroubens.kredimizik.kredi_mizik_rest_api.domain.EntityType;
import com.julroubens.kredimizik.kredi_mizik_rest_api.domain.Instrument;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EntityRepository extends JpaRepository<Entity, Long> {

    List<Entity> findAllByInstrument(Instrument instrument);

    List<Entity> findAllByType(EntityType entityType);

}
