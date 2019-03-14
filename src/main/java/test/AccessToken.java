package test;

public class AccessToken {

    public void visitToken(Token t){
        System.out.println(t.getClass().getName());
    }

    public void visitToken(TokenA t){
        System.out.println(t.getClass().getName());
    }
}
