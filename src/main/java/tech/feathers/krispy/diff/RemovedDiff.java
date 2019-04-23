package tech.feathers.krispy.diff;

public class RemovedDiff extends JsonDiff {
    private Object oldValue;

    public RemovedDiff(String path, Object oldValue) {
        super(path);
        this.oldValue = oldValue;
    }

    @Override
    public Object getOldValue() {
        return oldValue;
    }

    @Override
    public Object getNewValue() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("REMOVED\t%s : %s", path, oldValue);
    }
}