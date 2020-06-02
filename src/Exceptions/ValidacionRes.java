
package Exceptions;


public class ValidacionRes extends Exception {

    public String message;

    public ValidacionRes(String message) {
        this.message = message;
    }
    
    public String getMessage(){
        
      return super.getMessage();
    }
    
   
    
    
    
}
