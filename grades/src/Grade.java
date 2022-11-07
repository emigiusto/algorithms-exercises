

public class Grade implements Comparable<Grade>{
    private String name;
    private String grade;
    private boolean special = false;
    private String modifier;
    private int modifierCount;
    private boolean modifierSign;

    public Grade(String name,String grade){
        this.name = name;
        this.grade = grade.substring(0, 1);
        if (grade.length()>1) {
            this.special = grade.substring(1, 2).equals("X") ? true : false;
            if (grade.length()>2) {
                    this.modifier = this.special ? grade.substring(2, grade.length()) :  grade.substring(1, grade.length());
                    this.modifierSign = this.modifier.substring(0,1).equals("+") ? true : false;
            } else {
                this.modifier  = this.special ? "" : grade.substring(1, grade.length());
                this.modifierSign = this.modifier.equals("+") ? true : false;
            }
            this.modifierCount = this.modifier.length();
        }
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(Grade g) {
        int gradeComp = checkGrade(g);
        if (gradeComp == 0) {
            return g.getName().compareTo(this.getName()); 
        } else {
            return gradeComp;
        }
    }

    public int checkGrade(Grade g) {
        if (this.grade.equals("F") && g.grade.equals("F")) {
            if (this.special && !g.special) {
                return 1;
            }
            if (!this.special && g.special) {
                return -1;
            }
        }
        if (Character.valueOf(this.grade.charAt(0))>Character.valueOf(g.grade.charAt(0))) {
            return -1;
        }
        if (Character.valueOf(this.grade.charAt(0))<Character.valueOf(g.grade.charAt(0))) {
            return 1;
        }
        if (this.modifierSign && !g.modifierSign) {
            return 1;
        }
        if (!this.modifierSign && g.modifierSign) {
            return -1;
        }
        if (this.modifierCount > g.modifierCount) {
            if (this.modifierSign) {
                return 1;
            } else {
                return -1;
            }
        }
        if (this.modifierCount < g.modifierCount) {
            if (this.modifierSign) {
                return -1;
            } else {
                return 1;
            }
        }
        return 0;
    }
}