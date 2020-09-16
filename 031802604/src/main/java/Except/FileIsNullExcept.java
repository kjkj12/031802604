package Except;

public class FileIsNullExcept extends RuntimeException{

    public FileIsNullExcept(){
        super();
    }

    public FileIsNullExcept(String message){
        super(message);
    }
}
