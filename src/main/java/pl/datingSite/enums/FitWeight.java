package pl.datingSite.enums;

public enum FitWeight {
    NONE, VERY_SMALL, SMALL, MEDIUM, LARGE, VERY_LARGE
}

//
//    float percentage, temp;
//
//        while (iterator.hasNext()) {
//                User userFromDB = iterator.next();
//                percentage = 0;
//                temp = 0;
//
//                System.out.println("*****************************************\n" + userFromDB.getName());
//                temp = calculateLocation(userFromDB);
//                percentage += temp;
//                System.out.println("Location: " + temp);
//                System.out.println("Total: " + percentage);
//
//                temp = calculateAge(userFromDB);
//                percentage += temp;
//                System.out.println("Age: " + temp);
//                System.out.println("Total: " + percentage);
//
//                temp = calculateZodiacSign(userFromDB);
//                percentage += temp;
//                System.out.println("Zodiac Sign: " + temp);
//                System.out.println("Total: " + percentage);
//
//                temp = calculateProfession(userFromDB);
//                percentage += temp;
//                System.out.println("Profession: " + temp);
//                System.out.println("Total: " + percentage);
//
//                temp = calculateMaritalStatus(userFromDB);
//                percentage += temp;
//                System.out.println("Marital Status: " + temp);
//                System.out.println("Total: " + percentage);
//
//                temp = calculateEducation(userFromDB);
//                percentage += temp;
//                System.out.println("Education: " + temp);
//                System.out.println("Total: " + percentage);
//
//                temp = calculateInterests(userFromDB);
//                percentage += temp;
//                System.out.println("Interests: " + temp);
//                System.out.println("Total: " + percentage);
//
//                temp = calculateFigure(userFromDB);
//                percentage += temp;
//                System.out.println("Figure: " + temp);
//                System.out.println("Total: " + percentage);
//
//                temp = calculateHeight(userFromDB);
//                percentage += temp;
//                System.out.println("Height: " + temp);
//                System.out.println("Total: " + percentage);
//
//                temp = calculateHairColor(userFromDB);
//                percentage += temp;
//                System.out.println("Hair Color: " + temp);
//                System.out.println("Total: " + percentage);
//
//                temp = calculateEyeColor(userFromDB);
//                percentage += temp;
//                System.out.println("Eye Color: " + temp);
//                System.out.println("Total: " + percentage);
//
//                temp = calculateSmoking(userFromDB);
//                percentage += temp;
//                System.out.println("Smoking: " + temp);
//                System.out.println("Total: " + percentage);
//
//                temp = calculateAlcohol(userFromDB);
//                percentage += temp;
//                System.out.println("Alcohol: " + temp);
//                System.out.println("Total: " + percentage);
//
//                temp = calculateChildren(userFromDB);
//                percentage += temp;
//                System.out.println("Children: " + temp);
//                System.out.println("Total: " + percentage);
//
//                temp = calculateHolidays(userFromDB);
//                percentage += temp;
//                System.out.println("Holidays: " + temp);
//                System.out.println("Total: " + percentage);
//
//                temp = calculateLookingFor(userFromDB);
//                percentage += temp;
//                System.out.println("Looking For: " + temp);
//                System.out.println("Total: " + percentage);
//
//                temp = calculateMovieTypes(userFromDB);
//                percentage += temp;
//                System.out.println("Movie Type: " + temp);
//                System.out.println("Total: " + percentage);
//
//                temp = calculateStyles(userFromDB);
//                percentage += temp;
//                System.out.println("Styles: " + temp);
//                System.out.println("Total: " + percentage);
//
//                temp = calculateReligion(userFromDB);
//                percentage += temp;
//                System.out.println("Religion: " + temp);
//                System.out.println("Total: " + percentage);
//
//                System.out.println("All Percentage: " + percentage);
//                percentage /= 19;
//
//                percentage = calculatePercentage(percentage);
//                System.out.println("Percentage: " + percentage);
//
//                ClassifiedUser classifiedUser = new ClassifiedUser(userFromDB.getAvatar(), userFromDB.getName(), userFromDB.getUsername(), userFromDB.getDateOfBirth(), userFromDB.getCity(), userFromDB.isFake(), percentage);
//                fitUsers.add(classifiedUser);
//                }