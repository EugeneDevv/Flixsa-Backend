package com.smartspotsolutions.flixsa.repository.implementation;

import com.smartspotsolutions.flixsa.domain.cinemas.Role;
import com.smartspotsolutions.flixsa.domain.cinemas.Staff;
import com.smartspotsolutions.flixsa.enumeration.RoleType;
import com.smartspotsolutions.flixsa.exception.ApiException;
import com.smartspotsolutions.flixsa.repository.RoleRepository;
import com.smartspotsolutions.flixsa.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
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
import static com.smartspotsolutions.flixsa.enumeration.VerificationType.STAFF;
import static com.smartspotsolutions.flixsa.query.StaffQuery.*;
import static java.util.Objects.requireNonNull;

/**
 * Created by Eugene Devv on 20 Dec, 2023
 */

@Repository
@RequiredArgsConstructor
@Slf4j
public class StaffRepositoryImpl implements StaffRepository<Staff> {

    private final NamedParameterJdbcTemplate jdbc;
    private final RoleRepository<Role> roleRoleRepository;
    private final BCryptPasswordEncoder encoder;


    @Override
    public Staff create(Staff staff, boolean createdCinema) {
        //        Check the email is unique
        if (getEmailCount(staff.getEmail().toLowerCase()) > 0)
            throw new ApiException("Email already in use. Please use a different email and try again.");


        String roleName = createdCinema ? RoleType.ROLE_OWNER.name() : RoleType.ROLE_USER.name();
        //        Save new staff
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource parameters = getSqlParameterSource(staff);
            jdbc.update(INSERT_STAFF_QUERY, parameters, holder);
            staff.setId(requireNonNull(holder.getKey()).longValue());
            //        Add role to the staff
            roleRoleRepository.addRoleToStaff(staff.getId(), roleName);
            //        Send Verification URL
            String verificationURL = getVerificationURL(UUID.randomUUID().toString(), STAFF.getType());
            //        Save URL in StaffVerification table
            jdbc.update(INSERT_STAFF_ACCOUNT_VERIFICATION_QUERY, Map.of("staffId", staff.getId(), "url", verificationURL));
            //        Send email to staff with verification URL
//            emailService.sendVerification(staff.getFirstName(), staff.getEmail(), verificationURL, STAFF.getType());
            staff.setEnabled(false);
            //        Return the newly created staff
            return staff;
            //        If any errors, throw exception with proper message

        } catch (Exception exception) {
            throw new ApiException("An error occurred. Please try again.");
        }

    }


    @Override
    public Collection<Staff> list(int page, int pageSize) {
        return null;
    }

    @Override
    public Staff get(Long id) {
        return null;
    }

    @Override
    public Staff update(Staff data) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    private Integer getEmailCount(String email) {
        return jdbc.queryForObject(COUNT_STAFF_EMAIL_QUERY, Map.of("email", email), Integer.class);
    }

    private SqlParameterSource getSqlParameterSource(Staff staff) {
        return new MapSqlParameterSource()
                .addValue("firstName", staff.getFirstName())
                .addValue("lastName", staff.getLastName())
                .addValue("email", staff.getEmail())
                .addValue("password", encoder.encode(staff.getPassword()))
                ;
    }

}
