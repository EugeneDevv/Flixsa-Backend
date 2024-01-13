package com.smartspotsolutions.flixsa.repository;

import com.smartspotsolutions.flixsa.domain.User;

import java.util.Collection;

/**
 * Created by Eugene Devv on 20 Dec, 2023
 */
public interface UserRepository<T extends User> {
//*    Basic CRUD operations */

    T create(T data);
    Collection<T> list(int page, int pageSize);
    T get(Long id);
    T update(T data);
    Boolean delete(Long id);

//*    More complex operations /*
}
