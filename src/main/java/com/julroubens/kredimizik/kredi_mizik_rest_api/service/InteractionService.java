package com.julroubens.kredimizik.kredi_mizik_rest_api.service;

import com.julroubens.kredimizik.kredi_mizik_rest_api.domain.Album;
import com.julroubens.kredimizik.kredi_mizik_rest_api.domain.Interaction;
import com.julroubens.kredimizik.kredi_mizik_rest_api.domain.Song;
import com.julroubens.kredimizik.kredi_mizik_rest_api.domain.Target;
import com.julroubens.kredimizik.kredi_mizik_rest_api.domain.User;
import com.julroubens.kredimizik.kredi_mizik_rest_api.model.InteractionDTO;
import com.julroubens.kredimizik.kredi_mizik_rest_api.repos.AlbumRepository;
import com.julroubens.kredimizik.kredi_mizik_rest_api.repos.InteractionRepository;
import com.julroubens.kredimizik.kredi_mizik_rest_api.repos.SongRepository;
import com.julroubens.kredimizik.kredi_mizik_rest_api.repos.TargetRepository;
import com.julroubens.kredimizik.kredi_mizik_rest_api.repos.UserRepository;
import com.julroubens.kredimizik.kredi_mizik_rest_api.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class InteractionService {

    private final InteractionRepository interactionRepository;
    private final SongRepository songRepository;
    private final UserRepository userRepository;
    private final AlbumRepository albumRepository;
    private final TargetRepository targetRepository;

    public InteractionService(final InteractionRepository interactionRepository,
            final SongRepository songRepository, final UserRepository userRepository,
            final AlbumRepository albumRepository, final TargetRepository targetRepository) {
        this.interactionRepository = interactionRepository;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.albumRepository = albumRepository;
        this.targetRepository = targetRepository;
    }

    public List<InteractionDTO> findAll() {
        final List<Interaction> interactions = interactionRepository.findAll(Sort.by("id"));
        return interactions.stream()
                .map(interaction -> mapToDTO(interaction, new InteractionDTO()))
                .toList();
    }

    public InteractionDTO get(final Long id) {
        return interactionRepository.findById(id)
                .map(interaction -> mapToDTO(interaction, new InteractionDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final InteractionDTO interactionDTO) {
        final Interaction interaction = new Interaction();
        mapToEntity(interactionDTO, interaction);
        return interactionRepository.save(interaction).getId();
    }

    public void update(final Long id, final InteractionDTO interactionDTO) {
        final Interaction interaction = interactionRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(interactionDTO, interaction);
        interactionRepository.save(interaction);
    }

    public void delete(final Long id) {
        interactionRepository.deleteById(id);
    }

    private InteractionDTO mapToDTO(final Interaction interaction,
            final InteractionDTO interactionDTO) {
        interactionDTO.setId(interaction.getId());
        interactionDTO.setAction(interaction.getAction());
        interactionDTO.setCommentContent(interaction.getCommentContent());
        interactionDTO.setTimestamp(interaction.getTimestamp());
        interactionDTO.setStatus(interaction.getStatus());
        interactionDTO.setSong(interaction.getSong() == null ? null : interaction.getSong().getId());
        interactionDTO.setUser(interaction.getUser() == null ? null : interaction.getUser().getId());
        interactionDTO.setAlbum(interaction.getAlbum() == null ? null : interaction.getAlbum().getId());
        interactionDTO.setTarget(interaction.getTarget() == null ? null : interaction.getTarget().getId());
        return interactionDTO;
    }

    private Interaction mapToEntity(final InteractionDTO interactionDTO,
            final Interaction interaction) {
        interaction.setAction(interactionDTO.getAction());
        interaction.setCommentContent(interactionDTO.getCommentContent());
        interaction.setTimestamp(interactionDTO.getTimestamp());
        interaction.setStatus(interactionDTO.getStatus());
        final Song song = interactionDTO.getSong() == null ? null : songRepository.findById(interactionDTO.getSong())
                .orElseThrow(() -> new NotFoundException("song not found"));
        interaction.setSong(song);
        final User user = interactionDTO.getUser() == null ? null : userRepository.findById(interactionDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user not found"));
        interaction.setUser(user);
        final Album album = interactionDTO.getAlbum() == null ? null : albumRepository.findById(interactionDTO.getAlbum())
                .orElseThrow(() -> new NotFoundException("album not found"));
        interaction.setAlbum(album);
        final Target target = interactionDTO.getTarget() == null ? null : targetRepository.findById(interactionDTO.getTarget())
                .orElseThrow(() -> new NotFoundException("target not found"));
        interaction.setTarget(target);
        return interaction;
    }

}
