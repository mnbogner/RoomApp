package mnbogner.roomapp;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "story")
public class Story {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title;
    private String interviewers;
    private String interviewees;
    private String description;
    private String mergedFilePath;
    private long createdDate;
    private long modifiedDate;
    private long uploadDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInterviewers() {
        return interviewers;
    }

    public void setInterviewers(String interviewers) {
        this.interviewers = interviewers;
    }

    public String getInterviewees() {
        return interviewees;
    }

    public void setInterviewees(String interviewees) {
        this.interviewees = interviewees;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMergedFilePath() {
        return mergedFilePath;
    }

    public void setMergedFilePath(String mergedFilePath) {
        this.mergedFilePath = mergedFilePath;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public long getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(long modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public long getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(long uploadDate) {
        this.uploadDate = uploadDate;
    }
}
