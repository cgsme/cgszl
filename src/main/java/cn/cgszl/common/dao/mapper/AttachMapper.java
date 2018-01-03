package cn.cgszl.common.dao.mapper;

import cn.cgszl.common.dao.pojo.Attach;
import cn.cgszl.common.dao.pojo.AttachExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AttachMapper {
    long countByExample(AttachExample example);

    int deleteByExample(AttachExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Attach record);

    int insertSelective(Attach record);

    List<Attach> selectByExample(AttachExample example);

    Attach selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Attach record, @Param("example") AttachExample example);

    int updateByExample(@Param("record") Attach record, @Param("example") AttachExample example);

    int updateByPrimaryKeySelective(Attach record);

    int updateByPrimaryKey(Attach record);
}