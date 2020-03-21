package springdata.hibernate2mappings.entities.TablePerClass;

import javax.persistence.Entity;

@Entity
public class RegularEmployeeTable extends EmployeeTable {
    private String project;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
