package LoginServer.Test.Controller;

import LoginServer.Test.Login.Params.KakaoLoginParams;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    @GetMapping("/kakao")
    public ResponseEntity kakao(@RequestParam String code) {
        KakaoLoginParams params = new KakaoLoginParams();
        params.setAuthorizationCode(code);

    }
}
