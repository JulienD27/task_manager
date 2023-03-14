package modernproject.controller;

import java.time.YearMonth;

import javafx.scene.control.ComboBox;
import modernproject.ModernProject;

import java.util.Calendar;

public class BuildCalendar {

    public int getDaysInMonth(int year, int month) {
        YearMonth yearMonthObject = YearMonth.of(year, month);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        return daysInMonth;
    }

    public void buildMonthComboBox(ComboBox<String> menu) {
        menu.getItems().addAll("January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October", "November", "December");
    }

    public int monthConverter(String month) {
        int mNum = 0;
        if (month.equals("January")) {
            mNum = 0;
        } else if (month.equals("February")) {
            mNum = 1;
        } else if (month.equals("March")) {
            mNum = 2;
        } else if (month.equals("April")) {
            mNum = 3;
        } else if (month.equals("May")) {
            mNum = 4;
        } else if (month.equals("June")) {
            mNum = 5;
        } else if (month.equals("July")) {
            mNum = 6;
        } else if (month.equals("August")) {
            mNum = 7;
        } else if (month.equals("September")) {
            mNum = 8;
        } else if (month.equals("October")) {
            mNum = 9;
        } else if (month.equals("November")) {
            mNum = 10;
        } else if (month.equals("December")) {
            mNum = 11;
        }
        return mNum;
    }


    public void buildYearComboBox(ComboBox<String> menu) {
//        LocalDate year = new LocalDate();
//        int currentYear = year.getYear();
//        int pYear = currentYear - 1;
//        String[] years = new String[6];
//        for(int x = 0; x < 6; x++){
//            years[x] = String.valueOf(pYear++);
//        }
        menu.getItems().addAll("2018", "2019", "2020", "2021", "2022", "2023");
    }

    public String returnDayWeek(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        int x = c.get(Calendar.DAY_OF_WEEK);
        return dayConverter(x);
    }

    private String dayConverter(int day) {
        if (day == 1) {
            return "Sunday";
        } else if (day == 2) {
            return "Monday";
        } else if (day == 3) {
            return "Tuesday";
        } else if (day == 4) {
            return "Wednesday";
        } else if (day == 5) {
            return "Thursday";
        } else if (day == 6) {
            return "Friday";
        } else if (day == 7) {
            return "Saturday";
        }
        return null;
    }

    public String findingDeadline(int year, int mNum, int day, String month, String list) {
        String dayOfWeek = returnDayWeek(year, mNum, day);
        String dateFormat = dayOfWeek + " " + month + " " + day + ", " + year;
        String[] names;
        if (list.equals("taskLL")) {
            names = ModernProject.taskLL.findDeadline(dateFormat, true);
        } else {
            names = ModernProject.eventsLL.findDeadline(dateFormat, false);
        }
        if (names == null) {
            return "n/a";
        }

        int count = 0, i = 0;
        int[] indices = new int[names.length];
        for (int x = 0; x < names.length; x++) {
            if (names[x] != null) {
                indices[count++] = x;
                i++;
            } else {
                indices[count++] = -1;
            }
        }

        String[] dNames = new String[i];
        int index = 0, counter = 0;
        for (int y = 0; y < dNames.length; y++) {
            if (indices[y] != -1) {
                index = indices[y];
                dNames[counter++] = names[index];
            }
        }

        String format = "-";
        for (int z = 0; z < dNames.length; z++) {
            if (z == dNames.length - 1) {
                format = format + dNames[z];
                break;
            }
            format = format + dNames[z] + "\n-";
        }
        return format;
    }
}
