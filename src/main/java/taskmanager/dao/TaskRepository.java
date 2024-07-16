package taskmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taskmanager.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}