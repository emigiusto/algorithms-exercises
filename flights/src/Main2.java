import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Main2 {
    public static int dateToTime(String date){
        String[] h1 = date.split(":");
        int hour = Integer.parseInt(h1[0]);
        int minute = Integer.parseInt(h1[1]);
        int second = Integer.parseInt(h1[2]);
        return second + (60 * minute) + (3600 * hour);
    }
    public static String timeToDate(int timeInSeconds){
        Integer h = 0;
        Integer m = 0;
        Integer s = 0;
        if ((timeInSeconds / 3600) != 0) {
            h = timeInSeconds / 3600;
            timeInSeconds = timeInSeconds % 3600;
            if ((timeInSeconds / 60) != 0) {
                m = timeInSeconds / 60;
                timeInSeconds = timeInSeconds % 60;
            }
            s = timeInSeconds;
        }
        return ("" + Main.convertString(h) + ":" + Main.convertString(m) + ":" + Main.convertString(s));
    }

    public static String convertString(int time){
        String stringTime = Integer.toString(time);
        if (stringTime.length()==1) {
            return ("0" + stringTime);
        }
        return stringTime;
    }

    public static void main(String[] args) throws Exception {
            int flights = StdIn.readInt();
            int operations = StdIn.readInt();
            StdIn.readLine();

            RedBlackBST<Integer, String> schedule = new RedBlackBST<>();
            for (int i = 0; i < flights; ++i) {
                String timestamps = StdIn.readLine();
                String[] h1 = timestamps.split(" ");
                int timeInInteger = Main.dateToTime(h1[0]);
                String destination = h1[1];
                schedule.put(timeInInteger, destination);
            }

            for (int i = 0; i < operations; i++) {
                String line = StdIn.readLine();
                String[] operationLine = line.split(" ");
                String operation = operationLine[0];
                int firstTime = Main.dateToTime(operationLine[1]);

                if (operation.equals("next")) {
                    int next = schedule.ceiling(firstTime);
                    String destination = schedule.get(next);
                    
                    StdOut.println(timeToDate(next)+ " " + destination);

                } else if (operation.equals("destination")) {
                    if (!schedule.contains(firstTime)) {
                        StdOut.println("-");
                    } else {
                        StdOut.println(schedule.get(firstTime));
                    }
                } else if (operation.equals("cancel")) {
                    schedule.delete(firstTime);
                } else if (operation.equals("delay")) {
                    int addedSeconds = Integer.parseInt(line.split(" ")[2]);
                    String destination = schedule.get(firstTime);

                    int delayedTime = firstTime + addedSeconds;
                    schedule.delete(firstTime);
                    schedule.put(delayedTime, destination);
                } else if (operation.equals("reroute")) {
                    String newDestination = (line.split(" ")[2]);
                    schedule.delete(firstTime);
                    schedule.put(firstTime, newDestination);
                } else if (operation.equals("count")) {
                    int secondTime = Main.dateToTime(operationLine[2]);
                    StdOut.println(schedule.size(firstTime, secondTime));
                }
            }
    }
}