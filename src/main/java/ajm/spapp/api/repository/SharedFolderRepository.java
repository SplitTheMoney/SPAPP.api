package ajm.spapp.api.repository;

import ajm.spapp.api.model.SharedFolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharedFolderRepository extends JpaRepository<SharedFolder, Long> {
}
