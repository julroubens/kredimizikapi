package com.julroubens.kredimizik.kredi_mizik_rest_api.service;

import com.julroubens.kredimizik.kredi_mizik_rest_api.domain.Target;
import com.julroubens.kredimizik.kredi_mizik_rest_api.model.TargetDTO;
import com.julroubens.kredimizik.kredi_mizik_rest_api.repos.TargetRepository;
import com.julroubens.kredimizik.kredi_mizik_rest_api.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class TargetService {

    private final TargetRepository targetRepository;

    public TargetService(final TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }

    public List<TargetDTO> findAll() {
        final List<Target> targets = targetRepository.findAll(Sort.by("id"));
        return targets.stream()
                .map(target -> mapToDTO(target, new TargetDTO()))
                .toList();
    }

    public TargetDTO get(final Long id) {
        return targetRepository.findById(id)
                .map(target -> mapToDTO(target, new TargetDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final TargetDTO targetDTO) {
        final Target target = new Target();
        mapToEntity(targetDTO, target);
        return targetRepository.save(target).getId();
    }

    public void update(final Long id, final TargetDTO targetDTO) {
        final Target target = targetRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(targetDTO, target);
        targetRepository.save(target);
    }

    public void delete(final Long id) {
        targetRepository.deleteById(id);
    }

    private TargetDTO mapToDTO(final Target target, final TargetDTO targetDTO) {
        targetDTO.setId(target.getId());
        targetDTO.setTargetType(target.getTargetType());
        return targetDTO;
    }

    private Target mapToEntity(final TargetDTO targetDTO, final Target target) {
        target.setTargetType(targetDTO.getTargetType());
        return target;
    }

}
