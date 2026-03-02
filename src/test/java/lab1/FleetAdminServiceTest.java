package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FleetAdminServiceTest {

    @Test
    void addRandomCarAddsUntilCapacityThenStops() {
        InMemoryVehicleStore store = new InMemoryVehicleStore(2);
        FleetAdminService service = new FleetAdminService(store, new DefaultVehicleFactory());

        assertTrue(service.addRandomCar());
        assertTrue(service.addRandomCar());
        assertEquals(2, store.size());

        assertFalse(service.addRandomCar());
        assertEquals(2, store.size());
    }

    @Test
    void removeCarFromEmptyStoreReturnsNull() {
        InMemoryVehicleStore store = new InMemoryVehicleStore(2);
        FleetAdminService service = new FleetAdminService(store, new DefaultVehicleFactory());

        assertNull(service.removeCar());
    }

    @Test
    void removeCarUsesLifoOrder() {
        InMemoryVehicleStore store = new InMemoryVehicleStore(5);
        FleetAdminService service = new FleetAdminService(store, new DefaultVehicleFactory());

        Vehicle first = new Volvo240();
        Vehicle second = new Saab95();

        assertTrue(service.addGivenCar(first));
        assertTrue(service.addGivenCar(second));

        Vehicle removed = service.removeCar();
        assertSame(second, removed);
        assertEquals(1, store.size());
    }
}

