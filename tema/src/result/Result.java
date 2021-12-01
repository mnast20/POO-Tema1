package result;

public final class Result {
    private final int id;
    private final String message;

    public Result(final int id, final String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public Object getResult() {
        return null;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "{"
                + "\"id\":"
                + id
                + ",\"message\":"
                + message
                + "}";
    }
}
