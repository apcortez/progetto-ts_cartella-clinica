package it.progettots.cartellacardiovirtuale.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Table;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import it.progettots.cartellacardiovirtuale.dao.DBFileRepository;
import it.progettots.cartellacardiovirtuale.entity.DBFile;




@Service
public class PDFServices {
@Autowired
private DBFileRepository dbFileRepo;
	public void newPDF(String file_name,List<DBFile>files,List<DBFile> pdfFiles) throws MalformedURLException, IOException, DocumentException {
		String FILE_NAME = file_name.replace(".pdf", "_final.pdf");
		 
        test(files,file_name.replace(".pdf", "_imgfinal.pdf"));
        try {
        	Document document = new Document();
        	PdfCopy copy = new PdfCopy(document, new FileOutputStream(FILE_NAME));
        	document.open();
        	PdfReader reader = new PdfReader(file_name);//main
        	PdfReader reader2 = new PdfReader(file_name.replace(".pdf", "_imgfinal.pdf"));
        	copy.addDocument(reader);
        	copy.freeReader(reader);

        	
        	copy.addDocument(reader2);
        	copy.freeReader(reader2);
        	
        	if(pdfFiles!=null)
        	for(DBFile file:pdfFiles) {
        		PdfReader reader1 = new PdfReader(file.getData());
        		copy.addDocument(reader1);
        		copy.freeReader(reader1);
        		reader1.close();
        	}
        	reader.close();
        	reader2.close();
        	
        	document.close();
        	
  
        } catch (Exception e) {
            e.printStackTrace();
        } 
	}
	void test(List<DBFile>dbfiles,String dest) throws MalformedURLException, IOException, DocumentException {
		
             
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(dest));
            document.open();
            for(DBFile file:dbfiles) {
            
            	Image img = Image.getInstance(file.getData());
                document.setPageSize(img);
                document.newPage();
                img.setAbsolutePosition(0, 0);
                document.add(img);
      
        }
            document.close();
        }
        
	
}
