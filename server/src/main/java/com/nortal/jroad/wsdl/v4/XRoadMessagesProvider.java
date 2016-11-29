package com.nortal.jroad.wsdl.v4;

import org.springframework.util.Assert;
import org.springframework.ws.wsdl.wsdl11.provider.SuffixBasedMessagesProvider;
import org.w3c.dom.Element;

import javax.wsdl.Definition;
import javax.wsdl.Part;
import javax.wsdl.WSDLException;
import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Aleksei Bogdanov (aleksei.bogdanov@nortal.com)
 */
public class XRoadMessagesProvider extends SuffixBasedMessagesProvider {
    private final Map<String, QName> elementTypeMap = new HashMap<String, QName>();
    private int nscounter = 1;

    @Override
    protected void populatePart(Definition definition, Part part, QName elementName) throws WSDLException {
        QName typeName = elementTypeMap.get(elementName.getLocalPart());
        part.setElementName(typeName);
        part.setName(elementName.getLocalPart());
        if (definition.getPrefix(typeName.getNamespaceURI()) == null) {
            definition.addNamespace("xns" + nscounter++, typeName.getNamespaceURI());
        }
    }

    protected boolean isMessageElementBase(Element element) {
        boolean isMessageElement = "element".equals(element.getLocalName())
                && "http://www.w3.org/2001/XMLSchema".equals(element.getNamespaceURI());
        if (!isMessageElement) {
            return false;
        } else {
            String elementName = this.getElementName(element);
            Assert.hasText(elementName, "Element has no name");
            return !element.hasChildNodes();
        }
    }

    @Override
    protected boolean isMessageElement(Element element) {
        boolean result = isMessageElementBase(element);
        if (result) {
            String[] prefixAndType = element.getAttribute("type").split(":");
            String ns;
            String name = element.getAttribute("name");
            if (prefixAndType.length > 1) {
                ns = element.lookupNamespaceURI(prefixAndType[0]);
            } else {
                ns = element.lookupNamespaceURI(null);
            }
            elementTypeMap.put(element.getAttribute("name"), ns == null ? new QName(name) : new QName(ns, name));
        }
        return result;
    }
}