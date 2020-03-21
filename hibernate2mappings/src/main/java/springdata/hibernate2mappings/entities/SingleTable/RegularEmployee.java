package springdata.hibernate2mappings.entities.SingleTable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("regular_employee")
public class RegularEmployee extends EmployeeSingle {
    private String project;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
