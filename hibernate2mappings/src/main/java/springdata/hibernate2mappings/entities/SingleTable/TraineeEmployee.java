package springdata.hibernate2mappings.entities.SingleTable;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("trainee_employee")
public class TraineeEmployee extends EmployeeSingle {
    private String assessmentScore;

    public String getAssessmentScore() {
        return assessmentScore;
    }

    public void setAssessmentScore(String assessmentScore) {
        this.assessmentScore = assessmentScore;
    }
}
