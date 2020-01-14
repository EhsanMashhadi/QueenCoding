package q2;

/**
 * People POJO class
 */

class People {

    private String assignee;
    private String reporter;
    private String votes;
    private String watchers;

    People(String assignee, String reporter, String votes, String watchers) {
        this.assignee = assignee;
        this.reporter = reporter;
        this.votes = votes;
        this.watchers = watchers;
    }

    public String getAssignee() {
        return assignee;
    }

    public String getReporter() {
        return reporter;
    }

    public String getVotes() {
        return votes;
    }

    public String getWatchers() {
        return watchers;
    }
}
