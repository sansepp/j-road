package ee.webmedia.xtee.client.rr;

import ee.webmedia.xtee.client.exception.XTeeServiceConsumptionException;
import ee.webmedia.xtee.client.rr.RrXTeeService.RR42RequestCallback;
import ee.webmedia.xtee.client.rr.types.ee.riik.xtee.rr.producers.producer.rr.DokumendiTyyp;
import ee.webmedia.xtee.client.rr.types.ee.riik.xtee.rr.producers.producer.rr.Kodif;
import ee.webmedia.xtee.client.rr.types.ee.riik.xtee.rr.producers.producer.rr.RR40Response;
import ee.webmedia.xtee.client.rr.types.ee.riik.xtee.rr.producers.producer.rr.RR42Request;
import ee.webmedia.xtee.client.rr.types.ee.riik.xtee.rr.producers.producer.rr.RR42Response;
import ee.webmedia.xtee.client.rr.types.ee.riik.xtee.rr.producers.producer.rr.RR43Response;
import ee.webmedia.xtee.client.rr.types.ee.riik.xtee.rr.producers.producer.rr.RR52Response;
import ee.webmedia.xtee.client.rr.types.ee.riik.xtee.rr.producers.producer.rr.RR63ResponseV1;
import ee.webmedia.xtee.client.rr.types.ee.riik.xtee.rr.producers.producer.rr.RR81Response;
import ee.webmedia.xtee.client.rr.types.ee.riik.xtee.rr.producers.producer.rr.RR96ResponseV1;
import ee.webmedia.xtee.client.rr.types.ee.riik.xtee.rr.producers.producer.rr.RRExtDocumentDataRequest;
import ee.webmedia.xtee.client.rr.types.ee.riik.xtee.rr.producers.producer.rr.RRExtDocumentDataResponse;
import ee.webmedia.xtee.client.rr.types.ee.riik.xtee.rr.producers.producer.rr.RR52Response.Dok;
import ee.webmedia.xtee.client.rr.types.ee.riik.xtee.rr.producers.producer.rr.RR52Response.Isik;
import ee.webmedia.xtee.client.rr.types.ee.riik.xtee.rr.producers.producer.rr.RR72Response.TtIsikud;
import ee.webmedia.xtee.client.rr.types.ee.riik.xtee.rr.producers.producer.rr.RRExtDocumentDataRequest.Dokument;
import ee.webmedia.xtee.client.rr.types.ee.riik.xtee.rr.producers.producer.rr.RRExtDocumentDataRequest.Isikud;
import ee.webmedia.xtee.client.rr.types.ee.riik.xtee.rr.producers.producer.rr.RRExtDocumentDataRequest.Tegevus;
import ee.webmedia.xtee.client.rr.types.ee.riik.xtee.rr.producers.producer.rr.RRExtDocumentDataRequest.Dokument.KoostanudAsutus;
import ee.webmedia.xtee.client.test.BaseXTeeServiceImplTest;
import ee.webmedia.xtee.client.util.XmlBeansUtil;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.annotation.Resource;
import org.apache.xmlbeans.XmlString;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Roman Tekhov
 */
public class RrXTeeServiceImplTest extends BaseXTeeServiceImplTest {

  @Resource
  private RrXTeeServiceImpl rrXTeeServiceImpl;

  @Test
  public void findRR72Isik() throws XTeeServiceConsumptionException {
    List<TtIsikud.Item> items = rrXTeeServiceImpl.findRR72Isik("37109046017", "47702037790");

    Assert.assertNotNull(items);
    Assert.assertTrue("Person not found!", !items.isEmpty());

    TtIsikud.Item isik1 = items.get(0);
    Assert.assertEquals("\u017D\u0160\u017E\u0161", isik1.getTtIsikudCEesnimi());
    Assert.assertEquals("\u00C4\u00D5\u00D6\u00DC\u00E4\u00F5\u00F6\u00FC", isik1.getTtIsikudCPerenimi());

    TtIsikud.Item isik2 = items.get(1);
    Assert.assertEquals("KATI", isik2.getTtIsikudCEesnimi());
    Assert.assertEquals("\u00D6\u00D6VIIUL", isik2.getTtIsikudCPerenimi());
  }

  @Test
  public void RR63ResponseV1() throws XTeeServiceConsumptionException {
    String idCode = "38307070013";
    RR63ResponseV1 response = rrXTeeServiceImpl.findRR63IsikAadrDok("Tamm*", "A*", idCode, null);

    Assert.assertNotNull(response);
    Assert.assertTrue(response.getIsikuandmed().sizeOfItemArray() > 0);
  }

  @Test
  public void getRR81KMAisikkontroll() throws XTeeServiceConsumptionException {
    RR81Response response = rrXTeeServiceImpl.getRR81KMAisikkontroll("37707010014");

    Assert.assertNotNull(response);
    Assert.assertEquals("PEKKA", response.getEesnimi());
    Assert.assertEquals("01.07.1977", response.getSynniaeg());
  }

  @Test
  public void findRR40isikTaielikIsikukoodV1() throws XTeeServiceConsumptionException {
    RR40Response response = rrXTeeServiceImpl.findRR40isikTaielikIsikukood("37707010014");
    Assert.assertNotNull(response);
    Assert.assertEquals("SOOME", response.getIsik().getItemList().get(0).getIsikEmakeel());
    Assert.assertEquals("01.07.1977", response.getIsik().getItemList().get(0).getIsikSynniaeg());
    
  }

  @Test
  public void findRR42isikAadressKoodV1() throws XTeeServiceConsumptionException {
    RR42Response response =
        rrXTeeServiceImpl.findRR42isikAadressKood(new RR42RequestCallback() {

          public void populate(RR42Request paring) {
            XmlString isikukoodElement = XmlBeansUtil.getAttributedXmlString("37707010014");
            paring.xsetIsikukoodFragment(isikukoodElement);

          }
        });

    Assert.assertNotNull(response);
    Assert.assertEquals("SOOMLANE", response.getIsik().getItemList().get(0).getIsikPerekonnanimi());
    Assert.assertEquals("JOKKA", response.getIsik().getItemList().get(0).getIsikIsanimi());
    
  }

  @Test
  public void getRR52() throws XTeeServiceConsumptionException {
    RR52Response response = rrXTeeServiceImpl.getRR52("47707178682", "Olga", "Sidorova");

    Assert.assertNotNull(response);

    List<Dok.Item> dokList = response.getDok().getItemList();
    Assert.assertTrue(!dokList.isEmpty());
    Assert.assertEquals("ELAMISLUBA", dokList.get(0).getDokDokTyyp());

    List<Isik.Item> isikList = response.getIsik().getItemList();
    Assert.assertTrue(!isikList.isEmpty());
    Assert.assertEquals("OLGA", isikList.get(0).getIsikEesnimi());
  }
  
  @Test
  public void getRR43dokument() throws XTeeServiceConsumptionException {
    RR43Response response = rrXTeeServiceImpl.getRR43dokument("mingi dok number", "serria 1222", DokumendiTyyp.L_9);
    Assert.assertNotNull(response);

    List<ee.webmedia.xtee.client.rr.types.ee.riik.xtee.rr.producers.producer.rr.RR43Response.Dok.Item> dokList = response.getDok().getItemList();
    Assert.assertFalse(dokList.isEmpty());
    Assert.assertEquals("ELAMISLUBA", dokList.get(0).getDokDokTyyp());
  }
  
  @Test
  public void sendRRExtDocumentDataArkLuba() throws XTeeServiceConsumptionException {
	try{
	DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	RRExtDocumentDataRequest request=RRExtDocumentDataRequest.Factory.newInstance();
	request.setTegevus(Tegevus.K);
	
	Dokument dokument= request.addNewDokument();
	
	Kodif liik=dokument.addNewLiik();
	liik.setKood("D48");
	liik.setNimi("juhiluba");
	dokument.setLiik(liik);
	
	dokument.setDokumendiNumber("ET123456");
	
	Calendar valjandiseKp = Calendar.getInstance();
    valjandiseKp.setTime(dateFormat.parse("24.04.2009"));
	dokument.setValjaandmiseKP(valjandiseKp);
	
	Calendar joustumiseKP = Calendar.getInstance();
	joustumiseKP.setTime(dateFormat.parse("19.04.2009"));
	dokument.setJoustumiseKP(joustumiseKP);
	
	Calendar kehtivKuniKP =Calendar.getInstance();
	kehtivKuniKP.setTime(dateFormat.parse("19.04.2019"));
	dokument.setKehtivKuniKP(kehtivKuniKP);
	
	Calendar kehtetuAlatesKP=Calendar.getInstance();
	kehtetuAlatesKP.setTime(dateFormat.parse("13.12.2010"));
	dokument.setKehtetuAlatesKP(kehtetuAlatesKP);
	
	dokument.setAmetnikuIsikukood("48404172231");
	dokument.setAmetnikuNimed("Admin Admin");
	
	KoostanudAsutus koostanudAsutus=dokument.addNewKoostanudAsutus();
	
	Kodif riik=koostanudAsutus.addNewRiik();
	riik.setKood("EST");
	koostanudAsutus.setRiik(riik);
	
	koostanudAsutus.setAsutuseRegNumber("70001490");
	koostanudAsutus.setAsutuseNimi("Maanteeamet");
	
	dokument.setKoostanudAsutus(koostanudAsutus);
	
	request.setDokument(dokument);
	
	Isikud isikud= request.addNewIsikud();
	Isikud.Isik isik= isikud.addNewIsik();
	isik.setIsikukood("47707178682");
	isik.setEesnimi("OLGA");
	isik.setEesnimiRR("OLGA");
	isik.setPerenimi("SIDOROVA");
	isik.setPerenimiRR("SIDOROVA");
	isikud.setIsikArray(0, isik);
	
	request.setIsikud(isikud);
	RRExtDocumentDataResponse response= rrXTeeServiceImpl.sendRRExtDocumentDataArkLuba(request);  
	Assert.assertNotNull(response);
	Assert.assertTrue(response.getIsikud() != null);
	} catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  @Test
  public void getRR96isikDokElukSuhe() throws XTeeServiceConsumptionException {
    RR96ResponseV1 response = rrXTeeServiceImpl.getRR96isikDokElukSuhe("KAAREL", "METSAMEES", "32105258875", 5L);

    Assert.assertNotNull(response);
    Assert.assertNotNull(response.getIsikuandmed());
    Assert.assertTrue(response.getIsikuandmed().getItemList().size()>0);
    Assert.assertTrue(response.getIsikuandmed().getItemList().get(0).getIsikuandmedPerenimi().equalsIgnoreCase("METSAMEES"));
    
    checkXteeAnnotation(ee.webmedia.xtee.client.rr.types.ee.riik.xtee.rr.producers.producer.rr.RR96ResponseV1.Isikuandmed.Item.class);
  }  
}
