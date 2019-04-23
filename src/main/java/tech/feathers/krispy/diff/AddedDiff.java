package tech.feathers.krispy.diff;

public class AddedDiff extends JsonDiff {
    private Object newValue;

    public AddedDiff(String path, Object newValue) {
        super(path);
        this.newValue = newValue;
    }

    @Override
    public Object getOldValue() {
        return null;
    }

    @Override
    public Object getNewValue() {
        return newValue;
    }

    @Override
    public String toString() {
        return String.format("ADDED\t%s : %s", path, newValue);
    }
}