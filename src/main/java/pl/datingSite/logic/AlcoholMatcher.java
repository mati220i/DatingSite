package pl.datingSite.logic;

import pl.datingSite.enums.Alcohol;
import pl.datingSite.enums.MatchingDegree;

public class AlcoholMatcher {

    private MatchingDegree[][] matchingDegree = {
            {MatchingDegree.HIGH, MatchingDegree.GOOD, MatchingDegree.WEAK},
            {MatchingDegree.HIGH, MatchingDegree.HIGH, MatchingDegree.HIGH},
            {MatchingDegree.HIGH, MatchingDegree.HIGH, MatchingDegree.HIGH}
    };

    public MatchingDegree getMatching(Alcohol alcoholUser1, Alcohol alcoholUser2) {
        return matchingDegree[alcoholUser1.ordinal()][alcoholUser2.ordinal()];
    }
}
