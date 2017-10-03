package com.nortal.jroad.client.priacrosscheck;

import com.nortal.jroad.client.exception.XRoadServiceConsumptionException;
import com.nortal.jroad.client.priacrosscheck.types.ee.riik.xtee.mait.producers.producer.mait.Projekt;
import com.nortal.jroad.client.test.BaseXRoadServiceImplTest;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Priit Laht
 */
public class PriaCrossCheckXRoadServiceImplTest extends BaseXRoadServiceImplTest {
  @Resource
  private PriaCrossCheckXRoadService priaCrossCheckXRoadService;

  @Test
  public void testCheckApplicantByRegNumber() throws XRoadServiceConsumptionException {
    List<Projekt> projects = priaCrossCheckXRoadService.checkApplicantByRegNumber(11493046);
    Assert.assertFalse(projects.isEmpty());
  }

  @Test
  public void testCheckApplicantByRegNumber_validNumber() throws XRoadServiceConsumptionException {
    List<Projekt> projects = priaCrossCheckXRoadService.checkApplicantByRegNumber("11493046");
    Assert.assertFalse(projects.isEmpty());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCheckApplicantByRegNumber_invalidNumber() throws XRoadServiceConsumptionException {
    priaCrossCheckXRoadService.checkApplicantByRegNumber("TEST_STRING");
  }

  @Test
  public void testCheckApplicantByName() throws XRoadServiceConsumptionException {
    List<Projekt> projects = priaCrossCheckXRoadService.checkApplicantByName("OÜ FOODEST BALTIC");
    Assert.assertFalse(projects.isEmpty());
  }
}