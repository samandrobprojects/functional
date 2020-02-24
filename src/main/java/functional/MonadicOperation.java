package functional;
/**
* This class represents the functional concept of a monadic operation
*
* <p> This interface is used to create a monadic operation, which takes in an object which is the object wrapped
* by the monad, and then transforms this object and returns the transformed object inside another monadic object
*
* @author  Rob
* @author  Sam
*/

public interface MonadicOperation<MONADIC_OBJECT extends Monad<OBJECT_OUTPUT>, OBJECT_INPUT,OBJECT_OUTPUT> {

    /**
    * Take an object and perform an operation on it, returning the result wrapped inside a monadic object
    *
    * <p>The method is used with objects that extend Monad, in order to implement the functional idea of applying 
    * an operation monadically
    *
    * @param  input the object that will be operated on
    * @return the operation result wrapped in an object thta extends Monad 
    */
    MONADIC_OBJECT performMonadicOperation(OBJECT_INPUT input);
}