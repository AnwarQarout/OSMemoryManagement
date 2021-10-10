import java.util.ArrayList;

public class JobQueue {
    private ArrayList<Job> jobArrayList = new ArrayList<>();

    public JobQueue(){

    }


    public ArrayList<Job> getJobArrayList() {
        return jobArrayList;
    }

    public void setJobArrayList(ArrayList<Job> jobArrayList) {
        this.jobArrayList = jobArrayList;
    }

    public void insertJob(Job job){
        jobArrayList.add(job);
    }

    public void removeJob(Job job){
        jobArrayList.remove(job);
    }

    public boolean isFull(){
        return jobArrayList.size() <= 12? true:false;
    }

    public Job popBestJob(int size){
        int maxIndex = -1, maxSize = -1;
        for(Job job : jobArrayList){
            if(job.getSize() <= size && job.getSize() > maxSize ){
                maxIndex = jobArrayList.indexOf(job);
            }
        }
        return jobArrayList.get(maxIndex);
    }
}
