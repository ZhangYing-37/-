package edu.hstc.service;

import edu.hstc.bean.Option;
import edu.hstc.mapper.OptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionService {

    @Autowired
    OptionMapper optionMapper;

    public Integer addOption(String o_name,Integer isTrue,Integer sj_id){
        Option option = new Option();
        option.setO_name(o_name);
        option.setO_isTrue(isTrue);
        option.setSj_id(sj_id);
        return optionMapper.insertOption(option);
    }

    public Integer updateOption(String o_name,Integer isTrue,Integer o_id){
        Option option = new Option();
        option.setO_name(o_name);
        option.setO_isTrue(isTrue);
        option.setO_id(o_id);
        return optionMapper.updateOption(option);
    }

    public Integer deleteOption(Integer o_id){
        Option option = new Option();
        option.setO_id(o_id);
        return optionMapper.deleteOption(option);
    }
}
