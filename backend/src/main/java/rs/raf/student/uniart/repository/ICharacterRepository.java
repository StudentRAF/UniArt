package rs.raf.student.uniart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import rs.raf.student.uniart.entity.Character;

@Repository
public interface ICharacterRepository extends JpaRepository<Character, Long>, JpaSpecificationExecutor<Character> {

}
