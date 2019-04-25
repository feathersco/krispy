package tech.feathers.krispy.marshall;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.ArgumentMarshaller;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import org.apache.commons.lang3.NotImplementedException;

import com.amazonaws.services.dynamodbv2.datamodeling.marshallers.*;

public class AVMarshaller implements ArgumentMarshaller {
    @Override
    public AttributeValue marshall(Object obj) {
        if (obj instanceof String) {
            return StringToStringMarshaller.instance().marshall((String) obj);
        } else if (obj instanceof Number) {
            return NumberToNumberMarshaller.instance().marshall((Number) obj);
        } else if (obj instanceof Boolean) { 
            return BooleanToBooleanMarshaller.instance().marshall((Boolean) obj);
        } else if (obj == null) { 
            return new AttributeValue().withNULL(true);
        } else if (obj instanceof List<?>) {
            @SuppressWarnings("unchecked")
            List<Object> lst = (List<Object>) obj;
            return new CollectionToListMarshaller(this).marshall(lst);
        } else if (obj instanceof Map<?, ?>) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) obj;
            return new MapToMapMarshaller(this).marshall(map);
        } else {
            throw new NotImplementedException("Object to AttributeValue marshalling is not yet supported.");
        }
    }
}