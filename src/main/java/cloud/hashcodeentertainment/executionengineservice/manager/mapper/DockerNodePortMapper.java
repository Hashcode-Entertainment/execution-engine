package cloud.hashcodeentertainment.executionengineservice.manager.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DockerNodePortMapper {

    default String toText(int port) {
        if (port == 0) {
            return null;
        }
        return String.valueOf(port);
    }
}
