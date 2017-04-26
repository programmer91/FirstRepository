/*
 * QuestionsVTO.java
 *
 * Created on July 23, 2013, 7:42 PM
 * Author:Prasad Kandregula-vkandregula@miraclesoft.com
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.mirage.ecertification;

/**
 *
 * @author miracle
 */
public class QuestionsVTO {
    
    /** Creates a new instance of QuestionsVTO */
    public QuestionsVTO() {
    }
    private int id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int answer;
    private String description;
    private int userAnswer;
    private int subtopicId;
    private String subTopicName;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }

    public int getSubtopicId() {
        return subtopicId;
    }

    public void setSubtopicId(int subtopicId) {
        this.subtopicId = subtopicId;
    }

    /**
     * @return the subTopicName
     */
    public String getSubTopicName() {
        return subTopicName;
    }

    /**
     * @param subTopicName the subTopicName to set
     */
    public void setSubTopicName(String subTopicName) {
        this.subTopicName = subTopicName;
    }
    
}
