package com.billlong.salsaexample.mapper;

import com.billlong.salsaexample.model.SalsaCommunity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommunityMapper {

    //커뮤니티 게시글 총 목록 가져오기
    @Select("SELECT" +
                " community_idx" +
                ", title" +
                ", writer" +
                ", regdate" +
                ", hit_count" +
                ", content" +
                ", (select count(*) from salsa_comment where board_idx = community_idx) as recommend_count " +
            "FROM salsa_community" )
    List<SalsaCommunity> getCommunityAll();
}
