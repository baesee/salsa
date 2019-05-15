package com.billlong.salsaexample.restController;

import com.billlong.salsaexample.mapper.CommentMapper;
import com.billlong.salsaexample.mapper.CommunityMapper;
import com.billlong.salsaexample.model.SalsaCommunity;
import com.billlong.salsaexample.model.SalsaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class CommunityController {

    @Autowired
    private CommunityMapper communityMapper;

    // 모든 커뮤니티 글 목록 가져오기
    @GetMapping("/community/all")
    public List<SalsaCommunity> getAll() throws Exception{
        List<SalsaCommunity> list = communityMapper.getCommunityAll();

        return list;
    }
}
