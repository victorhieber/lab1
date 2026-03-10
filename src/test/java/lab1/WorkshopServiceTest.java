package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WorkshopServiceTest {

    @Test
    void receivedCarIsParkedAndCannotDriveAway() {
        Workshop<Volvo240> workshop = new VolvoWorkshop(2);
        WorkshopService service = new WorkshopService(workshop, 10, 10, 5);
        Volvo240 volvo = new Volvo240(10, 10);

        service.tryReceive(volvo);

        assertTrue(service.isParked(volvo));
        assertEquals(1, workshop.size());

        double parkedX = volvo.getx();
        double parkedY = volvo.gety();

        volvo.startEngine();
        volvo.gas(1.0);
        volvo.move();

        assertEquals(0.0, volvo.getCurrentSpeed(), 0.0001);
        assertEquals(parkedX, volvo.getx(), 0.0001);
        assertEquals(parkedY, volvo.gety(), 0.0001);
    }
}
