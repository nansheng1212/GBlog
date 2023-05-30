package com.guo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.domain.ResponseResult;
import com.guo.domain.dto.LinkListDto;
import com.guo.domain.entity.Link;
import com.guo.domain.vo.LinkVo;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2023-03-27 15:14:16
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();

    ResponseResult pageLinkList(Integer pageNum, Integer pageSize, LinkListDto linkListDto);

    ResponseResult addLink(LinkVo linkVo);

    ResponseResult getLink(String id);

    ResponseResult update(LinkVo linkVo);

    ResponseResult deleteLink(String id);
}
