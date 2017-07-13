package Entity;

/**
 * Created by jacky on 18/11/2016.
 */
public enum ELocationKind {
    WHITE(0), BLACK(1), SPANISH(2), JEWISH(3);

    private int value;
    ELocationKind(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
