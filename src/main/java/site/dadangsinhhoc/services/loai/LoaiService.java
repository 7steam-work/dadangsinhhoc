package site.dadangsinhhoc.services.loai;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.models.LoaiImageModel;
import site.dadangsinhhoc.models.LoaiModel;
import site.dadangsinhhoc.repositories.LoaiImageRepository;
import site.dadangsinhhoc.repositories.LoaiRepository;
import org.springframework.data.jpa.domain.Specification;
import site.dadangsinhhoc.services.helper.HelperService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class LoaiService implements ILoaiService {
    private final LoaiRepository loaiRepository;
    private final LoaiImageRepository loaiImageRepository;
    private final HelperService helperService;

    @Override
    public boolean existById(Long id) {
        return loaiRepository.existsById(id);
    }

    @Override
    public ResponseObject findById(Long id) {
        return loaiRepository.findById(id)
                .map(ResponseObject::success)
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
    }

    @Override
    public ResponseObject getAllLoai() {
        try {
            List<LoaiModel> loaiModels = loaiRepository.findAll();
            return ResponseObject.success(loaiModels);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while fetching all Loai");
        }
    }

    @Override
    public ResponseObject getAllLoaiByLoai(Boolean loai) {
        try {
            // Sử dụng Specification để tìm kiếm theo điều kiện loai = false
            Specification<LoaiModel> spec = (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("loai"), loai);
            List<LoaiModel> loaiModels = loaiRepository.findAll(spec);
            return ResponseObject.success(loaiModels);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while fetching all Loai by Loai");
        }
    }

    @Override
    public ResponseObject countAllLoai() {
        long quantity = loaiRepository.count();
        return ResponseObject.success(quantity);
    }

    @Override
    public ResponseObject saveLoai(LoaiModel model, MultipartFile thumbnail, List<MultipartFile> images) {
        try {
            if (model.getName() == null || model.getName().isEmpty()) {
                log.error("Name for `TABLE_Loai` is null or empty");
                return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), ErrorCode.BAD_REQUEST.getMessage());
            }
            ResponseObject validationResult = helperService.validateAndStoreImage(thumbnail);
            if (validationResult.getCode() != 200) {
                return validationResult;
            }
            String file = helperService.StoreFile(thumbnail);
            model.setId(null);
            model.setThumbnail(file);
            LoaiModel savedLoai = loaiRepository.save(model);

            if(savedLoai != null) {
                for (MultipartFile image : images) {
                    validationResult = helperService.validateAndStoreImage(image);
                    if (validationResult.getCode() != 200) {
                        return validationResult;
                    }
                    String uniqueFileName = helperService.StoreFile(image);

                    LoaiImageModel.builder()
                            .loai(savedLoai)
                            .fileName(uniqueFileName)
                            .build();
                }
            }
            return ResponseObject.success(savedLoai);
        } catch (Exception e) {
            log.error("An error occurred while saving: {}", e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    public ResponseObject saveImageLoai(Long idLoai, List<MultipartFile> images) {
        try {
            if (idLoai == null) {
                return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
            }
            if (findById(idLoai) == null) {
                return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), ErrorCode.BAD_REQUEST.getMessage());
            }
            LoaiModel loaiModel = loaiRepository.findById(idLoai)
                    .orElseThrow(() -> new RuntimeException("Loai not found with id: " + idLoai));
            List<MultipartFile> files = images;
            files = files == null ? new ArrayList<MultipartFile>() : files;

            List<LoaiImageModel> loaiImageModels = new ArrayList<>();
            int count = 1;
            for (MultipartFile file: files){
                count = count + 1;
                if(file.getSize() == 0){
                    continue;
                }
                if(file != null && !file.isEmpty()) {
                    if (file.getSize() > 10 * 1024 * 1024) {
                        log.error("Image size exceeds the maximum allowed size of 10MB");
                        return ResponseObject.error(ErrorCode.PAYLOAD_TOO_LARGE.getCode(), ErrorCode.PAYLOAD_TOO_LARGE.getMessage());
                    }

                    String contentType = file.getContentType();

                    if(contentType == null || !contentType.startsWith("image/")) {
                        log.error("Unsupported media type for image upload");
                        return ResponseObject.error(ErrorCode.UNSUPPORTED_MEDIA_TYPE.getCode(), ErrorCode.UNSUPPORTED_MEDIA_TYPE.getMessage());
                    }

                    String uniqueFileName = helperService.StoreFile(file);
                    log.info("Image {} uploaded successfully with name: {}", count, uniqueFileName);
                    
                    LoaiImageModel model = LoaiImageModel.builder()
                        .loai(loaiModel)
                        .fileName(uniqueFileName)
                        .build();

                    LoaiImageModel loaiImageModel = loaiImageRepository.save(model);
                    loaiImageModels.add(loaiImageModel);

                }
            }
            return ResponseObject.success("Add Image Success", loaiImageModels);

        } catch (Exception e) {
            log.error("An error occurred while saving images: {}", e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
        }
    }



    @Override
    public ResponseObject updateLoai(Long id, LoaiModel loaiModel) {
        if (loaiModel.getName() == null || loaiModel.getName().isEmpty()) {
            return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), "Name for `TABLE_Loai` is null or empty");
        }
        if (!loaiRepository.existsById(loaiModel.getId())) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Loai with id: " + loaiModel.getId());
        }
        return loaiRepository.findById(id)
                .map(existingLoai -> {
                    existingLoai.setName(loaiModel.getName());
                    existingLoai.setNameLatinh(loaiModel.getNameLatinh());
                    existingLoai.setLoai(loaiModel.getLoai());
                    existingLoai.setHo(loaiModel.getHo());
                    existingLoai.setRPH(loaiModel.getRPH());
                    existingLoai.setDacDiem(loaiModel.getDacDiem());
                    existingLoai.setGiaTriSuDung(loaiModel.getGiaTriSuDung());
                    existingLoai.setPhanBo(loaiModel.getPhanBo());
                    existingLoai.setNguonTaiLieu(loaiModel.getNguonTaiLieu());
                    existingLoai.setThumbnail(loaiModel.getThumbnail());
                    existingLoai.setMucDoBaoTonIUCN(loaiModel.getMucDoBaoTonIUCN());
                    existingLoai.setMucDoBaoTonSDVN(loaiModel.getMucDoBaoTonSDVN());
                    existingLoai.setMucDoBaoTonNDCP(loaiModel.getMucDoBaoTonNDCP());
                    existingLoai.setMucDoBaoTonND64CP(loaiModel.getMucDoBaoTonND64CP());
                    existingLoai.setStatus(loaiModel.getStatus());
                    existingLoai.setUpdatedAt(loaiModel.getUpdatedAt());
                    existingLoai.setUpdatedBy(loaiModel.getUpdatedBy());
                    return ResponseObject.success(loaiRepository.save(existingLoai));
                })
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Loai with id: " + id));
    }

    @Override
    public ResponseObject deleteByIdLoai(Long id) {
        if (!loaiRepository.existsById(id)) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        } else {
            loaiRepository.deleteById(id);
            return ResponseObject.success("Successfully delete record " + id, null);
        }
    }

}
