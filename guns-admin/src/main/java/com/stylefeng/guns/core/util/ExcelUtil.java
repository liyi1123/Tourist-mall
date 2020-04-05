package com.stylefeng.guns.core.util;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelUtil {

    private static Logger log = Logger.getLogger(ExcelUtil.class);

    public static String formatCell(HSSFCell hssfCell){
        String value = "";
        if(null==hssfCell){
            return value;
        }
        switch (hssfCell.getCellType()){
            case HSSFCell.CELL_TYPE_NUMERIC:
                if(HSSFDateUtil.isCellDateFormatted(hssfCell)){
                    Date date=HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    value = format.format(date);
                }else {
                    BigDecimal big=new BigDecimal(hssfCell.getNumericCellValue());
                    value = big.toString();
                    if(null!=value&&!"".equals(value.trim())){
                        String[] item = value.split("[.]");
                        if(1<item.length&&"0".equals(item[1])){
                            value=item[0];
                        }
                    }
                }
                break;
            case HSSFCell.CELL_TYPE_STRING:
                value = hssfCell.getStringCellValue().toString();
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                value = String.valueOf(hssfCell.getNumericCellValue());
                if(value.equals("NaN")){
                    value = hssfCell.getStringCellValue().toString();
                }
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                value = " "+hssfCell.getBooleanCellValue();
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                value = "";
                log.info("excel出现空值");
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                value = "";
                log.info("excel出现错误");
                break;
            default:
                value = hssfCell.getStringCellValue().toString();
        }
        if("null".endsWith(value.trim())){
            value ="";
        }
        return value;
    }
}
