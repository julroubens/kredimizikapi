package com.julroubens.kredimizik.kredi_mizik_rest_api.service;

import com.julroubens.kredimizik.kredi_mizik_rest_api.domain.EntityType;
import com.julroubens.kredimizik.kredi_mizik_rest_api.model.EntityTypeDTO;
import com.julroubens.kredimizik.kredi_mizik_rest_api.repos.EntityRepository;
import com.julroubens.kredimizik.kredi_mizik_rest_api.repos.EntityTypeRepository;
import com.julroubens.kredimizik.kredi_mizik_rest_api.util.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class EntityTypeService {

    private final EntityTypeRepository entityTypeRepository;
    private final EntityRepository entityRepository;

    public EntityTypeService(final EntityTypeRepository entityTypeRepository,
            final EntityRepository entityRepository) {
        this.entityTypeRepository = entityTypeRepository;
        this.entityRepository = entityRepository;
    }

    public List<EntityTypeDTO> findAll() {
        final List<EntityType> entityTypes = entityTypeRepository.findAll(Sort.by("id"));
        return entityTypes.stream()
                .map(entityType -> mapToDTO(entityType, new EntityTypeDTO()))
                .toList();
    }

    public EntityTypeDTO get(final Long id) {
        return entityTypeRepository.findById(id)
                .map(entityType -> mapToDTO(entityType, new EntityTypeDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final EntityTypeDTO entityTypeDTO) {
        final EntityType entityType = new EntityType();
        mapToEntity(entityTypeDTO, entityType);
        return entityTypeRepository.save(entityType).getId();
    }

    public void update(final Long id, final EntityTypeDTO entityTypeDTO) {
        final EntityType entityType = entityTypeRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(entityTypeDTO, entityType);
        entityTypeRepository.save(entityType);
    }

    public void delete(final Long id) {
        final EntityType entityType = entityTypeRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        // remove many-to-many relations at owning side
        entityRepository.findAllByType(entityType)
                .forEach(entity -> entity.getType().remove(entityType));
        entityTypeRepository.delete(entityType);
    }

    private EntityTypeDTO mapToDTO(final EntityType entityType, final EntityTypeDTO entityTypeDTO) {
        entityTypeDTO.setId(entityType.getId());
        entityTypeDTO.setName(entityType.getName());
        entityTypeDTO.setImageUrl(entityType.getImageUrl());
        entityTypeDTO.setStatus(entityType.getStatus());
        return entityTypeDTO;
    }

    private EntityType mapToEntity(final EntityTypeDTO entityTypeDTO, final EntityType entityType) {
        entityType.setName(entityTypeDTO.getName());
        entityType.setImageUrl(entityTypeDTO.getImageUrl());
        entityType.setStatus(entityTypeDTO.getStatus());
        return entityType;
    }

}
