package com.nortal.jroad.client.ariregv6;

import com.nortal.jroad.client.ariregv6.requests.DetailandmedV1Request;
import com.nortal.jroad.client.ariregv6.types.eu.x_road.arireg.producer.DetailandmedV5Ettevotja;
import com.nortal.jroad.client.exception.XRoadServiceConsumptionException;
import java.util.List;

/**
 * @author Priit Laht
 */
public interface AriregXRoadService {
  List<DetailandmedV5Ettevotja> findDetailandmedV1(DetailandmedV1Request request) throws XRoadServiceConsumptionException;
}
