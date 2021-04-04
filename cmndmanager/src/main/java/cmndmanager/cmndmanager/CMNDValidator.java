package cmndmanager.cmndmanager;

import org.thymeleaf.util.DateUtils;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Optional;

public class CMNDValidator {
    CMNDRepository cmndRepository;

    public boolean isValid(CMNDModel cmndModel){
        System.out.println("Có vào isvalid");
        boolean isNull = Optional.ofNullable(cmndModel)
                .filter(t -> !StringUtils.isEmpty(t.getCode()) && t.getCode().length() <= 12 && t.getCode().length() >= 9)
                .filter(t -> !StringUtils.isEmpty(t.getName()))
                .filter(t -> t.getJob() != null)
                .filter(t -> !StringUtils.isEmpty(t.getAddress()))
                .filter((t -> !StringUtils.isEmpty(t.getJob())))
                .filter(t-> t.getDeleted() == 0)
                .isEmpty();
        if (isNull) return false;
        System.out.println("isNULL : "+isNull);
        return true;
    }
}
