package tech.feathers.krispy;

import java.util.Map;

public class KrispyCase {
    private String description;
    private boolean warnOnAdd;
    private String cognitoIdentityId;
    private Map<String, Object> args;
    private Map<String, Object> result;
    private Map<String, Object> prevResult;
    private String templateFile;

    public String getDescription() {
        return description;
    }

    public boolean getWarnOnAdd() {
        return warnOnAdd;
    }

    public String getCognitoIdentityId() {
        return cognitoIdentityId;
    }

    public Map<String, Object> getArgs() {
        return args;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public Map<String, Object> getPrevResult() {
        return prevResult;
    }

    public String getTemplateFile() {
        return templateFile;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWarnOnAdd(boolean warnOnAdd) {
        this.warnOnAdd = warnOnAdd;
    }

    public void setCognitoIdentityId(String cognitoIdentityId) {
        this.cognitoIdentityId = cognitoIdentityId;
    }

    public void setArgs(Map<String, Object> args) {
        this.args = args;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    public void setPrevResult(Map<String, Object> prevResult) {
        this.prevResult = prevResult;
    }

    public void setTemplateFile(String templateFile) {
        this.templateFile = templateFile;
    }
}