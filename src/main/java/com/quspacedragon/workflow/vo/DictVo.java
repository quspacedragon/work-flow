package com.quspacedragon.workflow.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Title: DictVo
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/9/26
 */
public class DictVo extends BaseVo {
    @ApiModelProperty(value = "字典名称")
    private String dictName;
    @ApiModelProperty(value = "字典类型")
    private Integer dictType;
    @ApiModelProperty(value = "字典关键字")
    private Integer dictKey;
    @ApiModelProperty(value = "拼音简码")
    private String code;

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public Integer getDictType() {
        return dictType;
    }

    public void setDictType(Integer dictType) {
        this.dictType = dictType;
    }

    public Integer getDictKey() {
        return dictKey;
    }

    public void setDictKey(Integer dictKey) {
        this.dictKey = dictKey;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
