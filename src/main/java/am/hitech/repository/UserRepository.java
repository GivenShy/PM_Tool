package am.hitech.repository;

import am.hitech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsByEmail(String email);


    @Query("select u from User u where u.status = 'Active'")
    List<User> getActiveUsers();

    @Query(nativeQuery = true,value = "SELECT * from `user` where " +
            "if(?1 is not null, lower(first_name) like lower(concat(?1,'%')), TRUE)" +
            "and if(?2 is not null, lower(last_name) like lower(concat(?2,'%')), TRUE)"+
            "and if(?3 is not null, lower(email) like lower(concat(?3,'%')),TRUE)")
    List<User> search( String firsName, String lastName,  String email);
    Optional<User> findByEmail(String email);
}
