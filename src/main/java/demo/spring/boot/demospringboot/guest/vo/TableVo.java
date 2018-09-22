package demo.spring.boot.demospringboot.guest.vo;

import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * 2018/9/22    Created by   chao
 */
@Data
@ToString
public class TableVo {
    List<FieldVo> fileldVos;
}
