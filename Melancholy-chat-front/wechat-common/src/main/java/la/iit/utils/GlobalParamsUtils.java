package la.iit.utils;

import la.iit.entity.domain.OwUser;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author JuRan
 * @date 2023/3/14
 */
@Component
@Data
public class GlobalParamsUtils {
    private String token;
    private OwUser currentUser;
}
