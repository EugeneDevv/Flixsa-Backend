package com.smartspotsolutions.flixsa.repository.implementation;

import com.smartspotsolutions.flixsa.domain.User;
import com.smartspotsolutions.flixsa.domain.cinemas.Cinema;
import com.smartspotsolutions.flixsa.domain.cinemas.Staff;
import com.smartspotsolutions.flixsa.exception.ApiException;
import com.smartspotsolutions.flixsa.repository.CinemaRepository;
import com.smartspotsolutions.flixsa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

import static com.smartspotsolutions.flixsa.query.CinemaQuery.COUNT_CINEMA_EMAIL_QUERY;
import static com.smartspotsolutions.flixsa.query.CinemaQuery.INSERT_CINEMA_QUERY;
import static java.util.Objects.requireNonNull;

/**
 * Created by Eugene Devv on 20 Dec, 2023
 */

@Repository
@RequiredArgsConstructor
@Slf4j
public class CinemaRepositoryImpl implements CinemaRepository<Cinema> {

    private final NamedParameterJdbcTemplate jdbc;


    @Override
    public Cinema create(Cinema cinema) {
        //        Check the email is unique
        if (getEmailCount(cinema.getEmail().toLowerCase()) > 0)
            throw new ApiException("Email already in use. Please use a different email and try again.");
        //        Save new cinema
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource parameters = getSqlParameterSource(cinema);
            jdbc.update(INSERT_CINEMA_QUERY, parameters, holder);
            cinema.setId(requireNonNull(holder.getKey()).longValue());

            //        Create new staff with owner role
            //        Return newly created cinema and logged in staff
            //        If any errors, throw exception with proper message

        } catch (EmptyResultDataAccessException exception) {

        } catch (Exception exception) {

        }
        return null;
    }

    @Override
    public Collection<Cinema> list(int page, int pageSize) {
        return null;
    }

    @Override
    public Cinema get(Long id) {
        return null;
    }

    @Override
    public Cinema update(Cinema data) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    private Integer getEmailCount(String email) {
        return jdbc.queryForObject(COUNT_CINEMA_EMAIL_QUERY, Map.of("email", email), Integer.class);
    }

    private SqlParameterSource getSqlParameterSource(Cinema cinema) {
        return new MapSqlParameterSource()
                .addValue("name", cinema.getName())
                .addValue("email", cinema.getEmail())
                ;
    }

}
