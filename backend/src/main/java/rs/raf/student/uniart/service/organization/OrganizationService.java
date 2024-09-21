package rs.raf.student.uniart.service.organization;

import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.raf.student.uniart.dto.organization.OrganizationCreateDto;
import rs.raf.student.uniart.dto.organization.OrganizationGetDto;
import rs.raf.student.uniart.dto.organization.OrganizationUpdateDto;
import rs.raf.student.uniart.entity.Organization;
import rs.raf.student.uniart.exception.UniArtException;
import rs.raf.student.uniart.mapper.OrganizationMapper;
import rs.raf.student.uniart.repository.IOrganizationRepository;

import static rs.raf.student.uniart.exception.ExceptionType.FIND_ORGANIZATION_NOT_FOUND_NAME;
import static rs.raf.student.uniart.exception.ExceptionType.UPDATE_ORGANIZATION_NOT_FOUND_NAME;

@Service
@RequiredArgsConstructor
@ExtensionMethod({OrganizationMapper.class})
public class OrganizationService implements IOrganizationService {

    private final IOrganizationRepository repository;

    @Override
    public Page<OrganizationGetDto> findAll(Pageable pageable) throws UniArtException {
        Page<Organization> organizations = repository.findAll(pageable);

        return organizations.map(organization -> organization.mapDto());
    }

    @Override
    public OrganizationGetDto findByName(String name) throws UniArtException {
        return repository.findByName(name)
                         .orElseThrow(() -> new UniArtException(FIND_ORGANIZATION_NOT_FOUND_NAME,
                                                                name))
                         .mapDto();
    }

    @Override
    public OrganizationGetDto create(OrganizationCreateDto createDto) throws UniArtException {
        return repository.save(createDto.mapEntity())
                         .mapDto();
    }

    @Override
    public OrganizationGetDto update(OrganizationUpdateDto updateDto) throws UniArtException {
        Organization organization = repository.findByName(updateDto.oldName())
                                              .orElseThrow(() -> new UniArtException(UPDATE_ORGANIZATION_NOT_FOUND_NAME,
                                                                                     updateDto.oldName()));

        return repository.save(organization.map(updateDto))
                         .mapDto();
    }

}
