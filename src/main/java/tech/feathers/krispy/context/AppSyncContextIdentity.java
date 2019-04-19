package tech.feathers.krispy.context;

public class AppSyncContextIdentity {
    private String cognitoIdentityId;

    public AppSyncContextIdentity(String cognitoIdentityId) {
        this.cognitoIdentityId = cognitoIdentityId;
    }

    public String getCognitoIdentityId() {
        return cognitoIdentityId;
    }
}