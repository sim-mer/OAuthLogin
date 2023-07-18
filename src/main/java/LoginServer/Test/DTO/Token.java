package LoginServer.Test.DTO;

import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class Token {
    String accessToken;
    String refreshToken;
}
