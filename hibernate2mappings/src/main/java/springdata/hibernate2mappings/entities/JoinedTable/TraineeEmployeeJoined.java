package springdata.hibernate2mappings.entities.JoinedTable;

import javax.persistence.Entity;

@Entity
public class TraineeEmployeeJoined extends EmployeeJoined {
    private String assessmentScore;

    public String getAssessmentScore() {
        return assessmentScore;
    }

    public void setAssessmentScore(String assessmentScore) {
        this.assessmentScore = assessmentScore;
    }
}