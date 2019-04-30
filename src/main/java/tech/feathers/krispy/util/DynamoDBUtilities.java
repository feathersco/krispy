package tech.feathers.krispy.util;

import tech.feathers.krispy.marshall.AVJsonMarshaller;
import tech.feathers.krispy.marshall.AVMarshaller;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.List;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.apache.commons.lang3.NotImplementedException;


/**
 * Implementation of the AWS AppSync resolver mapping template utility methods for DynamoDB related functionality.
 */
public class DynamoDBUtilities {
    private AVMarshaller avMarshaller;
    private AVJsonMarshaller avJsonMarshaller;

    public DynamoDBUtilities() {
        this(new AVMarshaller(), new AVJsonMarshaller());
    }

    public DynamoDBUtilities(AVMarshaller avMarshaller, AVJsonMarshaller avJsonMarshaller) {
        this.avMarshaller = avMarshaller;
        this.avJsonMarshaller = avJsonMarshaller;
    }

    public AttributeValue toDynamoDB(Object o) {
        return avMarshaller.marshall(o);
    }

    public String toDynamoDBJson(Object o) { 
        return avJsonMarshaller.marshall(toDynamoDB(o));
    }

    public AttributeValue toString(String s) {
        return avMarshaller.marshall(s);
    }

    public String toStringJson(String s) {
        return avJsonMarshaller.marshall(toString(s));
    }

    public AttributeValue toStringSet(List<String> strSet) { 
        return new AttributeValue().withSS(strSet);
    }

    public String toStringSetJson(List<String> strSet) {
        return avJsonMarshaller.marshall(toStringSet(strSet));
    }

    public AttributeValue toNumber(Number n) {
        return avMarshaller.marshall(n);
    }

    public String toNumberJson(Number n) {
        return avJsonMarshaller.marshall(toNumber(n));
    }

    public AttributeValue toNumberSet(List<Number> list) { throw new NotImplementedException("Not yet implemented."); }

    public String toNumberSetJson(List<Number> list) { throw new NotImplementedException("Not yet implemented."); }

    public AttributeValue toBinary(String s) { throw new NotImplementedException("Not yet implemented."); }

    public String toBinaryJson(String s) { throw new NotImplementedException("Not yet implemented."); }

    public AttributeValue toBinarySet(List<String> list) { throw new NotImplementedException("Not yet implemented."); }

    public String toBinarySetJson(List<String> list) { throw new NotImplementedException("Not yet implemented."); }

    public AttributeValue toBoolean(Boolean b) {
        return avMarshaller.marshall(b);
    }

    public String toBooleanJson(Boolean b) {
        return avJsonMarshaller.marshall(toBoolean(b));
    }

    public AttributeValue toNull() {
        return avMarshaller.marshall(null);
    }

    public String toNullJson() {
        return avJsonMarshaller.marshall(toNull());
    }

    public AttributeValue toList(List<Object> list) {
        return avMarshaller.marshall(list);
    }

    public String toListJson(List<Object> list) {
        return avJsonMarshaller.marshall(toList(list));
    }

    public AttributeValue toMap(Map<String, Object> map) {
        return avMarshaller.marshall(map);
    }

    public String toMapJson(Map<String, Object> map) {
        return avJsonMarshaller.marshall(toMap(map));
    }

    public Map<String, AttributeValue> toMapValues(Map<String, Object> map) {
        return avMarshaller.marshall(map).getM();
    }

    public String toMapValuesJson(Map<String, Object> map) {
        String result = "{";
        Set<Entry<String, AttributeValue>> entrySet = toMapValues(map).entrySet();
        boolean first = true;
        for (Entry<String, AttributeValue> entry : entrySet) {
            if (first) {
                first = false;
            } else {
                result += ", ";
            }
            result += "\"" + entry.getKey() + "\": " + avJsonMarshaller.marshall(entry.getValue());
            
        }
        result += "}";
        return result;
    }

    public AttributeValue toS3Object(String key, String bucket, String region) { throw new NotImplementedException("Not yet implemented."); }

    public String toS3ObjectJson(String key, String bucket, String region) { throw new NotImplementedException("Not yet implemented."); }

    public AttributeValue toS3Object(String key, String bucket, String region, String version) { throw new NotImplementedException("Not yet implemented."); }

    public String toS3ObjectJson(String key, String bucket, String region, String version) { throw new NotImplementedException("Not yet implemented."); }

    public Map<String, Object> fromS3ObjectJson(String s) { throw new NotImplementedException("Not yet implemented."); }
}