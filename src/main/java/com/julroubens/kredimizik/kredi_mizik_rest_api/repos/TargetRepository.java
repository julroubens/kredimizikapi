package com.julroubens.kredimizik.kredi_mizik_rest_api.repos;

import com.julroubens.kredimizik.kredi_mizik_rest_api.domain.Target;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TargetRepository extends JpaRepository<Target, Long> {
}
