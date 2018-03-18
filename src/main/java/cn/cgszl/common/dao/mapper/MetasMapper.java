package cn.cgszl.common.dao.mapper;

import cn.cgszl.common.dao.dto.MetasDto;
import cn.cgszl.common.dao.pojo.Metas;
import cn.cgszl.common.dao.pojo.MetasExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MetasMapper {
    long countByExample(MetasExample example);

    int deleteByExample(MetasExample example);

    int deleteByPrimaryKey(Integer mid);

    int insert(Metas record);

    int insertSelective(Metas record);

    List<Metas> selectByExample(MetasExample example);

    Metas selectByPrimaryKey(Integer mid);

    int updateByExampleSelective(@Param("record") Metas record, @Param("example") MetasExample example);

    int updateByExample(@Param("record") Metas record, @Param("example") MetasExample example);

    int updateByPrimaryKeySelective(Metas record);

    int updateByPrimaryKey(Metas record);

    /**
     * 根据sql查询分类
     * @param paramMap 参数
     * @return
     */
    List<MetasDto> selectFormSql(Map<String, Object> paramMap);
}