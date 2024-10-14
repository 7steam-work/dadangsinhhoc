package site.dadangsinhhoc.services.lop;

import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.LopModel;

public interface ILopService {
     boolean existById(Long id);

     ResponseObject findLopById(Long id);

     ResponseObject getAllLop();

     ResponseObject getAllLopByLoai(Boolean loai);

     ResponseObject countAllLop();

     ResponseObject saveLop(LopModel lopModels);

     ResponseObject updateLop(Long id, LopModel lopModel);

     ResponseObject deleteByIdLop(Long id);

}
