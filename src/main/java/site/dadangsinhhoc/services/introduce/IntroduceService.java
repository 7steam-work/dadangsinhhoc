package site.dadangsinhhoc.services.introduce;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.models.IntroduceModel;
import site.dadangsinhhoc.repositories.IntroduceRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class IntroduceService implements IIntroduceService {
    private final IntroduceRepository introduceRepository;

    @Override
    public boolean existById(Long id) {
        return introduceRepository.existsById(id);
    }

    @Override
    public ResponseObject findById(Long id) {
        return introduceRepository.findById(id)
                .map(ResponseObject::success)
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
    }

    @Override
    public ResponseObject getAllIntroduce() {
        try {
            List<IntroduceModel> introduceModels = introduceRepository.findAll();
            return ResponseObject.success(introduceModels);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while fetching all Introduce");
        }
    }

    @Override
    public ResponseObject countAllIntroduce() {
        try {
            long quantity = introduceRepository.count();
            return ResponseObject.success(quantity);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
        }
    }

    @Override
    public ResponseObject saveIntroduce(IntroduceModel introduceModel) {
        if (introduceRepository.existsById(introduceModel.getId())) {
            log.error(ErrorCode.CONFLICT.getMessage());
            return ResponseObject.error(ErrorCode.CONFLICT.getCode(), ErrorCode.CONFLICT.getMessage());
        }
        IntroduceModel savedIntroduce = introduceRepository.save(introduceModel);
        return ResponseObject.success(savedIntroduce);
    }

    @Override
    public ResponseObject updateIntroduce(Long id, IntroduceModel introduceModel) {
        if (!introduceRepository.existsById(introduceModel.getId())) {
            log.error(ErrorCode.NOT_FOUND.getMessage());
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Introduce with id: " + introduceModel.getId());
        }
        return introduceRepository.findById(id)
                .map(existingIntroduce -> {
                    existingIntroduce.setContent(introduceModel.getContent());
                    existingIntroduce.setUpdatedAt(introduceModel.getUpdatedAt());
                    existingIntroduce.setUpdatedBy(introduceModel.getUpdatedBy());
                    return ResponseObject.success(introduceRepository.save(existingIntroduce));
                })
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Introduce with id: " + id));
    }

    @Override
    public ResponseObject deleteByIdIntroduce(Long id) {
        if (!introduceRepository.existsById(id)) {
            log.error(ErrorCode.NOT_FOUND.getMessage());
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        } else {
            introduceRepository.deleteById(id);
            return ResponseObject.success("Successfully delete record " + id, null);
        }
    }

}
