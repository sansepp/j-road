package com.nortal.jroad.client.ariregv6;

import com.nortal.jroad.client.ariregv6.requests.DetailAndmedV1Status;
import com.nortal.jroad.client.ariregv6.requests.DetailandmedV1Request;
import com.nortal.jroad.client.ariregv6.requests.DetailandmedV1ValidGeneralJuridicalRequest;
import com.nortal.jroad.client.ariregv6.requests.DetailandmedV1ValidGeneralPersonRequest;
import com.nortal.jroad.client.ariregv6.requests.DetailandmedV1ValidGeneralRegCodeRequest;
import com.nortal.jroad.client.ariregv6.types.eu.x_road.arireg.producer.DetailandmedV5Ettevotja;
import com.nortal.jroad.client.exception.XRoadServiceConsumptionException;
import com.nortal.jroad.client.test.BaseXRoadServiceImplTest;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Priit Laht
 */
public class AriregXRoadServiceImplTest extends BaseXRoadServiceImplTest {
  @Resource
  private AriregXRoadService ariregXRoadService;

  @Test
  public void testFindDetailandmedV1_regCode() throws XRoadServiceConsumptionException {
    DetailandmedV1Request request = new DetailandmedV1ValidGeneralRegCodeRequest(11508836, DetailAndmedV1Status.R);
    List<DetailandmedV5Ettevotja> detailandmedV1 = ariregXRoadService.findDetailandmedV1(request);
    Assert.assertFalse(detailandmedV1.isEmpty());
  }

  @Test
  public void testFindDetailandmedV1_personalCode() throws XRoadServiceConsumptionException {
    DetailandmedV1Request request = new DetailandmedV1ValidGeneralPersonRequest("37312246029");
    List<DetailandmedV5Ettevotja> detailandmedV1 = ariregXRoadService.findDetailandmedV1(request);
    Assert.assertFalse(detailandmedV1.isEmpty());
  }

  @Test
  public void testFindDetailandmedV1_juridicalCode() throws XRoadServiceConsumptionException {
    DetailandmedV1Request request = new DetailandmedV1ValidGeneralJuridicalRequest("70000740", DetailAndmedV1Status.R);
    List<DetailandmedV5Ettevotja> detailandmedV1 = ariregXRoadService.findDetailandmedV1(request);
    Assert.assertFalse(detailandmedV1.isEmpty());
  }
}