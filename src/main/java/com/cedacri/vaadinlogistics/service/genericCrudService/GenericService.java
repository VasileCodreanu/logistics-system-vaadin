package com.cedacri.vaadinlogistics.service.genericCrudService;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public interface GenericService<T extends Serializable> {

    T getById(final Long id);

    List<T> getAll();

    T save(final T entity);

    T update(final T entity);

    void deleteById(final Long entityId);
}
