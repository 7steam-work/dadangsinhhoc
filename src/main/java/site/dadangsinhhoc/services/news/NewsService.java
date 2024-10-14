package site.dadangsinhhoc.services.news;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.models.NewsModel;
import site.dadangsinhhoc.repositories.NewsRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class NewsService implements INewsService {
    private final NewsRepository newsRepository;

    @Override
    public boolean existById(Long id) {
        return newsRepository.existsById(id);
    }

    @Override
    public ResponseObject findNewsById(Long id) {
        return newsRepository.findById(id)
                .map(ResponseObject::success)
                .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()));
    }

    @Override
    public ResponseObject getAllNews() {
        try {
            List<NewsModel> newsModels = newsRepository.findAll();
            return ResponseObject.success(newsModels);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    public ResponseObject countAllNews() {
        try {
            long quantity = newsRepository.count();
            return ResponseObject.success(quantity);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    public ResponseObject saveNews(NewsModel newsModels) {
        try {
            if (newsRepository.existsById(newsModels.getId())) {
                return ResponseObject.error(ErrorCode.CONFLICT.getCode(), ErrorCode.CONFLICT.getMessage());
            }
            newsModels.setId(null);
            NewsModel savedNews = newsRepository.save(newsModels);
            return ResponseObject.success(savedNews);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    public ResponseObject updateNews(Long id, NewsModel newsModel) {
        try {
            if (!newsRepository.existsById(newsModel.getId())) {
                return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find News with id: " + newsModel.getId());
            }
            return newsRepository.findById(id)
                    .map(existingNganh -> {
                        existingNganh.setTitle(newsModel.getTitle());
                        existingNganh.setImage(newsModel.getImage());
                        existingNganh.setRPH(newsModel.getRPH());
                        existingNganh.setContent(newsModel.getContent());
                        existingNganh.setStatus(newsModel.getStatus());
                        existingNganh.setAuthor(newsModel.getAuthor());
                        existingNganh.setUpdatedAt(newsModel.getUpdatedAt());
                        return ResponseObject.success(newsRepository.save(existingNganh));
                    })
                    .orElse(ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "Cannot find News with id: " + id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    public ResponseObject deleteByIdNews(Long id) {
        try {
            if (!newsRepository.existsById(id)) {
                return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
            } else {
                newsRepository.deleteById(id);
                return ResponseObject.success("Successfully delete record " + id, null);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
        }
    }

}
