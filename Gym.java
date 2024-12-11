public class Gym {
    private static Gym instance;

    private Gym() {
    }

    public Gym getInstance(){
        Gym temp = instance;
        synchronized (Gym.class);
        if (instance==null)
    }
}
