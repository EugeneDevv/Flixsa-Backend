package com.smartspotsolutions.flixsa.repository.implementation;

import com.smartspotsolutions.flixsa.domain.User;
import com.smartspotsolutions.flixsa.exception.ApiException;
import com.smartspotsolutions.flixsa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import static com.smartspotsolutions.flixsa.Utils.getVerificationURL;
import static com.smartspotsolutions.flixsa.enumeration.VerificationType.ACCOUNT;
import static com.smartspotsolutions.flixsa.query.UserQuery.*;
import static java.util.Objects.requireNonNull;

/**
 * Created by Eugene Devv on 20 Dec, 2023
 */

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository<User> {
    private final NamedParameterJdbcTemplate jdbc;
    private final BCryptPasswordEncoder encoder;


    @Override
    public User create(User user) {
//        Check the email is unique
        if (getEmailCount(user.getEmail().toLowerCase()) > 0)
            throw new ApiException("Email already in use. Please use a different email and try again.");
//        Save new user
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource parameters = getSqlParameterSource(user);
            jdbc.update(INSERT_USER_QUERY, parameters, holder);
            user.setId(requireNonNull(holder.getKey()).longValue());


//        Send Verification URL
            String verificationURL = getVerificationURL(UUID.randomUUID().toString(), ACCOUNT.getType());

//        Save URL in UserVerification table
            jdbc.update(INSERT_USER_ACCOUNT_VERIFICATION_QUERY, Map.of("userId", user.getId(), "url", verificationURL));
//        Send email to user with verification URL
//            emailService.sendVerification(user.getFirstName(), user.getEmail(), verificationURL, ACCOUNT);
            user.setEnabled(false);
            user.setNotLocked(true);
//        Return the newly created user
            return user;
            //        If any errors, throw exception with proper message
        } catch (Exception exception) {
            throw new ApiException("An error occurred. Please try again.");
        }
    }

    @Override
    public Collection<User> list(int page, int pageSize) {
        return null;
    }

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public User update(User data) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    private Integer getEmailCount(String email) {

        return jdbc.queryForObject(COUNT_USER_EMAIL_QUERY, Map.of("email", email), Integer.class);
    }

    private SqlParameterSource getSqlParameterSource(User user) {
        return new MapSqlParameterSource()
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("email", user.getEmail())
                .addValue("password", encoder.encode(user.getPassword()))
                ;
    }
}
