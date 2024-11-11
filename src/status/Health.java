package status;

public class Health {
    private int currentHealth;
    private int maxHealth;

    public Health(int currentHealth, int maxHealth) {
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }
    public int getMaxHealth(){
        return maxHealth;
    }
    public void setCurrentHealth(int c){
        currentHealth = c;
    }
    public void setMaxHealth(int m){
        maxHealth = m;
    }
}
