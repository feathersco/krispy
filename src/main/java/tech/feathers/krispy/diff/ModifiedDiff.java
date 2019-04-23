package tech.feathers.krispy.diff;

public class ModifiedDiff extends JsonDiff {
    private Object oldValue;
    private Object newValue;

    public ModifiedDiff(String path, Object oldValue, Object newValue) {
        super(path);
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @Override
    public Object getOldValue() {
        return oldValue;
    }

    @Override
    public Object getNewValue() {
        return newValue;
    }

    @Override
    public String toString() {
        return String.format("MODIFIED\t%s : %s ~> %s", path, oldValue, newValue);
    }
}