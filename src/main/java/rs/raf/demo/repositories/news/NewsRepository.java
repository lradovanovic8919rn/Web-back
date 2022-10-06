package rs.raf.demo.repositories.news;

import rs.raf.demo.entities.Comment;
import rs.raf.demo.entities.News;
import rs.raf.demo.entities.Tag;

import java.util.List;

public interface NewsRepository {

    List<News> allNews();


    List<News> allNewsByCategory(String category);

    List<Tag> allTags();

    List<News> allNewsByTag(String tag);

    News findNews(Integer id);

    Comment addComment(Comment comment, Integer id);

    News addNews(News news);

    void deleteNews(Integer id);

    News updateNews(News news, Integer id);

}
