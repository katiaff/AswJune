package asw.uniovi.june.citizensLoader.letters;

import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import persistence.User;


public class PDFLetter implements LetterGenerator{

	/**
	 * Writes a pdf letter for each User user
	 */
	public void generateLetter(User user) throws IOException {
        PdfWriter writer = new PdfWriter("PersonalLetters/"+user.getEmail()+".pdf");
 
        PdfDocument pdf = new PdfDocument(writer);
 
        Document document = new Document(pdf);
        document.add(new Paragraph("User: " + user.getEmail()+ "\nPassword: "+user.getPassword()));
        document.close();
	}
}
