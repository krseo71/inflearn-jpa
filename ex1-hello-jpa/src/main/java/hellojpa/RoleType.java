package hellojpa;

public enum RoleType {
    ADMIN("ADMIN"),
    USER("USER");

    private String value;
    private RoleType(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }


}
