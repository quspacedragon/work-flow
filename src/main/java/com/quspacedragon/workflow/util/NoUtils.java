package com.quspacedragon.workflow.util;

/**
 * Title: NoUtils
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/9/30
 */
public class NoUtils {
    private static final int ENTERPRISE_NO_LENGTH = 3;
    private static final int CUSTOMER_NO_LENGTH = 5;
    private static final int BILL_NO_LENGTH = 16;

    public static String createCustomerNo(Long enterpriseId, Long customerId) throws Exception {
        String enterpriseNo = fill(enterpriseId, ENTERPRISE_NO_LENGTH);
        String customerNo = fill(customerId, CUSTOMER_NO_LENGTH);
        return enterpriseNo + customerNo;
    }


    public static String createBillNo(String customerNo, Long billId) throws Exception {
        String billNo = fill(billId, BILL_NO_LENGTH);
        return customerNo + billNo;
    }

    private static String fill(long value, int length) throws Exception {
        String data = Long.toString(value);
        if (data.length() > length) {
            throw new Exception("超过限制");
        }
        if (data.length() == length) {
            return data;
        }
        StringBuilder stringBuilder = new StringBuilder(data);
        while (stringBuilder.length() < length) {
            stringBuilder.insert(0, "0");
        }
        return stringBuilder.toString();
    }
}
