/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.ecertification;

/**
 *
 * @author miracle
 */
public class TopicsVTO {
    private int authorId;
    private int topicId;
    private String authorLoginId;
    private String createdBy;
    private String createdDate;
    private String authorStatus;
    
   
    private int subTopicId;
    private String subtopic;
    private String subTopicStatus;

    /**
     * @return the authorId
     */
    public int getAuthorId() {
        return authorId;
    }

    /**
     * @param authorId the authorId to set
     */
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    /**
     * @return the authorLoginId
     */
    public String getAuthorLoginId() {
        return authorLoginId;
    }

    /**
     * @param authorLoginId the authorLoginId to set
     */
    public void setAuthorLoginId(String authorLoginId) {
        this.authorLoginId = authorLoginId;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the createdDate
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the authorStatus
     */
    public String getAuthorStatus() {
        return authorStatus;
    }

    /**
     * @param authorStatus the authorStatus to set
     */
    public void setAuthorStatus(String authorStatus) {
        this.authorStatus = authorStatus;
    }

    /**
     * @return the subTopicId
     */
    public int getSubTopicId() {
        return subTopicId;
    }

    /**
     * @param subTopicId the subTopicId to set
     */
    public void setSubTopicId(int subTopicId) {
        this.subTopicId = subTopicId;
    }

  

    /**
     * @return the subTopicStatus
     */
    public String getSubTopicStatus() {
        return subTopicStatus;
    }

    /**
     * @param subTopicStatus the subTopicStatus to set
     */
    public void setSubTopicStatus(String subTopicStatus) {
        this.subTopicStatus = subTopicStatus;
    }

    /**
     * @return the subtopic
     */
    public String getSubtopic() {
        return subtopic;
    }

    /**
     * @param subtopic the subtopic to set
     */
    public void setSubtopic(String subtopic) {
        this.subtopic = subtopic;
    }

    /**
     * @return the topicId
     */
    public int getTopicId() {
        return topicId;
    }

    /**
     * @param topicId the topicId to set
     */
    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }
    
}
