package cloud.hashcodeentertainment.executionengineservice.taks.adapters.rest;

import cloud.hashcodeentertainment.executionengineservice.taks.domain.Task;
import cloud.hashcodeentertainment.executionengineservice.taks.domain.TaskCreate;
import cloud.hashcodeentertainment.executionengineservice.taks.domain.TaskResult;
import cloud.hashcodeentertainment.executionengineservice.taks.domain.TaskResultLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskRestMapper {

    TaskCreate toDomainTaskCreate(TaskCreateRequest taskCreateRequest);

    default TaskCreateResponse toRestTaskCreateResponse(Long taskId) {
        return TaskCreateResponse.builder()
                .id(taskId)
                .build();
    }

    default TaskResultResponse toRestTaskResultResponse(TaskResult taskResult) {
        var logs = taskResult.getLogs().stream()
                .map(TaskResultLog::getBody)
                .toList();

        return TaskResultResponse.builder()
                .id(taskResult.getId())
                .timestamp(taskResult.getTimestamp())
                .runStatus(taskResult.getRunStatus())
                .exitCode(taskResult.getExitCode())
                .logs(logs)
                .build();
    }
}
