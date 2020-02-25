package functional;
/**
* This class represents a Natural Number
*
* <p> This class is used to implement the idea of Natural Numbers in the type system, by which we mean you can write numbers,
* such as One, Two, Three etc. in the type signature of functions and values.
*
* @author  Rob
* @author  Sam
*/

public class N {

    /*-------------------------------------------------------------------------------------------------
     * PUBLIC
     -------------------------------------------------------------------------------------------------*/

    /**
    * Get the integer representation of natural number
    *
    * <p> This method gets the integer representation of the natural number that this type represents.
    *
    * @return the integer representation of this natural number 
    */
    public int integerRepresentationOfNaturalNumber() {
        throw new UnsupportedOperationException("Natural number without valid construction cannot have integer representation");
    }

    /**
    * This class represents the natural number one
    *
    * <p> This class is used in type signatures to represent 'one'
    *
    * @author  Rob
    * @author  Sam
    */
    public static class O extends N {
        
        /*-------------------------------------------------------------------------------------------------
         * PUBLIC
         -------------------------------------------------------------------------------------------------*/
        
        /**
        * Constructor for a NaturalNumber.O instance
        *
        * @return new NaturalNumber.O instance
        */
        public O() {
            // Nothing
        }

        /**
        * Get the integer representation for this Natural Number instance
        *
        * <p> This returns 1, since (int) represents the natural number one
        *
        * @return integer representation of the natural number one
        */        
        @Override
        public int integerRepresentationOfNaturalNumber() {
            return 1;
        }
    }

    /**
    * This class represents all Natural Numbers greater than one
    *
    * <p> This class is used in type signatures to represent all natural numbers greater
    * than one, using inductive principles
    *
    * @author  Rob
    * @author  Sam
    */
    public static class S<SUCCEEDED_NATURAL_VALUE extends N> extends N {
        
        private final SUCCEEDED_NATURAL_VALUE succeededNaturalValue;
        
        /*-------------------------------------------------------------------------------------------------
         * PUBLIC
         -------------------------------------------------------------------------------------------------*/
        
        /**
        * Constructor for a NaturalNumber.S instance
        *
        * @param succeededNaturalValue an instance for the natural number one less than the number we want to represent
        * @return new NaturalNumber.S instance
        */
        public S(SUCCEEDED_NATURAL_VALUE succeededNaturalValue) {
            this.succeededNaturalValue = succeededNaturalValue;
        }

        /**
        * Get the integer representation for this Natural Number instance
        *
        * <p> This returns the integer representation of the natural number this represents
        *
        * @return integer representation of the natural number this instance represents
        */ 
        @Override
        public int integerRepresentationOfNaturalNumber() {
            return this.succeededNaturalValue.integerRepresentationOfNaturalNumber() + 1;
        }
    }
}
