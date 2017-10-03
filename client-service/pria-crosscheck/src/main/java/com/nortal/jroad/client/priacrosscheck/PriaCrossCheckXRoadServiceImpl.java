package com.nortal.jroad.client.priacrosscheck;

import com.nortal.jroad.client.exception.XRoadServiceConsumptionException;
import com.nortal.jroad.client.priacrosscheck.database.PriaCrosscheckXRoadDatabase;
import com.nortal.jroad.client.priacrosscheck.types.ee.riik.xtee.mait.producers.producer.mait.Kulurida;
import com.nortal.jroad.client.priacrosscheck.types.ee.riik.xtee.mait.producers.producer.mait.KulutusRistkontrollRequest;
import com.nortal.jroad.client.priacrosscheck.types.ee.riik.xtee.mait.producers.producer.mait.Projekt;
import com.nortal.jroad.client.priacrosscheck.types.ee.riik.xtee.mait.producers.producer.mait.ProjektRistkontrollRequest;
import com.nortal.jroad.client.priacrosscheck.types.ee.riik.xtee.mait.producers.producer.mait.Taotleja;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author Priit Laht
 */
@Service("priaCrossCheckXRoadService")
public class PriaCrossCheckXRoadServiceImpl implements PriaCrossCheckXRoadService {
  @Resource
  private PriaCrosscheckXRoadDatabase priaCrosscheckXRoadDatabase;

  @Override
  public List<Projekt> checkApplicant(String applicantName) throws XRoadServiceConsumptionException {
    ProjektRistkontrollRequest request = ProjektRistkontrollRequest.Factory.newInstance();
    Taotleja applicant = request.addNewTaotleja();
    applicant.setNimi(applicantName);
    return priaCrosscheckXRoadDatabase.projektRistkontroll(request).getProjektList();
  }

  @Override
  public List<Projekt> checkApplicant(long applicantRegNumber) throws XRoadServiceConsumptionException {
    ProjektRistkontrollRequest request = ProjektRistkontrollRequest.Factory.newInstance();
    Taotleja applicant = request.addNewTaotleja();
    applicant.setKood(applicantRegNumber);
    return priaCrosscheckXRoadDatabase.projektRistkontroll(request).getProjektList();
  }

  @Override
  public List<Kulurida> checkExpense(Date expenseDate, BigDecimal expenseSum) throws XRoadServiceConsumptionException {
    KulutusRistkontrollRequest request = KulutusRistkontrollRequest.Factory.newInstance();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(expenseDate);
    request.setKuupaev(calendar);
    request.setReaSumma(expenseSum);
    return priaCrosscheckXRoadDatabase.kulutusRistkontroll(request).getKuluridaList();
  }
}
