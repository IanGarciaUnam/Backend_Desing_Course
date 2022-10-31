package com.product.exception;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ExceptionResponse{

  @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-mm-dd hh:mm:ss")
  private LocalDateTime timestamp;
  private Integer status;
  private HttpStatus error;
  private String message;
  private String path;

  public ExceptionResponse(){

  }

  public LocalDateTime getTimesStamp(){
    return this.timestamp;
  }

  public LocalDateTime setTimesStamp(LocalDateTime l){
    return this.timestamp=l;
  }

  public Integer getStatus(){
    return this.status;
  }

  public Integer setStatus(Integer s){
      return this.status=s;
  }

  public HttpStatus getError(){
    return this.error;
  }

  public HttpStatus setError(HttpStatus e){
      return this.error=e;
  }

  public String getMessage(){
    return this.message;
  }

  public String setMessage(String m){
      return this.message=m;
  }

  public String getPath(){
    return this.path;
  }

  public String setPath(String p){
      return this.path=p;
  }


}
