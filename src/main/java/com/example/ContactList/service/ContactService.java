package com.example.ContactList.service;

import com.example.ContactList.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    List<Contact> findAll();

    Contact findById(long id);

    Contact save(Contact contact);

    Contact update(Contact contact);

    void deleteById(long id);
}
