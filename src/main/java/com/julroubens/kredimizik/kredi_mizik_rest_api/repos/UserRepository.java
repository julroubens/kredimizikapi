package com.julroubens.kredimizik.kredi_mizik_rest_api.repos;

import com.julroubens.kredimizik.kredi_mizik_rest_api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
