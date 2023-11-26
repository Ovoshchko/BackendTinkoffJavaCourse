package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadLockPersonDatabase implements PersonDatabase {
    private final Map<Integer, Person> personDatabase = new HashMap<>();
    private final Map<String, List<Person>> personsByName = new HashMap<>();
    private final Map<String, List<Person>> personsByAddress = new HashMap<>();
    private final Map<String, List<Person>> personsByPhone = new HashMap<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    @Override
    public synchronized void add(Person person) {
        Objects.requireNonNull(person);

        if (personDatabase.containsKey(person.id())) {
            delete(person.id());
        }

        try {
            writeLock.lock();

            personDatabase.put(person.id(), person);
            addIndex(personsByName, person.name(), person);
            addIndex(personsByAddress, person.address(), person);
            addIndex(personsByPhone, person.phoneNumber(), person);
        } finally {
            writeLock.unlock();
        }

    }

    @Override
    public synchronized void delete(int id) {
        try {
            writeLock.lock();
            Person person = personDatabase.remove(id);

            if (person != null) {
                deleteIndex(personsByName, person.name(), person);
                deleteIndex(personsByAddress, person.address(), person);
                deleteIndex(personsByPhone, person.phoneNumber(), person);
            }
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        try {
            readLock.lock();
            return personsByName.getOrDefault(name, new ArrayList<>());
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        try {
            readLock.lock();
            return personsByAddress.getOrDefault(address, new ArrayList<>());
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        try {
            readLock.lock();
            return personsByPhone.getOrDefault(phone, new ArrayList<>());
        } finally {
            readLock.unlock();
        }
    }

    private void addIndex(Map<String, List<Person>> indexList, String indexParameter, Person person) {
        indexList.computeIfAbsent(indexParameter, p -> new ArrayList<>()).add(person);
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
