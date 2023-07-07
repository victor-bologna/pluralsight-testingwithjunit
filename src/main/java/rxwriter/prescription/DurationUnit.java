package rxwriter.prescription;

import java.util.Objects;

public enum DurationUnit {

    DAY("day", "days", 1),
    WEEK("week", "weeks", 7),
    MONTH("month", "months", 30);

    private final String singularForm;
    private final String pluralForm;
    private final int multiplier;

    DurationUnit(String singularForm, String pluralForm, int multiplier) {
        this.singularForm = singularForm;
        this.pluralForm = pluralForm;
        this.multiplier = multiplier;
    }

    public static DurationUnit getByTextValue(String durationString) {
        Objects.requireNonNull(durationString, "Duration string must be non-null");
        for (DurationUnit unit : DurationUnit.values()) {
            if ((unit.singularForm.equals(durationString.toLowerCase())) ||
                    (unit.pluralForm.equals(durationString.toLowerCase()))) {
                return unit;
            }
        }
        return null;
    }

    public int getMultiplier() {
        return multiplier;
    }
}
