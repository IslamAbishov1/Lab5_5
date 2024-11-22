package service;

import entity.Task;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.TaskRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Page<Task> getTasksByUser(User user, Pageable pageable) {
        return taskRepository.findByUser(user, pageable);
    }

    public Page<Task> searchTasks(User user, String title, Pageable pageable) {
        return taskRepository.findByUserAndTitleContaining(user, title, pageable);
    }

    public Page<Task> filterTasksByCategory(User user, Long categoryId, Pageable pageable) {
        return taskRepository.findByUserAndCategoryId(user, categoryId, pageable);
    }
}
