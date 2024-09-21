package rs.raf.student.uniart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.raf.student.uniart.entity.Organization;

import java.util.Optional;

@Repository
public interface IOrganizationRepository extends JpaRepository<Organization, Long> {

    Optional<Organization> findByName(String name);

}
