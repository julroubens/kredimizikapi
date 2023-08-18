package com.julroubens.kredimizik.kredi_mizik_rest_api.repos;

import com.julroubens.kredimizik.kredi_mizik_rest_api.domain.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InteractionRepository extends JpaRepository<Interaction, Long> {
}
