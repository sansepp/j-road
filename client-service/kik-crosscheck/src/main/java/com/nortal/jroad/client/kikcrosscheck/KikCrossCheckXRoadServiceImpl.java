package com.nortal.jroad.client.kikcrosscheck;


import com.nortal.jroad.client.exception.XRoadServiceConsumptionException;
import com.nortal.jroad.client.kikcrosscheck.database.KikCrosscheckXRoadDatabase;
import com.nortal.jroad.client.kikcrosscheck.types.ee.x_road.kikas.Projekt;
import com.nortal.jroad.client.kikcrosscheck.types.ee.x_road.kikas.ProjektRistkontrollDocument.ProjektRistkontroll;
import com.nortal.jroad.client.kikcrosscheck.types.ee.x_road.kikas.SisendTaotleja;
import com.nortal.jroad.client.kikcrosscheck.types.ee.x_road.kikas.Taotlejad;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author Priit Laht
 */
@Service("kikCrossCheckXRoadService")
public class KikCrossCheckXRoadServiceImpl implements KikCrossCheckXRoadService {
  @Resource
  private KikCrosscheckXRoadDatabase kikCrosscheckXRoadDatabase;

  @Override
  public List<Projekt> checkApplicantByRegNumber(String regNumber) throws XRoadServiceConsumptionException {
    ProjektRistkontroll projektRistkontroll = ProjektRistkontroll.Factory.newInstance();
    Taotlejad taotlejad = projektRistkontroll.addNewTaotlejad();
    SisendTaotleja taotleja = taotlejad.addNewSisendTaotleja();
    taotleja.setTaotlejaKood(regNumber);
    return kikCrosscheckXRoadDatabase.projektRistkontrollV1(projektRistkontroll).getProjektidList();
  }

  @Override
  public List<Projekt> checkApplicantByName(String name) throws XRoadServiceConsumptionException {
    ProjektRistkontroll projektRistkontroll = ProjektRistkontroll.Factory.newInstance();
    Taotlejad taotlejad = projektRistkontroll.addNewTaotlejad();
    SisendTaotleja taotleja = taotlejad.addNewSisendTaotleja();
    taotleja.setTaotlejaNimi(name);
    return kikCrosscheckXRoadDatabase.projektRistkontrollV1(projektRistkontroll).getProjektidList();
  }
}
