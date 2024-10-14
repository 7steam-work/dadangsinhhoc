package site.dadangsinhhoc.services.ho;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.models.HoModel;
import site.dadangsinhhoc.repositories.HoRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class HoService implements IHoService {
    private final HoRepository hoRepository;

    @Override
    public boolean existById(Long id) {
        return hoRepository.existsById(id);
    }

    @Override
    public ResponseObject findById(Long id) {
        return hoRepository.findById(id)
                .map(ResponseObject::success)
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
    }

    @Override
    public ResponseObject getAllHo() {
        try {
            List<HoModel> hoModels = hoRepository.findAll();
            return ResponseObject.success(hoModels);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while fetching all Ho");
        }
    }

    @Override
    public ResponseObject getAllHoByLoai(Boolean loai) {
        try {
            Specification<HoModel> spec = (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("loai"), loai);
            List<HoModel> hoModels = hoRepository.findAll(spec);
            return ResponseObject.success(hoModels);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while fetching all Ho by Loai");
        }
    }

    @Override
    public ResponseObject searchByNameOrNameLatinh(String keyword, Boolean loai, int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<HoModel> hoModels = hoRepository.searchByNameOrNameLatinh(keyword, loai, pageable);
            return ResponseObject.success(hoModels);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while searching Ho");
        }
    }

    @Override
    public boolean checkDuplicateNameLatinh(String nameLatinh, boolean isAddingNew, String oldNameLatinh) {
        try {
            if (isAddingNew) {
                // Nếu đang thêm mới, chỉ cần kiểm tra xem tên Latin đã tồn tại hay chưa
                return hoRepository.existsByNameLatinh(nameLatinh);
            } else {
                // Nếu đang chỉnh sửa, kiểm tra xem tên Latin mới có trùng với tên Latin khác (ngoại trừ tên Latin cũ)
                return !nameLatinh.equalsIgnoreCase(oldNameLatinh) && hoRepository.existsByNameLatinh(nameLatinh);
            }
        } catch (Exception e) {
            // Ghi log lỗi để dễ dàng gỡ lỗi sau này
            log.error("Lỗi khi kiểm tra trùng tên Latin: {}", e);

            // Trong trường hợp này, chúng ta tạm thời trả về true để tránh lỗi ở frontend
            // Tuy nhiên, bạn nên xem xét cách xử lý lỗi phù hợp với ứng dụng của bạn
            return true;
        }

    }

    @Override
    public ResponseObject countAllHo() {
        long quantity = hoRepository.count();
        return ResponseObject.success(quantity);
    }

    @Override
    public ResponseObject saveHo(HoModel hoModel) {
        if (hoModel.getName() == null || hoModel.getName().isEmpty()) {
            return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), ErrorCode.BAD_REQUEST.getMessage());
        }
        if (hoRepository.existsById(hoModel.getId())) {
            return ResponseObject.error(ErrorCode.CONFLICT.getCode(), ErrorCode.CONFLICT.getMessage());
        }
        hoModel.setId(null);
        HoModel savedHo = hoRepository.save(hoModel);
        return ResponseObject.success(savedHo);
    }

    @Override
    public ResponseObject updateHo(Long id, HoModel hoModel) {
        if (hoModel.getName() == null || hoModel.getName().isEmpty()) {
            return ResponseObject.error(ErrorCode.BAD_REQUEST.getCode(), "Name for `TABLE_Ho` is null or empty");
        }
        if (!hoRepository.existsById(id)) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Ho with id: " + id);
        }
        return hoRepository.findById(id)
                .map(existingHo -> {
                    existingHo.setName(hoModel.getName());
                    existingHo.setNameLatinh(hoModel.getNameLatinh());
                    existingHo.setLoai(hoModel.getLoai());
                    existingHo.setBo(hoModel.getBo());
                    existingHo.setStatus(hoModel.getStatus());
                    existingHo.setUpdatedAt(hoModel.getUpdatedAt());
                    existingHo.setUpdatedBy(hoModel.getUpdatedBy());
                    return ResponseObject.success(hoRepository.save(existingHo));
                })
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Ho with id: " + id));
    }

    @Override
    public ResponseObject deleteByIdHo(Long id) {
        if (!hoRepository.existsById(id)) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        } else {
            hoRepository.deleteById(id);
            return ResponseObject.success("Successfully delete record " + id, null);
        }
    }

}
