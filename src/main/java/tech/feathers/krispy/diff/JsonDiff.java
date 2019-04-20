package tech.feathers.krispy.diff;

/**
 * Represents a diff between two JSON documents.
 */
public abstract class JsonDiff {
    protected String path;
    
    public JsonDiff(String path) {
        this.path = path;
    }

    /**
     * @return Path of the differing property.
     */
    public String getPath() {
        return path;
    }

    /**
     * @return The value of the differing property in the original JSON.
     */
    public abstract Object getOldValue();

    /**
     * @return The value of the differing property in the new JSON.
     */
    public abstract Object getNewValue();
}