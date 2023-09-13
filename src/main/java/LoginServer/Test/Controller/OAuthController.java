package LoginServer.Test.Controller;

import LoginServer.Test.DTO.Token;
import LoginServer.Test.Login.Params.KakaoLoginParams;
import LoginServer.Test.Service.LoginService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth")
public class OAuthController {
    @Autowired
    LoginService loginService;

    @GetMapping("/kakao")
    public String kakao(@RequestParam String code) {
        KakaoLoginParams params = new KakaoLoginParams();
        params.setAuthorizationCode(code);
        Token token = loginService.kakao(params);
        return token.toString();
    }
}
