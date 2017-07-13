package Entity;

/**
 * Created by jacky on 18/11/2016.
 */
public enum EHouseKind {
    FAMILLY(0), YONG_COUPLE(1), ALONE(2), MAJORS(3);

    private int value;
    EHouseKind(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
