import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class carTest {

    @Test
    void gasShouldOnlyAcceptValuesBetweenZeroAndOne() {
        // Vi använder Volvo för att testa car-logiken
        Volvo240 volvo = new Volvo240();
        volvo.startEngine(); // Fart: 0.1

        // Testa för högt värde
        volvo.gas(1.1);
        assertEquals(0.1, volvo.getCurrentSpeed(), "Farten ska inte ändras om värdet är > 1");

        // Testa för lågt värde
        volvo.gas(-0.1);
        assertEquals(0.1, volvo.getCurrentSpeed(), "Farten ska inte ändras om värdet är < 0");
    }

    @Test
    void brakeShouldOnlyAcceptValuesBetweenZeroAndOne() {
        Volvo240 volvo = new Volvo240();
        volvo.startEngine();
        volvo.gas(0.5); // Ge den lite fart
        double speedAfterGas = volvo.getCurrentSpeed();

        // Testa ogiltiga värden för brake
        volvo.brake(1.1);
        assertEquals(speedAfterGas, volvo.getCurrentSpeed(), "Farten ska inte ändras vid ogiltig bromsning");

        volvo.brake(-0.1);
        assertEquals(speedAfterGas, volvo.getCurrentSpeed(), "Farten ska inte ändras vid ogiltig bromsning");
    }

    @Test
    void incrementSpeedShouldNeverExceedEnginePower() {
        Volvo240 volvo = new Volvo240();
        // Gasa jättemycket
        for(int i = 0; i < 200; i++) {
            volvo.gas(1.0);
        }

        assertTrue(volvo.getCurrentSpeed() <= volvo.getEnginePower(),
                "Farten får aldrig bli högre än motorns styrka");
    }

    @Test
    void decrementSpeedShouldNeverGoBelowZero() {
        Volvo240 volvo = new Volvo240();
        volvo.startEngine();

        // Bromsa jättemycket
        for(int i = 0; i < 10; i++) {
            volvo.brake(1.0);
        }

        assertTrue(volvo.getCurrentSpeed() >= 0,
                "Farten får aldrig bli negativ");
    }
}