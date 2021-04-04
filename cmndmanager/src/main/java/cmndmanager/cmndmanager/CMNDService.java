package cmndmanager.cmndmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CMNDService {
    @Autowired
    private CMNDRepository cmndRepository;

    @Autowired
    private CMNDValidator validator;

    public List<CMNDModel> findAll(Integer limit){
        return Optional.ofNullable(limit)
                .map(value -> cmndRepository.findAll(PageRequest.of(0, value)).getContent())
                .orElseGet(() -> cmndRepository.findAll());

    }

    public CMNDModel findByID(String code){
        CMNDModel cmndModel = cmndRepository.findById(code).orElse(null);
        return cmndModel;
    }

    public CMNDModel add(CMNDModel cmndModel){
        System.out.println("Vào add");
        if(validator.isValid(cmndModel)){
            System.out.println(cmndModel.getCode()
                                +"\n" + cmndModel.getName()
                                +"\n" + cmndModel.getDob()
                                +"\n" + cmndModel.getAddress()
                                +"\n" + cmndModel.getJob());
            CMNDModel cmndModel1 = cmndRepository.findById(cmndModel.getCode()).orElse(null);
            if(cmndModel1 == null){
                return cmndRepository.save(cmndModel);
            }
        }
        System.out.println("lỗi rồi nè!");
        return null;
    }

    @Transactional
    public CMNDModel update(CMNDModel model, String code){
        System.out.println("code :" +code);
        CMNDModel cmndModel = cmndRepository.findById(code).orElse(null);
        System.out.println("model : "+ model.getCode() + "/n Name : " + model.getName());
            if(validator.isValid(model)) {
                if(model.getCode() != code) cmndRepository.delete(cmndModel);
                return cmndRepository.save(model);
            }
        System.out.println("NULL nè");
            return null;
    }

    public boolean delele(CMNDModel model){
        CMNDModel cmndModel = cmndRepository.findById(model.getCode()).get();
        cmndModel.setDeleted(1);
        cmndRepository.save(cmndModel);
        return true;
    }
}
