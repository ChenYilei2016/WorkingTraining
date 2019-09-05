package cn.chenyilei.work.web.controller;

import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.web.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 注释
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/05 15:02
 */
public abstract class CommonController<T,S extends CommonService<T>>{
    @Autowired
    S commonService;

    @GetMapping("/select")
    @ResponseBody
    public AjaxResult<T> select(T t){
        try {
            List<T> select = commonService.select(t);
            return AjaxResult.success(select,"查询成功!");
        }catch (Exception e){
            return AjaxResult.error("查询失败!");
        }
    }
    @PostMapping("/insert")
    @ResponseBody
    public AjaxResult<T> insert(T t){
        try {
            int i = commonService.insert(t);
            return AjaxResult.success(null,"插入成功!");
        }catch (Exception e){
            return AjaxResult.error("插入失败!");
        }
    }

    @PutMapping("/update")
    @ResponseBody
    public AjaxResult<T> update(T t){
        try {
            int i = commonService.update(t);
            return AjaxResult.success(null,"更新成功!");
        }catch (Exception e){
            return AjaxResult.error("更新失败!");
        }
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public AjaxResult<T> delete(T t){
        try {
            int i  = commonService.delete(t);
            return AjaxResult.success(null,"删除成功!");
        }catch (Exception e){
            return AjaxResult.error("删除失败!");
        }
    }

}
