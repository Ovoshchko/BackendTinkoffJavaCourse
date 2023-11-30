package edu.hw7.Task3;

import java.util.List;

public class SyncPersonDatabase implements PersonDatabase {

    private final StandartPersonDatabase database = new StandartPersonDatabase();

    @Override
    public synchronized void add(Person person) {
        database.add(person);
    }

    @Override
    public synchronized void delete(int id) {
        database.delete(id);
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return database.findByName(name);
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return database.findByAddress(address);
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return database.findByPhone(phone);
    }
}
