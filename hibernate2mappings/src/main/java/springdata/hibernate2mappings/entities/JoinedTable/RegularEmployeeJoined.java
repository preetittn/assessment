package springdata.hibernate2mappings.entities.JoinedTable;

import javax.persistence.Entity;

@Entity
public class RegularEmployeeJoined extends EmployeeJoined {
    private String project;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
