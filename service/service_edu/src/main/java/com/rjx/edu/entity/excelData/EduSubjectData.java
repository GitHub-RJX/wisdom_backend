package com.rjx.edu.entity.excelData;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class EduSubjectData {

    @ExcelProperty(index = 0)
    private String twoName;
    @ExcelProperty(index = 1)
    private String oneName;
}
