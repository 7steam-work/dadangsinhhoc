package site.dadangsinhhoc.services.news;

import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.NewsModel;

public interface INewsService {
     boolean existById(Long id);

     ResponseObject findNewsById(Long id);

     ResponseObject getAllNews();

     ResponseObject countAllNews();

     ResponseObject saveNews(NewsModel newsModels);

     ResponseObject updateNews(Long id, NewsModel newsModel);

     ResponseObject deleteByIdNews(Long id);

}
