package ryan.projectserver.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ryan.projectserver.server.model.Server;

public interface ServerRepo extends JpaRepository<Server, Long> {
    Server findByIpAddress(String ipAddress);
}
