package spectra.recruitment.task.error;

public class LoginFailException extends RuntimeException {
  public LoginFailException(){

  }

  public LoginFailException(String message) {
    super(message);
  }
}
