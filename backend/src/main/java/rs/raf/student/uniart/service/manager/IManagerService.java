package rs.raf.student.uniart.service.manager;

import rs.raf.student.uniart.dto.user.manager.ManagerCreateDto;
import rs.raf.student.uniart.dto.user.manager.ManagerUpdateDto;
import rs.raf.student.uniart.exception.UniArtException;

public interface IManagerService {

    Object create(ManagerCreateDto createDto) throws UniArtException;

    Object update(ManagerUpdateDto updateDto) throws UniArtException;

}
