package rs.raf.student.uniart.service.organization;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.raf.student.uniart.dto.organization.OrganizationCreateDto;
import rs.raf.student.uniart.dto.organization.OrganizationGetDto;
import rs.raf.student.uniart.dto.organization.OrganizationUpdateDto;
import rs.raf.student.uniart.exception.UniArtException;

public interface IOrganizationService {

    Page<OrganizationGetDto> findAll(Pageable pageable) throws UniArtException;

    OrganizationGetDto findByName(String name) throws UniArtException;

    OrganizationGetDto create(OrganizationCreateDto createDto) throws UniArtException;

    OrganizationGetDto update(OrganizationUpdateDto updateDto) throws UniArtException;

}
