package pl.datingSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.datingSite.model.ActivationCode;

import javax.transaction.Transactional;

public interface ActivationCodeRepository extends JpaRepository<ActivationCode, Long> {
    @Transactional
    @Modifying
    @Query("delete from ActivationCode code where code.email = :email")
    public void deleteByEmail(@Param("email") String email);
}
