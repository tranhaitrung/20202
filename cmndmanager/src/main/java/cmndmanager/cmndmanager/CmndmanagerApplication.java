package cmndmanager.cmndmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CmndmanagerApplication {

    public static void main(String[] args) {
//        ApplicationContext context = SpringApplication.run(CmndmanagerApplication.class, args);
//        CMNDRepository cmndRepository = context.getBean(CMNDRepository.class);
//        cmndRepository.findAll().forEach(System.out::println);

        SpringApplication.run(CmndmanagerApplication.class,args);
    }

}
