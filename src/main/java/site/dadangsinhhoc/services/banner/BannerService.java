package site.dadangsinhhoc.services.banner;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.models.BannerModel;
import site.dadangsinhhoc.repositories.BannerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BannerService implements IBannerService {
    private final BannerRepository bannerRepository;

    @Override
    public boolean existById(Long id) {
        return bannerRepository.existsById(id);
    }

    @Override
    public ResponseObject findById(Long id) throws Throwable{
        return bannerRepository.findById(id)
                .map(ResponseObject::success)
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
    }

    @Override
    public ResponseObject getAllBanner() {
        try {
            List<BannerModel> bannerModels = bannerRepository.findAll();
            return ResponseObject.success(bannerModels);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while fetching all Banner");
        }
    }

    @Override
    public ResponseObject countAllBanner() {
        long quantity = bannerRepository.count();
        return ResponseObject.success(quantity);
    }

    @Override
    public ResponseObject saveBanner(BannerModel bannerModel) {
        if (bannerRepository.existsById(bannerModel.getId())) {
            return ResponseObject.error(ErrorCode.CONFLICT.getCode(), ErrorCode.CONFLICT.getMessage());
        }
        bannerModel.setId(null);
        BannerModel savedBanner = bannerRepository.save(bannerModel);
        return ResponseObject.success(savedBanner);
    }

    @Override
    public ResponseObject updateBanner(Long id, BannerModel bannerModel) {
        if (!bannerRepository.existsById(bannerModel.getId())) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Banner with id: " + bannerModel.getId());
        }
        return bannerRepository.findById(id)
                .map(existingBanner -> {
                    existingBanner.setImage(bannerModel.getImage());
                    existingBanner.setStatus(bannerModel.getStatus());
                    existingBanner.setUpdatedAt(bannerModel.getUpdatedAt());
                    existingBanner.setUpdatedBy(bannerModel.getUpdatedBy());
                    return ResponseObject.success(bannerRepository.save(existingBanner));
                })
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find Banner with id: " + id));
    }

    @Override
    public ResponseObject deleteByIdBanner(Long id) {
        if (!bannerRepository.existsById(id)) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        } else {
            bannerRepository.deleteById(id);
            return ResponseObject.success("Successfully delete record " + id, null);
        }
    }

}
