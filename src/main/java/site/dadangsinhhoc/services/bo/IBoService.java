package site.dadangsinhhoc.services.bo;

import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.BoModel;

public interface IBoService {
    boolean existById(long id);

    ResponseObject findById(long id);

    ResponseObject getAllBo();

    ResponseObject getAllBoByLoai(Boolean loai);

    ResponseObject countAllBo();

    ResponseObject saveBo(BoModel boModel);

    ResponseObject updateBo(BoModel boModel);

    ResponseObject deleteByIdBo(Long id);

}
