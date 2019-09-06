package cn.chenyilei.work.web.controller;

import cn.chenyilei.work.domain.vo.AjaxResult;
import cn.chenyilei.work.web.service.CommonService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 通用Controller模版
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/09/05 15:02
 */
@Slf4j
public abstract class CommonController<T,S extends CommonService<T>>{
    @Autowired
    S commonService;

    @GetMapping("/select/{id:\\d+}")
    @ResponseBody
    public AjaxResult<T> select(@PathVariable("id") int id){
        try {
            T select = commonService.selectById(id);
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
            if(i <= 0){
                throw new RuntimeException();
            }
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
            if(i <= 0){
                throw new RuntimeException();
            }
            return AjaxResult.success(null,"更新成功!");
        }catch (Exception e){
            return AjaxResult.error("更新失败!");
        }
    }

    @DeleteMapping("/delete/{id:\\d+}")
    @ResponseBody
    public AjaxResult<T> delete(@PathVariable("id")int id){
        try {
            int i  = commonService.deleteById(id);
            if(i <= 0){
                throw new RuntimeException();
            }
            return AjaxResult.success(null,"删除成功!");
        }catch (Exception e){
            return AjaxResult.error("删除失败!");
        }
    }

}
