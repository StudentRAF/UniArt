package rs.raf.student.uniart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.raf.student.uniart.entity.Project;

import java.util.Optional;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByNameAndOrganizationName(String name, String organization);

    Page<Project> findAllByOrganizationName(String organization, Pageable pageable);

}
