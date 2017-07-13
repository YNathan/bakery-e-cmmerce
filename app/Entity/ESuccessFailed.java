package Entity;

/**
 * Created by jacky on 18/11/2016.
 */
public enum ESuccessFailed {
    SUCCESS(0), FAILED(1);

    private int value;
    private ESuccessFailed(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
