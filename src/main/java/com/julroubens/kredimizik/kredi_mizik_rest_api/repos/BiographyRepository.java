package com.julroubens.kredimizik.kredi_mizik_rest_api.repos;

import com.julroubens.kredimizik.kredi_mizik_rest_api.domain.Biography;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BiographyRepository extends JpaRepository<Biography, Long> {
}
