package rxwriter.drug;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DrugConceptTest {

    private final static DrugConcept TEST_CONCEPT =
            new DrugConcept(new DrugClassification[]{
                    DrugClassification.ANTIANXIETY,
                    DrugClassification.ANALGESICS_NARCOTIC,
                    DrugClassification.NARCOTIC_ANTIHISTAMINE
            });

    @Test
    void drugBelongsInConceptIfOneClassMatches() {
        Assertions.assertTrue(TEST_CONCEPT.isDrugInConcept(new DispensableDrug("adrug",
                new DrugClassification[]{DrugClassification.ANTIANXIETY}, false)));
    }

    @Test
    void drugNotInConceptIfNoClassesMatches() {
        Assertions.assertFalse(TEST_CONCEPT.isDrugInConcept(new DispensableDrug("adrug",
                new DrugClassification[]{DrugClassification.NASAL_CORTICOSTEROIDS}, false)));
    }
}