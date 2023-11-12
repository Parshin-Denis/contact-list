package com.example.ContactList.repository;

import com.example.ContactList.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {
    List<Contact> findAll();

    Optional<Contact> findById(long id);

    Contact save(Contact contact);

    Contact update(Contact contact);

    void deleteById(long id);
}
