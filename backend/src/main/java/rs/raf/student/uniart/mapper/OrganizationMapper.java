package rs.raf.student.uniart.mapper;

import rs.raf.student.uniart.dto.organization.OrganizationCreateDto;
import rs.raf.student.uniart.dto.organization.OrganizationGetDto;
import rs.raf.student.uniart.dto.organization.OrganizationUpdateDto;
import rs.raf.student.uniart.entity.Organization;

public class OrganizationMapper {

    public static Organization map(Organization organization, OrganizationUpdateDto updateDto) {
        return organization.setName(updateDto.name());
    }

    public static Organization mapEntity(OrganizationUpdateDto updateDto) {
        return map(new Organization(), updateDto);
    }

    public static Organization map(Organization organization, OrganizationCreateDto createDto) {
        return organization.setName(createDto.name());
    }

    public static Organization mapEntity(OrganizationCreateDto createDto) {
        return map(new Organization(), createDto);
    }

    public static OrganizationGetDto mapDto(Organization organization) {
        return new OrganizationGetDto().setName(organization.name());
    }

}
