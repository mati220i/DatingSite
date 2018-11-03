package pl.datingSite.logic;

import pl.datingSite.enums.MatchingDegree;
import pl.datingSite.enums.Smoking;

public class SmokingMatcher {

    private MatchingDegree[][] matchingDegree = {
            {MatchingDegree.HIGH, MatchingDegree.GOOD, MatchingDegree.WEAK, MatchingDegree.AVERAGE},
            {MatchingDegree.HIGH, MatchingDegree.HIGH, MatchingDegree.HIGH, MatchingDegree.HIGH},
            {MatchingDegree.HIGH, MatchingDegree.HIGH, MatchingDegree.HIGH, MatchingDegree.HIGH},
            {MatchingDegree.HIGH, MatchingDegree.HIGH, MatchingDegree.HIGH, MatchingDegree.HIGH}
    };

    public MatchingDegree getMatching(Smoking smokingUser1, Smoking smokingUser2) {
        return matchingDegree[smokingUser1.ordinal()][smokingUser2.ordinal()];
    }

}
