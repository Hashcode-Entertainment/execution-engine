package cloud.hashcodeentertainment.executionengineservice.taks.ports;

import cloud.hashcodeentertainment.executionengineservice.taks.domain.TaskCreate;

public interface TaskService {

    Long createTask(TaskCreate taskCreate);

    void runTask();

    void getExecutionResult();

    void getTaksOutput();

    void getTaskHistory();
}
