package com.julroubens.kredimizik.kredi_mizik_rest_api.repos;

import com.julroubens.kredimizik.kredi_mizik_rest_api.domain.Entity;
import com.julroubens.kredimizik.kredi_mizik_rest_api.domain.Instrument;
import com.julroubens.kredimizik.kredi_mizik_rest_api.domain.Song;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findAllByEntity(Entity entity);

    List<Song> findAllByInstrument(Instrument instrument);

}
