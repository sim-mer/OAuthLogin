package LoginServer.Test.Login.Info;

import LoginServer.Test.Login.Enum.OAuthProvider;

public interface OAuthInfo {
    String getEmail();

    OAuthProvider getOAuthProvider();
}
