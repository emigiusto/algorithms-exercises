public class Party implements Comparable<Party>{
    private double votes;
    private double seats = 0.00;
    private int id;
    private double dont;

    public Party(double votes, int id){
        this.votes = votes;
        this.id = id;
        this.dont = votes;
    }

    public void addSeat() {
        seats++;
        dont = votes/(seats+1.00);
    }

    public double getDont(){
        return dont;
    }

    public int getSeats() {
        return (int) this.seats;
    }
    public int getId() {
        return this.id;
    }

    @Override
    public int compareTo(Party g) {
        var dont1 = getDont();
        var dont2 = g.getDont();
        if (dont1>dont2) return 1;
        if (dont1<dont2) return -1;
        return 0;
    }

}