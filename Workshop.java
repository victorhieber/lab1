public class Workshop<T extends Vehicle> {
    private final CarrierCargo<T> storage; 

    public Workshop(int maxCars) {
        this.storage = new CarrierCargo<>(maxCars);
    }

    public boolean recieveCar(T car){
        return storage.addcar(car);
    }

    public T pickup() {                         // Hamta ut en bil med sa precis typ som mojligt.
        return storage.unload();            // Returnerar T (t.ex. Volvo240 i en Volvo-verkstad).
    }
    public int size(){
        return storage.size();
    }

}
