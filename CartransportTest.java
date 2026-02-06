import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CartransportTest {

    @Test
    void carCanOnlyBeLoadedWhenRampIsDownAndCarIsNear() {
        Cartransport transport = new Cartransport();
        Volvo240 car = new Volvo240();
        car.setPosition(0, 0);

        boolean loadWithRampUp = transport.LoadCar(car);
        assertFalse(loadWithRampUp);

        transport.lowerramp();
        boolean loadWithRampDown = transport.LoadCar(car);
        assertTrue(loadWithRampDown);
    }

    @Test
    void cannotLoadAnotherCartransport() {
        Cartransport transport = new Cartransport();
        Cartransport otherTransport = new Cartransport();
        otherTransport.setPosition(0, 0);

        transport.lowerramp();
        boolean loaded = transport.LoadCar(otherTransport);
        assertFalse(loaded);
    }

    @Test
    void unloadIsLifo() {
        Cartransport transport = new Cartransport();
        Volvo240 first = new Volvo240();
        Saab95 second = new Saab95();

        first.setPosition(0, 0);
        second.setPosition(0, 0);

        transport.lowerramp();
        assertTrue(transport.LoadCar(first));
        assertTrue(transport.LoadCar(second));

        Vehicle unloaded = transport.unloadcar();
        assertSame(second, unloaded);
    }

    @Test
    void loadedCarsFollowTransportPositionWhenMoving() {
        Cartransport transport = new Cartransport();
        Volvo240 car = new Volvo240();
        car.setPosition(0, 0);

        transport.lowerramp();
        assertTrue(transport.LoadCar(car));
        transport.raiseramp();

        transport.startEngine();
        transport.gas(1.0);
        transport.move();

        assertEquals(transport.getx(), car.getx(), 0.0001);
        assertEquals(transport.gety(), car.gety(), 0.0001);
    }


}
