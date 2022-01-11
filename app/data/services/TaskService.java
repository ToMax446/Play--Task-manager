package data.services;

import com.google.inject.Inject;
import data.domain.TasksDAO;
import data.domain.TasksDO;
import data.dto.TasksDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class TaskService {
    @Inject
    private TasksDAO tasksDAO;

    public List<TasksDTO> getAll() {
        List<TasksDO> tasksDOList = tasksDAO.find();
        return tasksDOList.stream().map(domain -> TasksMapper.toTransfer(domain)).collect(Collectors.toList());
    }

    public Optional<TasksDTO> getById(String id) {
        TasksDO tasksDO = tasksDAO.find(id);
        if (tasksDO == null) {
            return Optional.empty();
        }

        return Optional.of(TasksMapper.toTransfer(tasksDO));
    }

    public TasksDTO create(TasksDTO tasksDTO) {
        TasksDO tasksDO = TasksMapper.fromTransfer(tasksDTO);
        tasksDO = tasksDAO.create(tasksDO);
        return TasksMapper.toTransfer(tasksDO);
    }

    public Optional<TasksDTO> update(TasksDTO tasksDTO, String id) {
        TasksDO fromDb = tasksDAO.find(id);
        if (fromDb == null) {
            return Optional.empty();
        }

        fromDb.setTitle(tasksDTO.getTitle());
        fromDb.setDetails(tasksDTO.getDetails());
        fromDb.setStatus(tasksDTO.getStatus());
        fromDb.setOwnerId(tasksDTO.getOwnerId());
        fromDb.setDueDate(tasksDTO.getDueDate());

        return Optional.of(TasksMapper.toTransfer(fromDb));
    }

    public void delete(String id) {
        tasksDAO.delete(id);
    }
}
