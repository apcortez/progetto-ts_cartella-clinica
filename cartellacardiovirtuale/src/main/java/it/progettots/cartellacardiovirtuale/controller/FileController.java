package it.progettots.cartellacardiovirtuale.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;

import it.progettots.cartellacardiovirtuale.entity.DBFile;
import it.progettots.cartellacardiovirtuale.entity.Utente;
import it.progettots.cartellacardiovirtuale.payload.UploadFileResponse;
import it.progettots.cartellacardiovirtuale.service.DBFileStorageService;
import it.progettots.cartellacardiovirtuale.service.PDFServices;
import it.progettots.cartellacardiovirtuale.service.UtenteService;
import it.progettots.cartellacardiovirtuale.user.TsScheda;

@Controller
@RequestMapping("/files")
public class FileController {
	@Autowired
	private DBFileStorageService dbFileStorageService;
	@Autowired
	private PazienteController pazienteController;
	@Autowired
	DBFileStorageService fileService;
	@Autowired
	private UtenteService utenteService;
	@Autowired
	private TemplateEngine templateEngine;
	@Autowired
	private PDFServices pdfService ;
	private Logger logger = Logger.getLogger(getClass().getName());

	public UploadFileResponse uploadFile(MultipartFile file, String username) {
		
		DBFile dbFile = dbFileStorageService.storeFile(file, username);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(dbFile.getId()).toUriString();

		return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
	}

	@PostMapping("/uploadMultipleFiles")
	public String uploadMultipleFiles(@RequestBody() MultipartFile[] files, @RequestParam("username") String username) {
		System.out.println(username);
		Arrays.asList(files).stream().map(file -> uploadFile(file, username)).collect(Collectors.toList());
		return "redirect:/pazienti/schedaPDF?pazienteId=" + username;
	}

	@RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
	public void showImage(@RequestParam("id") String itemId, HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {

		System.err.println("hello");
		DBFile item = dbFileStorageService.getFile(itemId);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(item.getData());

		response.getOutputStream().close();
	} 
  
	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        DBFile dbFile = dbFileStorageService.getFile(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
	}


	/**
	 * @param request
	 * @param response
	 * @param theModel
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(path = "/pdf")
	public String getPDF(@RequestParam("username") String username, HttpServletRequest request, HttpServletResponse response, Model theModel) throws IOException {
 
		/* Do Business Logic */
		Utente thePaziente = utenteService.findByUsername(username);
		TsScheda theTsScheda = utenteService.updateScheda(thePaziente);

		Context context = new Context();
		// context.setVariable("paziente", theTsScheda);

		Map<String, Object> variables = new HashMap();
		variables.put("paziente", theTsScheda);
		String diabete;
		String fumatore;
		if(theTsScheda.getDiabete()==1)
			diabete="SI";
		else 
			diabete="NO";
		
		if(theTsScheda.getFumatore()==1)
			fumatore="SI";
		else 
			fumatore="NO";
		System.out.println(fumatore+diabete);
		variables.put("fumatore", fumatore);
		variables.put("diabete", diabete);
		List<DBFile> dBFiles = fileService.getFilesByPaziente(username);
		List<DBFile> pdfFiles = fileService.getPDFFilesByPaziente(username);
		context.setVariables(variables);
          
		String html = templateEngine.process("pazienti/pdf_scheda_template", context); 
		
		String outputFolder = "C:\\ListaPazienti" + File.separator +username+".pdf";
		String xhtml = htmlToXhtml(html);
        xhtmlToPdf(xhtml, outputFolder);
        
		try {
			pdfService.newPDF(outputFolder.replace("\\","\\\\"),dBFiles,pdfFiles);
		} catch (IOException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pazienteController.listPazienti(theModel);

	}
	@GetMapping("/deleteFile")
	public String deleteFile(@RequestParam("fileId") String fileId,@RequestParam("username") String username,Model theModel) {
        dbFileStorageService.removeFile(fileId);
        return pazienteController.schedaPazientePDF(username, theModel);
       
	}
	private static String htmlToXhtml(String html) {
	    Document document = Jsoup.parse(html);
	    document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
	    return document.html();
	}
	
	private static void xhtmlToPdf(String xhtml, String outFileName) throws IOException {
		
	    File output = new File(outFileName);
	    ITextRenderer iTextRenderer = new ITextRenderer();
	    iTextRenderer.setDocumentFromString(xhtml);
	    iTextRenderer.layout();
	    OutputStream os = new FileOutputStream(output);
	    iTextRenderer.createPDF(os);
	    
	    os.close();
	}
	
	

	
   
}
