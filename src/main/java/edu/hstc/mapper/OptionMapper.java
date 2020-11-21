package edu.hstc.mapper;

import edu.hstc.bean.Option;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionMapper {

    //插入选项
    public Integer insertOption(Option option);

    //更新选项
    public Integer updateOption(Option option);

    //删除选项
    public Integer deleteOption(Option option);

}
