package com.swipejobs.techtest.model;

/**
 * Class to define workers' availability during the week returned from the API
 */
public class Availability {

    private String title;
    private int dayIndex;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    public void setDayIndex(int dayIndex) {
        this.dayIndex = dayIndex;
    }

    @Override
    public boolean equals(Object available) {
        if (this == available) return true;
        if (available == null || getClass() != available.getClass()) return false;

        Availability availability = (Availability) available;

        return dayIndex == availability.dayIndex;
    }

    @Override
    public int hashCode() {
        return dayIndex;
    }

    @Override
    public String toString() {
        return "Availability{" +
                "title='" + title + '\'' +
                ", dayIndex=" + dayIndex +
                '}';
    }
}
