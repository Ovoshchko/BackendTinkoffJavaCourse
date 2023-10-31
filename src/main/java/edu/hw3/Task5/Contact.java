package edu.hw3.Task5;

public record Contact(String surName, String firstName) {

    public int compareTo(Contact other) {

        if (surName.isBlank() && !other.surName.isBlank()) {
            return 1;
        } else if (!surName.isBlank() && other.surName.isBlank()) {
            return -1;
        }

        String thisContact = surName + firstName;
        String otherContact = other.surName + other.firstName;

        return thisContact.compareTo(otherContact);
    }

}
