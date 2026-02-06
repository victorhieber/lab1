import org.junit.jupiter.api.Test;              // JUnit-annotation for testmetoder.
import static org.junit.jupiter.api.Assertions.*; // Assertioner som assertTrue, assertSame.

class WorkshopTest { // Testklass for verkstadsgenerics.

    @Test // Markerar att metoden ar ett testfall.
    void volvoWorkshopReturnsExactType() {
        VolvoWorkshop ws = new VolvoWorkshop(2); // Verkstad som bara tar Volvo240.
        Volvo240 v = new Volvo240();             // Skapa en Volvo.

        assertTrue(ws.recieveCar(v));                 // Ska ga att lagga in Volvo i Volvo-verkstad.

        Volvo240 out = ws.pickup();              // Compile-time-typ ar Volvo240 (ingen cast behovs).
        assertSame(v, out);                      // Kontrollera att vi fick tillbaka samma objekt.
    }

    @Test // Andra testfallet.
    void genericWorkshopCanHandleDifferentVehicleTypes() {
        Workshop<Vehicle> ws = new Workshop<>(3); // Generell verkstad for alla Vehicle.

        assertTrue(ws.recieveCar(new Volvo240()));      // Volvo accepteras.
        assertTrue(ws.recieveCar(new Saab95()));        // Saab accepteras.
        assertEquals(2, ws.size());                // Två bilar inne i verkstaden.
    }

    // Exempel pa compile-time-regel (ska INTE avkommenteras, den kompilerar inte):
    // @Test
    // void wrongTypeShouldFailAtCompileTime() {
    //     VolvoWorkshop ws = new VolvoWorkshop(2);
    //     ws.admit(new Saab95()); // Kompileringsfel: Saab95 ar inte Volvo240.
    // }
}
