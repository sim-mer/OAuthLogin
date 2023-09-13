package LoginServer.Test.Service;

import LoginServer.Test.DTO.Token;
import LoginServer.Test.Domain.Member;
import LoginServer.Test.Login.Info.OAuthInfo;
import LoginServer.Test.Login.OAuthService;
import LoginServer.Test.Login.Params.OAuthLoginParams;
import LoginServer.Test.Repository.MemberRepository;
import LoginServer.Test.Utils.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





@Service
public class LoginService {
    @Autowired
    OAuthService oAuthService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    Jwt jwt;

    public Token kakao(OAuthLoginParams params) {
        OAuthInfo oAuthInfo = oAuthService.request(params); //유저 정보
        Long memberId = findOrCreateMember(oAuthInfo);
        Token token = new Token();
        token.setAccessToken(jwt.getAccessToken(memberId.toString(), null));
        token.setRefreshToken(jwt.getRefreshToken(memberId.toString()));
        return token;
    }

    private Long findOrCreateMember(OAuthInfo oAuthInfo) {
        return memberRepository.findByEmail(oAuthInfo.getEmail())
                .map(Member::getId)
                .orElseGet(() -> newMember(oAuthInfo));
    }

    private Long newMember(OAuthInfo oAuthInfo) {
        Member member = Member.builder()
                .email(oAuthInfo.getEmail())
                .oAuthProvider(oAuthInfo.getOAuthProvider())
                .build();

        return memberRepository.save(member).getId();
    }
}
