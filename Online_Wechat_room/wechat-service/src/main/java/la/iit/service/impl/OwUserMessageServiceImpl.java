package la.iit.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import la.iit.entity.domain.OwUserMessage;
import la.iit.mapper.OwUserMessageMapper;
import la.iit.service.OwUserMessageService;
import org.springframework.stereotype.Service;

/**
 * @author Juran
 * @createDate 2023-03-16 23:25:58
 */
@Service
public class OwUserMessageServiceImpl extends ServiceImpl<OwUserMessageMapper, OwUserMessage>
        implements OwUserMessageService {

}
