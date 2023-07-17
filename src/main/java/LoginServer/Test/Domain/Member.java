package LoginServer.Test.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import LoginServer.Test.Login.Enum.OAuthProvider;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String name;

    private OAuthProvider oAuthProvider;

    @Builder
    public Member(String email, String name, OAuthProvider oAuthProvider) {
        this.email = email;
        this.name = name;
        this.oAuthProvider = oAuthProvider;
    }
}
