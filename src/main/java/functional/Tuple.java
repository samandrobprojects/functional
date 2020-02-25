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

public class Tuple<TUPLE_SIZE extends N, TUPLE_ELEMENT> {

    public static <TUPLE_SIZE extends N, TUPLE_ELEMENT> Maybe<Tuple<TUPLE_SIZE, TUPLE_ELEMENT>> multiplexMaybeTuple(final Tuple<TUPLE_SIZE, Maybe<TUPLE_ELEMENT>> givenTuple) {
        return Tuple.getHead(givenTuple).applyGivenOperationOntoThisObjectMondically(new MonadicOperation<Monad<Tuple<TUPLE_SIZE, TUPLE_ELEMENT>>, TUPLE_ELEMENT, Tuple<TUPLE_SIZE, TUPLE_ELEMENT>>() {
            @Override
            public Maybe<Tuple<TUPLE_SIZE, TUPLE_ELEMENT>> performMonadicOperation(final TUPLE_ELEMENT headElement) {
                boolean givenTupleIsAMultipleTuple = (givenTuple instanceof Multiple);
                if (givenTupleIsAMultipleTuple) {
                    Multiple givenTupleAsAMultiple = (Multiple)givenTuple;
                    Tuple restOfTupleWithoutHead = givenTupleAsAMultiple.withoutHead();
                    Maybe<Tuple> maybeMultiplexedRestOfTupleWithoutHead = Tuple.multiplexMaybeTuple(restOfTupleWithoutHead);
                    return maybeMultiplexedRestOfTupleWithoutHead.applyGivenOperationOntoThisObjectMondically(new MonadicOperation<Monad<Tuple<TUPLE_SIZE, TUPLE_ELEMENT>>, Tuple, Tuple<TUPLE_SIZE, TUPLE_ELEMENT>>() {
                        @Override
                        public Maybe<Tuple<TUPLE_SIZE, TUPLE_ELEMENT>> performMonadicOperation(final Tuple multiplexedRestOfTupleWithoutHead) {
                            return Maybe.asObject(Multiple.withHead(headElement, multiplexedRestOfTupleWithoutHead));
                        }
                    });
                } else {
                    Tuple<TUPLE_SIZE, TUPLE_ELEMENT> singleTupleContainingHeadElement = (Tuple<TUPLE_SIZE, TUPLE_ELEMENT>) Tuple.single(headElement);
                    return Maybe.asObject(singleTupleContainingHeadElement);
                }
            }
        });
    }


    /*-------------------------------------------------------------------------------------------------
     * PUBLIC STATIC
     -------------------------------------------------------------------------------------------------*/

    /**
     * Get the tuple without the head element from a given tuple
     *
     * @param givenTuple the tuple to retrieve the tail of the tuple from
     * @return the tuple without the head element
     */
    public static <ONE_LESS_THEN_TUPLE_SIZE extends N, TUPLE_ELEMENT> Tuple<ONE_LESS_THEN_TUPLE_SIZE, TUPLE_ELEMENT> withoutHead(Tuple<N.S<ONE_LESS_THEN_TUPLE_SIZE>, TUPLE_ELEMENT> givenTuple) {
        Multiple<ONE_LESS_THEN_TUPLE_SIZE, TUPLE_ELEMENT> givenTupleAfterTypeCast = (Multiple<ONE_LESS_THEN_TUPLE_SIZE, TUPLE_ELEMENT>) givenTuple;
        return givenTupleAfterTypeCast.withoutHead();
    }

    /**
     * Get the head element from the tuple
     *
     * @param givenTuple the tuple to retrieve the head element from
     * @return the head element
     */
    public static <TUPLE_SIZE extends N, TUPLE_ELEMENT> TUPLE_ELEMENT getHead(Tuple<TUPLE_SIZE, TUPLE_ELEMENT> givenTuple) {
        return givenTuple.getHead();
    }

    /**
     * Constructor for single tuple
     *
     * @param givenElement the element to store in the tuple
     * @return the single tuple instance
     */
    public static <TUPLE_ELEMENT> Tuple<N.O, TUPLE_ELEMENT> single(TUPLE_ELEMENT givenElement) {
        return (Tuple<N.O, TUPLE_ELEMENT>) new Single(givenElement);
    }

    /**
     * Constructor for multiple tuple
     *
     * @param givenElement the element to store in the tuple head
     * @param givenTuple the tuple to add to the end of the new tuple
     * @return the multiple tuple instance
     */
    public static <TUPLE_SIZE extends N, TUPLE_ELEMENT> Tuple<N.S<TUPLE_SIZE>, TUPLE_ELEMENT> withHead(TUPLE_ELEMENT givenElement, Tuple<TUPLE_SIZE, TUPLE_ELEMENT> givenTuple) {
        return (Tuple<N.S<TUPLE_SIZE>, TUPLE_ELEMENT>) new Multiple<>(givenElement, givenTuple);
    }

    /*-------------------------------------------------------------------------------------------------
     * PRIVATE
     -------------------------------------------------------------------------------------------------*/
    protected TUPLE_ELEMENT getHead() {
        throw new UnsupportedOperationException("Tuple not constructed somehow");
    }

    /**
    * This class represents single tuple
    *
    * <p> This class is used to represent a tuple with a single element
    *
    * @author  Rob
    * @author  Sam
    */
    private static class Single<TUPLE_ELEMENT> extends Tuple<N.O, TUPLE_ELEMENT> {
        
        private final TUPLE_ELEMENT singleTupleElement;

        /*-------------------------------------------------------------------------------------------------
         * PUBLIC
         -------------------------------------------------------------------------------------------------*/

        /**
        * Get the head element from the tuple
        *
        * @return the head element of the tuple
        */
        @Override
        public TUPLE_ELEMENT getHead() {
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
    private static class Multiple<ONE_LESS_THEN_TUPLE_SIZE extends N, TUPLE_ELEMENT> extends Tuple<N.S<ONE_LESS_THEN_TUPLE_SIZE>, TUPLE_ELEMENT> {
        
        private final TUPLE_ELEMENT firstTupleElement;
        private final Tuple<ONE_LESS_THEN_TUPLE_SIZE, TUPLE_ELEMENT> tupleTheFirstElementIsPrependedTo;

        /*-------------------------------------------------------------------------------------------------
         * PUBLIC
         -------------------------------------------------------------------------------------------------*/

        /**
         * Get the head element from the tuple
         *
         * @return the head element of the tuple
         */
        @Override
        public TUPLE_ELEMENT getHead() {
            return this.firstTupleElement;
        }

        /**
         * Get the tuple without the head element from a given tuple
         *
         * @return the tuple without the head element
         */
        public Tuple<ONE_LESS_THEN_TUPLE_SIZE, TUPLE_ELEMENT> withoutHead() {
            return this.tupleTheFirstElementIsPrependedTo;
        }

        /*-------------------------------------------------------------------------------------------------
         * PRIVATE
         -------------------------------------------------------------------------------------------------*/
        private Multiple(TUPLE_ELEMENT firstTupleElement, Tuple<ONE_LESS_THEN_TUPLE_SIZE, TUPLE_ELEMENT> tupleToPrependTo) {
            this.firstTupleElement = firstTupleElement;
            this.tupleTheFirstElementIsPrependedTo = tupleToPrependTo;
        }
    }
}
