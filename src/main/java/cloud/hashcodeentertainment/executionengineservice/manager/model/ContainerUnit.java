package cloud.hashcodeentertainment.executionengineservice.manager.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerUnit {
    private String id;
    private String status;
    private Long exitCode;
    private boolean isRunning;
}
