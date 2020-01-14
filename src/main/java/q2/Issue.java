package q2;

import java.util.List;

/**
 * Issue POJO class
 */

class Issue {

    private Detail detail;
    private People people;
    private Dates dates;
    private Description description;
    private List<Comment> comments;

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Detail getDetail() {
        return detail;
    }

    public People getPeople() {
        return people;
    }

    public Dates getDates() {
        return dates;
    }

    public Description getDescription() {
        return description;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
