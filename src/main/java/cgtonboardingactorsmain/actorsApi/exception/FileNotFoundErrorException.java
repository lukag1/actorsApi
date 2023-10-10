package cgtonboardingactorsmain.actorsApi.exception;

public class FileNotFoundErrorException extends RuntimeException{
    public FileNotFoundErrorException(String message) {
        super(message);
    }
}
