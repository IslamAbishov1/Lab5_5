package repository;

import entity.Task;
import entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByUserAndTitleContaining(User user, String title, Pageable pageable);
    Page<Task> findByUserAndCategoryId(User user, Long categoryId, Pageable pageable);
    Page<Task> findByUser(User user, Pageable pageable);
}
