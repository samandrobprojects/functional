package functional;
/**
* This class represents the functional idea of a Maybe
*
* <p> This class wraps an object, and can store either an instance of that object or nothing. You
* can then check this for the existence of an object inside, and get that instance also.
*
* @author  Rob
* @author  Sam
*/

public class Maybe<OBJECT> extends Monad<OBJECT> {

    private final OBJECT _object;
    private final boolean _isNothing;

    /*-------------------------------------------------------------------------------------------------
    * PUBLIC
    -------------------------------------------------------------------------------------------------*/

    /**
    * Check if this Maybe contains nothing
    *
    * <p> This method checks whether the current Maybe instance just contains null
    *
    * @return whether instance contains nothing 
    */
    public boolean isNothing() {
        return this._isNothing;
    }

    /**
    * Check if this Maybe contains an object
    *
    * <p> This method checks whether the current Maybe instance contains an object
    *
    * @return whether this instance contains an object 
    */
    public boolean isNotNothing() {
        return !this._isNothing;
    }

    /**
    * Get the object contained inside this instance
    *
    * <p> This method returns the object that inside the Maybe
    *
    * @return object inside of Maybe instance 
    * @throws IllegalOperationForMaybeState if Maybe instance does not contain an object
    */
    public OBJECT object() {
        _validateMaybeObjectContainsValidObject();
        return this._object;
    }

    /**
    * Check if the object inside Maybe instance is equal to another object
    *
    * <p>The method checks if the object inside the maybe equals to the given object using the
    * equals method of the given object type
    *
    * @param  objectInQuestion the object to compare to
    * @return whether the given object is equal to the object inside the Maybe inside 
    */
    public boolean containsObjectEqualTo(OBJECT objectInQuestion) {
        if (this.isNotNothing()) {
            return objectInQuestion.equals(this.object());
        }
        return false;
    }

    /**
     * Check if two Maybe instances are equal
     *
     * <p>This method checks if two Maybe instances contain the same object or are both
     * empty
     *
     * @param  objectToCompareToo the object to compare to
     * @return whether Maybe instances are equal
     */
    @Override
    public boolean equals(Object objectToCompareToo) {
        if (objectToCompareToo == null || !(objectToCompareToo instanceof Maybe)) {
            return false;
        } else {
            Maybe maybeObjectToComapareToo = (Maybe) objectToCompareToo;
            return _equalsMaybeObject(maybeObjectToComapareToo);
        }
    }

    /**
    * Apply the given monadic operation onto the object inside the Maybe instance
    *
    * <p> The method is takes a monadic operation and applies it to the object inside the Maybe instance
    *
    * @param  monadicOperationInQuestion the mopnadic operation to apply
    * @return the result of the given monadic operation wrapped inside the Maybe instance. 
    * @throws IllegalOperationForMaybeState if given monadic operation is the wrong type
    */
    @Override
    public <OBJECT_OUTPUT> Maybe<OBJECT_OUTPUT> applyGivenOperationOntoThisObjectMondically(MonadicOperation<Monad<OBJECT_OUTPUT>, OBJECT,OBJECT_OUTPUT> monadicOperationInQuestion) {
        if (this.isNotNothing()) {
            Monad<OBJECT_OUTPUT> resultOfMonadicOperation = monadicOperationInQuestion.performMonadicOperation(this.object());
            _validateThatObjectReturnedByMonadicOperationIsAMaybeMonad(resultOfMonadicOperation);
            return (Maybe<OBJECT_OUTPUT>) resultOfMonadicOperation;
        } else {
            return (Maybe<OBJECT_OUTPUT>) Maybe.asNothing();
        }
    }

    /*-------------------------------------------------------------------------------------------------
    * PUBLIC STATIC
    -------------------------------------------------------------------------------------------------*/
    
    /**
    * Create a Maybe instance that contains nothing
    *
    * @return instance of Maybe containing nothing 
    */
    public static Maybe asNothing() {
        return new Maybe(null, true);
    }

    /**
    * Create a Maybe instance that contains the given object
    *
    * <p> This can be given nothing as null is a valid object
    *
    * @param itself the object to be contained within the Maybe
    * @param <OBJECT> the object type that is contained in Maybe
    * @return instance of Maybe containing given object
    */
    public static <OBJECT> Maybe<OBJECT> asObject(OBJECT itself) {
        return new Maybe(itself, false);
    }

    /*-------------------------------------------------------------------------------------------------
    * PRIVATE
    -------------------------------------------------------------------------------------------------*/
    private <OBJECT_OUTPUT> void _validateThatObjectReturnedByMonadicOperationIsAMaybeMonad(Monad<OBJECT_OUTPUT> objectInQuestion) {
        if(!(objectInQuestion instanceof Maybe)) {
            throw new IllegalOperationForMaybeState("Monadic operation returned the wrong type");
        }
    }

    private void _validateMaybeObjectContainsValidObject() {
        if(isNothing()) {
            throw new IllegalOperationForMaybeState("No object instance inside Maybe");
        }
    }

    private Maybe(OBJECT object, boolean isNothing) {
        this._object = object;
        this._isNothing = isNothing;
    }

    private boolean _equalsMaybeObject(Maybe maybeObjectToCompareToo) {
        if (this.isNothing() && maybeObjectToCompareToo.isNothing()) {
            return true;
        } else if (this.isNotNothing() && maybeObjectToCompareToo.isNotNothing()) {
            return (this.object().equals(maybeObjectToCompareToo.object()));
        } else {
            return false;
        }
    }
}