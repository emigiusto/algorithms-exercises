public class Edge{
    private int pointer;
    private boolean conflict;

    public Edge(int pointer, boolean conflict){
        this.pointer = pointer;
        this.conflict = conflict;
    }

    public int getPointer() {
        return pointer;
    }

    public boolean getConflict() {
        return conflict;
    }
}
