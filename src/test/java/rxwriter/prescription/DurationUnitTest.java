package rxwriter.prescription;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DurationUnitTest {

    @Test
    public void matchUnitBySingularString() {
        assertEquals(DurationUnit.WEEK, DurationUnit.getByTextValue("week"));
    }

    @Test
    public void matchUnitByPluralString() {
        assertSame(DurationUnit.WEEK, DurationUnit.getByTextValue("weeks"));
    }

    @Test
    void returnsNullForUnmatchedUnit() {
        assertNull(DurationUnit.getByTextValue("test"));
    }

    @Test
    void requiresNonNullArgument() {
        Exception thrown = assertThrows(NullPointerException.class, () -> DurationUnit.getByTextValue(null));
        assertEquals("Duration string must be non-null", thrown.getMessage());
    }
}