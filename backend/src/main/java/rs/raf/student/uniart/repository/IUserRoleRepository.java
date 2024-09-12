package rs.raf.student.uniart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.raf.student.uniart.entity.UserRoleEntity;

import java.util.Optional;

@Repository
public interface IUserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    Optional<UserRoleEntity> findByName(String name);

}
