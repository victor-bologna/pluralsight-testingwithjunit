package rxwriter.drug;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import rxwriter.drug.database.DrugRecord;
import rxwriter.drug.database.DrugSource;

@DisplayName("DrugService should ")
class DrugServiceTest implements DrugSource {

    private DrugService drugService;

    @BeforeEach
    void setup() {
        drugService = new DrugService(this);
    }

    @DisplayName("return drug from the database sorted by drug name")
    @Test
    void drugsAreReturnedSorted() {
        List<DispensableDrug> drugList = drugService.findDrugsStartingWith("as");
        assertNotNull(drugList);
        assertEquals(2, drugList.size());
        assertEquals("asmanex", drugList.get(0).drugName());
        assertEquals("aspirin", drugList.get(1).drugName());
    }

    @DisplayName("return dispensable drugs with all properties set correctly from database")
    @Test
    @Tag("database")
    void setsDrugsPropertiesCorrectly() {
        List<DispensableDrug> drugList = drugService.findDrugsStartingWith("aspirin");
        DrugClassification[] drugClassifications = {
                DrugClassification.ANALGESIC, DrugClassification.PLATELET_AGGREGATION_INHIBITORS
        };
        DispensableDrug dispensableDrug = drugList.get(0);
        assertAll(
                () -> assertEquals("aspirin", dispensableDrug.drugName()),
                () -> assertFalse(dispensableDrug.isControlled()),
                () -> assertEquals(2, dispensableDrug.drugClassifications().length),
                () -> assertArrayEquals(drugClassifications, dispensableDrug.drugClassifications())
        );
    }

    @Override
    public List<DrugRecord> findDrugsStartingWith(String startingString) {
        List<DrugRecord> recordList = new ArrayList<>();
        if (startingString.equals("as")) {
            recordList.add(new DrugRecord("asmanex", new int[]{301}, 0));
            recordList.add(new DrugRecord("aspirin", new int[]{3645, 3530}, 0));
        } else if ((startingString.equals("aspirin"))) {
            recordList.add(new DrugRecord("aspirin", new int[]{3645, 3530}, 0));
        }
        return recordList;
    }

    @Nested
    @DisplayName("throw illegal argument exception ")
    class ThrowsExceptionTests {

        @DisplayName("when empty strings")
        @Test
        void throwsIllegalArgumentExceptionOnEmptyStrings() {
            System.out.println(assertThrows(IllegalArgumentException.class,
                    () -> drugService.findDrugsStartingWith("")).getMessage());
        }

        @DisplayName("when blank strings")
        @Test
        void throwsIllegalArgumentExceptionOnBlankStrings() {
            System.out.println(assertThrows(IllegalArgumentException.class,
                    () -> drugService.findDrugsStartingWith(" ")).getMessage());
        }
    }
}