package LoginServer.Test.Login.OAuthClient;


import LoginServer.Test.Login.Enum.OAuthProvider;
import LoginServer.Test.Login.Info.OAuthInfo;
import LoginServer.Test.Login.Params.OAuthLoginParams;

public interface OAuthApiClient {
    OAuthProvider oAuthProvider();
    String requestAccessToken(OAuthLoginParams params);
    OAuthInfo requestOauthInfo(String accessToken);
}
