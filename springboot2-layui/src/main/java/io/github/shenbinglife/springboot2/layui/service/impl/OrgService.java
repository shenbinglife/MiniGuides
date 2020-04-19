package io.github.shenbinglife.springboot2.layui.service.impl;

import io.github.shenbinglife.springboot2.layui.dao.OrgDao;
import io.github.shenbinglife.springboot2.layui.entity.Org;
import io.github.shenbinglife.springboot2.layui.model.TreeNode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrgService extends BaseService<Org, OrgDao> {


    public List<TreeNode<Long>> tree(Long parentId) {
        List<Org> orgs = baseDao.findByParentId(parentId);
        List<TreeNode<Long>> treeList = orgs.stream().map(it -> it.toTreeNode(parentId)).collect(Collectors.toList());
        return treeList;
    }
}
