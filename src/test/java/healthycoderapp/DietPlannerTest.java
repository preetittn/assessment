package healthycoderapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DietPlannerTest {

    private DietPlanner dietPlanner;

    @BeforeEach
    void setup() {
        this.dietPlanner = new DietPlanner(20,30,50);
    }

    @AfterEach
    void afterEach() {
        System.out.println("A unit test was finished");
    }

    @Test
    void shouldReturnCorrectDietPlanWhenCorrectCoder() {
        // given
        double height = 1.89;
        double weight = 100.45;
        int expectedCalories = 2374;
        int expectedProtein = 119;
        int expectedFat = 79;
        int expectedCarbohydrate = 297;

        // when
        Coder coder = new Coder(height,weight);
        DietPlan dietPlan = dietPlanner.calculateDiet(coder);
        System.out.println(dietPlan);

        // then
        assertAll(
                () ->assertEquals(dietPlan.getCalories(),expectedCalories),
                () -> assertEquals(dietPlan.getCarbohydrate(),expectedCarbohydrate),
                () -> assertEquals(dietPlan.getFat(),expectedFat),
                () -> assertEquals(dietPlan.getCarbohydrate(),expectedCarbohydrate)
        );
    }

    @Test
    void shouldReturnCorrectBMRWhenCorrectCoder() {
        // given
        double height = 1.89;
        double weight = 100.45;
        int expectedBMR = 2374;

        // when
        Coder coder = new Coder(height,weight);
        int returnedBMR = dietPlanner.calculateBMR(coder);

        // then
        assertEquals(expectedBMR,returnedBMR);
    }

}