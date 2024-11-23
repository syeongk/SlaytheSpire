package characterStatus;

public class Energy {
    private int currentEnergy;
    private int maxEnergy;

    public Energy(int e){
        this.currentEnergy = e;
        this.maxEnergy = e;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }


}
