package site.dadangsinhhoc.services.nganh;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.models.NganhModel;
import site.dadangsinhhoc.repositories.NganhRepository;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class NganhService implements INganhService{
    private final NganhRepository nganhRepository;

    @Override
    public boolean existById(Long id) {
        return nganhRepository.existsById(id);
    }

    @Override
    public ResponseObject findById(Long id) {
        try {
            return nganhRepository.findById(id)
                    .map(ResponseObject::success)
                    .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    public ResponseObject getAllNganh() {
        try {
            List<NganhModel> nganhModels = nganhRepository.findAll();
            return ResponseObject.success(nganhModels);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while fetching all Nganh");
        }
    }

    @Override
    public ResponseObject getAllNganhByLoai(Boolean loai) {
        try {
            // Sử dụng Specification để tìm kiếm theo điều kiện loai = false
            Specification<NganhModel> spec = (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("loai"), loai);
            List<NganhModel> nganhModels = nganhRepository.findAll(spec);
            return ResponseObject.success(nganhModels);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while fetching all Nganh by Loai");
        }
    }

    @Override
    public ResponseObject countAllNganh() {
        try {
            long quantity = nganhRepository.count();
            return ResponseObject.success(quantity);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    public ResponseObject saveNganh(NganhModel nganhModel) {
        try {
            if (nganhModel.getName() == null || nganhModel.getName().isEmpty()) {
                return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), ErrorCode.BAD_REQUEST.getMessage());
            }
            if (nganhRepository.existsById(nganhModel.getId())) {
                return ResponseObject.error(ErrorCode.CONFLICT.getCode(), ErrorCode.CONFLICT.getMessage());
            }
            nganhModel.setId(null);
            NganhModel record = nganhRepository.save(nganhModel);
            return ResponseObject.success(record);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    public ResponseObject updateNganh(Long id, NganhModel nganhModel) {
        try {
            if (nganhModel.getName() == null || nganhModel.getName().isEmpty()) {
                return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), "Name for `TABLE_Nganh` is null or empty");
            }
            if (!nganhRepository.existsById(nganhModel.getId())) {
                return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Nganh with id: " + nganhModel.getId());
            }
            return nganhRepository.findById(id)
                    .map(existingNganh -> {
                        existingNganh.setName(nganhModel.getName());
                        existingNganh.setNameLatinh(nganhModel.getNameLatinh());
                        existingNganh.setLoai(nganhModel.getLoai());
                        existingNganh.setStatus(nganhModel.getStatus());
                        existingNganh.setUpdatedAt(nganhModel.getUpdatedAt());
                        existingNganh.setUpdatedBy(nganhModel.getUpdatedBy());
                        return ResponseObject.success(nganhRepository.save(existingNganh));
                    })
                    .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Nganh with id: " + id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    public ResponseObject deleteByIdNganh(Long id) {
        try {
            if (!nganhRepository.existsById(id)) {
                return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
            } else {
                nganhRepository.deleteById(id);
                return ResponseObject.success("Successfully delete record " + id, null);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
        }
    }

}
