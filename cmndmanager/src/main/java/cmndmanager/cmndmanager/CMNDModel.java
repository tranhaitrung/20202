package cmndmanager.cmndmanager;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "cmnd")
public class CMNDModel {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "address")
    private String address;

    @Column(name = "job")
    private String job;

    @Column(name = "deleted")
    private int deleted = 0;
}
