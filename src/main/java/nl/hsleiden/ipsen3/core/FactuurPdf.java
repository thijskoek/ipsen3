package nl.hsleiden.ipsen3.core;

import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.omg.IOP.Encoding;

/**
 * @author Brandon van Wijk
 * Deze klasse maakt een pdf aan van de factuur die opgeslagen wordt in het
 * Factuuroverzicht. Ook staan de gevens van het lid erop en de informatie die
 * Het lid nodig heeft om te kunnen betalen.
 */

public class FactuurPdf {
    /**
     * attributen
     */
    private Factuur factuur;
    private List<Factuurregel> factuurregels = new ArrayList<>();
    private Debiteur debiteur;
    private Document document;
    private Paragraph preface;
    private Paragraph preface2;
    private Double totaalbedrag = 0.0;
    private String FILE;
    private PdfWriter writer;
    private Rectangle rect;
    protected final static Font FONT_SIZE_11_BOLD = new Font(Font.FontFamily.HELVETICA, 18f, Font.BOLD);
    protected final static Font font14 = new Font(Font.FontFamily.HELVETICA, 12f, Font.BOLD);
    protected final static Font font12 = new Font(Font.FontFamily.HELVETICA, 11f, Font.NORMAL);


    //private ArrayList<Settings> settings = new ArrayList<>();

    /**
     * @author Brandon van Wijk
     * De constructor maakt een factuur file aan en vult deze met de benodigde informatie
     * @param factuur
     * @param factuurregels
     * @param debiteur
     */
    public FactuurPdf(Factuur factuur, List<Factuurregel> factuurregels, Debiteur debiteur) {
        this.factuur = factuur;
        this.factuurregels = factuurregels;
        this.debiteur = debiteur;

        try {
            FILE = "" + factuur.getFactuurnummer()+"-"+ debiteur.getNaam() +".pdf";
            //factuur.setPDF(this);
            //String pdfPath = FILE;
            factuur.setPdfPath(FILE);
            this.document = new Document();
            this.writer = PdfWriter.getInstance(document, new FileOutputStream(FILE));
            rect = new Rectangle(30, 30, 550, 800);
            writer.setBoxSize("art", rect);
            document.open();
            addTitlePage(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Brandon van Wijk
     * Deze methode vult de pdf met alle benodigde info
     * De gegevens van het lid en de gegevens van de bestelling.
     * @param document
     * @throws Exception
     */
    private void addTitlePage(Document document) throws Exception {
        

        Image image1 = Image.getInstance("C:/Users/Brandon/Desktop/logo_lions.jpg");
        document.add(image1);
        Paragraph lionsInfo = new Paragraph();
        addEmptyLine(lionsInfo,1);
        lionsInfo.add(new Paragraph("Lions Club Oegstgeest"));
        lionsInfo.add(new Paragraph("KVK: 123456789"));
        lionsInfo.add(new Paragraph("Btwnummer: 645677"));
        lionsInfo.add(new Paragraph("IBAN: NL76RABO4567898455"));
        addEmptyLine(lionsInfo,1);
        document.add(lionsInfo);

        preface = new Paragraph();

        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Naam: " + debiteur.getAanhef() + " " + debiteur.getVoornaam() + " " + debiteur.getTussenvoegsel() + " " + debiteur.getNaam()));
        preface.add(new Paragraph("Adres: " + debiteur.getAdres()));
        preface.add(new Paragraph("Woonplaats: " + debiteur.getWoonplaats() + " " + debiteur.getPostcode()));
        addEmptyLine(preface, 3);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        preface.add(new Paragraph("Factuur", FONT_SIZE_11_BOLD));
        preface.setAlignment(Element.ALIGN_LEFT);

        addEmptyLine(preface, 1);
        document.add(preface);

        createFactuurInfoTable();

        preface2 = new Paragraph();
        addEmptyLine(preface2, 1);
        document.add(preface2);

        createTable();
        showOpmerking();
        onEndPage();
    }

    /**
     * @author Brandon van Wijk
     * Deze methode voegt de opmerking die ingevuld is bij
     * Het aanmaken van de factuur toe aan het pdf document
     * @throws Exception
     */
    public void showOpmerking() throws  Exception {
        Paragraph opmerking = new Paragraph();
        addEmptyLine(opmerking, 2);
        opmerking.add(new Paragraph(factuur.getOpmerking()));
        document.add(opmerking);
    }

    /**
     * @author Brandon van Wijk
     * Deze methode maakt de table in waar alle orderegels instaan
     * Zoals de producten die zijn besteld, het aantal ervan, de prijs
     * Per product en het uiteindelijke edrag van de hele bestelling
     * @throws Exception
     */
    private void createTable() throws Exception {
        PdfPTable table = new PdfPTable(4);

        PdfPCell productnummer = new PdfPCell(new Phrase("Productnr.", font12));
        PdfPCell aantal = new PdfPCell(new Phrase("Aantal", font12));
        PdfPCell omschrijving = new PdfPCell(new Phrase("Omschrijving", font12));
        PdfPCell prijs = new PdfPCell(new Phrase("Prijs", font12));

        productnummer.setBorder(Rectangle.BOTTOM);
        aantal.setBorder(Rectangle.BOTTOM);
        omschrijving.setBorder(Rectangle.BOTTOM);
        prijs.setBorder(Rectangle.BOTTOM);

        table.addCell(productnummer);
        table.addCell(aantal);
        table.addCell(omschrijving);
        table.addCell(prijs);

        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.GERMAN);
        otherSymbols.setDecimalSeparator(',');
        otherSymbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", otherSymbols);

//        for(Factuurregel factuurregel: factuurregels) {
//            table.addCell(String.valueOf(factuurregel.getWijn().getProductnummer()));
//            table.addCell(factuurregel.getWijn().getNaam());
//            table.addCell(String.valueOf(factuurregel.getAantal()));
//            table.addCell("\u20ac " + String.valueOf(df.format(calculatePrice(factuurregel))));
//        }

        for(Factuurregel factuurregel: factuurregels) {
            productnummer = new PdfPCell(new Phrase(Phrase.getInstance(String.valueOf(factuurregel.getWijn().getProductnummer()))));
            productnummer.setBorder(Rectangle.NO_BORDER);

            aantal = new PdfPCell(new Phrase(String.valueOf(factuurregel.getAantal())));
            aantal.setBorder(Rectangle.NO_BORDER);

            omschrijving = new PdfPCell(new Phrase(String.valueOf(factuurregel.getWijn().getNaam())));
            omschrijving.setBorder(Rectangle.NO_BORDER);

            prijs = new PdfPCell(new Phrase(String.valueOf(df.format(calculatePrice(factuurregel)))));
            prijs.setBorder(Rectangle.NO_BORDER);


            table.addCell(productnummer);
            table.addCell(aantal);
            table.addCell(omschrijving);
            table.addCell(prijs);

        }

        productnummer = new PdfPCell(new Phrase(Phrase.getInstance("")));
        productnummer.setBorder(Rectangle.NO_BORDER);

        aantal = new PdfPCell(new Phrase(String.valueOf("")));
        aantal.setBorder(Rectangle.NO_BORDER);

        omschrijving = new PdfPCell(new Phrase(String.valueOf("")));
        omschrijving.setBorder(Rectangle.NO_BORDER);

        prijs = new PdfPCell(new Phrase(String.valueOf("")));
        prijs.setBorder(Rectangle.NO_BORDER);

        table.addCell(productnummer);
        table.addCell(aantal);
        table.addCell(omschrijving);
        table.addCell(prijs);

        productnummer = new PdfPCell(new Phrase(Phrase.getInstance("")));
        productnummer.setBorder(Rectangle.NO_BORDER);

        aantal = new PdfPCell(new Phrase(String.valueOf("")));
        aantal.setBorder(Rectangle.NO_BORDER);

        omschrijving = new PdfPCell(new Phrase(String.valueOf("Totaal")));
        omschrijving.setBorder(Rectangle.NO_BORDER);

        prijs = new PdfPCell(new Phrase(String.valueOf(calculateTotalPrice(factuurregels))));
        prijs.setBorder(Rectangle.TOP);

        table.addCell(productnummer);
        table.addCell(aantal);
        table.addCell(omschrijving);
        table.addCell(prijs);

        table.setWidthPercentage(100);
        //createFooter(table, this.factuurregels);
        document.add(table);
    }

    private void createFactuurInfoTable() throws DocumentException {
        PdfPTable factuurInfo = new PdfPTable(4);

        PdfPCell factuurnummer = new PdfPCell(new Phrase("Factuurnummer", font14));
        PdfPCell factuurdatum = new PdfPCell(new Phrase("Factuurdatum", font14));
        PdfPCell vervaldatum = new PdfPCell(new Phrase("Vervaldatum", font14));
        PdfPCell lidnummer = new PdfPCell(new Phrase("Lidnummer", font14));

        factuurnummer.setBorder(Rectangle.NO_BORDER);
        factuurdatum.setBorder(Rectangle.NO_BORDER);
        vervaldatum.setBorder(Rectangle.NO_BORDER);
        lidnummer.setBorder(Rectangle.NO_BORDER);

        factuurInfo.addCell(factuurnummer);
        factuurInfo.addCell(factuurdatum);
        factuurInfo.addCell(vervaldatum);
        factuurInfo.addCell(lidnummer);

        factuurnummer = new PdfPCell(new Phrase(Phrase.getInstance(String.valueOf(factuur.getFactuurnummer()))));
        factuurnummer.setBorder(Rectangle.NO_BORDER);
        factuurInfo.addCell(factuurnummer);

        factuurdatum = new PdfPCell(new Phrase("27-01-2016"));
        factuurdatum.setBorder(Rectangle.NO_BORDER);
        factuurInfo.addCell(factuurdatum);

        vervaldatum = new PdfPCell(new Phrase("27-02-2016"));
        vervaldatum.setBorder(Rectangle.NO_BORDER);
        factuurInfo.addCell(vervaldatum);

        lidnummer = new PdfPCell(new Phrase(Phrase.getInstance(String.valueOf(debiteur.getId()))));
        lidnummer.setBorder(Rectangle.NO_BORDER);
        factuurInfo.addCell(lidnummer);

        factuurInfo.setWidthPercentage(100);
        document.add(factuurInfo);
    }

    /**
     * @author Brandon van Wijk
     * Deze methode maakt de footer aan van de tabel met factuurregels
     * Hier staat het totaalbedrag in van de bestelling.
     * @param table
     * @param factuurregels
     */
    public void createFooter(PdfPTable table, List<Factuurregel> factuurregels) {
        char symbol = '\u20ac';
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell(symbol + " " + calculateTotalPrice(factuurregels));
    }

    /**
     * @author Brandon van Wijk
     * Deze methode berekend de prijs van een bestelde wijn
     * Doormiddel van de prijs van het product en het aantal dat daarbij hoort.
     * @param regel
     * @return de totaalprijs per wijn
     */
    public double calculatePrice(Factuurregel regel) {
        double prijs;
        prijs = regel.getAantal() * regel.getWijn().getPrijs();
        DecimalFormat df = new DecimalFormat("0.00");
        df.format(prijs);
        return prijs;
    }

    /**
     * @author Brandon van Wijk
     * Deze methode berekend de totaalprijs van de bestelling die
     * Het lid heeft gedaan. Hij loopt langs alle factuurregels en
     * telt de prijs per product en aantal op bij de totaalprijs.
     * @param factuurregels
     * @return totaalprijs van bestelling
     */
    public String calculateTotalPrice(List<Factuurregel> factuurregels) {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.GERMAN);
        otherSymbols.setDecimalSeparator(',');
        otherSymbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", otherSymbols);
        double prijs = 0.00;
        for(Factuurregel factuurregel: factuurregels) {
            prijs += factuurregel.getWijn().getPrijs() * factuurregel .getAantal();
        }
        return df.format(prijs);
    }

    /**
     * @author Brandon van Wijk
     * Deze methode maakt de header aan van de tabel met factuurregels
     * @param table
     */
    public void createHeader(PdfPTable table) {
        table.addCell(new Phrase("Productnr.", font14));
        table.addCell(new Phrase("Omschrijving", font14));
        table.addCell(new Phrase("Aantal", font14));
        table.addCell(new Phrase("Prijs", font14));
    }

    /**
     * @author Brandon van Wijk
     * Deze methode maakt de footer aan van het document
     * Hier staan een aantal belangrijke gegevens in die nodig zijn
     * Zodat een lid zijn bestelling kan betalen.
     * @throws Exception
     */
    public void onEndPage() throws Exception {
        //this.settings = new SettingsDAO().getAllSettings();
        LineSeparator ls = new LineSeparator();
        document.add(new Chunk(ls));
        rect = writer.getBoxSize("art");
//        for (Settings setting : settings) {
//            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase(setting.getBedrijfsnaam()),
//                    document.leftMargin() - 1, document.bottom() + 90, 0);
//            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("Bankrekening "+setting.getIban()),
//                    document.leftMargin() - 1, document.bottom() + 70, 0);
//            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("Inschrijfnummer KvK Rijnland: "+setting.getKvK()),
//                    document.leftMargin() - 1, document.bottom() + 50, 0);
//        }
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("Gelieve binnen 14 dagen betalen"), document.leftMargin() - 1, document.bottom() + 50,0);
    }

    /**
     * @author Brandon van Wijk
     * Deze methode kun je aanroepen als je een of meerdere witregels
     * Wilt toevoegen aan het document. Het aantal witregels dat gewenst is
     * Kan je simpel meegeven als parameter bij de aanroep van de methode.
     * @param paragraph
     * @param number
     */
    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}