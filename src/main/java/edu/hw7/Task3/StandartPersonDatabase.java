package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StandartPersonDatabase implements PersonDatabase {

    private final Map<Integer, Person> personDatabase = new HashMap<>();
    private final Map<String, List<Person>> personsByName = new HashMap<>();
    private final Map<String, List<Person>> personsByAddress = new HashMap<>();
    private final Map<String, List<Person>> personsByPhone = new HashMap<>();

    @Override
    public void add(Person person) {

        if (personDatabase.containsKey(person.id())) {
            delete(person.id());
        }

        personDatabase.put(person.id(), person);
        addIndex(personsByName, person.name(), person);
        addIndex(personsByAddress, person.address(), person);
        addIndex(personsByPhone, person.phoneNumber(), person);
    }

    @Override
    public void delete(int id) {
        Person person = personDatabase.remove(id);

        if (person != null) {
            deleteIndex(personsByName, person.name(), person);
            deleteIndex(personsByAddress, person.address(), person);
            deleteIndex(personsByPhone, person.phoneNumber(), person);
        }
    }

    @Override
    public List<Person> findByName(String name) {
        return personsByName.getOrDefault(name, new ArrayList<>());
    }

    @Override
    public List<Person> findByAddress(String address) {
        return personsByAddress.getOrDefault(address, new ArrayList<>());
    }

    @Override
    public List<Person> findByPhone(String phone) {
        return personsByPhone.getOrDefault(phone, new ArrayList<>());
    }

    private boolean addIndex(Map<String, List<Person>> indexList, String indexParameter, Person person) {
        return indexList.computeIfAbsent(indexParameter, p -> new ArrayList<>()).add(person);
    }

    private void deleteIndex(Map<String, List<Person>> indexList, String indexParameter, Person person) {
        List<Person> people = indexList.get(indexParameter);

        if (people != null) {
            people.remove(person);
            if (people.isEmpty()) {
                indexList.remove(indexParameter);
            }
        }
    }
}
