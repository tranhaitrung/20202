package cmndmanager.cmndmanager;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class CMNDController {
    @Autowired
    private CMNDService cmndService;

    @GetMapping("/")
    public String getList(Model model, @RequestParam(value = "limit", required = false) Integer limit){
        List<CMNDModel> list = cmndService.findAll(limit).stream().filter(t->t.getDeleted()==0).collect(Collectors.toList());
        model.addAttribute("listCMND",list);
        return "home";
    }

    @GetMapping("/addCMND")
    public String addCMND(Model model){
        model.addAttribute("CMND",new CMNDModel());
        return "addCMND";
    }

    @PostMapping("/addCMND")
    public String addCMND(@ModelAttribute CMNDModel cmndModel, Model model){
        if(cmndService.add(cmndModel) != null){
            String notice = "Thêm thành công!";
            model.addAttribute("CMND", cmndModel);
            model.addAttribute("notice",notice);
            return "success";
        }
        return "fail";
    }

    @GetMapping("/detail")
    public String detailCMND(@RequestParam("id") String id, Model model){
        CMNDModel cmndModel = cmndService.findByID(id);
        System.out.println("Tới đây");
        model.addAttribute("CMND",cmndModel);
        return "detail";
    }


    private String code;
    @GetMapping("/updateCMND")
    public String updateCMND(@RequestParam("id") String id, Model model){
        CMNDModel cmndModel = cmndService.findByID(id);
        code = id;
        model.addAttribute("CMND",cmndModel);
        return "update";
    }

    @PostMapping("/updateCMND")
    public String updateCMNDpost(@ModelAttribute CMNDModel cmndModel, Model model){
        if(cmndService.update(cmndModel,code) != null){
            model.addAttribute("CMND",cmndModel);
            model.addAttribute("notice","Cập nhật thành công!");
            return "success";
        }
        return "fail";
    }

    @GetMapping("/deleteCMND")
    public String deleleCMND(@RequestParam("id") String id, Model model){
        CMNDModel cmndModel = cmndService.findByID(id);
        cmndService.delele(cmndModel);
        model.addAttribute("notification","Xóa thành công CMND "+ cmndModel.getCode());
        List<CMNDModel> list = cmndService.findAll(null).stream().filter(t->t.getDeleted()==0).collect(Collectors.toList());
        model.addAttribute("listCMND", list);
        return "home";
    }
}
