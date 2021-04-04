package cmndmanager.cmndmanager;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CMNDRepository extends JpaRepository<CMNDModel, String> {

}
