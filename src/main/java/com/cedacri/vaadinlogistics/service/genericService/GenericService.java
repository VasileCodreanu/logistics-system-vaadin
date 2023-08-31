package com.cedacri.vaadinlogistics.service.genericService;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T extends Serializable> {

    T getById(final Long id);

    List<T> getAll();

    T save(final T entity);

    T update(final T entity);

    void deleteById(final Long entityId);
}
