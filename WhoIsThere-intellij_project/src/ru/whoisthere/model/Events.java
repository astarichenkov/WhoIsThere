package ru.whoisthere.model;

import java.util.ArrayList;

public class Events {
    private String name;
    private String surname;
    private int pObject;
    private int time;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getpObject() {
        return pObject;
    }

    public int getTime() {
        return time;
    }

    public Events(String name, String surname, int pObject, int time) {
        this.name = name;
        this.surname = surname;
        this.pObject = pObject;
        this.time = time;
    }

    public static int calculateTime() {
        ArrayList<Events> eventsList = new ArrayList<>();
//        eventsList.add(new Events("Старичеоков", "Антон", 69, 25169430));
//        eventsList.add(new Events("Старичеоков", "Антон", 76, 51679350));
//        eventsList.add(new Events("Старичеоков", "Антон", 69, 55091160));
//        eventsList.add(new Events("Старичеоков", "Антон", 76, 57625850));
//        eventsList.add(new Events("Старичеоков", "Антон", 83, 58242430));
//        eventsList.add(new Events("Старичеоков", "Антон", 83, 58688840));

        eventsList.add(new Events("Стариченков", "Антон", 69, 45223070));
        eventsList.add(new Events("Стариченков", "Антон", 76, 56919100));
        eventsList.add(new Events("Стариченков", "Антон", 69, 58563740));
        eventsList.add(new Events("Стариченков", "Антон", 76, 70587280));
        eventsList.add(new Events("Стариченков", "Антон", 69, 72184920));

        int rsl = 0;
        int startTime = eventsList.get(0).getTime();
        int endTime = eventsList.get(eventsList.size() - 1).getTime();
        for (int i = 1; i < eventsList.size(); i++) {
            int lastEventTime = eventsList.get(i).getpObject();
            int previousEventTime = eventsList.get(i - 1).getpObject();

            if (eventsList.get(i).getpObject() == 69 && eventsList.get(i - 1).getpObject() == 76) {
                System.out.println("+++ " + i);
                rsl += eventsList.get(i).getTime() - eventsList.get(i - 1).getTime();
            }
        }
        System.out.println(rsl / 1000);
        return rsl;

    }

    public static void main(String[] args) {
        calculateTime();
    }

}
