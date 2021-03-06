package cn.cgszl.common.dao.mapper;

import cn.cgszl.common.dao.pojo.RelationshipExample;
import cn.cgszl.common.dao.pojo.RelationshipKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RelationshipMapper {
    long countByExample(RelationshipExample example);

    int deleteByExample(RelationshipExample example);

    int deleteByPrimaryKey(RelationshipKey key);

    int insert(RelationshipKey record);

    int insertSelective(RelationshipKey record);

    List<RelationshipKey> selectByExample(RelationshipExample example);

    int updateByExampleSelective(@Param("record") RelationshipKey record, @Param("example") RelationshipExample example);

    int updateByExample(@Param("record") RelationshipKey record, @Param("example") RelationshipExample example);
}