package com.guo.controller;

import com.guo.domain.ResponseResult;
import com.guo.domain.dto.CategoryListDto;
import com.guo.domain.dto.LinkListDto;
import com.guo.domain.vo.LinkVo;
import com.guo.service.LinkService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author guo
 * @Date 2023 03 27 15 11
 **/

@RestController
@RequestMapping("/content/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/list")
    public ResponseResult pageLinkList(Integer pageNum, Integer pageSize, LinkListDto linkListDto){
        return linkService.pageLinkList(pageNum,pageSize,linkListDto);
    }

    @PostMapping
    public ResponseResult addLink(@RequestBody LinkVo linkVo){
        return linkService.addLink(linkVo);
    }

    @GetMapping("/{id}")
    public ResponseResult getLink(@PathVariable String id){
        return linkService.getLink(id);
    }

    @PutMapping
    public ResponseResult updateLink(@RequestBody LinkVo linkVo){
        return linkService.update(linkVo);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteLink(@PathVariable String id){
        return linkService.deleteLink(id);
    }
}
