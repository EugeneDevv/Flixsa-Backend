package com.smartspotsolutions.flixsa.repository;

import com.smartspotsolutions.flixsa.domain.cinemas.Cinema;
import com.smartspotsolutions.flixsa.domain.cinemas.Staff;

import java.util.Collection;

/**
 * Created by Eugene Devv on 20 Dec, 2023
 */
public interface CinemaRepository<T extends Cinema> {
//*    Basic CRUD operations */

    T create(T data);
    Collection<T> list(int page, int pageSize);
    T get(Long id);
    T update(T data);
    Boolean delete(Long id);

//*    More complex operations /*
}
