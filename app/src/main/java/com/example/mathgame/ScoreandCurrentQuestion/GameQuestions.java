package com.example.mathgame.ScoreandCurrentQuestion;

import com.example.mathgame.Questions.Question;

import java.util.ArrayList;
import java.util.List;

public class GameQuestions {

    //List of questions
    private List<Question> questions;
    //correct answer
    private int correctNumber;
    //Incorrect answer
    private int incorrectNumber;
    //Total number of questions
    private int totalQuestions;
    //Score
    private int score;
    //To keep the track of current question
    private Question currentQuestion;


    //Generating the constructor
    public GameQuestions () {
        //Setting all the properties to a default value.
        correctNumber = 0;
        incorrectNumber = 0;
        totalQuestions = 0;
        score = 0;
        currentQuestion = new Question(10); //Look on this
        questions = new ArrayList<Question>();

    }


    //Creating a new function to generate a new question and assign it to the current question
    public void newQuestion () {
        currentQuestion = new Question(totalQuestions * 2 + 5);
        totalQuestions++;
        questions.add(currentQuestion);
    }

    /**
     * Maintaining the score
     * @param userAnswer useranswer
     * @return isCorrect
     */
    public boolean checkAnswer (int userAnswer) {
        boolean isCorrect;
        if (currentQuestion.getAnswer() == userAnswer) {
            correctNumber++;
            isCorrect = true;
        } else {
            incorrectNumber++;
            isCorrect = false;
        }

        score = correctNumber * 10 - incorrectNumber * 30;
        return isCorrect;
    }


    //Generating getters and setters
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getCorrectNumber() {
        return correctNumber;
    }

    public void setCorrectNumber(int correctNumber) {
        this.correctNumber = correctNumber;
    }

    public int getIncorrectNumber() {
        return incorrectNumber;
    }

    public void setIncorrectNumber(int incorrectNumber) {
        this.incorrectNumber = incorrectNumber;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

}
