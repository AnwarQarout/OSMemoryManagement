public class Job {
    private int time;
    private int baseRegister;
    private int limitRegister;
    private int size;

    public Job(){

    }

    public Job(int time){
        this.time = time;
    }


    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getBaseRegister() {
        return baseRegister;
    }

    public void setBaseRegister(int baseRegister) {
        this.baseRegister = baseRegister;
    }

    public int getLimitRegister() {
        return limitRegister;
    }

    public void setLimitRegister(int limitRegister) {
        this.limitRegister = limitRegister;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
