package pl.lukas121213.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@ApiModel(description = "Model for project.")
@Entity
@Table(name = "projects", uniqueConstraints = { @UniqueConstraint(columnNames = {Project.NAME_COLUMN})})
public class Project {

    public static final String NAME_COLUMN = "name";

    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @ApiModelProperty(notes = "Project name")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(notes = "Project description")
    @Column(name = "description")
    private String description;

    @ApiModelProperty(notes = "Is project open for voting")
    @JsonProperty("voting_open")
    @Column(name = "voting_open")
    private boolean votingOpen;

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProjectVoter> voters = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVotingOpen() {
        return votingOpen;
    }

    public void setVotingOpen(boolean votingOpen) {
        this.votingOpen = votingOpen;
    }

    public Set<ProjectVoter> getVoters() {
        return voters;
    }

    public void setVoters(Set<ProjectVoter> voters) {
        this.voters = voters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (votingOpen != project.votingOpen) return false;
        if (name != null ? !name.equals(project.name) : project.name != null) return false;
        return description != null ? description.equals(project.description) : project.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (votingOpen ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", votingOpen=" + votingOpen +
                ", voters=" + voters +
                '}';
    }
}
