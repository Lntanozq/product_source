package com.briup.framework.mapper.extend;

import com.briup.framework.bean.extend.OutCircleExtend;

/**
 * @author pgc
 * @Description: 溯源生猪出栏记录mapper
 * @date 2022/4/8 10:52
 */
public interface OutCircleExtendMapper {
    OutCircleExtend queryByBatchId(String batchId);
}
