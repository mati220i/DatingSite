package pl.datingSite.logic;

import pl.datingSite.enums.Children;
import pl.datingSite.enums.MatchingDegree;

public class ChildrenMatcher {

    private MatchingDegree[][] matchingDegree = {
            {MatchingDegree.HIGH, MatchingDegree.WEAK, MatchingDegree.WEAK, MatchingDegree.GOOD},
            {MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.GOOD, MatchingDegree.WEAK},
            {MatchingDegree.WEAK, MatchingDegree.WEAK, MatchingDegree.WEAK, MatchingDegree.HIGH},
            {MatchingDegree.GOOD, MatchingDegree.WEAK, MatchingDegree.HIGH, MatchingDegree.WEAK},
    };

    public MatchingDegree getMatching(Children childrenUser1, Children childrenUser2) {
        return matchingDegree[childrenUser1.ordinal()][childrenUser2.ordinal()];
    }

}
