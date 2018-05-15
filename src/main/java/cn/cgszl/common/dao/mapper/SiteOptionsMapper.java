package cn.cgszl.common.dao.mapper;

import cn.cgszl.common.dao.pojo.SiteOptions;
import cn.cgszl.common.dao.pojo.SiteOptionsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SiteOptionsMapper {
    long countByExample(SiteOptionsExample example);

    int deleteByExample(SiteOptionsExample example);

    int deleteByPrimaryKey(String name);

    int insert(SiteOptions record);

    int insertSelective(SiteOptions record);

    List<SiteOptions> selectByExample(SiteOptionsExample example);

    SiteOptions selectByPrimaryKey(String name);

    int updateByExampleSelective(@Param("record") SiteOptions record, @Param("example") SiteOptionsExample example);

    int updateByExample(@Param("record") SiteOptions record, @Param("example") SiteOptionsExample example);

    int updateByPrimaryKeySelective(SiteOptions record);

    int updateByPrimaryKey(SiteOptions record);
}