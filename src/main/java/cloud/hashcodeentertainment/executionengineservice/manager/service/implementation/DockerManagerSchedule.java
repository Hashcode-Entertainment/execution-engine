package cloud.hashcodeentertainment.executionengineservice.manager.service.implementation;

import cloud.hashcodeentertainment.executionengineservice.manager.service.DockerManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class DockerManagerSchedule {

    private final DockerManagerService managerService;

    @Scheduled(initialDelay = 10, fixedRate = 60, timeUnit = TimeUnit.SECONDS)
    private void updateDockerNodesStatuses() {
        managerService.updateDockerClientsStatuses();
    }
}
