import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
public class Memory {
    int[][] registers = new int[100][2];
    int[] limits = new int[100];
    boolean[] status = new boolean[100];
    ArrayList<Process> ReadyQueue = new ArrayList<>();
    Process[] jobQueue = new Process[12];
    static int ids = 1;
    public static int MULTI_PROGRAMMING_DEGREE = 8;

    public void DeFrag() {
        System.out.println("DEFRAGGING... \n\n");
            ArrayList<Process> tempList = ReadyQueue;
            ReadyQueue = new ArrayList<>();
        for (int i = 0; i < registers.length; i++) {
            registers[i][0] = -1;
            registers[i][1] = -1;
            status[i] = false;
            limits[i] = -1;
        }
        registers[0][0] = 0;
        registers[0][1] = 768;
        limits[0] = 768;
        for (Process p: tempList)
            admitProcess(p);

        System.out.println("\n\n");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public Memory() throws InterruptedException {
        for (int i = 0; i < registers.length; i++) {
            registers[i][0] = -1;
            registers[i][1] = -1;
            status[i] = false;
            limits[i] = -1;
        }
        registers[0][0] = 0;
        registers[0][1] = 768;
        limits[0] = 768;
        CreateRandomProcesses();
        fillJobQueue();
        for (int i = 0; i < 15; i++) {
            if (NumberOfHoles()>=3)
                DeFrag();
            FinishProcesses();
            Thread.sleep(3000);
            loadProcesses();
            Print();
            System.out.println("\u001B[31m"+"ـــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــــ"+"\u001B[0m");
            Thread.sleep(7000);


        }
    }

    private int NumberOfHoles() {
        int count = 0;
        for (int i = 0; i < registers.length; i++) {
            if (registers[i][0]>=0 && !status[i])
                count++;
        }
        return count;
    }

    private void FinishProcesses() {
int min = Integer.MAX_VALUE;
        for (int i = 0; i < ReadyQueue.size(); i++) {
            if (ReadyQueue.get(i).getTime()<min)
                min = ReadyQueue.get(i).getTime();
        }

        for (int i = 0; i < ReadyQueue.size(); i++) {
            ReadyQueue.get(i).setTime(ReadyQueue.get(i).getTime() - min);
        }
        for (int i = 0; i < ReadyQueue.size(); i++) {

            if (ReadyQueue.get(i).getTime() == 0) {
                System.err.println("Processes #" + ReadyQueue.get(i).getId() + " Finished Execution.");

                status[ReadyQueue.get(i).getIndexInMemory()] = false;
                limits[ReadyQueue.get(i).getIndexInMemory()] = -1;
                ReadyQueue.remove(ReadyQueue.get(i));

            }
        }
        }


    private void loadProcesses() {
        for (int i = 0; i < jobQueue.length; i++) {
            if (admitProcess(jobQueue[i]))
            {
                System.err.println("Process #"+jobQueue[i].getId()+" Added to memoyry.");
                Process r = getRandomProcess();
                jobQueue[i] = r;
                System.err.println("Process #"+jobQueue[i].getId()+" Added to Job Queue.");
            }
        }
    }

    private void fillJobQueue() {
        for (int i = 0; i < 12; i++) {
            jobQueue[i] = getRandomProcess();
        }
    }

    private void CreateRandomProcesses() {
        while (admitProcess(getRandomProcess())) {
        }
    }

    private boolean admitProcess(Process randomProcess) {
        for (int i = 0; i < registers.length; i++) {
            if (!status[i] && limits[i] >= randomProcess.getSize()) {
                status[i] = true;
                registers[i+1][0] = randomProcess.getSize()+registers[i][0];
                registers[i+1][1] = registers[i][1];
                registers[i][1] = randomProcess.getSize()+registers[i][0];
                limits[i] = registers[i][1] - registers[i][0];
                status[i] = true;
                limits[i+1] = registers[i+1][1] - registers[i+1][0];
                randomProcess.setIndexInMemory(i);
                ReadyQueue.add(randomProcess);
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                return true;
            }
        }
        return false;
    }

    private Process getRandomProcess() {
        return new Process(ids++, (int) ((Math.random() * 450) + 50), (int) ((Math.random() * 80) + 20));
    }

    public void Print() {
        System.out.println("|*| Ready Queue Contents: |*|");
        for (Process p : ReadyQueue)
            System.out.println("| Process id: (" + p.getId() + ") | Process Size: (" + p.getSize() + ")" + " | Process Time: (" + p.getTime()+")");

        System.out.println("\n|*| Job Queue Contents: |*|");
        for (Process p : jobQueue)
            System.out.println("| Process id: (" + p.getId() + ") | Process Size: (" + p.getSize() + ")" + " | Process Time: (" + p.getTime()+")");

        System.out.println("\n|*| Memory Contents: |*|");
        for (int i = 0; i < ReadyQueue.size(); i++) {
            System.out.println("| Base: " + registers[ReadyQueue.get(i).getIndexInMemory()][0] + " | Upper Limit: " + registers[ReadyQueue.get(i).getIndexInMemory()][1] + " Process id: " + ReadyQueue.get(i).getId());
        }

        System.out.println("\n|*| Holes |*|");
        for (int i = 0; i< registers.length; i++) {
            if (registers[i][0]>=0 && !status[i])
            System.out.println("| Base: " + registers[i][0] + " | Upper Limit: " + registers[i][1]);
        }
    }
}