package q2;

/**
 * Description POJO class
 */
class Description {

    private String description;

    Description(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description.replaceAll("\\<.*?>", "");
    }
}
