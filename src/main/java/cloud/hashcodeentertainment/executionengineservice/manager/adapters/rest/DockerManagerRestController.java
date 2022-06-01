package cloud.hashcodeentertainment.executionengineservice.manager.adapters.rest;

import cloud.hashcodeentertainment.executionengineservice.manager.ports.DockerManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("manager")
@RequiredArgsConstructor
public class DockerManagerRestController {

    private final DockerManagerService managerService;
    private final DockerManagerRestMapper restMapper;

    @GetMapping("nodes/info")
    public ResponseEntity<List<DockerNodeResponse>> getAllNodesInfo() {
        var nodes = managerService.getAllNodesOnlyNamesAndStatuses();
        var nodeResponses = restMapper.toRest(nodes);

        return ResponseEntity.ok(nodeResponses);
    }

    @GetMapping("nodes/info/detailed")
    public ResponseEntity<List<DockerNodeResponse>> getAllNodesDetailedInfo() {
        var nodes = managerService.getAllNodesFullInfo();
        var nodeResponses = restMapper.toRest(nodes);

        return ResponseEntity.ok(nodeResponses);
    }
}
