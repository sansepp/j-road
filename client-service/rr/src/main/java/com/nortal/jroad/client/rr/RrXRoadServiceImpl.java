package com.nortal.jroad.client.rr;

import com.nortal.jroad.client.exception.XTeeServiceConsumptionException;
import com.nortal.jroad.client.rr.database.RrXRoadDatabase;
import com.nortal.jroad.client.rr.types.eu.x_road.rr.producer.*;
import com.nortal.jroad.client.rr.types.eu.x_road.rr.producer.RR40IsikTaielikIsikukoodDocument.RR40IsikTaielikIsikukood;
import com.nortal.jroad.client.rr.types.eu.x_road.rr.producer.RR40IsikTaielikIsikukoodResponseDocument.RR40IsikTaielikIsikukoodResponse;
import com.nortal.jroad.client.rr.types.eu.x_road.rr.producer.RR41IsikPohiandmedDocument.RR41IsikPohiandmed;
import com.nortal.jroad.client.rr.types.eu.x_road.rr.producer.RR41IsikPohiandmedResponseDocument.RR41IsikPohiandmedResponse;
import com.nortal.jroad.client.rr.types.eu.x_road.rr.producer.RR42IsikAadressKoodDocument.RR42IsikAadressKood;
import com.nortal.jroad.client.rr.types.eu.x_road.rr.producer.RR42IsikAadressKoodResponseDocument.RR42IsikAadressKoodResponse;
import com.nortal.jroad.client.rr.types.eu.x_road.rr.producer.RR52Document.RR52;
import com.nortal.jroad.client.rr.types.eu.x_road.rr.producer.RR52ResponseDocument.RR52Response;
import com.nortal.jroad.client.rr.types.eu.x_road.rr.producer.RR63IsikAadrDokDocument.RR63IsikAadrDok;
import com.nortal.jroad.client.rr.types.eu.x_road.rr.producer.RR63IsikAadrDokResponseDocument.RR63IsikAadrDokResponse;
import com.nortal.jroad.client.rr.types.eu.x_road.rr.producer.RR67MuutusResponseDocument.RR67MuutusResponse;
import com.nortal.jroad.client.rr.types.eu.x_road.rr.producer.RR67MuutusResponseType.TtKood.TtKoodid;
import com.nortal.jroad.client.rr.types.eu.x_road.rr.producer.RR72IsikResponseType.TtIsik;
import com.nortal.jroad.client.rr.types.eu.x_road.rr.producer.RR72IsikResponseType.TtIsik.TtIsikud;
import com.nortal.jroad.client.rr.types.eu.x_road.rr.producer.RR81KMAisikkontrollDocument.RR81KMAisikkontroll;
import com.nortal.jroad.client.rr.types.eu.x_road.rr.producer.RR81KMAisikkontrollResponseDocument.RR81KMAisikkontrollResponse;
import com.nortal.jroad.client.rr.types.eu.x_road.rr.producer.RR84IsikuSeosedDocument.RR84IsikuSeosed;
import com.nortal.jroad.client.rr.types.eu.x_road.rr.producer.RR84IsikuSeosedResponseDocument.RR84IsikuSeosedResponse;
import com.nortal.jroad.client.rr.types.eu.x_road.rr.producer.RR96IsikDokElukSuheDocument.RR96IsikDokElukSuhe;
import com.nortal.jroad.client.rr.types.eu.x_road.rr.producer.RR96IsikDokElukSuheResponseDocument.RR96IsikDokElukSuheResponse;
import com.nortal.jroad.client.service.MetaserviceOperations;
import com.nortal.jroad.client.util.XmlBeansUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlString;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Roman Tekhov
 */
@Service("rrXRoadService")
public class RrXRoadServiceImpl implements RrXRoadService {

  @Resource
  private RrXRoadDatabase rrXRoadDatabase;

  private MetaserviceOperations metaserviceOperations;

  @PostConstruct
  public void setUpCollaborators() {
    metaserviceOperations = new MetaserviceOperations(rrXRoadDatabase);
  }

  public Integer getState() throws XTeeServiceConsumptionException {
    return metaserviceOperations.getState();
  }

  public RR42IsikAadressKoodResponse findRR42isikAadressKood(RR42RequestCallback callback)
      throws XTeeServiceConsumptionException {
    RR42IsikAadressKood paring = RR42IsikAadressKood.Factory.newInstance();

    callback.populate(paring);

    return rrXRoadDatabase.rr42IsikAadressKoodV1(paring);
  }

  public RR40IsikTaielikIsikukoodResponse findRR40isikTaielikIsikukood(String isikukood)
      throws XTeeServiceConsumptionException {
    RR40IsikTaielikIsikukood rr40IsikTaielikIsikukood = RR40IsikTaielikIsikukood.Factory.newInstance();

    RR40IsikTaielikIsikukoodRequestType request = rr40IsikTaielikIsikukood.addNewRequest();
    request.setIsikukood(isikukood);

    return rrXRoadDatabase.rr40IsikTaielikIsikukoodV1(rr40IsikTaielikIsikukood);
  }

  public List<TtIsikud> findRR72Isik(String... idCodes) throws XTeeServiceConsumptionException {
    RR72IsikDocument.RR72Isik rr72Isik = RR72IsikDocument.RR72Isik.Factory.newInstance();
    RR72IsikRequestType request = rr72Isik.addNewRequest();

    String isikukoodid = StringUtils.join(idCodes, ",");
    XmlString isikukoodidElement = XmlBeansUtil.getAttributedXmlString(isikukoodid);
    request.xsetCIsikukoodid(isikukoodidElement);

    TtIsik ttIsik = rrXRoadDatabase.rr72IsikV1(rr72Isik).getResponse().getTtIsik();

    return ttIsik != null ? ttIsik.getTtIsikudList() : Collections.<TtIsikud> emptyList();
  }

  public RR63IsikAadrDokResponse findRR63IsikAadrDok(String surname, String firstName, String idCode, String birthDate)
      throws XTeeServiceConsumptionException {
    RR63IsikAadrDok rr63IsikAadrDok = RR63IsikAadrDok.Factory.newInstance();

    RR63IsikAadrDokRequestType request = rr63IsikAadrDok.addNewRequest();
    XmlString eesnimiElement = XmlBeansUtil.getAttributedXmlString(firstName);
    request.xsetIsikueesnimi(eesnimiElement);
    XmlString perenimiElement = XmlBeansUtil.getAttributedXmlString(surname);
    request.xsetIsikuperenimi(perenimiElement);
    XmlString isikukoodElement = XmlBeansUtil.getAttributedXmlString(idCode);
    request.xsetIsikukood(isikukoodElement);
    XmlString synnikpElement = XmlBeansUtil.getAttributedXmlString(birthDate);
    request.xsetIsikuSynnikuupaev(synnikpElement);

    return rrXRoadDatabase.rr63IsikAadrDokV1(rr63IsikAadrDok);
  }

  public RR81KMAisikkontrollResponse getRR81KMAisikkontrollV1(String idCode) throws XTeeServiceConsumptionException {
    RR81KMAisikkontroll rr81KMAisikkontroll = RR81KMAisikkontroll.Factory.newInstance();

    RR81KMAisikkontrollRequestType request = rr81KMAisikkontroll.addNewRequest();

    XmlString isikukoodElement = XmlBeansUtil.getAttributedXmlString(idCode);
    request.xsetIsikukood(isikukoodElement);

    return rrXRoadDatabase.rr81KMAisikkontrollV1(rr81KMAisikkontroll);
  }

  public RR81KMAisikkontrollResponse getRR81KMAisikkontrollV2(String idCode) throws XTeeServiceConsumptionException {
    RR81KMAisikkontroll rr81KMAisikkontroll = RR81KMAisikkontroll.Factory.newInstance();

    RR81KMAisikkontrollRequestType request = rr81KMAisikkontroll.addNewRequest();

    XmlString isikukoodElement = XmlBeansUtil.getAttributedXmlString(idCode);
    request.xsetIsikukood(isikukoodElement);

    return rrXRoadDatabase.rr81KMAisikkontrollV2(rr81KMAisikkontroll);
  }

  public RR52Response getRR52(String idCode, String forename, String surname) throws XTeeServiceConsumptionException {
    RR52 rr52 = RR52.Factory.newInstance();

    RR52RequestType request = rr52.addNewRequest();

    XmlString isikukoodElement = XmlBeansUtil.getAttributedXmlString(idCode);
    request.xsetIsikukood(isikukoodElement);

    XmlString forenameElement = XmlBeansUtil.getAttributedXmlString(forename);
    request.xsetIsikueesnimi(forenameElement);

    XmlString surnameElement = XmlBeansUtil.getAttributedXmlString(surname);
    request.xsetIsikuperenimi(surnameElement);

    return rrXRoadDatabase.rr52V1(rr52);
  }

  public RR96IsikDokElukSuheResponse getRR96isikDokElukSuhe(final String isikueesnimi,
                                                            final String isikuperenimi,
                                                            final String isikukood,
                                                            final Long vastusteArv)
      throws XTeeServiceConsumptionException {
    RR96IsikDokElukSuhe rr96IsikDokElukSuhe = RR96IsikDokElukSuhe.Factory.newInstance();

    RR96IsikDokElukSuheRequestType request = rr96IsikDokElukSuhe.addNewRequest();

    request.setIsikueesnimi(isikueesnimi);
    request.setIsikuperenimi(isikuperenimi);
    request.setIsikukood(isikukood);
    request.setVastusteArv(String.valueOf(vastusteArv));

    return rrXRoadDatabase.rr96IsikDokElukSuheV1(rr96IsikDokElukSuhe);
  }

  public List<TtKoodid> findRR67MuutusV1(Date algus, Date lopp, String... koodid)
      throws XTeeServiceConsumptionException {
    RR67MuutusDocument.RR67Muutus rr67Muutus = RR67MuutusDocument.RR67Muutus.Factory.newInstance();

    RR67MuutusRequestType request = rr67Muutus.addNewRequest();

    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
    if (koodid != null) {
      StringBuilder codes = new StringBuilder();
      for (String item : koodid) {
        codes.append(codes.length() > 0 ? "," : "");
        codes.append(item);
      }
      request.setCMuutused(codes.toString());
    }
    if (algus != null) {
      request.setCAlgKpv(df.format(algus));
      request.setCAlgKell(tf.format(algus));
    }

    if (lopp != null) {
      request.setCLoppKpv(df.format(lopp));
      request.setCLoppKell(tf.format(lopp));
    }
    RR67MuutusResponse rsp = rrXRoadDatabase.rr67MuutusV1(rr67Muutus);
    RR67MuutusResponseType.TtKood ttKood = rsp.getResponse().getTtKood();
    return ttKood != null ? ttKood.getTtKoodidList() : Collections.<TtKoodid> emptyList();
  }

  public RR84IsikuSeosedResponse findRR84IsikuSeosed(String isikukood) throws XTeeServiceConsumptionException {
    RR84IsikuSeosed rr84IsikuSeosed = RR84IsikuSeosed.Factory.newInstance();
    RR84IsikuSeosedRequestType request = rr84IsikuSeosed.addNewRequest();
    request.setIsikukood(isikukood);
    return rrXRoadDatabase.rr84IsikuSeosedV1(rr84IsikuSeosed);
  }

  public RR41IsikPohiandmedResponse findRR41isikPohiandmedV1(String perenimi,
                                                             String eesnimi,
                                                             String isikukood,
                                                             String vald,
                                                             IsikuStaatus staatus,
                                                             Long vastusteArv)
      throws XTeeServiceConsumptionException {
    RR41IsikPohiandmed rr41IsikPohiandmed = RR41IsikPohiandmed.Factory.newInstance();

    RR41IsikPohiandmedRequestType request = rr41IsikPohiandmed.addNewRequest();

    request.setIsikuperenimi(perenimi);
    request.setIsikueesnimi(eesnimi);
    request.setIsikukood(isikukood);
    request.setVald(vald);
    request.xsetStaatus(staatus);
    request.setMitu(String.valueOf(vastusteArv));

    return rrXRoadDatabase.rr41IsikPohiandmedV1(rr41IsikPohiandmed);
  }

}