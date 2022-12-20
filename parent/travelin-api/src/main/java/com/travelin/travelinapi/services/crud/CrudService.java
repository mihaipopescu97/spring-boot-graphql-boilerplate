package com.travelin.travelinapi.services.crud;

import com.travelin.travelinapi.entities.generics.GenericEntity;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/31/2022, Sat
 **/


public interface CrudService <T, V extends GenericEntity> {

    default V get(final Long id, final JpaRepository<V, Long> repository, Class<V> clazz, final Logger logger) {
        final V entity = repository.findById(id).orElseThrow(() -> {
            final String message = clazz.getSimpleName() +
                    ":"
                    + id
                    + " not found!";

            logger.info("message");
            return new EntityNotFoundException(message);
        });

        final String message = "Found entity of type "
                + clazz.getSimpleName()
                + " with ID "
                + id;

        logger.info(message);

        return entity;
    }

    default V create(final T dto, final JpaRepository<V, Long> repository,
                     final ModelMapper modelMapper, Class<V> clazz, final Logger logger) {
        final V entity = modelMapper.map(dto, clazz);
        final V savedEntity = repository.save(entity);

        final String logMessage = "Created entity of type " +
                clazz.getSimpleName() +
                " with ID " +
                savedEntity.getId();

        logger.info(logMessage);

        return savedEntity;
    }

    default V update(final Long id, final T dto, final JpaRepository<V, Long> repository,
                     final ModelMapper modelMapper, Class<V> clazz, final Logger logger) {

        final V foundEntity = repository.findById(id).orElseThrow(() -> {
            final String message = clazz.getSimpleName() +
                    ":"
                    + id
                    + " not found!";

            logger.info("message");
            return new EntityNotFoundException(message);
        });

        modelMapper.map(dto, foundEntity);

        final V updatedEntity = repository.save(foundEntity);

        final String message = "Updated entity of type "
                + clazz.getSimpleName()
                + " with ID "
                + id;

        logger.info(message);

        return updatedEntity;
    }

    default Long delete(final Long id, final JpaRepository<V, Long> repository, Class<V> clazz, final Logger logger) {
        repository.deleteById(id);

        final String message = "Deleted entity of type "
                + clazz.getSimpleName()
                + " with ID "
                + id;

        logger.info(message);

        return id;
    }
}
