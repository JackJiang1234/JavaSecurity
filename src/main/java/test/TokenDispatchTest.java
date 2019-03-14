package test;

public class TokenDispatchTest {
    public static void main(String[] args){
        Token t = new Token();
        TokenA ta = new TokenA();

        AccessToken token = new AccessToken();
        token.visitToken(t);
        token.visitToken(ta);
    }
}
