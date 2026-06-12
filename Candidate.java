package VotingSystem;


public class Candidate {
    private int candidateId;
    private String name;
    private int voteCount;

    // Constructor
    public Candidate(int candidateId, String name) {
        this.candidateId = candidateId;
        this.name = name;
        this.voteCount = 0;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public String getName() {
        return name;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void incrementVote() {
        voteCount++;
    }
}
