package q2;

import java.text.ParseException;

/**
 * Dates POJO class
 */

class Dates {

    private String created;
    private String updated;
    private String resolved;

    public Dates(String created, String updated, String resolved) {
        this.created = created;
        this.updated = updated;
        this.resolved = resolved;
    }

    public String getCreated() {
        try {
            return Util.dateToString(Util.stringToDate(created));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUpdated() {
        try {
            return Util.dateToString(Util.stringToDate(updated));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getResolved() {
        try {
            return Util.dateToString(Util.stringToDate(resolved));
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

    public String getUpdatedEpoch() {
        try {
            return Util.getEpoch(Util.stringToDate(updated));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getResolvedEpoch() {
        try {
            return Util.getEpoch(Util.stringToDate(resolved));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
