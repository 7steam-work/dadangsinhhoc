package site.dadangsinhhoc.services.ho;

import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.HoModel;

public interface IHoService {
    boolean existById(Long id);

    ResponseObject findById(Long id);

    ResponseObject getAllHo();

    ResponseObject getAllHoByLoai(Boolean loai);

    ResponseObject searchByNameOrNameLatinh(String keyword, Boolean loai, int page, int size);

    boolean checkDuplicateNameLatinh(String nameLatinh, boolean isAddingNew, String oldNameLatinh);

    ResponseObject countAllHo();

    ResponseObject saveHo(HoModel hoModel);

   ResponseObject updateHo(Long id, HoModel hoModel);

    ResponseObject deleteByIdHo(Long id);

}
