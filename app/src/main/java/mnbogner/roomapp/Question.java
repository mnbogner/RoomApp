package mnbogner.roomapp;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "question")
public class Question {
    
    @PrimaryKey(autoGenerate = true)
    private long id;
    private long storyId;
    private String questionText;
    private String mediaFilePath; // video? audio?
    private String altFilePath;   // thumbnail? image for audio?
    private int mediaStart;
    private int mediaStop;        // for future use

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStoryId() {
        return storyId;
    }

    public void setStoryId(long storyId) {
        this.storyId = storyId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getMediaFilePath() {
        return mediaFilePath;
    }

    public void setMediaFilePath(String mediaFilePath) {
        this.mediaFilePath = mediaFilePath;
    }

    public String getAltFilePath() {
        return altFilePath;
    }

    public void setAltFilePath(String altFilePath) {
        this.altFilePath = altFilePath;
    }

    public int getMediaStart() {
        return mediaStart;
    }

    public void setMediaStart(int mediaStart) {
        this.mediaStart = mediaStart;
    }

    public int getMediaStop() {
        return mediaStop;
    }

    public void setMediaStop(int mediaStop) {
        this.mediaStop = mediaStop;
    }
}
