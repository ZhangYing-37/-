package edu.hstc.mapper;

import edu.hstc.bean.Paper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PaperMapper {

    //通过ID查询试卷
    public Paper selectPaperById(Map map);

    //通过课程Id查询
    public List<Paper> selectPaperByCId(Integer co_id);

    //通过课程及结果查询试卷
    public List<Paper> selectPaperAndResult(Map map);

    public List<Paper> selectPaperByTitle(Map map);

    //创建试卷
    public Integer newPaper(Paper paper);

    //更新试卷
    public Integer updatePaper(Paper paper);

    public Integer updatePaperSetting(Paper paper);

    public Integer deletePaper(Integer p_id);

}
