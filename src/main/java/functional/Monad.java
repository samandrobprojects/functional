package functional;
/**
* Objects that extend this class capture one aspect of the functional idea of a monad, in that a monadic operation can be given to
* objects of this class, upon which they will be performed on this object.
*
* <p> This class currently only captures one aspect of the functional concept of a monad
*
* @author  Rob
* @author  Sam
*/

import java.lang.UnsupportedOperationException;

public abstract class Monad<OBJECT> {

    /**
    * Apply a given monadic operation onto this object
    *
    * <p>The applies a given monadic operation onto the object held within this class to return another monadic object that
    * contains the result of the monadic operation.
    *
    * @param monadicOperationInQuestion the monadic operation to be performed on the object inside this class
    * @param <OBJECT_OUTPUT> the output object type
    * @return a monad that contains the result of the monadic operation
    * @throws UnsupportedOperationException if this operation is not implemeted by the subclass
    */
    public <OBJECT_OUTPUT> Monad<OBJECT_OUTPUT> applyGivenOperationOntoThisObjectMondically(MonadicOperation<Monad<OBJECT_OUTPUT>, OBJECT,OBJECT_OUTPUT> monadicOperationInQuestion) {
        throw new UnsupportedOperationException("This operation has not been implemented");
    }
}
