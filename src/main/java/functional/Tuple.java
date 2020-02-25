package functional;
/**
* This class represents a Tuple
*
* <p> This class is used to represent Tuples, and uses NaturalNumber to encode the tuple degree
* in the type signature
*
* @author  Rob
* @author  Sam
*/

public class Tuple<TUPLE_SIZE, TUPLE_ELEMENT> {


    private static void test() {
        Multiple<NaturalNumber.One, String> twoStrings = new Multiple<>("string 1", new Single<>("string 2"));
        ((Single<String>)twoStrings.getNext()).getElement();
        Multiple<NaturalNumber.Succ<NaturalNumber.One>, String> threeStrings = new Multiple<>("string 0", twoStrings);
        ((Multiple<NaturalNumber.One, String>)threeStrings.getNext()).getNext();
    }

    /**
    * This class represents single tuple
    *
    * <p> This class is used to represent a tuple with a single element
    *
    * @author  Rob
    * @author  Sam
    */
    public static class Single<TUPLE_ELEMENT> extends Tuple<NaturalNumber.One, TUPLE_ELEMENT> {
        
        private final TUPLE_ELEMENT singleTupleElement;
        
        /*-------------------------------------------------------------------------------------------------
        * PUBLIC STATIC
        -------------------------------------------------------------------------------------------------*/

        /**
        * Construct a single tuple
        *
        * @param singleTupleElement the tuple element to store
        * @return single tuple instance
        */
        public static <TUPLE_ELEMENT> Single<TUPLE_ELEMENT> tupleOfSingleElement(TUPLE_ELEMENT singleTupleElement) {
            return new Single(singleTupleElement);
        }

        /*-------------------------------------------------------------------------------------------------
        * PUBLIC
        -------------------------------------------------------------------------------------------------*/

        /**
        * Get the tuple element
        *
        * <p> Get the single element stored inside the tuple
        *
        * @return the element stored in the tuple
        */
        public TUPLE_ELEMENT getElement() {
            return this.singleTupleElement;
        }

        /*-------------------------------------------------------------------------------------------------
        * PRIVATE
        -------------------------------------------------------------------------------------------------*/
        private Single(TUPLE_ELEMENT singleTupleElement) {
            this.singleTupleElement = singleTupleElement;
        }
    }

    /**
    * This class represents every tuple with greater than one element
    *
    * <p> This class is is used to represent every tuple with more than a single element and encodes
    * the number of elements in the type signature
    *
    * @author  Rob
    * @author  Sam
    */
    public static class Multiple<ONE_LESS_THEN_TUPLE_SIZE extends NaturalNumber, TUPLE_ELEMENT> extends Tuple<NaturalNumber.Succ<ONE_LESS_THEN_TUPLE_SIZE>, TUPLE_ELEMENT> {
        
        private final TUPLE_ELEMENT firstTupleElement;
        private final Tuple<ONE_LESS_THEN_TUPLE_SIZE, TUPLE_ELEMENT> tupleTheFirstElementIsPrependedTo;

        /*-------------------------------------------------------------------------------------------------
        * PUBLIC
        -------------------------------------------------------------------------------------------------*/
       
        /**
        * Construct a tuple with mutiple elements
        *
        * @param
        * @return integer representation of the natural number one
        */
        public Multiple(TUPLE_ELEMENT firstTupleElement, Tuple<ONE_LESS_THEN_TUPLE_SIZE, TUPLE_ELEMENT> tupleToPrependTo) {
            this.firstTupleElement = firstTupleElement;
            this.tupleTheFirstElementIsPrependedTo = tupleToPrependTo;
        }

        /**
        * Get the integer representation for this Natural Number instance
        *
        * <p> This returns 1, since (int) represents the natural number one
        *
        * @return integer representation of the natural number one
        */
        public TUPLE_ELEMENT getFirstElement() {
            return this.firstTupleElement;
        }

        /**
        * Get the integer representation for this Natural Number instance
        *
        * <p> This returns 1, since (int) represents the natural number one
        *
        * @return integer representation of the natural number one
        */
        public Tuple<ONE_LESS_THEN_TUPLE_SIZE, TUPLE_ELEMENT> getNext() {
            return this.tupleTheFirstElementIsPrependedTo;
        }
    }
}
