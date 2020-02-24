package functional;
/**
* This class represents an exception for an illegal state during a Maybe operation
*
* <p> If there is no object inside a Maybe object when code attempts to access the object inside
* this exception is thrown
*
* @author  Rob
* @author  Sam
*/

import java.lang.RuntimeException;

public class IllegalOperationForMaybeState extends RuntimeException {

    /**
    * Constructs an IllegalOperationForMaybeState exception
    *
    * @param errorMessage the error message
    */
    public IllegalOperationForMaybeState(String errorMessage) {
        super(errorMessage);
    }
}