package pl.lukas121213.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Information about project.")
public class ProjectInfo {

    @ApiModelProperty(notes = "Project name")
    private String name;

    @ApiModelProperty(notes = "Project description")
    private String description;

    @ApiModelProperty(notes = "Is project open for voting")
    @JsonProperty("voting_open")
    private boolean votingOpen;

    @ApiModelProperty(notes = "Number of votes for")
    @JsonProperty("votes_for")
    private long numberVotesFor;

    @ApiModelProperty(notes = "Number of votes against")
    @JsonProperty("votes_against")
    private long numberVotesAgainst;

    private ProjectInfo(String name, String description, boolean votingOpen, long numberVotesFor, long numberVotesAgainst) {
        this.name = name;
        this.description = description;
        this.votingOpen = votingOpen;
        this.numberVotesFor = numberVotesFor;
        this.numberVotesAgainst = numberVotesAgainst;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVotingOpen() {
        return votingOpen;
    }

    public long getNumberVotesFor() {
        return numberVotesFor;
    }

    public long getNumberVotesAgainst() {
        return numberVotesAgainst;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectInfo that = (ProjectInfo) o;

        if (votingOpen != that.votingOpen) return false;
        if (numberVotesFor != that.numberVotesFor) return false;
        if (numberVotesAgainst != that.numberVotesAgainst) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (votingOpen ? 1 : 0);
        result = 31 * result + (int) (numberVotesFor ^ (numberVotesFor >>> 32));
        result = 31 * result + (int) (numberVotesAgainst ^ (numberVotesAgainst >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ProjectInfo{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", votingOpen=" + votingOpen +
                ", numberVotesFor=" + numberVotesFor +
                ", numberVotesAgainst=" + numberVotesAgainst +
                '}';
    }

    public static class Builder {

        private String name;
        private String description;
        private boolean votingOpen;
        private long numberVotesFor;
        private long numberVotesAgainst;

        public Builder(Project project) {
            this.name = project.getName();
            this.description = project.getDescription();
            this.votingOpen = project.isVotingOpen();
        }

        public Builder setNumberVotesFor(long numberVotesFor) {
            this.numberVotesFor = numberVotesFor;
            return this;
        }

        public Builder setNumberVotesAgainst(long numberVotesAgainst) {
            this.numberVotesAgainst = numberVotesAgainst;
            return this;
        }

        public ProjectInfo build() {
            return new ProjectInfo(name, description, votingOpen, numberVotesFor, numberVotesAgainst);
        }
    }
}
