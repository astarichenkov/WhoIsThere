package ru.whoisthere.model;

import java.util.ArrayList;
import java.util.List;

public class PhotoCache {
    private static List<Person> personsCache = new ArrayList<>();

    public static void addPersonToCache(Person person) {
        personsCache.add(person);
    }

    public static Person getPersonFromCache(Person person) {
        for (Person p : personsCache) {
            if (p.equals(person)) {
                return p;
            }
        }
        return new Person("NotFound", "", "", "", new byte[10]);
    }

    public static boolean personsCacheContains(Person person) {
        return personsCache.contains(person);
    }

//    public static List<Person> getPersonsCache() {
//        return personsCache;
//    }
//
//    public static void setPersonsCache(List<Person> personsCache) {
//        PhotoCache.personsCache = personsCache;
//    }
}
