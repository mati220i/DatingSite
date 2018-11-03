package pl.datingSite.logic;

import pl.datingSite.enums.MatchingDegree;
import pl.datingSite.enums.ZodiacSign;

public class ZodiacSignMatcher {

    private MatchingDegree[][] matchingDegrees = {
            {MatchingDegree.GOOD, MatchingDegree.AVERAGE, MatchingDegree.HIGH, MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.AVERAGE, MatchingDegree.GOOD, MatchingDegree.AVERAGE, MatchingDegree.HIGH, MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.AVERAGE},
            {MatchingDegree.AVERAGE, MatchingDegree.GOOD, MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.AVERAGE, MatchingDegree.GOOD, MatchingDegree.AVERAGE, MatchingDegree.HIGH, MatchingDegree.WEAK, MatchingDegree.HIGH},
            {MatchingDegree.HIGH, MatchingDegree.WEAK, MatchingDegree.GOOD, MatchingDegree.AVERAGE, MatchingDegree.HIGH, MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.AVERAGE, MatchingDegree.GOOD, MatchingDegree.AVERAGE, MatchingDegree.HIGH, MatchingDegree.WEAK},
            {MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.AVERAGE, MatchingDegree.GOOD, MatchingDegree.AVERAGE, MatchingDegree.HIGH, MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.AVERAGE, MatchingDegree.GOOD, MatchingDegree.AVERAGE, MatchingDegree.HIGH},
            {MatchingDegree.HIGH, MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.AVERAGE, MatchingDegree.GOOD, MatchingDegree.AVERAGE, MatchingDegree.HIGH, MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.AVERAGE, MatchingDegree.GOOD, MatchingDegree.AVERAGE},
            {MatchingDegree.AVERAGE, MatchingDegree.HIGH, MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.AVERAGE, MatchingDegree.GOOD, MatchingDegree.AVERAGE, MatchingDegree.HIGH, MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.AVERAGE, MatchingDegree.GOOD},
            {MatchingDegree.GOOD, MatchingDegree.AVERAGE, MatchingDegree.HIGH, MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.AVERAGE, MatchingDegree.GOOD, MatchingDegree.AVERAGE, MatchingDegree.HIGH, MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.AVERAGE},
            {MatchingDegree.AVERAGE, MatchingDegree.GOOD, MatchingDegree.AVERAGE, MatchingDegree.HIGH, MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.AVERAGE, MatchingDegree.GOOD, MatchingDegree.AVERAGE, MatchingDegree.HIGH, MatchingDegree.WEAK, MatchingDegree.HIGH},
            {MatchingDegree.HIGH, MatchingDegree.AVERAGE, MatchingDegree.GOOD, MatchingDegree.AVERAGE, MatchingDegree.HIGH, MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.AVERAGE, MatchingDegree.GOOD, MatchingDegree.AVERAGE, MatchingDegree.HIGH, MatchingDegree.WEAK},
            {MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.AVERAGE, MatchingDegree.GOOD, MatchingDegree.AVERAGE, MatchingDegree.HIGH, MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.AVERAGE, MatchingDegree.GOOD, MatchingDegree.AVERAGE, MatchingDegree.HIGH},
            {MatchingDegree.HIGH, MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.AVERAGE, MatchingDegree.GOOD, MatchingDegree.AVERAGE, MatchingDegree.HIGH, MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.AVERAGE, MatchingDegree.GOOD, MatchingDegree.AVERAGE},
            {MatchingDegree.AVERAGE, MatchingDegree.HIGH, MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.AVERAGE, MatchingDegree.GOOD, MatchingDegree.AVERAGE, MatchingDegree.HIGH, MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.AVERAGE, MatchingDegree.GOOD}
    };


    public MatchingDegree getMatching(ZodiacSign zodiacSignUser1, ZodiacSign zodiacSignUser2) {
        return matchingDegrees[zodiacSignUser1.ordinal()][zodiacSignUser2.ordinal()];
    }
}
