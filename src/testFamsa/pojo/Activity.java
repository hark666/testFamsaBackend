package testFamsa.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Entity
@Table(name = "Activity", uniqueConstraints={@UniqueConstraint(columnNames={"id"})})
public class Activity {
	@Id
    @Column(name = "id", length=11, nullable=false, unique=true)
    private Integer activityId;
	
	@Column(name = "description", length=20, nullable=false)
    private String activityDescription;
	
	public Activity() { }
	
	public Activity(Integer activityId, String activityDescription) {
        this.activityId = activityId;
        this.activityDescription = activityDescription;
    }
	
	public Integer getActivityId() {
        return activityId;
    }
	
	public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
 
    public String getActivityDescription() {
        return activityDescription;
    }
 
    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    @Override
    public String toString() {
    	Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(this);
    }
}
