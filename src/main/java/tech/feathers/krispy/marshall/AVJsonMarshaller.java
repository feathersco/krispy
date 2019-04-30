package tech.feathers.krispy.marshall;

import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;

public class AVJsonMarshaller {
    public String marshall(AttributeValue av) {
        if (av.getS() != null) {
            return marshallString(av);
        } else if (av.getN() != null) {
            return marshallNumber(av);
        } else if (av.getSS() != null) {
            return marshallStringSet(av);
        } else if (av.getB() != null) {
            return marshallBoolean(av);
        } else if (av.getL() != null) {
            return marshallList(av);
        } else if (av.getM() != null) {
            return marshallMap(av);
        } else {
            return marshallNULL();
        }
    }

    private String marshallString(AttributeValue av) {
        return String.format("{\"S\": \"%s\"}", av.getS());
    }
    
    private String marshallNumber(AttributeValue av) {
        return String.format("{\"N\": %s}", av.getN());
    }

    private String marshallStringSet(AttributeValue av) {
        List<String> ss = av.getSS();
        String result = "[";
        for (int i = 0; i < ss.size(); i++) {
            if (i > 0) { result += ","; }
            result += "\"" + ss.get(i) + "\"";
        }
        result += "]";
        return String.format("{\"SS\": %s}", result);
    }

    private String marshallBoolean(AttributeValue av) {
        return String.format("{\"B\": %s}", av.getB());
    }

    private String marshallList(AttributeValue av) {
        String result = "{\"L\": [";
        List<AttributeValue> lst = av.getL();
        if (lst.size() > 0) {
            result += marshall(lst.get(0));
            for (int i = 1; i < lst.size(); i++) {
                result += ", " + marshall(lst.get(i));
            }
        }
        result += "]}";
        return result;
    }

    private String marshallMap(AttributeValue av) {
        String result = "{\"M\": {";
        Set<Entry<String, AttributeValue>> entrySet = av.getM().entrySet();
        boolean first = true;
        for (Entry<String, AttributeValue> entry : entrySet) {
            if (first) {
                first = false;
            } else {
                result += ", ";
            }
            result += "\"" + entry.getKey() + "\": " + marshall(entry.getValue());
            
        }
        result += "}}";
        return result;
    }

    private String marshallNULL() {
        return "{\"NULL\": true}";
    }
}