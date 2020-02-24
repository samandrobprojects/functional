package functional;

import org.junit.Test;
import static org.junit.Assert.*;

public class MaybeTest {
    
    @Test public void testEmptyMaybeContainsNothing() {
    	Maybe<Integer> maybeInteger = Maybe.asNothing();
        assertTrue(maybeInteger.isNothing());
    }

    @Test public void testMaybeObjectContainsObjectItWasCreatedFrom() {
    	Maybe<Integer> maybeInteger = Maybe.asObject((Integer) 10);
        assertTrue(maybeInteger.object() == (Integer) 10);
    }
}