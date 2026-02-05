public class Ramp implements Loadplatform {
    private boolean up = true;

    @Override
    public void raise(){
        up = true;
    }
    @Override
    public void lower(){
        up = false;
    }
    @Override
    public boolean isup(){
        return up;
    }

    @Override
    public boolean isdown(){
        return !up;
    }

}
