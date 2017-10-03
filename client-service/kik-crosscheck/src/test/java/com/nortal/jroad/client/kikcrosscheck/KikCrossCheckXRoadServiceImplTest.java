package com.nortal.jroad.client.kikcrosscheck;

import com.nortal.jroad.client.exception.XRoadServiceConsumptionException;
import com.nortal.jroad.client.kikcrosscheck.types.ee.x_road.kikas.Projekt;
import com.nortal.jroad.client.test.BaseXRoadServiceImplTest;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Priit Laht
 */
public class KikCrossCheckXRoadServiceImplTest extends BaseXRoadServiceImplTest {
  @Resource
  private KikCrossCheckXRoadService kikCrossCheckXRoadService;

  @Test
  public void testCheckApplicantByRegNumber() throws XRoadServiceConsumptionException {
    List<Projekt> projects = kikCrossCheckXRoadService.checkApplicantByRegNumber("9999999");
    Assert.assertTrue(projects.isEmpty());
  }

  @Test
  public void testCheckApplicantByName() throws XRoadServiceConsumptionException {
    List<Projekt> projects = kikCrossCheckXRoadService.checkApplicantByName("TEST STRING");
    Assert.assertTrue(projects.isEmpty());
  }
}