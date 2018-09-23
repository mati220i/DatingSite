package pl.datingSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import pl.datingSite.enums.*;
import pl.datingSite.model.City;
import pl.datingSite.model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username = :username")
    public User getUserByUserName(@Param("username")String username);

    @Query("select u.username from User u where u.username = :username ")
    public String checkIfExistLogin(@Param("username") String username);

    @Query("select u.email from User u where u.email = :email ")
    public String checkIfExistEmail(@Param("email") String email);

    @Query(value = "update User u set u.enabled = 0 where u.username = :username", nativeQuery = false)
    @Modifying
    public void deleteAccount(@Param("username") String username);

    @Query(value = "select u from User u, in(u.appearanceAndCharacter.lookingFor) lf where (u.sex = :sex or :sex is null) and (u.dateOfBirth <= :ageFrom or :ageFrom is null)" +
            "and (u.dateOfBirth >= :ageTo or :ageTo is null) and (u.maritalStatus in (:maritalStatus) or coalesce(:maritalStatus, null) is null)" +
            "and (u.appearanceAndCharacter.figure in (:figure) or coalesce(:figure, null) is null ) " +
            "and (u.appearanceAndCharacter.height > :heightFrom or :heightFrom is null) " +
            "and (u.appearanceAndCharacter.height < :heightTo or :heightTo is null)" +
            "and (u.appearanceAndCharacter.hairColor in (:hairColor) or coalesce(:hairColor, null) is null)" +
            "and (u.appearanceAndCharacter.smoking in (:smoking) or coalesce(:smoking, null) is null)" +
            "and (u.appearanceAndCharacter.alcohol in (:alcohol) or coalesce(:alcohol, null) is null)" +
            "and (u.appearanceAndCharacter.children in (:children) or coalesce(:children, null) is null)" +
            "and (lf in (:lookingFor) or coalesce(:lookingFor, null) is null or lf is null )" +
            "and (u.appearanceAndCharacter.religion in (:religion) or coalesce(:religion, null) is null)")
    public Set<User> getUsers(@Param("sex") String sex, @Param("ageFrom") Date ageFrom, @Param("ageTo") Date ageTo,
                              @Param("maritalStatus") List<MaritalStatus> maritalStatuses, @Param("figure")List<Figure> figures,
                              @Param("heightFrom") Integer heightFrom, @Param("heightTo") Integer heightTo,
                              @Param("hairColor") List<HairColor> hairColors, @Param("smoking")List<Smoking> smokings,
                              @Param("alcohol") List<Alcohol> alcohol, @Param("children") List<Children> children,
                              @Param("lookingFor") List<LookingFor> lookingFor, @Param("religion") List<Religion> religions);

    @Query(value = "select u from User u where (u.sex = :sex or :sex is null) and (u.dateOfBirth <= :ageFrom or :ageFrom is null)" +
            "and (u.dateOfBirth >= :ageTo or :ageTo is null) and (u.maritalStatus in (:maritalStatus) or coalesce(:maritalStatus, null) is null)" +
            "and (u.appearanceAndCharacter.figure in (:figure) or coalesce(:figure, null) is null ) " +
            "and (u.appearanceAndCharacter.height > :heightFrom or :heightFrom is null) " +
            "and (u.appearanceAndCharacter.height < :heightTo or :heightTo is null)" +
            "and (u.appearanceAndCharacter.hairColor in (:hairColor) or coalesce(:hairColor, null) is null)" +
            "and (u.appearanceAndCharacter.smoking in (:smoking) or coalesce(:smoking, null) is null)" +
            "and (u.appearanceAndCharacter.alcohol in (:alcohol) or coalesce(:alcohol, null) is null)" +
            "and (u.appearanceAndCharacter.children in (:children) or coalesce(:children, null) is null)" +
            "and (u.appearanceAndCharacter.religion in (:religion) or coalesce(:religion, null) is null)")
    public Set<User> getUsers(@Param("sex") String sex, @Param("ageFrom") Date ageFrom, @Param("ageTo") Date ageTo,
                              @Param("maritalStatus") List<MaritalStatus> maritalStatuses, @Param("figure")List<Figure> figures,
                              @Param("heightFrom") Integer heightFrom, @Param("heightTo") Integer heightTo,
                              @Param("hairColor") List<HairColor> hairColors, @Param("smoking")List<Smoking> smokings,
                              @Param("alcohol") List<Alcohol> alcohol, @Param("children") List<Children> children,
                              @Param("religion") List<Religion> religions);
}
