import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScaniaTest {

    @Test
    void scaniaCantGasWhenBedIsUp() {
        Scania scania = new Scania();
        scania.stopEngine();     // se till att den är helt stilla

        scania.raiseRamp(10);
        assertEquals(10, scania.getRampAngle(), 0.0001);

        scania.startEngine();    //i car blir speed = 0.1 när man startar :
        double before = scania.getCurrentSpeed();

        scania.gas(1.0);         // ska blockeras
        assertEquals(before, scania.getCurrentSpeed(), 0.0001);
    }
}