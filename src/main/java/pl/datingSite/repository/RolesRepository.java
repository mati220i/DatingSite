package pl.datingSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.datingSite.model.Roles;

import java.util.List;

public interface RolesRepository extends JpaRepository<Roles, Long> {

    @Query(value = "select r from Roles  r where r.username = :username")
    public List<Roles> getRoles(@Param("username") String username);
}
