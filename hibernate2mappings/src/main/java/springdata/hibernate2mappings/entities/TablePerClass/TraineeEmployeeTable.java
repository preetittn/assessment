package springdata.hibernate2mappings.entities.TablePerClass;

import javax.persistence.Entity;

@Entity
public class TraineeEmployeeTable extends EmployeeTable{
    private String assessmentScore;

    public String getAssessmentScore() {
        return assessmentScore;
    }

    public void setAssessmentScore(String assessmentScore) {
        this.assessmentScore = assessmentScore;
    }
}
