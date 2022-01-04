package dokerplp.controller.res;

public enum ResStatus {
    OK("ok"),
    VALIDATION_FAILED("Validation failed"),
    REQUEST_ERROR("Request error"),
    UNDEFINED_ERROR("Undefined error");

    private final String description;

    ResStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
