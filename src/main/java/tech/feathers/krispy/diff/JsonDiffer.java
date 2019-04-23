package tech.feathers.krispy.diff;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonDiffer {
    public List<JsonDiff> diff(Reader origFile, Reader newFile) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object origDoc = parser.parse(origFile);
        Object newDoc = parser.parse(newFile);

        if (origDoc == newDoc || (origDoc != null && origDoc.equals(newDoc))) {
            return new ArrayList<JsonDiff>();
        } else if (!(origDoc instanceof JSONObject) || !(newDoc instanceof JSONObject)) {
            List<JsonDiff> results = new ArrayList<JsonDiff>();
            results.add(new ModifiedDiff("", origDoc, newDoc));
            return results;
        }

        return diff("", (JSONObject) origDoc, (JSONObject) newDoc);
    }

    private List<JsonDiff> diff(String basePath, JSONObject origDoc, JSONObject newDoc) {
        @SuppressWarnings("unchecked")
        Set<Object> oldKeySet = origDoc == null ? new HashSet<Object>() : origDoc.keySet();
        @SuppressWarnings("unchecked")
        Set<Object> newKeySet = newDoc == null ? new HashSet<Object>() : newDoc.keySet();

        Set<Object> keySet = new HashSet<Object>();
        keySet.addAll(oldKeySet);
        keySet.addAll(newKeySet);

        List<JsonDiff> result = new ArrayList<JsonDiff>();
        for (Object key : keySet) {
            String path = basePath + key.toString();
            Object a = origDoc == null ? null : origDoc.get(key);
            Object b = newDoc == null ? null : newDoc.get(key);
            if (origDoc.containsKey(key) && newDoc.containsKey(key)) {
                if (a == b || (a != null && a.equals(b))) {
                    continue;
                } else if (a instanceof JSONObject && b instanceof JSONObject) {
                    List<JsonDiff> childDiffs = diff(path + ".", (JSONObject) a, (JSONObject) b);
                    result.addAll(childDiffs);
                } else {
                    result.add(new ModifiedDiff(path, a, b));
                }
            } else if (origDoc.containsKey(key)) {
                result.add(new RemovedDiff(path, a));
            } else {
                result.add(new AddedDiff(path, b));
            }
        }
        
        return result;
    }
}