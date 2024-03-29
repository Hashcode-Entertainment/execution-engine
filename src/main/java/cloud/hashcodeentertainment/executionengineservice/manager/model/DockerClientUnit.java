package cloud.hashcodeentertainment.executionengineservice.manager.model;

import cloud.hashcodeentertainment.executionengineservice.manager.exception.DockerClientException;
import cloud.hashcodeentertainment.executionengineservice.util.MessageUtils;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;

public class DockerClientUnit {

    private final String DOCKER_LINUX_LOCALHOST = "unix:///var/run/docker.sock";
    private final String DOCKER_WINDOWS_LOCALHOST = "tcp://localhost:2375";

    public DockerClient getLocalClient() {
        DefaultDockerClientConfig clientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(getLocalHost())
                .build();

        ApacheDockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(clientConfig.getDockerHost())
                .build();

        return DockerClientImpl.getInstance(clientConfig, httpClient);
    }

    public DockerClient getRemoteClient(String address, int port) {
        DefaultDockerClientConfig clientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(getRemoteHost(address, port))
                .build();

        ApacheDockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(clientConfig.getDockerHost())
                .build();

        return DockerClientImpl.getInstance(clientConfig, httpClient);
    }

    private String getLocalHost() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("windows") ? DOCKER_WINDOWS_LOCALHOST : DOCKER_LINUX_LOCALHOST;
    }

    private String getRemoteHost(String address, int port) {
        isAddressValid(address);
        isPortValid(port);

        return "tcp://" + address + ":" + port;
    }

    private void isAddressValid(String address) {
        if (!address.matches("^(?!.*[^0-9.]).*")) {
            throw new DockerClientException(MessageUtils.getMessage("docker.client.invalid.character"));
        }
    }

    private void isPortValid(int port) {
        if (port <= 0 || port >= 65536) {
            throw new DockerClientException(MessageUtils.getMessage("docker.client.invalid.port.number"));
        }
    }
}
