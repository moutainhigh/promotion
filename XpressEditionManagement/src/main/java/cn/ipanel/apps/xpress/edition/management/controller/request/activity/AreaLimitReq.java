package cn.ipanel.apps.xpress.edition.management.controller.request.activity;

import cn.ipanel.apps.xpress.edition.management.annotation.MyInterfaceRequestParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with Intellij IDEA.
 *
 * @author luzh
 * Create: 2018/9/13 下午4:22
 * Modified By:
 * Description:
 */
@Data
@MyInterfaceRequestParams(operationType = "添加/修改", operationName = "区域订购限额")
class AreaLimitReq {
    @ApiModelProperty(value = "区域id", required = true)
    private Integer areaId;
    @ApiModelProperty(value = "区域限额", required = true)
    private Integer areaLimitNumber;
    @ApiModelProperty(value = "区域名称", required = true)
    private String areaName;
}
