package ajm.spapp.api.service;

import ajm.spapp.api.model.SharedFolder;

import java.util.List;
import java.util.Optional;

public interface SharedFolderService {

    List<SharedFolder> getAllFolders();

    Optional<SharedFolder> getFolderById(Long id);

    SharedFolder createFolder(SharedFolder folder);

    SharedFolder updateFolder(Long id, SharedFolder folder);

    void deleteFolder(Long id);
}
