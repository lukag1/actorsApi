package cgtonboardingactorsmain.actorsApi.exception;

public class SqlSyntaxException extends RuntimeException{
    public SqlSyntaxException(String message) {
        super(message);
    }
}
