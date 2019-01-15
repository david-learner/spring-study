package hardlearner.springStudy.user.domain;

public enum Level {

    GOLD(3, null),
    SILVER(2, Level.GOLD),
    BASIC(1, Level.SILVER);


    private final int level;
    private final Level next;

    Level(int level, Level next) {
        this.level = level;
        this.next = next;
    }

    public int intValue() {
        return this.level;
    }

    public Level nextLevel() {
        return this.next;
    }


    public static Level valueOf(int value) {
        switch (value) {
            case 1:
                return BASIC;
            case 2:
                return SILVER;
            case 3:
                return GOLD;
            default:
                throw new AssertionError("Unknown value: " + value);
        }
    }
}
