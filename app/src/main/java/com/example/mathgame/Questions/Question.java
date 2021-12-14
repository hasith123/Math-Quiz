package com.example.mathgame.Questions;

import java.util.Random;



public class Question {

    //Variables
    private int numberOne;
    private int numberTwo;
    private int answer;

    //User has four choices to select the answer
    private int [] arrayAnswer;

    //This is the answer set. Like 0,1,2,&3.
    private int answerSet;

    //This is the difficulty of the questions
    private int upperLimit;

    //To display the arithmetic question
    private String displayQuestion;

    /**
     * Generating a constructor to get a random question
     * @param upperLimit upperlimit
     */
    public Question(int upperLimit) {
        this.upperLimit = upperLimit;
        Random randomQuestion = new Random();

        //Generating the question
        this.numberOne = randomQuestion.nextInt(upperLimit);
        this.numberTwo = randomQuestion.nextInt(upperLimit);
        this.answer = this.numberOne + this.numberTwo;

        //Displaying the question
        this.displayQuestion = numberOne + " + " + numberTwo + " = ";

        //Creating the answer set
        this.answerSet = randomQuestion.nextInt(4);  //Randomly choosing one of the 4 answers

        //Initializing the answer array
        this.arrayAnswer = new int[] {0,1,2,3};  //Recalculating the answers

        //Generating answers for the questions according to the selection of the user. (Ex:5,10,-8,etc)
        this.arrayAnswer [0] = answer + 9;
        this.arrayAnswer [1] = answer + 14;
        this.arrayAnswer [2] = answer - 6;
        this.arrayAnswer [3] = answer -1;

        //Implementing a method to shuffle answers (Constructor)
        this.arrayAnswer = shuffleArray (this.arrayAnswer);

        //Placing the correct answer of one of the 4 number sets
        arrayAnswer[answerSet] = answer;

    }

    /**
     * To shuffle the answers
     * @param array array
     * @return array
     */
    private int[] shuffleArray(int[] array) {
        //Have to clarify
        int index, temp;
        Random randomQuestionMaker = new Random();

        //For loop to count array list downwards
        for (int i = array.length - 1; i > 0; i--) {
            index = randomQuestionMaker.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        return array;
    }



    //Getters and Setters
    public int getNumberOne() {
        return numberOne;
    }

    public void setNumberOne(int numberOne) {
        this.numberOne = numberOne;
    }

    public int getNumberTwo() {
        return numberTwo;
    }

    public void setNumberTwo(int numberTwo) {
        this.numberTwo = numberTwo;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int[] getArrayAnswer() {
        return arrayAnswer;
    }

    public void setArrayAnswer(int[] arrayAnswer) {
        this.arrayAnswer = arrayAnswer;
    }

    public int getAnswerSet() {
        return answerSet;
    }

    public void setAnswerSet(int answerSet) {
        this.answerSet = answerSet;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    public String getDisplayQuestion() {
        return displayQuestion;
    }

    public void setDisplayQuestion(String displayQuestion) {
        this.displayQuestion = displayQuestion;
    }
}
