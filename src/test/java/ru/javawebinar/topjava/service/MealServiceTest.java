package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;

import static ru.javawebinar.topjava.MealTestData.*;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        Meal meal = service.get(1, ADMIN_ID);
        MATCHER.assertEquals(ADMIN_MEAL1, meal);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.delete(3, ADMIN_ID);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(5, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL4, MEAL3), service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        service.delete(1, 1);
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL5, MEAL4),
                service.getBetweenDateTimes(LocalDateTime.of(2015, Month.MAY, 31, 14, 0),
                        LocalDateTime.of(2015, Month.MAY, 31, 18, 0), USER_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Meal> meals = service.getAll(USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL5, MEAL4, MEAL3), meals);

    }

    @Test
    public void update() throws Exception {
        Meal updatedMeal = new Meal(4, LocalDateTime.of(2017, Month.APRIL, 17, 23, 0), "updatedMeal", 777);
        service.update(updatedMeal, USER_ID);
        MATCHER.assertEquals(updatedMeal, service.get(4, USER_ID));
    }

    @Test
    public void testSave() throws Exception {
        Meal newMeal = new Meal(null, LocalDateTime.of(2017, Month.APRIL, 17, 23, 0), "createdMeal", 999);
        Meal createdMeal = service.save(newMeal, ADMIN_ID);
        newMeal.setId(createdMeal.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(newMeal, ADMIN_MEAL2, ADMIN_MEAL1), service.getAll(ADMIN_ID));
    }
}