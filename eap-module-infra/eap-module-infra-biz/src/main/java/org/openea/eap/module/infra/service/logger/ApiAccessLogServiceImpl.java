package org.openea.eap.module.infra.service.logger;

import org.openea.eap.framework.common.pojo.PageResult;
import org.openea.eap.module.infra.api.logger.dto.ApiAccessLogCreateReqDTO;
import org.openea.eap.module.infra.controller.admin.logger.vo.apiaccesslog.ApiAccessLogExportReqVO;
import org.openea.eap.module.infra.controller.admin.logger.vo.apiaccesslog.ApiAccessLogPageReqVO;
import org.openea.eap.module.infra.convert.logger.ApiAccessLogConvert;
import org.openea.eap.module.infra.dal.dataobject.logger.ApiAccessLogDO;
import org.openea.eap.module.infra.dal.mysql.logger.ApiAccessLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

/**
 * API 访问日志 Service 实现类
 *
 */
@Service
@Validated
public class ApiAccessLogServiceImpl implements ApiAccessLogService {

    @Resource
    private ApiAccessLogMapper apiAccessLogMapper;

    @Override
    public void createApiAccessLog(ApiAccessLogCreateReqDTO createDTO) {
        ApiAccessLogDO apiAccessLog = ApiAccessLogConvert.INSTANCE.convert(createDTO);
        apiAccessLogMapper.insert(apiAccessLog);
    }

    @Override
    public PageResult<ApiAccessLogDO> getApiAccessLogPage(ApiAccessLogPageReqVO pageReqVO) {
        return apiAccessLogMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ApiAccessLogDO> getApiAccessLogList(ApiAccessLogExportReqVO exportReqVO) {
        return apiAccessLogMapper.selectList(exportReqVO);
    }

}
