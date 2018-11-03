package pl.datingSite.logic;

import pl.datingSite.enums.MaritalStatus;
import pl.datingSite.enums.MatchingDegree;

public class MaritalStatusMatcher {

    private MatchingDegree[][] matchingDegrees = {
            {MatchingDegree.HIGH, MatchingDegree.WEAK, MatchingDegree.GOOD, MatchingDegree.AVERAGE},
            {MatchingDegree.GOOD, MatchingDegree.GOOD, MatchingDegree.GOOD, MatchingDegree.GOOD},
            {MatchingDegree.GOOD, MatchingDegree.GOOD, MatchingDegree.HIGH, MatchingDegree.GOOD},
            {MatchingDegree.AVERAGE, MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.HIGH}
    };

    public MatchingDegree getMatching(MaritalStatus maritalStatusUser1, MaritalStatus maritalStatusUser2) {
        return matchingDegrees[maritalStatusUser1.ordinal()][maritalStatusUser2.ordinal()];
    }

}
