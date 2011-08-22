package ee.webmedia.xtee.model;

import java.util.List;

/**
 * Abstraction of a request or response message. Contains the header, body and attachments of a message.
 * 
 * @author Dmitri Danilkin
 * @param <T> type of body element
 */
public interface XTeeMessage<T> {
  XTeeHeader getHeader();

  T getContent();

  void setContent(T content);

  List<XTeeAttachment> getAttachments();
}
