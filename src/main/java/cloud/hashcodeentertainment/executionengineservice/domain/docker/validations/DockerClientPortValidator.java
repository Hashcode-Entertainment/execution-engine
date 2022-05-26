package cloud.hashcodeentertainment.executionengineservice.domain.docker.validations;

import cloud.hashcodeentertainment.executionengineservice.domain.docker.utils.MessageUtils;
import cloud.hashcodeentertainment.executionengineservice.domain.docker.utils.ValidationUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DockerClientPortValidator {

    public void validate(int port){
        List<String> errors = ValidationUtils.combine(validatePortInDockerClient(port));
    }

    private List<String> validatePortInDockerClient(int port){
        List<String> errors = new ArrayList<>();
        if (port <= 0 || port >= 65536) errors.add(MessageUtils.getMessage("validation.docker.client.port"));
        return errors;
    }
}