package demo.spring.boot.demospringboot.guest.vo;



import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Setter
@Getter
@ToString
public class DBVo {

    @ApiModelProperty(required = true, allowableValues = "com.mysql.jdbc.Driver,oracle.jdbc.driver.OracleDriver", name = "jdbcDriverClassName")
    String jdbcDriverClassName;
    @ApiModelProperty(required = true, name = "jdbcUrl")
    String jdbcUrl;
    @ApiModelProperty(required = true, name = "userName")
    String userName;
    @ApiModelProperty(required = true, name = "password")
    String password;
}
