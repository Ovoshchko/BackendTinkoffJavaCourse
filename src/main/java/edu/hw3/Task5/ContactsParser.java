package edu.hw3.Task5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ContactsParser {

    private final static Comparator<Contact> COMPARATOR = Contact::compareTo;
    private final static String STRAIGHT_ORDER = "ASC";
    private final static String REVERSE_ORDER = "DESC";
    private static final String WRONG_ORDER = "Wrong order";

    public Contact[] parseContacts(String[] contacts, String order) {
        Objects.requireNonNull(contacts);
        List<Contact> contactList = getContactList(contacts);
        contactList.sort(COMPARATOR);

        if (REVERSE_ORDER.equals(order)) {
            return contactList.reversed().toArray(new Contact[0]);
        } else if (STRAIGHT_ORDER.equals(order)) {
            return contactList.toArray(new Contact[0]);
        } else {
            throw new IllegalArgumentException(WRONG_ORDER);
        }
    }


    private List<Contact> getContactList(String[] contacts) {
        ArrayList<Contact> contactList = new ArrayList<>();
        String[] contactData;

        for (String contact: contacts) {
            Objects.requireNonNull(contact);

            contactData = contact.split(" +");
            String lastName = contactData.length > 1 ? contactData[1] : "";
            String firstName = contactData[0];

            contactList.add(new Contact(lastName, firstName));
        }

        return contactList;
    }
}
