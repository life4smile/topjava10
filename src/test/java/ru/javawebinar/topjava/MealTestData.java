package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;

public class MealTestData {

    public static final int USER_ID = 100000;
    public static final int ADMIN_ID = 100001;


    public static final Meal ADMIN_MEAL1 = new Meal(1, LocalDateTime.of(2015, Month.MAY, 30, 14, 0), "Админ ланч", 510);
    public static final Meal ADMIN_MEAL2 = new Meal(2, LocalDateTime.of(2015, Month.MAY, 30, 21, 0), "Админ ужин", 510);
    public static final Meal MEAL3 = new Meal(3, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 500);
    public static final Meal MEAL4 = new Meal(4, LocalDateTime.of(2015, Month.MAY, 31, 14, 0), "Обед", 1000);
    public static final Meal MEAL5 = new Meal(5, LocalDateTime.of(2015, Month.MAY, 31, 18, 0), "Ужин", 500);


    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected.toString().equals(actual.toString())
    );

}
