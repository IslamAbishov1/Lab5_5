package repository;

import entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by their username.
     *
     * @param username Username to search
     * @return User object or null if not found
     */
    User findByUsername(String username);

    /**
     * Fetch all users with pagination.
     *
     * @param pageable Pagination information
     * @return Page of users
     */
    Page<User> findAll(Pageable pageable);
}
