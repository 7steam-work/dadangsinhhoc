package site.dadangsinhhoc.services.helper;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;
import site.dadangsinhhoc.dto.SearchCriteriaDTO;
import site.dadangsinhhoc.dto.response.ResponseObject;
import java.io.File;
import java.io.IOException;

public interface IHelperService {

    ResponseObject SearchLoai(@NotNull SearchCriteriaDTO criteria);

    String StoreFile(@NotNull MultipartFile file) throws IOException;

    ResponseObject validateAndStoreImage(MultipartFile image);

    File selectImage();
}