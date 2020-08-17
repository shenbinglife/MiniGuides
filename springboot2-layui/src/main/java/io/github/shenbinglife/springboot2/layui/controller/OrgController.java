package io.github.shenbinglife.springboot2.layui.controller;

import io.github.shenbinglife.springboot2.layui.model.TreeNode;
import io.github.shenbinglife.springboot2.layui.service.impl.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("orgs")
@RestController
public class OrgController {

    @Autowired
    private OrgService orgService;

    @GetMapping("tree/{parentId}")
    public List<TreeNode<Long>> tree(@PathVariable("parentId") Long parentId) {
        return orgService.tree(parentId);
    }
}
