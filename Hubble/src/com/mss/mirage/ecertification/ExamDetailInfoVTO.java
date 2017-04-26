/*
 * ExamDetailInfoVTO.java
 *
 * Created on August 9, 2013, 6:30 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.ecertification;

/**
 *
 * @author miracle
 */
public class ExamDetailInfoVTO {
    
    /** Creates a new instance of ExamDetailInfoVTO */
    public ExamDetailInfoVTO() {
    }
    
    private String subtopicName;
    private int subtopictotalQuestions;
    private int attemptedQuestion;
    private int subtopicMarks;

    public String getSubtopicName() {
        return subtopicName;
    }

    public void setSubtopicName(String subtopicName) {
        this.subtopicName = subtopicName;
    }

    public int getSubtopictotalQuestions() {
        return subtopictotalQuestions;
    }

    public void setSubtopictotalQuestions(int subtopictotalQuestions) {
        this.subtopictotalQuestions = subtopictotalQuestions;
    }

    public int getAttemptedQuestion() {
        return attemptedQuestion;
    }

    public void setAttemptedQuestion(int attemptedQuestion) {
        this.attemptedQuestion = attemptedQuestion;
    }

    public int getSubtopicMarks() {
        return subtopicMarks;
    }

    public void setSubtopicMarks(int subtopicMarks) {
        this.subtopicMarks = subtopicMarks;
    }
    
    
}
