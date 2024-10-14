package site.dadangsinhhoc.services.lop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.models.LopModel;
import site.dadangsinhhoc.repositories.LopRepository;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class LopService implements ILopService {
    private final LopRepository lopRepository;

    @Override
    public boolean existById(Long id) {
        return lopRepository.existsById(id);
    }

    @Override
    public ResponseObject findLopById(Long id) {
        return lopRepository.findById(id)
                .map(ResponseObject::success)
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
    }

    @Override
    public ResponseObject getAllLop() {
        try {
            List<LopModel> lopModels = lopRepository.findAll();
            return ResponseObject.success(lopModels);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    public ResponseObject getAllLopByLoai(Boolean loai) {
        try {
            // Sử dụng Specification để tìm kiếm theo điều kiện loai = false
            Specification<LopModel> spec = (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("loai"), loai);
            List<LopModel> lopModels = lopRepository.findAll(spec);
            return ResponseObject.success(lopModels);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    public ResponseObject countAllLop() {
        try {
            long quantity = lopRepository.count();
            return ResponseObject.success(quantity);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    public ResponseObject saveLop(LopModel lopModels) {
        if (lopModels.getName() == null || lopModels.getName().isEmpty()) {
            return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), ErrorCode.BAD_REQUEST.getMessage());
        }
        if (lopRepository.existsById(lopModels.getId())) {
            return ResponseObject.error(ErrorCode.CONFLICT.getCode(), ErrorCode.CONFLICT.getMessage());
        }
        lopModels.setId(null);
        LopModel savedLop = lopRepository.save(lopModels);
        return ResponseObject.success(savedLop);
    }

    @Override
    public ResponseObject updateLop(Long id, LopModel lopModel) {
        try {
            if (lopModel.getName() == null || lopModel.getName().isEmpty()) {
                return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), "Name for `TABLE_Lop` is null or empty");
            }
            if (!lopRepository.existsById(lopModel.getId())) {
                return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Lop with id: " + lopModel.getId());
            }
            return lopRepository.findById(id)
                    .map(existingNganh -> {
                        existingNganh.setName(lopModel.getName());
                        existingNganh.setNameLatinh(lopModel.getNameLatinh());
                        existingNganh.setLoai(lopModel.getLoai());
                        existingNganh.setStatus(lopModel.getStatus());
                        existingNganh.setUpdatedAt(lopModel.getUpdatedAt());
                        existingNganh.setUpdatedBy(lopModel.getUpdatedBy());
                        return ResponseObject.success(lopRepository.save(existingNganh));
                    })
                    .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Lop with id: " + id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    public ResponseObject deleteByIdLop(Long id) {
        try {
            if (!lopRepository.existsById(id)) {
                return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
            } else {
                lopRepository.deleteById(id);
                return ResponseObject.success("Successfully delete record " + id, null);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
        }
    }
}
