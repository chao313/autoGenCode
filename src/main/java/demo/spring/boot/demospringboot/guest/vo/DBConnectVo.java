package demo.spring.boot.demospringboot.guest.vo;


import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * jdbc:mysql://127.0.0.1:3306/springBoot
 */



@Data
@ToString
@Component
public class DBConnectVo {
    @ApiModelProperty(required = true, allowableValues = "mysql,oracle", name = "type", notes = "数据库类型")
    String type;
    @ApiModelProperty(required = true, name = "ip", notes = "数据库ip")
    String ip;
    @ApiModelProperty(required = true, name = "port", notes = "数据库端口号")
    String port;
    @ApiModelProperty(required = true, name = "database", notes = "数据库名称")
    String database;
    @ApiModelProperty(required = true, name = "userName")
    String userName;
    @ApiModelProperty(required = true, name = "password")
    String password;

    public String genJdbcUrl() {
        return Type.genJdbcUrl(this.type, this.ip, this.port, this.database);
    }

    public String getDriver() {
        return Type.valueOf(this.type.toUpperCase()).driver;
    }


   public enum Type {
        MYSQL("mysql", "jdbc:mysql: ","com.mysql.jdbc.Driver"), ORACLE("mysql", "暂不支持","暂不支持");
        private String key;
        private String value;
        private String driver;

        Type(String key, String value , String driver) {
            this.key = key;
            this.value = value;
            this.driver = driver;
        }

        /**
         * 生成jdbcUrl
         */
        public static String genJdbcUrl(String type, String ip, String port, String database) {

            return Type.valueOf(type.toUpperCase()).value + ip + ":" + port + "/" + database;

        }
    }

}
