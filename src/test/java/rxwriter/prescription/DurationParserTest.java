package rxwriter.prescription;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DurationParserTest {

    @ParameterizedTest
    @CsvSource({"2 weeks, 14", "1 month, 30", "5 days, 5", "once, 1"})
    void parseDurationWithValidUnitAndQuantity(String durationString, int expectedResult) {
        assertEquals(expectedResult, DurationParser.parseDays(durationString));
    }

    @Test
    void parseDurationWithValidUnitAndQuantityWithStrings() {
        assertEquals(0, DurationParser.parseDays("two weeks"));
        assertEquals(0, DurationParser.parseDays("three months"));
    }
}