package ee.webmedia.xtee.client.rmrk;

import ee.webmedia.xtee.client.exception.XTeeServiceConsumptionException;
import ee.webmedia.xtee.client.rmrk.types.ee.riik.xtee.treasury.producers.producer.treasury.SendDocumentRequestType;
import ee.webmedia.xtee.client.rmrk.types.ee.riik.xtee.treasury.producers.producer.treasury.SendDocumentResponseType;
import ee.webmedia.xtee.client.service.XTeeDatabaseService;
import ee.webmedia.xtee.client.service.callback.CustomCallback;
import ee.webmedia.xtee.model.XTeeAttachment;
import ee.webmedia.xtee.model.XTeeMessage;
import ee.webmedia.xtee.model.XmlBeansXTeeMessage;
import ee.webmedia.xtee.util.SOAPUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerException;
import org.apache.xmlbeans.impl.util.Base64;
import org.springframework.ws.WebServiceMessage;
import org.w3c.dom.Node;

/**
 * At the time of writing the implementation of this service on the treasury side does NOT conform to their WSDL nor the
 * X-tee specification in general, thus the client-side implementation also does NOT conform to the WSDL. Hopefully this
 * will change in the future, but until then PLEASE DO NOT USE this service as an example. Due to the fact, that there
 * is an element present outside "keha". Some hacking is required to make this work...
 * 
 * @author Dmitri Danilkin
 */
public class RmvikiXTeeServiceImpl extends XTeeDatabaseService implements RmrkXTeeService {
  private static final String SEND_DOCUMENT = "sendDocument";
  private static final String VERSION = "v1";

  public String sendDocument(String uniqueId, String type, byte[] manus) throws XTeeServiceConsumptionException {
    SendDocumentRequestType kassaReq = SendDocumentRequestType.Factory.newInstance();

    kassaReq.setUniqueId(uniqueId);
    kassaReq.setType(type);

    List<XTeeAttachment> attachments = new ArrayList<XTeeAttachment>();
    attachments.add(new XTeeAttachment("manus", "application/octet-stream", Base64.encode(manus)));

    XTeeMessage<SendDocumentResponseType> response =
        send(new XmlBeansXTeeMessage<SendDocumentRequestType>(kassaReq, attachments),
             SEND_DOCUMENT,
             VERSION,
             new RmvikiCallback(),
             null);

    if (response.getContent().getCode() != 0) {
      return response.getContent().getMessage();
    }
    return null;
  }

  private static class RmvikiCallback extends CustomCallback {
    public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
      callback.doWithMessage(message);
      SOAPMessage extractedMessage = SOAPUtil.extractSoapMessage(message);
      try {
        Node operation = SOAPUtil.getFirstNonTextChild(extractedMessage.getSOAPBody());
        SOAPFactory factory = SOAPFactory.newInstance();
        SOAPElement p1 = factory.createElement("p1");
        p1.addAttribute(factory.createName("href"), "cid:manus");
        operation.appendChild(operation.getOwnerDocument().importNode(p1, true));
      } catch (SOAPException e) {
        throw new RuntimeException(e);
      }
    }
  }

}
