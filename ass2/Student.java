package ass2;

public class Student {
    private int grade;
    private long totalLearningTimeMinutes;

    public Student(int grade, long totalLearningTimeMinutes) {
        this.grade = grade;

        this.totalLearningTimeMinutes = totalLearningTimeMinutes;
    }

    public int getGrade() {
        return grade;
    }

    public long getTotalLearningTimeMinutes() {
        return totalLearningTimeMinutes;
    }

    public static void main(String[] args) {
        int grade = 5;
        long totalLearningTimeMinutes = 10000;
    
        Student person = new Student(grade, totalLearningTimeMinutes);
        System.out.println("The person is in grade " + person.getGrade() + ", and has a total learning time of " + person.getTotalLearningTimeMinutes() + " minutes.");
    }
    
}
