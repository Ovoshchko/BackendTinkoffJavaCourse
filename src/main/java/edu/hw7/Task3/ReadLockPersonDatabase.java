package edu.hw7.Task3;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadLockPersonDatabase implements PersonDatabase {

    private final StandartPersonDatabase database = new StandartPersonDatabase();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    @Override
    public void add(Person person) {
        Objects.requireNonNull(person);

        try {
            writeLock.lock();

            database.add(person);
        } finally {
            writeLock.unlock();
        }

    }

    @Override
    public void delete(int id) {
        try {
            writeLock.lock();

            database.delete(id);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        try {
            readLock.lock();
            return database.findByName(name);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        try {
            readLock.lock();
            return database.findByAddress(address);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        try {
            readLock.lock();
            return database.findByPhone(phone);
        } finally {
            readLock.unlock();
        }
    }
}
