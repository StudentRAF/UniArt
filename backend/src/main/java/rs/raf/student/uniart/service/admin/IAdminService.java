package rs.raf.student.uniart.service.admin;

import rs.raf.student.uniart.dto.user.admin.AdminCreateDto;
import rs.raf.student.uniart.dto.user.admin.AdminUpdateDto;
import rs.raf.student.uniart.exception.UniArtException;

public interface IAdminService {

    Object create(AdminCreateDto createDto) throws UniArtException;

    Object update(AdminUpdateDto updateDto) throws UniArtException;

}
