package com.gdssoft.cqjt.util;

import java.io.File;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

public class JacobUtils {

	public static void baseDoc2Pdf(String docPath,String pdfPath){
		System.out.println("----------1---------->");
		//打开word应用程序 
		ActiveXComponent app = new ActiveXComponent("Word.Application"); 
		try {
	        //设置word不可见，否则会弹出word界面 
	        app.setProperty("Visible", false); 
	        //获得word中所有打开的文档,返回Documents对象 
	        Dispatch docs = app.getProperty("Documents").toDispatch(); 
	        //调用Documents对象中Open方法打开文档，并返回打开的文档对象Document 
	        Dispatch doc = Dispatch.call(docs, 
	                                    "Open", 
	                                    docPath, 
	                                    false, 
	                                    true 
	                                    ).toDispatch(); 
	        //调用Document对象的SaveAs方法，将文档保存为pdf格式 
	        Dispatch.call(doc, 
	                "ExportAsFixedFormat", 
	                pdfPath, 
	                17        //word保存为pdf格式 wdFormatPDF
	                ); 
	        //关闭文档 
	        Dispatch.call(doc, "Close",false); 
	        System.out.println("----结束-----");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭word应用程序 
			app.invoke("Quit", 0); 
		}
		
	}
	
	public static void doc2Pdf(String docPath, String pdfPath){
		File docFile = new File(docPath);
		if(docFile.exists()){
			File pdfFile = new File(pdfPath);
			if(pdfFile.exists()){
				pdfFile.delete();
			}
			baseDoc2Pdf(docPath, pdfPath);
		}
	}
	public static void doc2Pdf(String docPath){
		String pdfPath = docPath.substring(0, docPath.lastIndexOf(".")) + ".pdf";
		System.out.println("----------pdfPath---------->"+pdfPath);
		File docFile = new File(docPath);
		if(docFile.exists()){
			System.out.println("----------1---------->");
			File pdfFile = new File(pdfPath);
			if(pdfFile.exists()){
				pdfFile.delete();
			}
			baseDoc2Pdf(docPath, pdfPath);
		}
		System.out.println("----------2---------->");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//JacobUtils.baseDoc2Pdf("D:/temp/2007.docx", "D:/temp/2007.pdf");
		JacobUtils.baseDoc2Pdf("D:/Program Files (x86)/temp/2003.doc", "D:/Program Files (x86)/temp/2003.pdf");
	}

}
