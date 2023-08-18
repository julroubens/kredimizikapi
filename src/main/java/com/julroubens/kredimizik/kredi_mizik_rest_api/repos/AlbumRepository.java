package com.julroubens.kredimizik.kredi_mizik_rest_api.repos;

import com.julroubens.kredimizik.kredi_mizik_rest_api.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlbumRepository extends JpaRepository<Album, Long> {
}
