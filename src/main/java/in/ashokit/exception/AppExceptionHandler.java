package in.ashokit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = NoPlanFoundException.class)
	public ResponseEntity<ExceptionBean> noPlanFoundException(NoPlanFoundException e){
		
		String message = e.getMessage();
		ExceptionBean  eb = new ExceptionBean();
		eb.setCode("ERRXX8080");
		eb.setMsg(message);
		return new ResponseEntity<ExceptionBean>(eb, HttpStatus.BAD_REQUEST);
	}
	
}
