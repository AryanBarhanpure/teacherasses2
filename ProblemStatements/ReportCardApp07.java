import java.util.*;

class Subject {
    private String name;
    private int marks;

    public Subject(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    public int getMarks() {
        return marks;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        if (marks >= 90) return "A+";
        else if (marks >= 80) return "A";
        else if (marks >= 70) return "B";
        else if (marks >= 60) return "C";
        else if (marks >= 50) return "D";
        else return "F";
    }

    public boolean isPass() {
        return marks >= 40;
    }
}

class Student {
    private String name;
    private String rollNo;
    private List<Subject> subjects;

    public Student(String name, String rollNo) {
        this.name = name;
        this.rollNo = rollNo;
        this.subjects = new ArrayList<>();
    }

    public void addSubject(String subjectName, int marks) {
        subjects.add(new Subject(subjectName, marks));
    }

    public double getAverage() {
        int total = 0;
        for (Subject s : subjects) {
            total += s.getMarks();
        }
        return (double) total / subjects.size();
    }

    public boolean hasPassed() {
        for (Subject s : subjects) {
            if (!s.isPass()) return false;
        }
        return true;
    }

    public void printReportCard() {
        System.out.println("\n📘 Report Card");
        System.out.println("Name    : " + name);
        System.out.println("Roll No : " + rollNo);
        System.out.println("-------------------------------");
        System.out.printf("%-15s %-10s %-10s\n", "Subject", "Marks", "Grade");
        for (Subject s : subjects) {
            System.out.printf("%-15s %-10d %-10s\n", s.getName(), s.getMarks(), s.getGrade());
        }
        System.out.println("-------------------------------");
        System.out.printf("Average Marks: %.2f\n", getAverage());
        System.out.println("Result       : " + (hasPassed() ? "PASS ✅" : "FAIL ❌"));
    }
}

public class ReportCardApp07 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Roll Number: ");
        String roll = sc.nextLine();

        Student student = new Student(name, roll);

        System.out.print("Enter number of subjects: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter subject " + i + " name: ");
            String subjectName = sc.nextLine();
            System.out.print("Enter marks in " + subjectName + ": ");
            int marks = sc.nextInt();
            sc.nextLine(); // consume newline
            student.addSubject(subjectName, marks);
        }

        student.printReportCard();
    }
}

