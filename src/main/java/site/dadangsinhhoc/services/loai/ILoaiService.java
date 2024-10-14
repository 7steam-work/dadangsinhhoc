package site.dadangsinhhoc.services.loai;

import org.springframework.web.multipart.MultipartFile;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.LoaiModel;
import java.util.List;

public interface ILoaiService {

    boolean existById(Long id);

    ResponseObject findById(Long id);

    ResponseObject getAllLoai();

    ResponseObject getAllLoaiByLoai(Boolean loai);

    ResponseObject countAllLoai();

    ResponseObject saveLoai(LoaiModel model, MultipartFile thumbnail, List<MultipartFile> images);

    ResponseObject saveImageLoai(Long idLoai, List<MultipartFile> images);

    ResponseObject updateLoai(Long id, LoaiModel loaiModel);

    ResponseObject deleteByIdLoai(Long id);
}
