public class Process {
    private int id;
    private int size;
    private int time;
    private String status;

    public int getIndexInMemory() {
        return indexInMemory;
    }

    public void setIndexInMemory(int indexInMemory) {
        this.indexInMemory = indexInMemory;
    }

    private int indexInMemory;

    public Process(int id, int size, int time) {
        this.id = id;
        this.size = size;
        this.time = time;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
