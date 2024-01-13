package com.smartspotsolutions.flixsa.repository;

import com.smartspotsolutions.flixsa.domain.cinemas.Staff;

import java.util.Collection;

/**
 * Created by Eugene Devv on 20 Dec, 2023
 */
public interface StaffRepository<T extends Staff> {
//*    Basic CRUD operations */

    T create(T data,  boolean createdCinema);
    Collection<T> list(int page, int pageSize);
    T get(Long id);
    T update(T data);
    Boolean delete(Long id);

//*    More complex operations /*
}
