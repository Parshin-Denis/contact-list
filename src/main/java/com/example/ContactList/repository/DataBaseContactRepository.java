package com.example.ContactList.repository;

import com.example.ContactList.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class DataBaseContactRepository implements ContactRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Contact> findAll() {
        String sql = "SELECT * FROM contact";
        return jdbcTemplate.query(sql, new ContactRowMapper());
    }

    @Override
    public Optional<Contact> findById(long id) {
        String sql = "SElECT * FROM contact WHERE id = ?";
        Contact contact = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                        sql,
                        new ArgumentPreparedStatementSetter(new Object[] {id}),
                        new RowMapperResultSetExtractor<>(new ContactRowMapper(), 1)
                )
        );
        return Optional.ofNullable(contact);
    }

    @Override
    public Contact save(Contact contact) {
        String sql = "INSERT INTO contact (first_name, last_name, email, phone) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone());
        return contact;
    }

    @Override
    public Contact update(Contact contact) {
        Contact existedContact = findById(contact.getId()).orElse(null);
        if (existedContact != null){
            String sql = "UPDATE contact SET first_name = ?, last_name = ?, email = ?, phone = ? WHERE id = ?";
            jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail()
                    , contact.getPhone(), contact.getId());
        }
        return existedContact;
    }

    @Override
    public void deleteById(long id) {
        String sql = "DELETE FROM contact WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
