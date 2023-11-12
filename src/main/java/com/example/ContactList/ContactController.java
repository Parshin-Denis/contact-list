package com.example.ContactList;

import com.example.ContactList.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("contacts", contactService.findAll());
        return "index";
    }

    @GetMapping("/contact/create")
    public String showCreateContactForm(Model model){
        model.addAttribute("contact", new Contact());
        model.addAttribute("action", "create");
        return "input";
    }

    @PostMapping("/contact/create")
    public String createContact(@ModelAttribute Contact contact){
        contactService.save(contact);
        return "redirect:/";
    }

    @GetMapping("/contact/edit/{id}")
    public String showEditContactForm(@PathVariable long id, Model model){
        Contact contact = contactService.findById(id);
        if (contact != null) {
            model.addAttribute("contact", contact);
            model.addAttribute("action", "edit");
            return "input";
        }
        return "redirect:/";
    }

    @PostMapping("/contact/edit")
    public String editContact(@ModelAttribute Contact contact, Model model){
        contactService.update(contact);
        return "redirect:/";
    }

    @GetMapping("/contact/delete/{id}")
    public String deleteContact(@PathVariable long id){
        contactService.deleteById(id);
        return "redirect:/";
    }
}
