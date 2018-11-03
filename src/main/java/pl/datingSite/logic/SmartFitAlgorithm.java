package pl.datingSite.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pl.datingSite.enums.*;
import pl.datingSite.model.ClassifiedUser;
import pl.datingSite.model.User;
import pl.datingSite.repository.CityRepository;
import pl.datingSite.tools.DistanceCalculator;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SmartFitAlgorithm {

    private User user;
    private int userAge;
    private List<User> users;

    @Autowired
    @Lazy
    private CityRepository cityRepository;

    private DistanceCalculator distanceCalculator;
    private ZodiacSignMatcher zodiacSignMatcher;
    private MaritalStatusMatcher maritalStatusMatcher;
    private SmokingMatcher smokingMatcher;
    private AlcoholMatcher alcoholMatcher;
    private ChildrenMatcher childrenMatcher;


    public SmartFitAlgorithm() {
        zodiacSignMatcher = new ZodiacSignMatcher();
        maritalStatusMatcher = new MaritalStatusMatcher();
        smokingMatcher = new SmokingMatcher();
        alcoholMatcher = new AlcoholMatcher();
        childrenMatcher = new ChildrenMatcher();
    }

    private int getUserAge(User user) {
        LocalDate now = LocalDate.now();
        LocalDate birth = user.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period period = Period.between(birth, now);
        return period.getYears();
    }

    private void filterSex() {
        if(user.getSex().equals("Mężczyzna"))
            this.users = users.stream().filter(user1 -> user1.getSex().equals("Kobieta")).collect(Collectors.toList());
        else
            this.users = users.stream().filter(user1 -> user1.getSex().equals("Mężczyzna")).collect(Collectors.toList());
    }

    private void filterReal() {
        this.users = users.stream().filter(user1 -> user1.isFake() == false).collect(Collectors.toList());
    }

    private float calculateLocation(User userFromDB) {
        if(user.getCity() != null && userFromDB.getCity() != null) {
            double distance = distanceCalculator.getDistance(user.getCity(), userFromDB.getCity());

            double points = 0, allPoints = 0;

            if(userAge >= 60)
                allPoints = 6;
            else
                allPoints = 5;

            if (distance <= 20) {
                if (userAge >= 60)
                    points = 5 + 1;
                else
                    points = 5;
            }
            if (distance > 20 && distance <= 50) {
                if (userAge >= 60)
                    points = 4 + 1;
                else
                    points = 4;
            }
            if (distance > 50 && distance <= 100)
                points = 3;
            if (distance > 100 && distance <= 200)
                points = 2;
            if (distance > 200)
                points = 1;

            return calculatePercentage(((float)points / (float)allPoints * 100) * (float)FitWeight.MEDIUM.ordinal());
        } else
            return 0;
    }

    private float calculateAge(User userFromDB) {
        double points = 0, allPoints = 5;

        int difference =  Math.abs((userAge - getUserAge(userFromDB)));

        if(difference <= 3)
            points = 5;
        if(difference > 3 && difference <= 5)
            points = 4;
        if(difference > 5 && difference <= 10)
            points = 3;
        if(difference > 10 && difference <= 15)
            points = 2;
        if(difference > 15 && difference <= 20)
            points = 1;
        if(difference > 20)
            points = 0;

        return calculatePercentage(((float)points / (float)allPoints * 100) * (float)FitWeight.LARGE.ordinal());
    }

    private float calculateZodiacSign(User userFromDB) {
        double points, allPoints = MatchingDegree.HIGH.ordinal();

        if(user.getZodiacSign() != null && userFromDB.getZodiacSign() != null) {
            points = zodiacSignMatcher.getMatching(user.getZodiacSign(), userFromDB.getZodiacSign()).ordinal();

            return calculatePercentage(((float)points / (float)allPoints * 100) * (float)FitWeight.SMALL.ordinal());
        } else
            return 0;
    }

    private float calculateProfession(User userFromDB) {
        if(user.getProfession() != null && userFromDB.getProfession() != null) {
            if(user.getProfession().equals(userFromDB.getProfession()))
                return calculatePercentage((1 * 100) * (float)FitWeight.VERY_SMALL.ordinal());
            else
                return calculatePercentage((0 * 100) * (float)FitWeight.VERY_SMALL.ordinal());
        } else
            return calculatePercentage((0 * 100) * (float)FitWeight.VERY_SMALL.ordinal());
    }

    private float calculateMaritalStatus(User userFromDB) {
        double points, allPoints = MatchingDegree.HIGH.ordinal();

        if(user.getMaritalStatus() != null && userFromDB.getMaritalStatus() != null) {
            points = maritalStatusMatcher.getMatching(user.getMaritalStatus(), userFromDB.getMaritalStatus()).ordinal();
            return calculatePercentage(((float)points / (float)allPoints * 100) * (float)FitWeight.LARGE.ordinal());
        } else
            return 0;
    }

    private float calculateEducation(User userFromDB) {
        double allPoints = 1;
        if(user.getEducation() != null && userFromDB.getEducation() != null) {
            if(user.getEducation().equals(userFromDB.getEducation()))
                return calculatePercentage((1 / (float)allPoints * 100) * (float)FitWeight.SMALL.ordinal());
            else
                return calculatePercentage((0 / (float)allPoints * 100) * (float)FitWeight.SMALL.ordinal());
        } else
            return calculatePercentage((0 / (float)allPoints * 100) * (float)FitWeight.SMALL.ordinal());
    }

    private float calculateInterests(User userFromDB) {
        if(user.getInterests() != null && userFromDB.getInterests() != null) {
            if(user.getInterests().isEmpty())
                return 0;

            Set<String> userInterests = user.getInterests();
            Set<String> userFromDBInterests = userFromDB.getInterests();

            double allPoints = userInterests.size();
            double points = 0;
            Iterator<String> iterator = userFromDBInterests.iterator();

            while (iterator.hasNext()) {
                String temp = iterator.next();
                if (userInterests.contains(temp))
                    points++;
            }
            return calculatePercentage(((float)points / (float)allPoints * 100) * (float)FitWeight.VERY_LARGE.ordinal());
        } else
            return 0;
    }

    private float calculateFigure(User userFromDB) {
        double allPoints = 1;
        if(user.getAppearanceAndCharacter().getFigure() != null && userFromDB.getAppearanceAndCharacter().getFigure() != null) {
            if(user.getAppearanceAndCharacter().getFigure().equals(userFromDB.getAppearanceAndCharacter().getFigure()))
                return calculatePercentage((1 / (float)allPoints * 100) * (float)FitWeight.SMALL.ordinal());
            else
                return calculatePercentage((0 / (float)allPoints * 100) * (float)FitWeight.SMALL.ordinal());
        } else
            return calculatePercentage((0 / (float)allPoints * 100) * (float)FitWeight.SMALL.ordinal());
    }

    private float calculateHeight(User userFromDB) {
        double points = 0, allPoints = 3;

        if(user.getAppearanceAndCharacter().getHeight() != null && userFromDB.getAppearanceAndCharacter().getHeight() != null) {
            int difference = Math.abs((user.getAppearanceAndCharacter().getHeight() - userFromDB.getAppearanceAndCharacter().getHeight()));

            if (difference <= 10)
                points = 3;
            if (difference > 10 && difference <= 20)
                points = 2;
            if (difference > 20)
                points = 1;

            return calculatePercentage(((float) points / (float) allPoints * 100) * (float)FitWeight.MEDIUM.ordinal());
        } else
            return 0;
    }

    private float calculateHairColor(User userFromDB) {
        double allPoints = 1;
        if(user.getAppearanceAndCharacter().getHairColor() != null && userFromDB.getAppearanceAndCharacter().getHairColor() != null) {
            if(user.getAppearanceAndCharacter().getHairColor().equals(userFromDB.getAppearanceAndCharacter().getHairColor()))
                return calculatePercentage((1 / (float)allPoints * 100) * (float)FitWeight.VERY_SMALL.ordinal());
            else
                return calculatePercentage((0 / (float)allPoints * 100) * (float)FitWeight.VERY_SMALL.ordinal());
        } else
            return calculatePercentage((0 / (float)allPoints * 100) * (float)FitWeight.VERY_SMALL.ordinal());
    }

    private float calculateEyeColor(User userFromDB) {
        double allPoints = 1;
        if(user.getAppearanceAndCharacter().getEyeColor() != null && userFromDB.getAppearanceAndCharacter().getEyeColor() != null) {
            if(user.getAppearanceAndCharacter().getEyeColor().equals(userFromDB.getAppearanceAndCharacter().getEyeColor()))
                return calculatePercentage((1 / (float)allPoints * 100) * (float)FitWeight.VERY_SMALL.ordinal());
            else
                return calculatePercentage((0 / (float)allPoints * 100) * (float)FitWeight.VERY_SMALL.ordinal());
        } else
            return calculatePercentage((0 / (float)allPoints * 100) * (float)FitWeight.VERY_SMALL.ordinal());
    }

    private float calculateSmoking(User userFromDB) {
        double points, allPoints = MatchingDegree.HIGH.ordinal();

        if(user.getAppearanceAndCharacter().getSmoking() != null && userFromDB.getAppearanceAndCharacter().getSmoking() != null) {
            points = smokingMatcher.getMatching(user.getAppearanceAndCharacter().getSmoking(), userFromDB.getAppearanceAndCharacter().getSmoking()).ordinal();
            return calculatePercentage(((float)points / (float)allPoints * 100) * (float)FitWeight.MEDIUM.ordinal());
        } else
            return 0;
    }

    private float calculateAlcohol(User userFromDB) {
        double points, allPoints = MatchingDegree.HIGH.ordinal();

        if(user.getAppearanceAndCharacter().getAlcohol() != null && userFromDB.getAppearanceAndCharacter().getAlcohol() != null) {
            points = alcoholMatcher.getMatching(user.getAppearanceAndCharacter().getAlcohol(), userFromDB.getAppearanceAndCharacter().getAlcohol()).ordinal();
            return calculatePercentage(((float)points / (float)allPoints * 100) * (float)FitWeight.MEDIUM.ordinal());
        } else
            return 0;
    }

    private float calculateChildren(User userFromDB) {
        double points, allPoints = MatchingDegree.HIGH.ordinal();

        if(user.getAppearanceAndCharacter().getChildren() != null && userFromDB.getAppearanceAndCharacter().getChildren() != null) {
            points = childrenMatcher.getMatching(user.getAppearanceAndCharacter().getChildren(), userFromDB.getAppearanceAndCharacter().getChildren()).ordinal();
            return calculatePercentage(((float)points / (float)allPoints * 100) * (float)FitWeight.MEDIUM.ordinal());
        } else
            return 0;
    }

    private float calculateHolidays(User userFromDB) {
        if(user.getAppearanceAndCharacter().getHoliday() != null && userFromDB.getAppearanceAndCharacter().getHoliday() != null) {
            if(user.getAppearanceAndCharacter().getHoliday().isEmpty())
                return 0;

            Set<Holiday> userHolidays = user.getAppearanceAndCharacter().getHoliday();
            Set<Holiday> userFromDBHolidays = userFromDB.getAppearanceAndCharacter().getHoliday();

            double allPoints = userHolidays.size();
            double points = 0;
            Iterator<Holiday> iterator = userFromDBHolidays.iterator();

            while (iterator.hasNext()) {
                Holiday temp = iterator.next();
                if (userHolidays.contains(temp))
                    points++;
            }
            return calculatePercentage(((float)points / (float)allPoints * 100) * (float)FitWeight.MEDIUM.ordinal());
        } else
            return 0;
    }

    private float calculateLookingFor(User userFromDB) {
        if(user.getAppearanceAndCharacter().getLookingFor() != null && userFromDB.getAppearanceAndCharacter().getLookingFor() != null) {
            if(user.getAppearanceAndCharacter().getLookingFor().isEmpty())
                return 0;

            Set<LookingFor> userLookingFor = user.getAppearanceAndCharacter().getLookingFor();
            Set<LookingFor> userFromDBLookingFor = userFromDB.getAppearanceAndCharacter().getLookingFor();

            double allPoints = userLookingFor.size();
            double points = 0;
            Iterator<LookingFor> iterator = userFromDBLookingFor.iterator();

            while (iterator.hasNext()) {
                LookingFor temp = iterator.next();
                if (userLookingFor.contains(temp))
                    points++;
            }
            return calculatePercentage(((float)points / (float)allPoints * 100) * (float)FitWeight.VERY_LARGE.ordinal());
        } else
            return 0;
    }

    private float calculateMovieTypes(User userFromDB) {
        if(user.getAppearanceAndCharacter().getMovieType() != null && userFromDB.getAppearanceAndCharacter().getMovieType() != null) {
            if(user.getAppearanceAndCharacter().getMovieType().isEmpty())
                return 0;

            Set<MovieType> userMovieTypes = user.getAppearanceAndCharacter().getMovieType();
            Set<MovieType> userFromDBMovieTypes = userFromDB.getAppearanceAndCharacter().getMovieType();

            double allPoints = userMovieTypes.size();
            double points = 0;
            Iterator<MovieType> iterator = userFromDBMovieTypes.iterator();

            while (iterator.hasNext()) {
                MovieType temp = iterator.next();
                if (userMovieTypes.contains(temp))
                    points++;
            }
            return calculatePercentage(((float)points / (float)allPoints * 100) * (float)FitWeight.MEDIUM.ordinal());
        } else
            return 0;
    }

    private float calculateStyles(User userFromDB) {
        if(user.getAppearanceAndCharacter().getStyle() != null && userFromDB.getAppearanceAndCharacter().getStyle() != null) {
            if(user.getAppearanceAndCharacter().getStyle().isEmpty())
                return 0;

            Set<Style> userStyles = user.getAppearanceAndCharacter().getStyle();
            Set<Style> userFromDBStyles = userFromDB.getAppearanceAndCharacter().getStyle();

            double allPoints = userStyles.size();
            double points = 0;
            Iterator<Style> iterator = userFromDBStyles.iterator();

            while (iterator.hasNext()) {
                Style temp = iterator.next();
                if (userStyles.contains(temp))
                    points++;
            }
            return calculatePercentage(((float)points / (float)allPoints * 100) * (float)FitWeight.MEDIUM.ordinal());
        } else
            return 0;
    }

    private float calculateReligion(User userFromDB) {
        double allPoints = 1;
        if(user.getAppearanceAndCharacter().getReligion() != null && userFromDB.getAppearanceAndCharacter().getReligion() != null) {
            if(user.getAppearanceAndCharacter().getReligion().equals(userFromDB.getAppearanceAndCharacter().getReligion()))
                return calculatePercentage((1 / (float)allPoints * 100) * (float)FitWeight.SMALL.ordinal());
            else
                return calculatePercentage((0 / (float)allPoints * 100) * (float)FitWeight.SMALL.ordinal());
        } else
            return calculatePercentage((0 / (float)allPoints * 100) * (float)FitWeight.SMALL.ordinal());
    }

    private float calculatePercentage(float percentage) {
        percentage *= 100;
        percentage = (int) percentage;
        percentage /= 100;
        return percentage;
    }

    public List<ClassifiedUser> getFittedUsers(User user, List<User> users, boolean real) {
        this.user = user;
        this.users = users;
        this.userAge = getUserAge(this.user);
        this.distanceCalculator = new DistanceCalculator(user.getCity(), user.getCity(), cityRepository.findAll());

        filterSex();

        if(real)
            filterReal();

        Iterator<User> iterator = this.users.iterator();

        List<ClassifiedUser> fitUsers = new LinkedList<>();

        float percentage = 0, temp;
        float divider = FitWeight.MEDIUM.ordinal() + FitWeight.LARGE.ordinal() + FitWeight.SMALL.ordinal() + FitWeight.VERY_SMALL.ordinal()
                        + FitWeight.LARGE.ordinal() + FitWeight.SMALL.ordinal() + FitWeight.VERY_LARGE.ordinal() + FitWeight.SMALL.ordinal()
                        + FitWeight.MEDIUM.ordinal() + FitWeight.VERY_SMALL.ordinal() + FitWeight.VERY_SMALL.ordinal() + FitWeight.MEDIUM.ordinal()
                        + FitWeight.MEDIUM.ordinal() + FitWeight.MEDIUM.ordinal() + FitWeight.MEDIUM.ordinal() + FitWeight.VERY_LARGE.ordinal()
                        + FitWeight.MEDIUM.ordinal() + FitWeight.MEDIUM.ordinal() + FitWeight.SMALL.ordinal();
        while (iterator.hasNext()) {
            User userFromDB = iterator.next();

            percentage = 0;

            percentage += calculateLocation(userFromDB);
            percentage += calculateAge(userFromDB);
            percentage += calculateZodiacSign(userFromDB);
            percentage += calculateProfession(userFromDB);
            percentage += calculateMaritalStatus(userFromDB);
            percentage += calculateEducation(userFromDB);
            percentage += calculateInterests(userFromDB);
            percentage += calculateFigure(userFromDB);
            percentage += calculateHeight(userFromDB);
            percentage += calculateHairColor(userFromDB);
            percentage += calculateEyeColor(userFromDB);
            percentage += calculateSmoking(userFromDB);
            percentage += calculateAlcohol(userFromDB);
            percentage += calculateChildren(userFromDB);
            percentage += calculateHolidays(userFromDB);
            percentage += calculateLookingFor(userFromDB);
            percentage += calculateMovieTypes(userFromDB);
            percentage += calculateStyles(userFromDB);
            percentage += calculateReligion(userFromDB);

            percentage /= divider;

            percentage = calculatePercentage(percentage);

            ClassifiedUser classifiedUser = new ClassifiedUser(userFromDB.getAvatar(), userFromDB.getName(), userFromDB.getUsername(), userFromDB.getDateOfBirth(), userFromDB.getCity(), userFromDB.isFake(), percentage);
            fitUsers.add(classifiedUser);
        }

        Collections.sort(fitUsers, Comparator.comparingInt(o -> (int) o.getFitPercentage()));

        return fitUsers;
    }
}
