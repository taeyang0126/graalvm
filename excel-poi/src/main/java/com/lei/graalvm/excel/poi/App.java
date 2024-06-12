package com.lei.graalvm.excel.poi;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author 伍磊
 */
public class App {

    public static void main(String[] args) throws Exception {
        readLagerExcel();
    }

    /**
     * 大批量数据读取 十万级以上
     * 思路：采用分段缓存加载数据，防止出现OOM的情况
     *
     * @throws Exception
     */
    public static void readLagerExcel() throws Exception {
        // 获取当前所在目录
        System.out.println("开始读取excel");
        InputStream inputStream = App.class.getResourceAsStream("/test.xlsx");

        long start = System.currentTimeMillis();
        try (Workbook workbook = StreamingReader.builder()
                .rowCacheSize(10 * 20)  // 缓存到内存中的行数，默认是10
                .bufferSize(1024 * 8)  // 读取资源时，缓存到内存的字节大小，默认是1024
                .open(inputStream)) { // 打开资源，可以是InputStream或者是File，注意：只能打开.xlsx格式的文件

            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
            while (sheetIterator.hasNext()) {
                Sheet sheet = sheetIterator.next();
                System.out.println(STR."开始读取excel，耗时：\{(System.currentTimeMillis() - start)} 毫秒");
                // 遍历所有的行
                for (Row row : sheet) {
                    System.out.println(STR."开始遍历第 \{row.getRowNum()} 行数据：");
                }
                // 总数
                System.out.println(STR."读取结束行数：\{sheet.getLastRowNum()}");
                System.out.println(STR."读取excel完毕，耗时：\{(System.currentTimeMillis() - start)} 毫秒");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
