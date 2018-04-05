package cn.cgszl.common.dao.mapper;

import cn.cgszl.common.dao.pojo.Article;
import cn.cgszl.common.dao.pojo.ArticleExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ArticleMapper {
    long countByExample(ArticleExample example);

    int deleteByExample(ArticleExample example);

    int deleteByPrimaryKey(Integer aid);

    int insert(Article record);

    int insertSelective(Article record);

    List<Article> selectByExampleWithBLOBs(ArticleExample example);

    List<Article> selectByExample(ArticleExample example);

    Article selectByPrimaryKey(Integer aid);

    int updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    /**
     * 查询所有文章
     *
     * @return 文章集合
     */
    List<Article> getArticleList();

    /**
     * 获取草稿箱列表
     *
     * @return 草稿集合
     */
    List<Article> getArticleDraftList();

    /**
     * 获取回收站文章列表
     *
     * @return 文章集合
     */
    List<Article> getAllTrashList();

    /**
     * 根据文章标识获取文章信息
     *
     * @param aid 文章标识
     * @return
     */
    Article selectArticleDetailById(Integer aid);

    /**
     * 获取热门分类
     *
     * @return
     */
    List<Article> listHotArticles();

    /**
     * 根据sql获取文章列表
     *
     * @param oederCause 排序方式
     * @return
     */
    List<Article> getArticleListBySql(Map<String, Object> oederCause);
}