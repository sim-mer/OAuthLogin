package LoginServer.Test.Login;

import LoginServer.Test.Login.Enum.OAuthProvider;
import LoginServer.Test.Login.Info.OAuthInfo;
import LoginServer.Test.Login.OAuthClient.OAuthApiClient;
import LoginServer.Test.Login.Params.OAuthLoginParams;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OAuthService {
    private final Map<OAuthProvider, OAuthApiClient> clients;

    public OAuthService(List<OAuthApiClient> clients) {
        this.clients = clients.stream().collect(
                Collectors.toUnmodifiableMap(OAuthApiClient::oAuthProvider, Function.identity()));
    }

    public OAuthInfo request(OAuthLoginParams params) {
        OAuthApiClient client = clients.get(params.oAuthProvider());
        String accessToken = client.requestAccessToken(params);
        return client.requestOauthInfo(accessToken);
    }
}
