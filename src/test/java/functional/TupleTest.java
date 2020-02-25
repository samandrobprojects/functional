package functional;

public class TupleTest {
    private static void test() {
        Tuple<N.S<N.S<N.O>>, String> aTupleOf3 = Tuple.withHead("String 1", Tuple.withHead("String 2", Tuple.single("string 3")));
        Tuple.withoutHead(Tuple.withoutHead(aTupleOf3)).getHead();

        /*Multiple<N.O, String> twoStrings = new Multiple<>("string 1", new Single<>("string 2"));
        ((Single<String>)twoStrings.getNext()).getElement();
        Multiple<N.S<N.O>, String> threeStrings = new Multiple<>("string 0", twoStrings);
        ((Multiple<N.O, String>)threeStrings.getNext()).getNext();*/
    }
}
