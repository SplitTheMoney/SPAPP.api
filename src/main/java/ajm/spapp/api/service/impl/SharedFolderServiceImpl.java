package ajm.spapp.api.service.impl;

import ajm.spapp.api.model.AccessRequest;
import ajm.spapp.api.model.RequestStatus;
import ajm.spapp.api.model.SharedFolder;
import ajm.spapp.api.repository.AccessRequestRepository;
import ajm.spapp.api.repository.SharedFolderRepository;
import ajm.spapp.api.service.SharedFolderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SharedFolderServiceImpl implements SharedFolderService {
    private final SharedFolderRepository sharedFolderRepository;
    private final AccessRequestRepository accessRequestRepository;

    public SharedFolderServiceImpl(SharedFolderRepository sharedFolderRepository, AccessRequestRepository accessRequestRepository) {
        this.sharedFolderRepository = sharedFolderRepository;
        this.accessRequestRepository = accessRequestRepository;
    }

    @Override
    public List<SharedFolder> getAllFolders() {
        return sharedFolderRepository.findAll();
    }

    @Override
    public List<SharedFolder> getFoldersByEmployee(Long employeeId) {
        List<AccessRequest> allRequestsByEmployee = this.accessRequestRepository.findByEmployee_Id(employeeId);

        Set<SharedFolder> acceptedFolder = allRequestsByEmployee.stream()
                .filter(accessRequest -> accessRequest.getStatus() == RequestStatus.APPROVED)
                .map(AccessRequest::getFolder)
                .collect(Collectors.toSet());

        return acceptedFolder.stream().toList();
    }

    @Override
    public Optional<SharedFolder> getFolderById(Long id) {
        return sharedFolderRepository.findById(id);
    }

    @Override
    public SharedFolder createFolder(SharedFolder folder) {
        return sharedFolderRepository.save(folder);
    }

    @Override
    public SharedFolder updateFolder(Long id, SharedFolder folder) {
        SharedFolder existingFolder = sharedFolderRepository.findById(id).orElseThrow(() -> new RuntimeException("No folder found with id " + id));
        existingFolder.setName(folder.getName());
        existingFolder.setPath(folder.getPath());
        existingFolder.setDescription(folder.getDescription());

        return sharedFolderRepository.save(existingFolder);


    }

    @Override
    public void deleteFolder(Long id) {
       sharedFolderRepository.deleteById(id);
    }
}
