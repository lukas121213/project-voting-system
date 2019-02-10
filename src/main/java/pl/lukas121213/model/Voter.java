package pl.lukas121213.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@ApiModel(description = "Model for voter.")
@Entity
@Table(name = "voters")
public class Voter {

    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voter_id")
    private Long id;

    @ApiModelProperty(notes = "Voter first name")
    @JsonProperty("first_name")
    @Column(name = "firstName", nullable = false)
    private String firstName;

    @ApiModelProperty(notes = "Voter surname")
    @Column(name = "surname", nullable = false)
    private String surname;

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    @OneToMany(mappedBy = "voter", fetch = FetchType.EAGER)
    private Set<ProjectVoter> projects = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Optional<Set<ProjectVoter>> getProjects() {
        return Optional.ofNullable(projects);
    }

    public void setProjects(Set<ProjectVoter> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Voter voter = (Voter) o;

        if (id != null ? !id.equals(voter.id) : voter.id != null) return false;
        if (firstName != null ? !firstName.equals(voter.firstName) : voter.firstName != null) return false;
        return surname != null ? surname.equals(voter.surname) : voter.surname == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Voter{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}