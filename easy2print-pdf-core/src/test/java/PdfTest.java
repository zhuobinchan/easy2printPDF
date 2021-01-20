import com.github.zhuobinchan.easy2print.pdf.core.PdfModelUtils;
import com.github.zhuobinchan.easy2print.pdf.core.annotiation.PdfField;
import com.github.zhuobinchan.easy2print.pdf.core.annotiation.PdfFieldStyle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuobin chan on 2021-01-20 15:37
 */
public class PdfTest {
    @Test
    public void test2() {
        InvoiceCodePdfModel model = new InvoiceCodePdfModel();
        model.setCode_1("123");
        model.setMachineNumber("abc哈哈");

        ArrayList<InvoiceCodeServiceParam> objects = new ArrayList();
        InvoiceCodeServiceParam invoiceCodeServiceParam = new InvoiceCodeServiceParam();
        invoiceCodeServiceParam.setServiceName("setServiceName1");
        objects.add(invoiceCodeServiceParam);
        model.setParams(objects);
        System.out.println(PdfModelUtils.printPdf("https://statics.xinshuibao1.com/7124eb8105c3d7de930d.pdf", "C:\\Users\\xinbao\\Desktop\\", model, InvoiceCodePdfModel.class));
    }


    public static class InvoiceCodePdfModel {
        @PdfField(fieldName = "code_1")
        @PdfFieldStyle
        private String code_1; // 发票左上角代码

        @PdfField(fieldName = "machineNumber")
        @PdfFieldStyle
        private String machineNumber; // 机器编号

        @PdfField(fieldName = "params", ignoreFieldNamePrefix4Collection = true)
        private List<InvoiceCodeServiceParam> params;

        public String getCode_1() {
            return code_1;
        }

        public void setCode_1(String code_1) {
            this.code_1 = code_1;
        }

        public String getMachineNumber() {
            return machineNumber;
        }

        public void setMachineNumber(String machineNumber) {
            this.machineNumber = machineNumber;
        }

        public List<InvoiceCodeServiceParam> getParams() {
            return params;
        }

        public void setParams(List<InvoiceCodeServiceParam> params) {
            this.params = params;
        }
    }

    public static class InvoiceCodeServiceParam {
        @PdfField(fieldName = "serviceName_${COLLECTION_INDEX}")
        private String serviceName;

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }
    }
}
