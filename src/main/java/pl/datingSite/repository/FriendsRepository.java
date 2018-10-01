package pl.datingSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.datingSite.model.Friends;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Long> {

}
