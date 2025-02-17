package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private enum Month{
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);


        private final int days;

        Month(final int days){
            this.days = days;
        }

        public static Month fromString(String monthString) throws IllegalArgumentException {
            Month foundMonth = null;
            int occurrencies = 0;
            for (final Month month : Month.values()) {
                if(month.name().startsWith(monthString.toUpperCase(Locale.ROOT))) {
                    foundMonth = month;
                    occurrencies++;
                }
            }
            if(occurrencies > 1) {
                throw new IllegalArgumentException("The string inserted is ambiguous");
            }
            else if(occurrencies <= 0) {
                throw new IllegalArgumentException("The string inserted is not a month");
            }
            return foundMonth;
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }

    private class SortByMonthOrder implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) throws IllegalArgumentException {
            return Month.fromString(o1).ordinal() - Month.fromString(o2).ordinal();
        }

    }

    private class SortByDate implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) throws IllegalArgumentException{
            return Month.fromString(o1).days - Month.fromString(o2).days;
        }

    }
}
