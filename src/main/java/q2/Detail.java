package q2;

/**
 * Detail POJO class
 */

class Detail {

    private String type;
    private String priority;
    private String affectVersions;
    private String components;
    private String labels;
    private String status;
    private String resolution;
    private String fixVersion;
    private String patchInfo;
    private String estimatedComplexity;

    public Detail(String type, String priority, String affectVersions,
                  String components, String labels, String status, String resolution, String fixVersion,
                  String patchInfo, String estimatedComplexity) {
        this.type = type;
        this.priority = priority;
        this.affectVersions = affectVersions;
        this.components = components;
        this.labels = labels;
        this.status = status;
        this.resolution = resolution;
        this.fixVersion = fixVersion;
        this.patchInfo = patchInfo;
        this.estimatedComplexity = estimatedComplexity;
    }

    public String getType() {
        return type;
    }

    public String getPriority() {
        return priority;
    }

    public String getAffectVersions() {
        return affectVersions;
    }

    public String getComponents() {
        return components;
    }

    public String getLabels() {
        if (labels.trim().equals("")) {
            return "None";
        }
        return labels;
    }

    public String getStatus() {
        return status;
    }

    public String getResolution() {
        return resolution;
    }

    public String getFixVersion() {
        return fixVersion;
    }

    public String getPatchInfo() {
        return patchInfo.trim();
    }

    public String getEstimatedComplexity() {
        return estimatedComplexity.trim();
    }
}
