import java.util.*;
import java.util.concurrent.TimeUnit;

class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public boolean checkAnswer(int answerIndex) {
        return answerIndex == correctAnswerIndex;
    }
}

class QuizApp {
    private List<Question> questions;
    private int score;
    private long startTime;

    public QuizApp() {
        questions = new ArrayList<>();
        score = 0;
        startTime = System.currentTimeMillis();

        // Sample Questions
        questions.add(new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1));
        questions.add(new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 2));
        questions.add(new Question("Which language is used for Android development?", new String[]{"Java", "Python", "C++", "Ruby"}, 0));
    }

    public void startQuiz() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + q.getQuestionText());
            String[] options = q.getOptions();
            for (int j = 0; j < options.length; j++) {
                System.out.println((j + 1) + ". " + options[j]);
            }

            int answer = sc.nextInt() - 1; // User's answer choice (1-4)
            if (q.checkAnswer(answer)) {
                score++;
                System.out.println("✅ Correct!");
            } else {
                System.out.println("❌ Incorrect!");
            }
        }
        long endTime = System.currentTimeMillis();
        long duration = TimeUnit.MILLISECONDS.toSeconds(endTime - startTime);

        System.out.println("\n⏳ Time Taken: " + duration + " seconds");
        System.out.println("🎯 Your score: " + score + "/" + questions.size());
    }
}

public class QuizAppMain11 {
    public static void main(String[] args) {
        QuizApp quiz = new QuizApp();
        quiz.startQuiz();
    }
}

