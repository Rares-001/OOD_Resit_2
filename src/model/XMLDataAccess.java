package model;

import model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XMLDataAccess implements DataAccessInterface
{

    private static final String SHOW_TITLE = "showtitle";
    private static final String SLIDE_TITLE = "title";
    private static final String SLIDE = "slide";
    private static final String ITEM = "item";
    private static final String LEVEL = "level";
    private static final String KIND = "kind";
    private static final String TEXT = "text";
    private static final String IMAGE = "image";

    public XMLDataAccess()
    {
        Style.createStyles();
    }

    @Override
    public PresentationModel loadPresentation(String filename) throws IOException
    {
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
            for (int j = 0; j < items.getLength(); j++)
            {
                Element itemElement = (Element) items.item(j);
                slide.addItem(createSlideItem(itemElement));
            }

            presentation.addSlide(slide);
        }

        return presentation;
    }

    private Document parseXMLFile(String filename) throws IOException
    {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return builder.parse(new File(filename));
        } catch (Exception e)
        {
            throw new IOException("Error parsing XML file", e);
        }
    }

    private String getElementContent(Element element, String tagName)
    {
        NodeList nodes = element.getElementsByTagName(tagName);
        if (nodes.getLength() == 0)
        {
            return null;
        }

        return nodes.item(0).getTextContent();
    }

    private SlideItemModel createSlideItem(Element itemElement)
    {
        int itemLevel = Integer.parseInt(itemElement.getAttribute(LEVEL));
        itemLevel = Math.max(itemLevel, 1);

        String itemType = itemElement.getAttribute(KIND);
        String itemContent = itemElement.getTextContent();
        Style itemStyle = Style.getStyle(itemLevel);

        System.out.println("Creating SlideItem: Level=" + itemLevel + ", Content=\"" + itemContent + "\"");

        if (TEXT.equals(itemType))
        {
            return new TextItemModel(itemLevel, itemContent, itemStyle);
        } else if (IMAGE.equals(itemType))
        {
            return new BitmapItemModel(itemLevel, itemContent);
        } else {
            throw new IllegalArgumentException("Unknown slide item type: " + itemType);
        }
    }

    public void savePresentation(PresentationModel presentation, String filename) throws IOException
    {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.newDocument();

            Element presentationElement = document.createElement("presentation");
            document.appendChild(presentationElement);

            Element showTitleElement = document.createElement(SHOW_TITLE);
            showTitleElement.setTextContent(presentation.getTitle());
            presentationElement.appendChild(showTitleElement);

            for (SlideModel slide : presentation.getSlides())
            {
                Element slideElement = document.createElement(SLIDE);
                Element titleElement = document.createElement(SLIDE_TITLE);
                titleElement.setTextContent(slide.getTitle());
                slideElement.appendChild(titleElement);

                for (SlideItemModel item : slide.getItems())
                {
                    Element itemElement = document.createElement(ITEM);
                    itemElement.setAttribute(LEVEL, String.valueOf(item.getLevel()));

                    if (item instanceof TextItemModel)
                    {
                        itemElement.setAttribute(KIND, TEXT);
                        itemElement.setTextContent(((TextItemModel) item).getText());
                    } else if (item instanceof BitmapItemModel)
                    {
                        itemElement.setAttribute(KIND, IMAGE);
                        itemElement.setTextContent(((BitmapItemModel) item).getImagePath());
                    }
                    slideElement.appendChild(itemElement);
                }
                presentationElement.appendChild(slideElement);
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(new File(filename)));
        } catch (TransformerException | ParserConfigurationException e)
        {
            throw new IOException("Error saving XML file", e);
        }
    }
}
