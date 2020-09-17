package util;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fon.elab.prodavnica.dao.impl.EmailServiceImpl;
import fon.elab.prodavnica.domain.Narudzbina;
import fon.elab.prodavnica.domain.StavkaNarudzbine;

public class Utility {
	private static Utility instance;
	@Autowired
	EmailServiceImpl emailServiceImpl;
	
	private Utility() {
		// TODO Auto-generated constructor stub
	}
	public static Utility getInstance () {
		if(instance == null) instance = new Utility();
		return instance;
	}
	public String generisiNoviNazivSlike() {
		String naziv = "kat_";
		naziv += UUID.randomUUID().toString();
		naziv += ".jpg";
		System.out.println("Novi naziv slike: "+naziv);
		return naziv;
	}
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}
	
	public void sendMailWithAttachment(Narudzbina narudzbina) throws DocumentException, MalformedURLException, IOException {
		Document document = new Document();
		String fileName = narudzbina.getId()+"-NAR-"+narudzbina.getKupac().getIme()+".pdf";
		String path = "C:\\Users\\Asus\\Desktop\\masterrad\\"+fileName;
		PdfWriter.getInstance(document, new FileOutputStream(path));
		 
		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 19, BaseColor.BLACK);
		Font font2 = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		Paragraph p = new Paragraph();
		p.setFont(font);
		p.setAlignment(Element.ALIGN_CENTER);
		p.add("E-spajz prodavnica domace hrane");
//		document.add(p);
		Image image = Image.getInstance("C:\\Users\\Asus\\Desktop\\masterrad\\logo.png");
		
		int indentation = 0;
		//whatever
		float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
		               - document.rightMargin() - indentation) / image.getWidth()) * 100;

		image.scalePercent(scaler);
		p.add(image);
		document.add(p);
		String info = "Datum kreiranja: "+narudzbina.getDatumKreiranja()+"\n";
		String ukupanIznos = "Ukupan iznos: "+narudzbina.getUkupnaVrednost()+"\n";
		String adresa = "Adresa isporuke: "+narudzbina.getUlica();
		String grad = "Grad: "+narudzbina.getGrad().getNaziv();
		String ptt = "Ptt: "+narudzbina.getGrad().getPtt();
		String isporuka = "Nacin isporuke: "+narudzbina.getNacinIsporuke()+"\n";
		String prodavac = narudzbina.getProdavac().getIme()+" "+narudzbina.getProdavac().getPrezime()+"\n";
		String ishod = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		
		if(narudzbina.isOdobrena()) {
			ishod = "Vasa narudzbina je odobrena! ";
			String datum = sdf.format(narudzbina.getDatumOdobrenja());
			ishod += "Datum odobravanja: "+datum+"\n";
		}else {
			ishod = "Prodavac se izvinjava, ali mora da otkaze vasu narudzbinu. ";
			String datum = sdf.format(narudzbina.getDatumOdbijanja());
			ishod += "Datum odbijanja: "+datum+"\n";
			
		}
		
		Paragraph p2 = new Paragraph();
		
		p2.setFont(font);
		p2.add("Odgovor od prodavca: "+prodavac+" je stigao."+"\n");
		p2.add(ishod);
		
		p2.add("\n\n\nOstale informacije o narudzbini\n");
		document.add(p2);
		Paragraph p3 = new Paragraph();
		p3.setFont(font2);
		p3.add(info);
		p3.add(ukupanIznos);
		p3.add(adresa+", "+grad+ " ("+ptt+")"+"\n");
		p3.add(isporuka+"\n\n");
		document.add(p3);
		
		PdfPTable table = new PdfPTable(3);
		addTableHeader(table);
		addRows(table,narudzbina);
		document.add(table);
		//emailServiceImpl.sendMessageWithAttachment(to, subject, text, pathToAttachment);
		document.close();
		String poruka = "Postovani "+narudzbina.getKupac().getIme() + " , prodavac "+ narudzbina.getProdavac().getIme() + " "+ narudzbina.getProdavac().getPrezime() + " kod kog ste narucili ukupno "+narudzbina.getStavke().size()+ " artikala je odgovorio na vas zahtev. Pogledajte dokument koji smo vam poslali.";
		try {
			emailServiceImpl.sendMessageWithAttachment(narudzbina.getKupac().getEmail(), "Obavestenje o narudzbi", poruka , path);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addTableHeader(PdfPTable table) {
	    Stream.of("Artikal", "Kolicina", "Cena")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(2);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
	}
	private void addRows(PdfPTable table, Narudzbina narudzbina) {
		for(StavkaNarudzbine sn : narudzbina.getStavke()) {
			table.addCell(sn.getArtikal().getNaziv());
			table.addCell(sn.getKolicina()+"");
			table.addCell(sn.getCenaStavke() + " din.");	
		}
			
		    
	}
	
}
