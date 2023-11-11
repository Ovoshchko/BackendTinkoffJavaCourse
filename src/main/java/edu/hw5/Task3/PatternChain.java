package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class PatternChain {

    private final PatternVariant patternVariant;
    private PatternChain nextPattern;

    private PatternChain(PatternVariant patternVariant) {
        this.patternVariant = patternVariant;
    }

    private void setNextPatternChain(PatternChain nextPattern) {
        this.nextPattern = nextPattern;
    }

    public Optional<LocalDate> parseDate(String date) {

        if (isInvalidString(date)) {
            return Optional.empty();
        }

        if (date.toLowerCase().matches(patternVariant.getPattern())) {
            return (new DataParser()).parseDate(patternVariant, date);
        } else if (nextPattern != null) {
            return nextPattern.parseDate(date);
        }

        return Optional.empty();

    }

    /*
    Вы рекомендовали не использовать статики, но у меня не придумалось через конструктор.
    Можете подсказать как переделать под non-static или в данном случае подходит?
     */
    public static PatternChain init() {
        PatternVariant[] patternVariants = PatternVariant.values();
        PatternChain currentPattern = null;
        PatternChain previousPattern;

        for(PatternVariant patternVariant : patternVariants) {
            previousPattern = currentPattern;
            currentPattern = new PatternChain(patternVariant);
            currentPattern.setNextPatternChain(previousPattern);
        }

        return currentPattern;
    }

    private boolean isInvalidString(String input) {
        return (input == null) || (input.isBlank());
    }

}
