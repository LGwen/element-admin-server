package com.lgwen.mybatisredismysql.mapper;

import com.lgwen.mybatisredismysql.beans.Right;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface RightMapper {
    @Select("select * from `right` where userid=#{userid}")
    public Right getUserRightByUserId(int userid);
}
