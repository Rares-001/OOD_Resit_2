package model;

import model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XMLDataAccess {

    private static final String SHOW_TITLE = "showtitle";
    private static final String SLIDE_TITLE = "title";
    private static final String SLIDE = "slide";
    private static final String ITEM = "item";
    private static final String LEVEL = "level";
    private static final String KIND = "kind";
    private static final String TEXT = "text";
    private static final String IMAGE = "image";

    // Load a presentation from an XML file
    public PresentationModel load(String filename) throws IOException {
        PresentationModel presentation = new PresentationModel();
        Document document = parseXMLFile(filename);

        Element docElement = document.getDocumentElement();
        presentation.setTitle(getElementContent(docElement, SHOW_TITLE));

        NodeList slides = docElement.getElementsByTagName(SLIDE);
        for (int i = 0; i < slides.getLength(); i++) {
            Element slideElement = (Element) slides.item(i);
            SlideModel slide = new SlideModel();
            slide.setTitle(getElementContent(slideElement, SLIDE_TITLE));

            NodeList items = slideElement.getElementsByTagName(ITEM);
            for (int j = 0; j < items.getLength(); j++) {
                Element itemElement = (Element) items.item(j);
                slide.addItem(createSlideItem(itemElement));
            }

            presentation.addSlide(slide);
        }
        return presentation;
    }

    // Helper method to parse XML file
    private Document parseXMLFile(String filename) throws IOException {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return builder.parse(new File(filename));
        } catch (Exception e) {
            throw new IOException("Error parsing XML file", e);
        }
    }

    // Helper method to get content of an XML element
    private String getElementContent(Element element, String tagName) {
        NodeList nodes = element.getElementsByTagName(tagName);
        if (nodes.getLength() == 0) {
            return null;
        }
        return nodes.item(0).getTextContent();
    }

    // Helper method to create SlideItem from XML element
    private SlideItemModel createSlideItem(Element itemElement) {
        int itemLevel = Integer.parseInt(itemElement.getAttribute(LEVEL));
        String itemType = itemElement.getAttribute(KIND);
        String itemContent = itemElement.getTextContent();

        if (TEXT.equals(itemType)) {
            return new TextItemModel(itemLevel, itemContent);
        } else if (IMAGE.equals(itemType)) {
            return new BitmapItemModel(itemLevel, itemContent);
        } else {
            throw new IllegalArgumentException("Unknown slide item type: " + itemType);
        }
    }

    // Save a presentation to an XML file
    public void save(PresentationModel presentation, String filename) throws IOException {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.newDocument();

            // Start building the XML structure
            Element presentationElement = document.createElement("presentation");
            document.appendChild(presentationElement);

            // Add show title
            Element showTitleElement = document.createElement(SHOW_TITLE);
            showTitleElement.setTextContent(presentation.getTitle());
            presentationElement.appendChild(showTitleElement);

            // Add slides
            for (SlideModel slide : presentation.getSlides()) {
                Element slideElement = document.createElement(SLIDE);
                Element titleElement = document.createElement(SLIDE_TITLE);
                titleElement.setTextContent(slide.getTitle());
                slideElement.appendChild(titleElement);

                // Add items to slide
                for (SlideItemModel item : slide.getItems()) {
                    Element itemElement = document.createElement(ITEM);
                    itemElement.setAttribute(LEVEL, String.valueOf(item.getLevel()));

                    if (item instanceof TextItemModel) {
                        itemElement.setAttribute(KIND, TEXT);
                        itemElement.setTextContent(((TextItemModel) item).getText());
                    } else if (item instanceof BitmapItemModel) {
                        itemElement.setAttribute(KIND, IMAGE);
                        itemElement.setTextContent(((BitmapItemModel) item).getImagePath());
                    }
                    slideElement.appendChild(itemElement);
                }

                presentationElement.appendChild(slideElement);
            }

            // Write to file
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(new File(filename)));
        } catch (TransformerException e) {
            throw new IOException("Error saving XML file", e);
        }
    }
}
