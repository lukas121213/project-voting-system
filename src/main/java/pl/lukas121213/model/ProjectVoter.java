package pl.lukas121213.model;

import javax.persistence.*;

@Entity
@Table(name = "project_voter")
@IdClass(ProjectVoterId.class)
public class ProjectVoter {

    @Id
    private long projectId;
    @Id
    private long voterId;

    @Column(name="voting_type")
    private VotingType votingType;

    @ManyToOne
    private Project project;

    @ManyToOne
    private Voter voter;

    public ProjectVoter() {
    }

    public ProjectVoter(Project project, Voter voter, VotingType votingType) {
        this.votingType = votingType;
        this.project = project;
        this.projectId = project.getId();
        this.voter = voter;
        this.voterId = voter.getId();
    }

    public VotingType getVotingType() {
        return votingType;
    }

    public Project getProject() {
        return project;
    }

    public Voter getVoter() {
        return voter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectVoter that = (ProjectVoter) o;

        if (projectId != that.projectId) return false;
        if (voterId != that.voterId) return false;
        return votingType == that.votingType;
    }

    @Override
    public int hashCode() {
        int result = (int) (projectId ^ (projectId >>> 32));
        result = 31 * result + (int) (voterId ^ (voterId >>> 32));
        result = 31 * result + (votingType != null ? votingType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProjectVoter{" +
                "projectId=" + projectId +
                ", voterId=" + voterId +
                ", votingType=" + votingType +
                '}';
    }

    public static class Builder {
        private Voter voter;
        private Project project;
        private VotingType votingType;

        public Builder setVoter(Voter voter) {
            this.voter = voter;
            return this;
        }

        public Builder setProject(Project project) {
            this.project = project;
            return this;
        }

        public Builder setVotingType(VotingType votingType) {
            this.votingType = votingType;
            return this;
        }

        public ProjectVoter build() {
            return new ProjectVoter(project, voter, votingType);
        }
    }
}
