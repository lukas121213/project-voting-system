package pl.lukas121213.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Information about voting.")
public class VotingInfo {

    @ApiModelProperty(notes = "Voter identifier")
    @JsonProperty("voter_id")
    private long voterId;

    @ApiModelProperty(notes = "Project identifier")
    @JsonProperty("project_id")
    private long projectId;

    @ApiModelProperty(notes = "Voting type")
    @JsonProperty("voting_type")
    private VotingType votingType;

    public long getVoterId() {
        return voterId;
    }

    public void setVoterId(long voterId) {
        this.voterId = voterId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public VotingType getVotingType() {
        return votingType;
    }

    public void setVotingType(VotingType votingType) {
        this.votingType = votingType;
    }

    @Override
    public String toString() {
        return "VotingInfo{" +
                "voterId=" + voterId +
                ", projectId=" + projectId +
                ", votingType=" + votingType +
                '}';
    }
}
