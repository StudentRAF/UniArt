package rs.raf.student.uniart.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import rs.raf.student.uniart.entity.Organization;
import rs.raf.student.uniart.entity.Project;
import rs.raf.student.uniart.entity.Character;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CharacterSpecification {

    @RequiredArgsConstructor
    public static class FindAll implements Specification<Character> {

        private final String project;
        private final String organization;

        public static FindAll of(String project, String organization) {
            return new FindAll(project, organization);
        }

        @Override
        public Predicate toPredicate(Root<Character> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            Predicate predicate = criteriaBuilder.conjunction();

            if (project == null || organization == null)
                return predicate;

            Path<Project>      projectPath      = root.join(Project.Meta.Table.NAME, JoinType.INNER)
                                                      .get(Project.Meta.Column.NAME);
            Path<Organization> organizationPath = root.join(Project.Meta.Table.NAME)
                                                      .join(Organization.Meta.Table.NAME, JoinType.INNER)
                                                      .get(Organization.Meta.Column.NAME);

            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(projectPath, project));
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(organizationPath, organization));

            return predicate;
        }

    }

    @RequiredArgsConstructor
    public static class Find implements Specification<Character> {

        private final String character;
        private final String project;
        private final String organization;

        public static Find of(String character, String project, String organization) {
            return new Find(character, project, organization);
        }

        @Override
        public Predicate toPredicate(Root<Character> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            Predicate predicate = criteriaBuilder.conjunction();

            if (character == null || project == null || organization == null)
                return predicate;

            Path<Character>    characterPath    = root.get(Character.Meta.Column.NAME);
            Path<Project>      projectPath      = root.join(Project.Meta.Table.NAME, JoinType.INNER)
                                                      .get(Project.Meta.Column.NAME);
            Path<Organization> organizationPath = root.join(Project.Meta.Table.NAME)
                                                      .join(Organization.Meta.Table.NAME, JoinType.INNER)
                                                      .get(Organization.Meta.Column.NAME);

            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(characterPath, character));
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(projectPath, project));
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(organizationPath, organization));

            return predicate;
        }

    }

}
