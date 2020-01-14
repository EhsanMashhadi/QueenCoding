package q2;

import java.text.ParseException;

/**
 * Comment POJO class
 */

class Comment {

    private String author;
    private String created;
    private String description;

    public Comment(String author, String created, String description) {
        this.author = author;
        this.created = created;
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public String getCreated() {
        try {
            return Util.dateToStringComment(Util.stringToDate(created));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getCreatedEpoch() {
        try {
            return Util.getEpoch(Util.stringToDate(created));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDescription() {
        return description.replaceAll("\\<.*?>", "");
    }

    @Override
    public String toString() {
        return getAuthor() + ":" + getCreatedEpoch() + ":" + getCreated() + ":" + getDescription();
    }
}
