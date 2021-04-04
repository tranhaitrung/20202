package cmndmanager.cmndmanager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CMNDConfig {
    @Bean
    public CMNDValidator validator(){
        return new CMNDValidator();
    }

}
