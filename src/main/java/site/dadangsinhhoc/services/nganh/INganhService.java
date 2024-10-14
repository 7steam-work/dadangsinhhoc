package site.dadangsinhhoc.services.nganh;

import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.NganhModel;

public interface INganhService {

     boolean existById(Long id);

     ResponseObject findById(Long id);

     ResponseObject getAllNganh();

     ResponseObject getAllNganhByLoai(Boolean loai);

     ResponseObject countAllNganh();

     ResponseObject saveNganh(NganhModel nganhModel);

     ResponseObject updateNganh(Long id, NganhModel nganhModel);

     ResponseObject deleteByIdNganh(Long id);

}
