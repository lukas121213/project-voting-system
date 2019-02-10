package pl.lukas121213.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProjectVoterId implements Serializable {

    private Long projectId;
    private Long voterId;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getVoterId() {
        return voterId;
    }

    public void setVoterId(Long voterId) {
        this.voterId = voterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectVoterId that = (ProjectVoterId) o;

        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
        return voterId != null ? voterId.equals(that.voterId) : that.voterId == null;
    }

    @Override
    public int hashCode() {
        int result = projectId != null ? projectId.hashCode() : 0;
        result = 31 * result + (voterId != null ? voterId.hashCode() : 0);
        return result;
    }
}