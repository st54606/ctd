package lv.ctd.document;

import lv.ctd.model.PaymentFormData;

public interface DocumentGenerator {

   <T extends PaymentFormData> String generateDocument( T data);
}
