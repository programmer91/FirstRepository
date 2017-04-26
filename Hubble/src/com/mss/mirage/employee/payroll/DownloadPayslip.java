 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.mirage.employee.payroll;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.mss.mirage.util.ApplicationConstants;
import com.mss.mirage.util.ConnectionProvider;
import com.mss.mirage.util.DataSourceDataProvider;
import com.mss.mirage.util.DateUtility;

import com.mss.mirage.util.ReportProperties;
import com.mss.mirage.util.ServiceLocatorException;
import com.opensymphony.xwork2.Action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

//start


import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

/**
 *
 * @author miracle
 */
public class DownloadPayslip implements
        Action, ServletRequestAware, ServletResponseAware {
    // private String URL="/images/flower.GIF";

    private String contentDisposition = "FileName=inline";
    public InputStream inputStream;
    public OutputStream outputStream;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private String fileName;
    private String downloadType;
    private String sheetType;
    //new
    private String fname = "";
    private String lname = "";
    private String country;
    private String leaveType;
    private String startdate;
    private String enddate;
    private String reportsToId;
    private String status;
    private String result;
    private String departmentId;
    private String empnameById;
    private String month;
    private String year;
    private String empNo;
    private int payslipFlag;
    private String payslipResponse;

//    public String execute() throws Exception {
//        Document document = new Document();
//        String responseString = "";
//        try {
//            System.out.println("passwords-->" + httpServletRequest.getParameter("passwordForPdf").toString());
//            System.out.println("months-->" + Integer.parseInt(httpServletRequest.getParameter("month").toString()));
//            System.out.println("empId-->" + httpServletRequest.getParameter("empnameById").toString());
//            System.out.println("deptId-->" + httpServletRequest.getParameter("departmentId").toString());
//            setMonth(httpServletRequest.getParameter("month").toString());
//            setYear(httpServletRequest.getParameter("year").toString());
//            setDepartmentId(httpServletRequest.getParameter("departmentId").toString());
//            setEmpnameById(httpServletRequest.getParameter("empnameById").toString());
//            int empId = DataSourceDataProvider.getInstance().getEmpIdByLoginId(httpServletRequest.getParameter("empnameById").toString());
//            System.out.println("empId-->" + empId);
//            String resultString = DataSourceDataProvider.getInstance().getEmpNoByEmpId(empId);
//            System.out.println("resultString-->" + resultString);
//            System.out.println("empNo-->" + resultString.split("\\^")[0]);
//            System.out.println("empName-->" + resultString.split("\\^")[1]);
//            String fileLocation = "D:\\" + resultString.split("\\^")[1] + "_" + resultString.split("\\^")[0] + ".pdf";
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileLocation));
//            if (!"".equals(httpServletRequest.getParameter("passwordForPdf").toString())) {
//                writer.setEncryption(httpServletRequest.getParameter("passwordForPdf").toString().getBytes(), httpServletRequest.getParameter("passwordForPdf").toString().getBytes(),
//                        PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
//            }
//            document.open();
//            PdfPTable table = new PdfPTable(2); // 2 columns.
//            table.setWidthPercentage(100); //Width 100%
//            table.setSpacingBefore(10f); //Space before table
//            table.setSpacingAfter(10f); //Space after table
//
//            //Set Column widths
//            float[] columnWidths = {1f, 1f};
//            table.setWidths(columnWidths);
//            Image image1;
//
//            image1 = Image.getInstance("C:\\Users\\miracle\\Desktop\\msslogo.png");
//            //Fixed Positioning
//
//
//            //Scale to new height and new width of image
//            image1.scaleAbsolute(210, 32);
//            Image image2 = Image.getInstance("C:\\Users\\miracle\\Desktop\\mssaddress.png");
//            //Fixed Positioning
//            image2.setAbsolutePosition(70f, 750f);
//            //Scale to new height and new width of image
//            image2.scaleAbsolute(190, 42);
//            PdfPCell cell1 = new PdfPCell(new Paragraph(""));
//            cell1.setBorder(Rectangle.NO_BORDER);
//            // cell1.setBorderColor(BaseColor.BLUE);
//            cell1.setPaddingLeft(10);
//            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell1.addElement(new Chunk(image1, 25, -10));
//            PdfPCell cell2 = new PdfPCell(new Paragraph(""));
//            cell2.setBorder(Rectangle.NO_BORDER);
//            //    cell2.setBorderColor(BaseColor.GREEN);
//            cell2.setPaddingLeft(10);
//            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell2.addElement(new Chunk(image2, 25, -10));
//            //Add to document
//            table.addCell(cell1);
//            table.addCell(cell2);
//            document.add(table);
//            Font bigAndBold = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
//            BaseColor myColor1 = WebColors.getRGBColor("#800000");
//            bigAndBold.setColor(myColor1);
//
//            Font bigAndBoldSmall = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
//
//            bigAndBoldSmall.setColor(myColor1);
//
//            Font bigAndBoldBlue = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
//            BaseColor myColorVBlue = WebColors.getRGBColor("#0000ff");
//            bigAndBoldBlue.setColor(myColorVBlue);
//
//            Font bigAndBoldBlueSmall = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
//
//            bigAndBoldBlueSmall.setColor(myColorVBlue);
//
//            Font bigAndBold1 = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
//            bigAndBold1.setColor(BaseColor.BLACK);
//
//            Font bigAndBold1Small = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
//            bigAndBold1Small.setColor(BaseColor.BLACK);
//            Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
//            normal.setColor(BaseColor.BLACK);
//
//            Font normalSmall = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);
//            normalSmall.setColor(BaseColor.BLACK);
//
//            Font normalSmallBlue = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);
//            normalSmallBlue.setColor(myColorVBlue);
//
//            Font bigAndBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
//            BaseColor myColor12 = WebColors.getRGBColor("#0000ff");
//            bigAndBold12.setColor(myColor12);
//            Font bigAndBold13 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
//            BaseColor myColor13 = WebColors.getRGBColor("#3366ff");
//            bigAndBold13.setColor(myColor13);
//            BaseColor myColor = WebColors.getRGBColor("#0000ff");
//            PdfPTable tableForBasicDetailsColumns = new PdfPTable(6); // 4 columns.
//            tableForBasicDetailsColumns.setWidthPercentage(85);
//            tableForBasicDetailsColumns.setSpacingBefore(3f);
//
//            String empDetailsForPayslipGeneration = DataSourceDataProvider.getInstance().getEmployeeDetailsForPaySlipGeneration(empId, getMonth(), getYear());
//            if (!"".equals(empDetailsForPayslipGeneration)) {
//                boolean isBlock = DataSourceDataProvider.getInstance().checkForIsBlock(empId,Integer.parseInt(getMonth()),Integer.parseInt(getYear()));
//                if (!isBlock) {
//                    System.out.println("basicDetails-->" + empDetailsForPayslipGeneration.split("\\#")[0]);
//                    System.out.println("basicDetails-->" + empDetailsForPayslipGeneration.split("#")[0]);
//                    System.out.println("payrollDetails-->" + empDetailsForPayslipGeneration.split("\\#")[1]);
//                    System.out.println("payrollDetails-->" + empDetailsForPayslipGeneration.split("#")[1]);
//                    System.out.println("totalDeductionDXetails-->" + empDetailsForPayslipGeneration.split("\\#")[2]);
//                    System.out.println("totalDeductionDXetails-->" + empDetailsForPayslipGeneration.split("#")[2]);
//
//
//                    //String basicDetails = "Employee Name:|Aditya malla^EmpId:|2185^Payroll end Date:|31-01-2015^Designation:|Jr.Programmer^Days Worked:|20^PAN NO:|567tyt6^Bank Name:|ICICI^Vactaion Availed:|1^PF NO:|1323456^Bank Acc.:|006001553005^Days Holidays:|0^NoData:|None^";
//                    String basicDetails = empDetailsForPayslipGeneration.split("\\#")[0];
//                    String basicdetails[] = basicDetails.split("\\^");
//
//                    //System.out.println(" count->" + details.length);
//                    for (int i = 0; i < basicdetails.length; i++) {
//
//                        String individualdetailsEarnings[] = basicdetails[i].split("\\|");
//                        //System.out.println(" count->" + individualdetails.length);
//                        for (int j = 0; j < individualdetailsEarnings.length; j++) {
//                            // System.out.println("j count->" + j);
//                            Paragraph paraBasicDetails = new Paragraph();
//
//                            if (j % 2 == 0) {
//                                //System.out.println("in iff-------->");
//
//                                paraBasicDetails.setFont(bigAndBoldSmall);
//                                if (!"NoData:".equals(individualdetailsEarnings[j])) {
//                                    paraBasicDetails.add(individualdetailsEarnings[j]);
//
//                                } else {
//                                    paraBasicDetails.add(" ");
//                                }
//                            } else {
//
//                                paraBasicDetails.setFont(normalSmall);
//                                if (!"None".equals(individualdetailsEarnings[j])) {
//                                    paraBasicDetails.add(individualdetailsEarnings[j]);
//                                    paraBasicDetails.setAlignment(Element.ALIGN_LEFT);
//                                } else {
//                                    paraBasicDetails.add(" ");
//                                    paraBasicDetails.setAlignment(Element.ALIGN_LEFT);
//                                }
//                            }
//
//                            PdfPCell celForBasicDetailsColumns = new PdfPCell();
//                            celForBasicDetailsColumns.setBorder(Rectangle.ALIGN_LEFT);
//                            celForBasicDetailsColumns.addElement(paraBasicDetails);
//                            celForBasicDetailsColumns.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                            celForBasicDetailsColumns.setBorderColor(myColor);
//                            celForBasicDetailsColumns.setBorderColorTop(myColor);
//                            celForBasicDetailsColumns.setBorderColorBottom(BaseColor.WHITE);
//                            tableForBasicDetailsColumns.addCell(celForBasicDetailsColumns);
//                        }
//                    }
//
//                    PdfPTable finalTableBasicDetails = new PdfPTable(1); // 1 columns.
//                    finalTableBasicDetails.setWidthPercentage(85);
//
//                    // finalTableBasicDetails.setSpacingBefore(5f);
//                    // finalTableBasicDetails.setSpacingAfter(5f);
//                    PdfPCell finalCelForBasicDetailsColumns = new PdfPCell(tableForBasicDetailsColumns);
//
//                    finalCelForBasicDetailsColumns.setBorderColor(myColor);
//                    finalCelForBasicDetailsColumns.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    finalCelForBasicDetailsColumns.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    finalCelForBasicDetailsColumns.setFixedHeight(100f);
//                    finalCelForBasicDetailsColumns.setBorderWidth(1f);
//                    finalTableBasicDetails.addCell(finalCelForBasicDetailsColumns);
//                    document.add(finalTableBasicDetails);
//                    PdfPTable tableForGriossDetailsColumns = new PdfPTable(4); // 4 columns.
//                    tableForGriossDetailsColumns.setWidthPercentage(85);
//                    tableForGriossDetailsColumns.setSpacingBefore(5f);
//
//                    Paragraph p8 = new Paragraph();
//                    p8.setFont(bigAndBold12);
//                    p8.add("Pay Item Description");
//
//                    Paragraph p9 = new Paragraph();
//                    p9.setFont(bigAndBold13);
//                    p9.add("Gross Details");
//
//                    Paragraph p10 = new Paragraph();
//                    p10.setFont(bigAndBold13);
//                    p10.add("Earned Details");
//
//                    Paragraph p11 = new Paragraph();
//                    p11.setFont(bigAndBold13);
//                    p11.add("Deduction Details");
//                    PdfPCell celForGriossDetailsColumns01 = new PdfPCell();
//
//                    celForGriossDetailsColumns01.setBorderWidthRight(0.5f);
//                    celForGriossDetailsColumns01.setBorderWidthLeft(1f);
//                    celForGriossDetailsColumns01.setBorderWidthTop(1f);
//                    celForGriossDetailsColumns01.setBorderWidthBottom(1f);
//
//
//                    celForGriossDetailsColumns01.addElement(p8);
//                    celForGriossDetailsColumns01.setBorderColor(myColor);
//                    celForGriossDetailsColumns01.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    celForGriossDetailsColumns01.setVerticalAlignment(Element.ALIGN_MIDDLE);
//
//
//                    PdfPCell celForGriossDetailsColumns02 = new PdfPCell();
//
//                    celForGriossDetailsColumns02.setBorderWidthRight(0.5f);
//                    celForGriossDetailsColumns02.setBorderWidthLeft(1f);
//                    celForGriossDetailsColumns02.setBorderWidthTop(1f);
//                    celForGriossDetailsColumns02.setBorderWidthBottom(1f);
//
//                    celForGriossDetailsColumns02.addElement(p9);
//                    celForGriossDetailsColumns02.setBorderColor(myColor);
//                    celForGriossDetailsColumns02.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    celForGriossDetailsColumns02.setVerticalAlignment(Element.ALIGN_MIDDLE);
//
//
//                    PdfPCell celForGriossDetailsColumns03 = new PdfPCell();
//
//                    celForGriossDetailsColumns03.setBorderWidthRight(0.5f);
//                    celForGriossDetailsColumns03.setBorderWidthLeft(1f);
//                    celForGriossDetailsColumns03.setBorderWidthTop(1f);
//                    celForGriossDetailsColumns03.setBorderWidthBottom(1f);
//
//                    celForGriossDetailsColumns03.addElement(p10);
//                    celForGriossDetailsColumns03.setBorderColor(myColor);
//                    celForGriossDetailsColumns03.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    celForGriossDetailsColumns03.setVerticalAlignment(Element.ALIGN_MIDDLE);
//
//
//                    PdfPCell celForGriossDetailsColumns04 = new PdfPCell();
//                    celForGriossDetailsColumns04.setBorderColor(myColor);
//                    celForGriossDetailsColumns04.setBorderWidthRight(1f);
//                    celForGriossDetailsColumns04.setBorderWidthLeft(1f);
//                    celForGriossDetailsColumns04.setBorderWidthTop(1f);
//                    celForGriossDetailsColumns04.setBorderWidthBottom(1f);
//
//                    celForGriossDetailsColumns04.addElement(p11);
//
//                    celForGriossDetailsColumns04.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    celForGriossDetailsColumns04.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    tableForGriossDetailsColumns.addCell(celForGriossDetailsColumns01);
//                    tableForGriossDetailsColumns.addCell(celForGriossDetailsColumns02);
//                    tableForGriossDetailsColumns.addCell(celForGriossDetailsColumns03);
//                    tableForGriossDetailsColumns.addCell(celForGriossDetailsColumns04);
//                    /* String totdetails = "Basic:|3300.00|3300.00|NoData^DNA:|3300.00|3300.00|NoData^HRA:|3300.00|3300.00|NoData^TA:|3300.00|3300.00|NoData^RA:|3300.00|3300.00|NoData^Entertainment:|3300.00|3300.00|NoData^KidsEducation:|3300.00|3300.00|NoData^VehicleAllownace:|3300.00|3300.00|NoData^CCA:|3300.00|3300.00|NoData^SpecialAllowance:|3300.00|3300.00|NoData^MISC Pay:|3300.00|3300.00|NoData^Health:|3300.00|NoData|NoData^"
//                    + "Gross Pay:|3300.00|NoData|NoData^LongTermBonus:|3300.00|3300.00|NoData^Attendance Allowance:|3300.00|3300.00|NoData^Project Allowance:|3300.00|3300.00|NoData^"
//                    + "Health Deduction:|NoData|NoData|0.00^LIC Deduction:|NoData|NoData|0.00^Earned LongTerm Bonus:|NoData|NoData|2500^Employee ESI Contribution:|NoData|NoData|0.00^Employee PF Contribution:|NoData|NoData|1481.00^Professional Tax:|NoData|NoData|200.00^Income Tax:|NoData|NoData|2,800.00^Other Deductions:|NoData|NoData|2,800.00^";*/
//
//                    String totdetails = empDetailsForPayslipGeneration.split("\\#")[1];
//                    String details[] = totdetails.split("\\^");
//
//                    //System.out.println(" count->" + details.length);
//                    for (int i = 0; i < details.length; i++) {
//                        //System.out.println("i count->" + i);
//                        int temp = 0;
//                        if (i == details.length - 1) {
//                            temp = 1;
//                        }
//                        String individualdetails[] = details[i].split("\\|");
//                        //System.out.println(" count->" + individualdetails.length);
//                        for (int j = 0; j < individualdetails.length; j++) {
//                            // System.out.println("j count->" + j);
//                            Paragraph paraGrossDetails = new Paragraph();
//                            if (j == 0) {
//                                //System.out.println("in if");
//                                //bigAndBoldBlue
//                                if (individualdetails[j].equalsIgnoreCase("Gross Pay:") || individualdetails[j].equalsIgnoreCase("LongTermBonus:") || individualdetails[j].equalsIgnoreCase("Attendance Allowance:") || individualdetails[j].equalsIgnoreCase("Project Allowance:")) {
//                                    paraGrossDetails.setFont(bigAndBoldBlueSmall);
//                                    paraGrossDetails.add(individualdetails[j]);
//                                } else {
//                                    paraGrossDetails.setFont(bigAndBoldSmall);
//                                    paraGrossDetails.add(individualdetails[j]);
//                                }
//                            } else {
//
//                                if (individualdetails[j].equalsIgnoreCase("NoData")) {
//                                    paraGrossDetails.setFont(normalSmall);
//                                    paraGrossDetails.add("");
//                                } else {
//
//                                    if (i == 12 || i == 13 || i == 14 || i == 15) {
//                                        //System.out.println("in if js--");
//                                        paraGrossDetails.setFont(normalSmallBlue);
//                                        paraGrossDetails.add(individualdetails[j]);
//                                    } else {
//                                        paraGrossDetails.setFont(normalSmall);
//                                        paraGrossDetails.add(individualdetails[j]);
//                                    }
//                                }
//                            }
//                            PdfPCell celForGriossDetailsColumns = new PdfPCell();
//                            if (temp == 0) {
//
//                                celForGriossDetailsColumns.setBorder(Rectangle.ALIGN_RIGHT);
//                                if (j == individualdetails.length - 1) {
//                                    celForGriossDetailsColumns.setBorderWidthRight(1f);
//                                } else {
//                                    celForGriossDetailsColumns.setBorderWidthRight(0.5f);
//                                }
//                                celForGriossDetailsColumns.setFixedHeight(20f);
//                                celForGriossDetailsColumns.setBorderWidthLeft(1f);
//                                celForGriossDetailsColumns.addElement(paraGrossDetails);
//                                celForGriossDetailsColumns.setBorderColor(myColor);
//                                celForGriossDetailsColumns.setHorizontalAlignment(Element.ALIGN_CENTER);
//                                celForGriossDetailsColumns.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                                celForGriossDetailsColumns.setBorderColorBottom(BaseColor.WHITE);
//
//                            } else {
//
//                                celForGriossDetailsColumns.setBorder(Rectangle.ALIGN_RIGHT);
//                                if (j == individualdetails.length - 1) {
//                                    celForGriossDetailsColumns.setBorderWidthRight(1f);
//                                } else {
//                                    celForGriossDetailsColumns.setBorderWidthRight(0.5f);
//                                }
//                                celForGriossDetailsColumns.setFixedHeight(25f);
//                                celForGriossDetailsColumns.setBorderWidthLeft(1f);
//                                celForGriossDetailsColumns.setBorderWidthBottom(1f);
//                                celForGriossDetailsColumns.addElement(paraGrossDetails);
//                                celForGriossDetailsColumns.setBorderColor(myColor);
//                                celForGriossDetailsColumns.setHorizontalAlignment(Element.ALIGN_CENTER);
//                                celForGriossDetailsColumns.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                                celForGriossDetailsColumns.setBorderColorBottom(myColor);
//                            }
//                            tableForGriossDetailsColumns.addCell(celForGriossDetailsColumns);
//                        }
//                    }
//
//
//
//                    document.add(tableForGriossDetailsColumns);
//
//                    PdfPTable tableForGriossEarnedDetailsColumns = new PdfPTable(6); // 4 columns.
//                    tableForGriossEarnedDetailsColumns.setWidthPercentage(85);
//                    tableForGriossEarnedDetailsColumns.setSpacingBefore(5f);
//
//
//                    // String totalEarnings = "Earned Gross|14,300.00^Expenses Paid|0.00^Total Deductions|6,981.00^Earned Varriable|9,000.00^Commission|18,000.00^Net Paid|34,339.00^";
//                    String totalEarnings = empDetailsForPayslipGeneration.split("\\#")[2];
//                    String detailsEarnings[] = totalEarnings.split("\\^");
//
//                    //System.out.println(" count->" + details.length);
//                    for (int i = 0; i < detailsEarnings.length; i++) {
//                        //System.out.println("i count->" + i);
//                        int temp = 0;
//                        if (i == detailsEarnings.length - 1) {
//                            temp = 1;
//                        }
//                        String individualdetailsEarnings[] = detailsEarnings[i].split("\\|");
//                        //System.out.println(" count->" + individualdetails.length);
//                        for (int j = 0; j < individualdetailsEarnings.length; j++) {
//                            // System.out.println("j count->" + j);
//                            Paragraph paraGrossDetails = new Paragraph();
//                            if (j % 2 == 0) {
//                                //System.out.println("in iff-------->");
//
//                                paraGrossDetails.setFont(bigAndBoldSmall);
//                                paraGrossDetails.add(individualdetailsEarnings[j]);
//                            } else {
//                                //System.out.println("else-------->");
//                                if (temp == 1) {
//                                    paraGrossDetails.setFont(bigAndBold1Small);
//                                    paraGrossDetails.add(individualdetailsEarnings[j]);
//                                } else {
//                                    paraGrossDetails.setFont(normalSmall);
//                                    paraGrossDetails.add(individualdetailsEarnings[j]);
//                                }
//                            }
//                            PdfPCell celForGriossEarnedDetailsColumns = new PdfPCell();
//                            celForGriossEarnedDetailsColumns.setBorder(Rectangle.ALIGN_LEFT);
//                            celForGriossEarnedDetailsColumns.addElement(paraGrossDetails);
//                            celForGriossEarnedDetailsColumns.setBorderColor(myColor);
//                            celForGriossEarnedDetailsColumns.setBorderColorTop(myColor);
//                            celForGriossEarnedDetailsColumns.setBorderColorBottom(BaseColor.WHITE);
//                            tableForGriossEarnedDetailsColumns.addCell(celForGriossEarnedDetailsColumns);
//                        }
//                    }
//
//                    PdfPTable finalTableEarnedGrossDetails = new PdfPTable(1); // 1 columns.
//                    finalTableEarnedGrossDetails.setWidthPercentage(85);
//
//                    finalTableEarnedGrossDetails.setSpacingBefore(5f);
//                    finalTableEarnedGrossDetails.setSpacingAfter(5f);
//                    PdfPCell finalCelForGriossEarnedDetailsColumns = new PdfPCell(tableForGriossEarnedDetailsColumns);
//
//                    finalCelForGriossEarnedDetailsColumns.setBorderColor(myColor);
//                    finalCelForGriossEarnedDetailsColumns.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    finalCelForGriossEarnedDetailsColumns.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    finalCelForGriossEarnedDetailsColumns.setFixedHeight(50f);
//                    finalCelForGriossEarnedDetailsColumns.setBorderWidth(1f);
//                    finalTableEarnedGrossDetails.addCell(finalCelForGriossEarnedDetailsColumns);
//                    document.add(finalTableEarnedGrossDetails);
//                    PdfPTable finalTableForFooter = new PdfPTable(1); // 1 columns.
//                    finalTableForFooter.setWidthPercentage(85);
//                    PdfPTable finalTableForFooterLine = new PdfPTable(1); // 1 columns.
//                    finalTableForFooterLine.setWidthPercentage(85);
//                    finalTableForFooterLine.setSpacingBefore(15f);
//                    PdfPCell celForFooter = new PdfPCell();
//                    celForFooter.setBorder(Rectangle.NO_BORDER);
//                    Paragraph paraGrossDetailsForFooter = new Paragraph();
//                    paraGrossDetailsForFooter.setFont(bigAndBold1);
//                    paraGrossDetailsForFooter.add("11 February 2015");
//                    celForFooter.addElement(paraGrossDetailsForFooter);
//                    finalTableForFooter.addCell(celForFooter);
//
//                    LineSeparator sep = new LineSeparator();
//                    sep.setOffset(0);
//                    sep.setLineWidth(3);
//                    PdfPCell celForFooterLine = new PdfPCell();
//                    celForFooterLine.setBorder(Rectangle.NO_BORDER);
//                    celForFooterLine.addElement(sep);
//                    finalTableForFooterLine.addCell(celForFooterLine);
//                    document.add(finalTableForFooterLine);
//                    document.add(finalTableForFooter);
//
//                    document.close();
//                    writer.close();
//                    //For creating Excel grind from Search result Grid
//                    // System.out.println("StartDate" + getStartdate());
//                    //  System.out.println("EndDate" + getEnddate());
//                    //  fileLocation = generateEmpTimesheetList(getStartdate(), getEnddate(), getReportsToId(),getStatus());
//
//                    httpServletResponse.setContentType("application/force-download");
//                    // File file = new File(Properties.getProperty("mscvp.docCreationPath")+"SearchedExcelDocument.xls");
//                    //   System.out.println("fileLocation-->"+fileLocation);
//                    File file = new File(fileLocation);
//                    Date date = new Date();
//
//                    //fileName = (date.getYear() + 1900) + "_" + (date.getMonth() + 1) + "_" + date.getDate() + "_" + file.getName();
//                    // fileName = getStartdate().substring(0,10) +"To"+ getEnddate().substring(0,10) +"_"+getLeaveType()+"_"+file.getName();
//                    fileName = file.getName();
//                    System.out.println("fileName-->" + fileName);
//                    if (file.exists()) {
//                        System.out.println("in if-->");
//                        inputStream = new FileInputStream(file);
//                        outputStream = httpServletResponse.getOutputStream();
//                        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
//                        int noOfBytesRead = 0;
//                        byte[] byteArray = null;
//                        while (true) {
//                            byteArray = new byte[1024];
//                            noOfBytesRead = inputStream.read(byteArray);
//                            if (noOfBytesRead == -1) {
//                                break;
//                            }
//                            outputStream.write(byteArray, 0, noOfBytesRead);
//                        }
//                        responseString = "downLoaded!!";
//
//                    } else {
//                        throw new FileNotFoundException("File not found");
//                    }
//                    inputStream.close();
//                    outputStream.close();
//                    file.setWritable(true);
//                    file.delete();
//
//                } else {
//                    httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>This record is blocked !!</font>");
//                    result = INPUT;
//                }
//
//            } else {
//                httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>");
//                result = INPUT;
//            }
//
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException ex) {
//            try {
//                httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
//            } catch (IOException ex1) {
//                // Logger.getLogger(CustTimeSheetDownLoadAction.class.getName()).log(Level.SEVERE, null, ex1);
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } /*catch (ServiceLocatorException ex) {
//        ex.printStackTrace();
//        } finally {
//        try {
//        
//        inputStream.close();
//        outputStream.close();
//        
//        } catch (IOException ex) {
//        ex.printStackTrace();
//        }
//        }*/
//
//
//        return result;
//    }
    public String paySlipGeneration() {
        String empName = "";
        int empId = 0;
        int temp = 0;
          String reportResult ="";
          String releaseStatus = "";
          boolean wageExists = false;
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
                
                
                 if( (getEmpnameById() !=null &&!"".equals(getEmpnameById())) && (getEmpNo()!=null && !"".equals(getEmpNo()) )){
                      
                      empId = DataSourceDataProvider.getInstance().getEmpIdByLoginId(httpServletRequest.getParameter("empnameById").toString());
                      int empIdbyNo = DataSourceDataProvider.getInstance().getEmpIdByEmpNo(Integer.parseInt(getEmpNo()));
                    
                      if(empIdbyNo != empId){
                        temp = 1;
                     }
                }
                  
                      
                 else {
                
          if(getEmpnameById() != null && !"".equals(getEmpnameById())  ){
                  
                empId = DataSourceDataProvider.getInstance().getEmpIdByLoginId(httpServletRequest.getParameter("empnameById").toString());
                 // System.out.println("emoName empId----->"+empId);
                }
           else if( getEmpNo() != null && !"".equals(getEmpNo())  ){
                empId =  DataSourceDataProvider.getInstance().getEmpIdByEmpNo(Integer.parseInt(getEmpNo()));
                // System.out.println("empId----->"+empId);
            }
                   }    
                   
                   
                   
               wageExists = DataSourceDataProvider.getInstance().checkForPayrollDateForReleases(Integer.parseInt(getMonth()), Integer.parseInt(getYear()));
              releaseStatus = DataSourceDataProvider.getInstance().getReleaseStatusByMonthAndYear(Integer.parseInt(getMonth()), Integer.parseInt(getYear()));
               // System.out.println("wageExists"+wageExists);
              if(wageExists == true && temp!=1){
                if("Release".equals(releaseStatus)){
                    reportResult = generatePaySlip(empId, empName);
                   // System.out.println("reportResult----"+reportResult);
                }
                else if("Revoke".equals(releaseStatus)){
               reportResult = "<font style='color:red;font-size:15px;'>Payslip is not released</font>";
                    //   httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>");
               // System.out.println("reportResult"+reportResult);    
            //   httpServletRequest.getSession(false).setAttribute("resultMessage12", "<font style='color:red;font-size:15px;'>Payslip is not released</font>");
                }
                else{
                    reportResult = "<font style='color:red;font-size:15px;'>Payslip is not released</font>";
                   //   httpServletRequest.getSession(false).setAttribute("resultMessage12", "<font style='color:red;font-size:15px;'>Payslip is not released</font>");
                }
              }
                else{
                   reportResult = "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>"; 
                //   httpServletRequest.getSession(false).setAttribute("resultMessage12", "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>");
              
                }
                if(temp == 1){
                      reportResult = "<font style='color:red;font-size:15px;'>Employee No and Name Doesn't match</font>"; 
                        // httpServletRequest.getSession(false).setAttribute("resultMessage12", "<font style='color:red;font-size:15px;'>Employee No and Name Doesn't match!!</font>");
               }
               // httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, reportResult);
              //System.out.println("--->"+reportResult);
                setPayslipResponse(reportResult);
                setPayslipFlag(1);
                result = INPUT;
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                //System.out.println("exception payslip generation"+ex.getMessage());
                result = ERROR;
            }
        }
        return result;
    }


    private String generatePaySlip(int empId, String empName) throws ServiceLocatorException {
       // System.out.println("generatePaySlip method");
        String reportResult = "";
        String empBasicDetails = "";
        String empPayrollDetails = "";
        String totalEarnings = " ";
        String totaldetailsForPayslip = "";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Map DetailsMap = new HashMap();

        Map RBMap = new HashMap();
        RBMap.put("generatedby", empName);
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "SELECT CONCAT(tblEmployee.FName,'.',tblEmployee.LName) AS EmpName,tblEmployee.EmpNo AS EmpId,PayrollDate,tblEmployee.TitleTypeId AS Designation,tblEmpWages.DaysWorked AS DaysWorked,  tblEmployee.SSN AS PAN,tblEmployee.PFNo AS PFNo,tblEmpWages.DaysVacation AS DaysVactaion,tblEmpPayRoll.Bank_Name AS Bank_Name,tblEmpPayRoll.BankAccountNumber AS BankAccountNumber,  tblEmpWages.DaysHolidays AS DaysHolidays,"
                    + "tblEmpWages.BasicPay1 AS BasicPay,tblEmpWages.Earned_Basic AS EarnedBasicPay,tblEmpWages.DA,tblEmpWages.Earned_DA AS EarnedDA,tblEmpWages.HRA,tblEmpWages.Earned_HRA AS EarnedHRA,"
                    + "tblEmpWages.TA,tblEmpWages.Earned_TA AS EarnedTA,tblEmpWages.RA,  tblEmpWages.Earned_RA AS EarnedRA,tblEmpWages.Entertainment,tblEmpWages.Earned_Entertainment AS EarnedEntertainment,tblEmpWages.KidsEducation,tblEmpWages.Earned_KidsEducation AS EarnedKidsEducation,"
                    + "tblEmpWages.VehcleAllowance,tblEmpWages.Earned_VehicleAllowance AS EarnedVehcleAllowance,tblEmpWages.CCA1 AS CCA,tblEmpWages.Earned_CCA AS EarnedCCA,tblEmpWages.SplAllowance,tblEmpWages.Earned_SplAllowance AS EarnedSplAllowance, tblEmpWages.MiscPay,tblEmpWages.Earned_MiscPay AS EarnedMiscPay,"
                    + "tblEmpWages.Earned_Health AS EarnedHealth,tblEmpWages.GrossPay AS GrossPay,tblEmpWages.LongTermBonus,tblEmpWages.Earned_LongTermBonus AS EarnedLongTermBonus,"
                    + "tblEmpWages.AttAllowance,tblEmpWages.Earned_Allowance AS EarnedAttAllowance,tblEmpWages.ProjectPay,tblEmpWages.Earned_ProjectPay AS EarnedProjectPay ,tblEmpWages.Earned_Life AS Life,"
                    + "Earned_Employee_ESI,tblEmpWages.Earned_EmployeePF AS Employee_PF, tblEmpWages.Ded_Professional_Tax AS ProfessionalTax,"
                    + " tblEmpWages.Ded_Others AS DedOthers ,Earned_Gross_Pay AS EarnedGrossPay,tblEmpWages.VariablePay AS VariablePay, Earned_Net_Paid AS NetPaid,tblEmpWages.IncomeTax as IncomeTax, tblEmpWages.Earned_Bonus as Earned_Bonus FROM tblEmpWages LEFT OUTER JOIN tblEmpPayRoll ON (tblEmpPayRoll.PayRollId = tblEmpWages.PayRoll_Id) LEFT OUTER JOIN tblEmployee ON (tblEmployee.Id = tblEmpPayRoll.EmpId) WHERE tblEmpWages.EmpId=" + empId + " AND MONTH(tblEmpWages.PayPeriodStartDate)=" + getMonth() + " AND YEAR(tblEmpWages.PayPeriodStartDate)=" + getYear();


         //   System.out.println("queryString-->" + queryString);
            String password = httpServletRequest.getParameter("passwordForPdf").toString();

         //   System.out.println("password-->" + password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
           //     System.out.println("resultSet.getString('DaysWorked')--->" + resultSet.getString("DaysWorked"));
                String PFNo="";
                if(resultSet.getString("PFNo")!=null && resultSet.getString("PFNo").length()>5){
                 PFNo = "AP/VP/" + resultSet.getString("PFNo").substring(resultSet.getString("PFNo").length() - 3, resultSet.getString("PFNo").length());
                }



                DetailsMap.put("EmpName", resultSet.getString("EmpName"));
                DetailsMap.put("EmpId", resultSet.getString("EmpId"));
                DetailsMap.put("PayrollDate", resultSet.getString("PayrollDate"));
                DetailsMap.put("Designation", resultSet.getString("Designation"));
                DetailsMap.put("DaysWorked", resultSet.getString("DaysWorked"));
                DetailsMap.put("PANNO", resultSet.getString("PAN"));
               // PFNo = "AP/VP/" + resultSet.getString("PFNo").substring(resultSet.getString("PFNo").length() - 3, resultSet.getString("PFNo").length());
                DetailsMap.put("PFNo", PFNo);
                DetailsMap.put("DaysVactaion", resultSet.getString("DaysVactaion"));
                //bank Name change 
                DetailsMap.put("Bank_Name", DataSourceDataProvider.getInstance().getBankNameById(Integer.parseInt(resultSet.getString("Bank_Name"))));
                DetailsMap.put("AccNum", resultSet.getString("BankAccountNumber"));
                DetailsMap.put("DaysHolidays", resultSet.getString("DaysHolidays"));
                DetailsMap.put("BasicPay", resultSet.getString("BasicPay"));
                DetailsMap.put("EarnedBasicPay", resultSet.getString("EarnedBasicPay"));
                DetailsMap.put("DA", resultSet.getString("DA"));
                DetailsMap.put("EarnedDA", resultSet.getString("EarnedDA"));
                DetailsMap.put("HRA", resultSet.getString("HRA"));
                DetailsMap.put("EarnedHRA", resultSet.getString("EarnedHRA"));
                DetailsMap.put("TA", resultSet.getString("TA"));
                DetailsMap.put("EarnedTA", resultSet.getString("EarnedTA"));
                DetailsMap.put("RA", resultSet.getString("RA"));
                DetailsMap.put("EarnedRA", resultSet.getString("EarnedRA"));
                DetailsMap.put("Entertainment", resultSet.getString("Entertainment"));
                DetailsMap.put("EarnedEntertainment", resultSet.getString("EarnedEntertainment"));
                DetailsMap.put("KidsEducation", resultSet.getString("KidsEducation"));
                DetailsMap.put("EarnedKidsEducation", resultSet.getString("EarnedKidsEducation"));
                DetailsMap.put("VehcleAllowance", resultSet.getString("VehcleAllowance"));
                DetailsMap.put("EarnedVehcleAllowance", resultSet.getString("EarnedVehcleAllowance"));
                DetailsMap.put("CCA", resultSet.getString("CCA"));
                DetailsMap.put("EarnedCCA", resultSet.getString("EarnedCCA"));
                DetailsMap.put("SplAllowance", resultSet.getString("SplAllowance"));
                DetailsMap.put("EarnedSplAllowance", resultSet.getString("EarnedSplAllowance"));
                DetailsMap.put("MiscPay", resultSet.getString("MiscPay"));
                DetailsMap.put("EarnedMiscPay", resultSet.getString("EarnedMiscPay"));
                DetailsMap.put("EarnedHealth", resultSet.getString("EarnedHealth"));
                DetailsMap.put("GrossPay", resultSet.getString("GrossPay"));
                DetailsMap.put("LongTermBonus", resultSet.getString("LongTermBonus"));
                DetailsMap.put("EarnedLongTermBonus", resultSet.getString("EarnedLongTermBonus"));
                DetailsMap.put("AttAllowance", resultSet.getString("AttAllowance"));
                DetailsMap.put("EarnedAttAllowance", resultSet.getString("EarnedAttAllowance"));
                DetailsMap.put("ProjectPay", resultSet.getString("ProjectPay"));
                DetailsMap.put("EarnedProjectPay", resultSet.getString("EarnedProjectPay"));


                DetailsMap.put("Life", resultSet.getString("Life"));


                DetailsMap.put("ESI", resultSet.getString("Earned_Employee_ESI"));
                DetailsMap.put("Employee_PF", resultSet.getString("Employee_PF"));
                DetailsMap.put("ProfessionalTax", resultSet.getString("ProfessionalTax"));
                DetailsMap.put("IncomeTax", resultSet.getString("IncomeTax"));

                DetailsMap.put("DedOthers", resultSet.getString("DedOthers"));
                DetailsMap.put("EarnedGrossPay", resultSet.getString("EarnedGrossPay"));
                DetailsMap.put("ExpensesPaid", "0.00");
                double totalDeductions = resultSet.getDouble("EarnedLongTermBonus") + resultSet.getDouble("EarnedHealth") + resultSet.getDouble("Life") + resultSet.getDouble("Employee_PF") + resultSet.getDouble("IncomeTax") + 0.00 + resultSet.getDouble("ProfessionalTax") + resultSet.getDouble("DedOthers") + resultSet.getDouble("Earned_Employee_ESI");
                NumberFormat formatter = new DecimalFormat("##,##,##,###.00");
                DetailsMap.put("TotalDeduc", formatter.format(totalDeductions));

                DetailsMap.put("VariablePay", resultSet.getString("VariablePay"));
                DetailsMap.put("NetPaid", resultSet.getString("NetPaid"));
                DetailsMap.put("Commission", resultSet.getString("Earned_Bonus"));
            }


          //  System.out.println("HashMap-----------------");

            boolean isBlock = DataSourceDataProvider.getInstance().checkForIsBlock(empId, Integer.parseInt(getMonth()), Integer.parseInt(getYear()));
            if (!isBlock) {
                if (DetailsMap.size() > 0) {

                    List DataList = new ArrayList();
                    DataList.add(DetailsMap);
                    String PaySlipSource = ReportProperties.getProperty("PaySlipJRXML");
                    //System.out.println("BankReportsSource==" + PaySlipSource);
                  //  System.out.println("before ");
                    JasperReport jasperReport = JasperCompileManager.compileReport(PaySlipSource);
                  //  System.out.println("after compiling");
                    JRBeanCollectionDataSource jrxmlds = new JRBeanCollectionDataSource(DataList);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, RBMap, jrxmlds);
                    // Document document=new Document();
                    // PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileLocation));


                   // System.out.println("asd");



                   // System.out.println("After");
                    byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
//        PdfWriter   writer = PdfWriter.getInstance(document, new FileOutputStream(pdf.toString()));
//           if (!"".equals(httpServletRequest.getParameter("passwordForPdf").toString())) {
//                writer.setEncryption(httpServletRequest.getParameter("passwordForPdf").toString().getBytes(), httpServletRequest.getParameter("passwordForPdf").toString().getBytes(),
//                        PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
//            }

                    String PaySlipDestination = ReportProperties.getProperty("PayslipDownloadLocation");
                    File file = new File(PaySlipDestination + "/" + DetailsMap.get("EmpName") + "_" + DetailsMap.get("EmpId") + ".pdf");
                    JRPdfExporter exporter = new JRPdfExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    exporter.setParameter(JRExporterParameter.OUTPUT_FILE, file);
                    exporter.setParameter(JRPdfExporterParameter.OWNER_PASSWORD, password);
                    exporter.setParameter(JRPdfExporterParameter.USER_PASSWORD, password);
                    exporter.setParameter(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.TRUE);
                     exporter.setParameter(JRPdfExporterParameter.PERMISSIONS,new Integer(PdfWriter.AllowCopy | PdfWriter.AllowPrinting));
                    exporter.exportReport();

                    Date date = new Date();

                    //fileName = (date.getYear() + 1900) + "_" + (date.getMonth() + 1) + "_" + date.getDate() + "_" + file.getName();
                    // fileName = getStartdate().substring(0,10) +"To"+ getEnddate().substring(0,10) +"_"+getLeaveType()+"_"+file.getName();
                    fileName = file.getName();
                //    System.out.println("fileName-->" + fileName);
                    if (file.exists()) {
                  //      System.out.println("in if-->");
                        inputStream = new FileInputStream(file);
                        outputStream = httpServletResponse.getOutputStream();
                        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
                        int noOfBytesRead = 0;
                        byte[] byteArray = null;
                        while (true) {
                            byteArray = new byte[1024];
                            noOfBytesRead = inputStream.read(byteArray);
                            if (noOfBytesRead == -1) {
                                break;
                            }
                            outputStream.write(byteArray, 0, noOfBytesRead);
                        }
                    }


                //    System.out.println("testtt");
                    /*outputStream = httpServletResponse.getOutputStream();
                    httpServletResponse.setContentType("application/pdf");
                    httpServletResponse.setContentLength(pdf.length);
                    httpServletResponse.setHeader("Content-disposition", "inline; filename=\"PaySlip.pdf\"");
                    outputStream.write(pdf);*/
                    outputStream.flush();
                    outputStream.close();
                 //  System.out.println("close---");
                } else {

                    reportResult = "No records are available between selected dates";
                    //   httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>");
                    httpServletRequest.getSession(false).setAttribute("resultMessage12", "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>");
                }
            } else {

                reportResult = "This Record is Blocked";
                //   httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>");
                httpServletRequest.getSession(false).setAttribute("resultMessage12", "<font style='color:red;font-size:15px;'>This Record is Blocked !!</font>");
            }
        } catch (Exception ex) {
            ex.printStackTrace();


        } finally {

            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }

            } catch (SQLException sqle) {
                sqle.printStackTrace();

            }
        }
        return reportResult;
    }

    public String generateTefReport() {
        String empName = "";
        int temp = 0;
        int empId = 0;
         String reportResult = "";
        if (httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_ID) != null) {
            try {
//                System.out.println("getEmpnameById"+getEmpnameById());
//                    System.out.println("getEmpNo"+getEmpNo());
                      
                 if( !"".equals(getEmpnameById()) && !"".equals(getEmpNo()) ){
                      
                      empId = DataSourceDataProvider.getInstance().getEmpIdByLoginId(httpServletRequest.getParameter("empnameById").toString());
                      int empIdbyNo = DataSourceDataProvider.getInstance().getEmpIdByEmpNo(Integer.parseInt(getEmpNo()));
                    
                      if(empIdbyNo != empId){
                        temp = 1;
                     }
                }
                  
                      
                 else {
                     if(!"".equals(getEmpnameById())  ){
//                      System.out.println("getEmpnameById"+getEmpnameById());
                  
                empId = DataSourceDataProvider.getInstance().getEmpIdByLoginId(httpServletRequest.getParameter("empnameById").toString());
//                  System.out.println("emoName empId----->"+empId);
                }
                else if( !"".equals(getEmpNo()) ){
                 empId = DataSourceDataProvider.getInstance().getEmpIdByEmpNo(Integer.parseInt(getEmpNo()));
//                 System.out.println("empId----->"+empId);
                }
                 }
               //  System.out.println("temp"+temp);
                empName = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.SESSION_USER_NAME).toString();
                 if(temp == 1){
                  reportResult = "<font style='color:red;font-size:15px;'>Employee No and name Doesn't match</font>";
                }
                else{
                 reportResult = generateTefReportt(empId);
                 }
                 setPayslipResponse(reportResult);
                setPayslipFlag(2);
               // httpServletRequest.setAttribute(ApplicationConstants.RESULT_MSG, reportResult);
                result = INPUT;
            } catch (Exception ex) {
                httpServletRequest.getSession(false).setAttribute("errorMessage", ex.toString());
                result = ERROR;
            }
        }
        return result;
    }


   private String generateTefReportt(int empId) throws ServiceLocatorException {
        //System.out.println("generateTefReportt method");
        String reportResult = "";
       // String empBasicDetails = "";
       // String empPayrollDetails = "";
       // String totalEarnings = " ";
       // String totaldetailsForPayslip = "";
        Statement statement = null;
        Statement statement1 = null;
       // Statement statement2 = null;
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
       // ResultSet resultSet2 = null;
        Connection connection = null;
        Map DetailsMap = new HashMap();

        Map RBMap = new HashMap();
        RBMap.put("generatedby", "");
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String queryString = "SELECT CONCAT(tblEmployee.FName,' ',tblEmployee.MName,' ',tblEmployee.LName) AS EmpName,tblEmployee.EmpNo AS EmpId,tblEmployee.curStatus,"
                    + "SUM(tblEmpWages.Earned_Basic) AS EarnedBasicPay,SUM(tblEmpWages.Earned_DA) AS EarnedDA,SUM(tblEmpWages.Earned_HRA) AS EarnedHRA,"
                    + "SUM(tblEmpWages.Earned_TA) AS EarnedTA,SUM(tblEmpWages.Earned_RA) AS EarnedRA,SUM(tblEmpWages.Earned_Entertainment) AS EarnedEntertainment,"
                    + "SUM(tblEmpWages.Earned_KidsEducation) AS EarnedKidsEducation,SUM(tblEmpWages.Earned_VehicleAllowance) AS EarnedVehcleAllowance,"
                    + "SUM(tblEmpWages.Earned_CCA) AS EarnedCCA,SUM(tblEmpWages.Earned_SplAllowance) AS EarnedSplAllowance,SUM(tblEmpWages.Earned_MiscPay) AS EarnedMiscPay,"
                    + "SUM(tblEmpWages.Earned_Bonus) AS EarnedBonus,SUM(tblEmpWages.Earned_Allowance) AS EarnedAttAllowance,"
                    + "SUM(tblEmpWages.Earned_ProjectPay) AS EarnedProjectPay,SUM(tblEmpWages.Ded_Professional_Tax) AS ProfessionalTax,SUM(tblEmpWages.Ded_Others) AS DedOthers,SUM(tblEmpWages.Earned_Gross_Pay) AS EarnedGrossPay,SUM(tblEmpWages.Earned_MiscPay) AS EmpOthers, "
                    + " SUM(tblEmpWages.Earned_EmployeePF) AS EmployeePF, SUM(tblEmpWages.Earned_Health) AS earnedHelth ,SUM(tblEmpWages.Ded_TDS_Bonus) AS TDS,SUM(tblEmpWages.Earned_life) AS earnedLife "
                    + "FROM tblEmpWages  "
                    + "LEFT OUTER JOIN tblEmployee ON (tblEmployee.Id = tblEmpWages.EmpId)  WHERE tblEmpWages.EmpId= " + empId;
            Calendar cal = Calendar.getInstance();
            //cal.setTime(date);
            //  int year = cal.get(Calendar.YEAR);
            int year = Integer.parseInt(getYear());
            // int month = cal.get(Calendar.MONTH);
            int month = Integer.parseInt(getMonth()) - 1;
            //System.out.println("month---"+month);
            //System.out.println("year---"+year);
            String monthVal = "";
            switch (month) {
                case 0:
                    monthVal = "Jan";
                    break;
                case 1:
                    monthVal = "Feb";
                    break;
                case 2:
                    monthVal = "Mar";
                    break;
                case 3:
                    monthVal = "Apr";
                    break;
                case 4:
                    monthVal = "May";
                    break;
                case 5:
                    monthVal = "June";
                    break;
                case 6:
                    monthVal = "July";
                    break;
                case 7:
                    monthVal = "Aug";
                    break;
                case 8:
                    monthVal = "Sept";
                    break;
                case 9:
                    monthVal = "Oct";
                    break;
                case 10:
                    monthVal = "Nov";
                    break;
                case 11:
                    monthVal = "Dec";
                    break;

            }
            String sdate = "";
            String edate = "";
            if (month < 3) {
                sdate = (year - 1) + "-04-01";    // 2015-04-01
                edate = year + "-0" + (month + 1) + "-01";   //2016-03-01
            } else {
                sdate = (year) + "-04-01";

                if (month < 9) {
                    edate = (year) + "-0" + (month + 1) + "-01";
                } else {
                    edate = (year) + "-" + (month + 1) + "-01";
                }
            }
            //System.out.println("sdate=="+sdate);
            //System.out.println("edate=="+edate);
           
            queryString = queryString + " AND (tblEmpWages.PayPeriodStartDate>= '" + sdate + "' AND tblEmpWages.PayPeriodStartDate<='" + edate + "')";
            queryString += "  GROUP BY tblEmployee.Id";
            Map TaxExemptionMap = DataSourceDataProvider.getInstance().getTaxExemptionDetails(empId, month,year);
            double TDS_uptoPreviousMonths = DataSourceDataProvider.getInstance().getPreviousMonthsTDS(empId, sdate, year + "-" + (month) + "-01");
//            String queryString1 = "SELECT tblEmpWages.BasicPay1 AS BasicPay,tblEmpWages.Earned_Basic AS EarnedBasicPay,tblEmpWages.Earned_DA AS EarnedDA,tblEmpWages.DA,tblEmpWages.HRA,tblEmpWages.Earned_HRA AS EarnedHRA,tblEmpWages.TA,tblEmpWages.RA,tblEmpWages.Entertainment,tblEmpWages.KidsEducation,"
//                    + "tblEmpWages.VehcleAllowance,tblEmpWages.CCA1 AS CCA,tblEmpWages.SplAllowance,"
//                    + "tblEmpWages.ProjectPay,tblEmpWages.LongTermBonus,tblEmpWages.AttAllowance,tblEmpWages.Ded_Professional_Tax AS ProfessionalTax,tblEmpWages.GrossPay AS GrossPay,tblEmpWages.Earned_Gross_Pay AS EarnedGrossPay,tblEmpWages.MiscPay AS EmpOthers,"
//                    + " tblEmpWages.Employee_PF AS EmployeePF, tblEmpWages.Health,SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 9 AND tblEmpTaxExemptionDetails.STATUS='Approved') THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS LICFROMSalary ,"
//                    + "SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 5 AND tblEmpTaxExemptionDetails.STATUS='Approved') THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS TutionFee,SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 4 AND tblEmpTaxExemptionDetails.STATUS='Approved') THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS ppf,"
//                    + "SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 1 AND tblEmpTaxExemptionDetails.STATUS='Approved') THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS lic, "
//                    + "SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 14 AND tblEmpTaxExemptionDetails.STATUS='Approved') "
//                    + " THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS HBLoan, "
//                    + "SUM(CASE WHEN (tblEmpTaxExemptionDetails.`ExemptionId` = 17 AND tblEmpTaxExemptionDetails.STATUS='Approved') "
//                    + " THEN tblEmpTaxExemptionDetails.ApprovedAmount ELSE 0.00 END) AS eduloan "
//                    + "FROM tblEmpWages LEFT OUTER JOIN tblEmpPayRoll ON (tblEmpPayRoll.PayRollId = tblEmpWages.PayRoll_Id) LEFT OUTER JOIN tblEmployee ON (tblEmployee.Id = tblEmpPayRoll.EmpId) LEFT OUTER JOIN tblEmpTaxExemptionDetails ON (tblEmployee.Id = tblEmpTaxExemptionDetails.EmpId) WHERE tblEmpWages.EmpId= " + empId;
             String queryString1 = "SELECT tblEmpWages.BasicPay1 AS BasicPay,tblEmpWages.Earned_Basic AS EarnedBasicPay,tblEmpWages.Earned_DA AS EarnedDA,tblEmpWages.DA,tblEmpWages.HRA,tblEmpWages.Earned_HRA AS EarnedHRA,tblEmpWages.TA,tblEmpWages.RA,tblEmpWages.Entertainment,tblEmpWages.KidsEducation,"
                    + "tblEmpWages.VehcleAllowance,tblEmpWages.CCA1 AS CCA,tblEmpWages.SplAllowance,"
                    + "tblEmpWages.ProjectPay,tblEmpWages.LongTermBonus,tblEmpWages.AttAllowance,tblEmpWages.Ded_Professional_Tax AS ProfessionalTax,tblEmpWages.GrossPay AS GrossPay,tblEmpWages.Earned_Gross_Pay AS EarnedGrossPay,tblEmpWages.MiscPay AS EmpOthers,"
                    + " tblEmpWages.Employee_PF AS EmployeePF, tblEmpWages.Health ,tblEmpWages.Life ,tblEmpWages.Earned_Bonus AS EarnedBonus,tblEmpWages.EmpSavings5,tblEmpWages.EmpSavings6 "
                    
                    + "FROM tblEmpWages  WHERE tblEmpWages.EmpId= " + empId;
            
            
            //setStartdate(DateUtility.getInstance().FirstDateOfCurrentMonth());
           queryString1 += " AND YEAR(tblEmpWages.PayPeriodStartDate)=" + year + " AND MONTH(tblEmpWages.PayPeriodStartDate)=" + (month + 1);

            queryString1 += "  GROUP BY tblEmployee.Id";

//            String queryString2 = "SELECT `PayRollId`,`PaymentTypeId`,`GrossPay`,`BasicPay`,`DA`,`HRA`,`TA`,	   `RA` ,`Life`,`Health`,`AttAllowance`,`Entertainment`,`KidsEducation`,"
//                    + "  `VehcleAllowance`,`LongTermbonus`,`Employer_PF`,`Employee_PF`,`CCA`,ProjectPay, `SplAllowances`, `MiscPay`, `VariablePay`,`Ded_Professional_Tax`,`Ded_Others`,"
//                    + " `Prev_Employer_YTD_Gross`,`EmpSavings1`,`EmpSavings2`,`EmpSavings3`,`EmpSavings4`,`EmpSavings5`,`EmpId`,ProjectEndDate,NetPaid,Employer_ESI,Employee_ESI ,OnProject,OnSite,TotalCost "
//                    + "FROM tblEmpPayRoll WHERE EmpId =" + empId;
          //  System.out.println("queryString0-->" + queryString);
          //  System.out.println("queryString1-->" + queryString1);
            //System.out.println("queryString2-->" + queryString2);
            String password = httpServletRequest.getParameter("passwordForPdf").toString();
            //System.out.println("password-->" + password);
            statement = connection.createStatement();
            statement1 = connection.createStatement();
         //   statement2 = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            resultSet1 = statement1.executeQuery(queryString1);
          //  resultSet2 = statement2.executeQuery(queryString2);

            DateFormatSymbols dfs = new DateFormatSymbols();
            String monthName = dfs.getMonths()[month];

            if (month < 9) {
                sdate = year + "-0" + (month + 1) + "-01";// 2015-02-01
            } else {
                sdate = year + "-" + (month + 1) + "-01";// 
            }
            if (month <= 02) {
                edate = year + "-03-31"; //2015-03-01
            } else {
                edate = (year + 1) + "-03-31";// 2016-03-01
            }
            //System.out.println("month=" + month);
            //System.out.println("monthName=" + monthName);
            //String sdate=year+"-"+
            int monthDiff = DataSourceDataProvider.getInstance().getMonthsBetweenDates(sdate, edate);

            //System.out.println("monthDiff--" + monthDiff);
            while (resultSet.next() && resultSet1.next()) {

                NumberFormat formatter = new DecimalFormat("#0.00");
             //   System.out.println("while block"+resultSet.getString("curStatus"));

if(resultSet.getString("curStatus").equalsIgnoreCase("InActive")){
    monthDiff=0;
}
                DetailsMap.put("title", "Tax Projections of " + resultSet.getString("EmpName"));
                DetailsMap.put("empId", resultSet.getString("EmpId"));
                DetailsMap.put("empName", resultSet.getString("EmpName"));
                DetailsMap.put("monthYear", monthName + " " + year);
                DetailsMap.put("month", "April & " + monthVal);

                DetailsMap.put("basic", resultSet.getString("EarnedBasicPay"));
                DetailsMap.put("pbasic", formatter.format(resultSet1.getDouble("BasicPay") * monthDiff));
                double basic = resultSet.getDouble("EarnedBasicPay") + (resultSet1.getDouble("BasicPay") * monthDiff);
                DetailsMap.put("tbasic", formatter.format(basic));

                DetailsMap.put("da", resultSet.getString("EarnedDA"));
                DetailsMap.put("pda", formatter.format(resultSet1.getDouble("DA") * monthDiff));
                double da = resultSet.getDouble("EarnedDA") + (resultSet1.getDouble("DA") * monthDiff);
                DetailsMap.put("tda", formatter.format(da));

                DetailsMap.put("hra", resultSet.getString("EarnedHRA"));
                DetailsMap.put("phra", formatter.format(resultSet1.getDouble("HRA") * monthDiff));
                double hra = resultSet.getDouble("EarnedHRA") + (resultSet1.getDouble("HRA") * monthDiff);
                DetailsMap.put("thra", formatter.format(hra));


                DetailsMap.put("ta", resultSet.getString("EarnedTA"));
                DetailsMap.put("pta", formatter.format(resultSet1.getDouble("TA") * monthDiff));
                double TA = resultSet.getDouble("EarnedTA") + (resultSet1.getDouble("TA") * monthDiff);
                DetailsMap.put("tta", formatter.format(TA));


                DetailsMap.put("ra", resultSet.getString("EarnedRA"));
                DetailsMap.put("pra", formatter.format(resultSet1.getDouble("RA") * monthDiff));
                double EarnedRA = resultSet.getDouble("EarnedRA") + (resultSet1.getDouble("RA") * monthDiff);
                DetailsMap.put("tra", formatter.format(EarnedRA));

                DetailsMap.put("entertainment", resultSet.getString("EarnedEntertainment"));
                DetailsMap.put("pEntertainment", formatter.format(resultSet1.getDouble("Entertainment") * monthDiff));
                double Entertainment = resultSet.getDouble("EarnedEntertainment") + (resultSet1.getDouble("Entertainment") * monthDiff);
                DetailsMap.put("tEntertainment", formatter.format(Entertainment));

                DetailsMap.put("kidsEdu", resultSet.getString("EarnedKidsEducation"));
                DetailsMap.put("pKidsEdu", formatter.format(resultSet1.getDouble("KidsEducation") * monthDiff));
                double EarnedKidsEducation = resultSet.getDouble("EarnedKidsEducation") + (resultSet1.getDouble("KidsEducation") * monthDiff);
                DetailsMap.put("tKidsEdu", formatter.format(EarnedKidsEducation));

                DetailsMap.put("vAllowance", resultSet.getString("EarnedVehcleAllowance"));
                DetailsMap.put("pvAllowance", formatter.format(resultSet1.getDouble("VehcleAllowance") * monthDiff));
                double EarnedVehcleAllowance = resultSet.getDouble("EarnedVehcleAllowance") + (resultSet1.getDouble("VehcleAllowance") * monthDiff);
                DetailsMap.put("tvAllowance", formatter.format(EarnedVehcleAllowance));

                DetailsMap.put("cca", resultSet.getString("EarnedCCA"));
                DetailsMap.put("pcca", formatter.format(resultSet1.getDouble("CCA") * monthDiff));
                double CCA = resultSet.getDouble("EarnedCCA") + (resultSet1.getDouble("CCA") * monthDiff);
                DetailsMap.put("tcca", formatter.format(CCA));

                DetailsMap.put("empOthers", resultSet.getString("EmpOthers"));
                DetailsMap.put("pEmpOthers", formatter.format(resultSet1.getDouble("EmpOthers") * monthDiff));
                double EmpOthers = resultSet.getDouble("EmpOthers") + (resultSet1.getDouble("EmpOthers") * monthDiff);
                DetailsMap.put("tEmpOthers", formatter.format(EmpOthers));

                DetailsMap.put("projectpay", resultSet.getString("EarnedProjectPay"));
                DetailsMap.put("pProjectpay", formatter.format(resultSet1.getDouble("ProjectPay") * monthDiff));
                double ProjectPay = resultSet.getDouble("EarnedProjectPay") + (resultSet1.getDouble("ProjectPay") * monthDiff);
                DetailsMap.put("tProjectpay", formatter.format(ProjectPay));

                DetailsMap.put("bonus", resultSet.getString("EarnedBonus"));
                //  DetailsMap.put("pBonus", formatter.format(resultSet1.getDouble("LongTermBonus") * monthDiff));
                DetailsMap.put("pBonus", "");
                // double EarnedLongTermBonus = resultSet.getDouble("EarnedLongTermBonus") + (resultSet1.getDouble("LongTermBonus") * monthDiff);
                double Bonus = resultSet.getDouble("EarnedBonus");
                DetailsMap.put("tBonus", formatter.format(Bonus));

                DetailsMap.put("attAllow", resultSet.getString("EarnedAttAllowance"));
                DetailsMap.put("pAttAllow", formatter.format(resultSet1.getDouble("AttAllowance") * monthDiff));
                double AttAllowance = resultSet.getDouble("EarnedAttAllowance") + (resultSet1.getDouble("AttAllowance") * monthDiff);
                DetailsMap.put("tAttAllow", formatter.format(AttAllowance));

                DetailsMap.put("nsAllow", resultSet.getString("EarnedSplAllowance"));
                DetailsMap.put("pNsAllow", formatter.format(resultSet1.getDouble("SplAllowance") * monthDiff));
                double nsSplAllowance = resultSet.getDouble("EarnedSplAllowance") + (resultSet1.getDouble("SplAllowance") * monthDiff);
                DetailsMap.put("tNsAllow", formatter.format(nsSplAllowance));

                double earnedGrossPay = resultSet.getDouble("EarnedBasicPay") + resultSet.getDouble("EarnedDA") + resultSet.getDouble("EarnedHRA") + resultSet.getDouble("EarnedTA") + resultSet.getDouble("EarnedRA") + resultSet.getDouble("EarnedEntertainment") + resultSet.getDouble("EarnedKidsEducation") + resultSet.getDouble("EarnedVehcleAllowance") + resultSet.getDouble("EarnedCCA") + resultSet.getDouble("EmpOthers") + resultSet.getDouble("EarnedProjectPay") + resultSet.getDouble("EarnedBonus") + resultSet.getDouble("EarnedAttAllowance") + resultSet.getDouble("EarnedSplAllowance");

                double projectedGrossPay = (resultSet1.getDouble("BasicPay") + resultSet1.getDouble("DA") + resultSet1.getDouble("HRA") + resultSet1.getDouble("TA") + resultSet1.getDouble("RA") + resultSet1.getDouble("Entertainment") + resultSet1.getDouble("KidsEducation") + resultSet1.getDouble("VehcleAllowance") + resultSet1.getDouble("CCA") + resultSet1.getDouble("EmpOthers") + resultSet1.getDouble("ProjectPay") + resultSet1.getDouble("AttAllowance") + resultSet1.getDouble("SplAllowance")) * monthDiff;
                double completeGross = earnedGrossPay + projectedGrossPay;
                //DetailsMap.put("gross", resultSet.getString("EarnedGrossPay"));
                DetailsMap.put("gross", formatter.format(earnedGrossPay));
                //DetailsMap.put("pGross", formatter.format(resultSet1.getDouble("GrossPay") * monthDiff));
                DetailsMap.put("pGross", formatter.format(projectedGrossPay));
                double EarnedGrossPay = resultSet.getDouble("EarnedGrossPay") + (resultSet1.getDouble("GrossPay") * monthDiff) - AttAllowance;
                //DetailsMap.put("tGross", formatter.format(EarnedGrossPay));
                DetailsMap.put("tGross", formatter.format(Math.round(completeGross)));
                if(TA>Double.parseDouble(com.mss.mirage.util.Properties.getProperty("TAE"))){
                    TA=Double.parseDouble(com.mss.mirage.util.Properties.getProperty("TAE"));
                }
                DetailsMap.put("TAE", formatter.format(TA));
                DetailsMap.put("RentReceipts", resultSet1.getString("EmpSavings5"));
               // DetailsMap.put("HRE", formatter.format(hra));


                DetailsMap.put("PFTax", resultSet.getString("ProfessionalTax"));
                DetailsMap.put("pPFTax", formatter.format(resultSet1.getDouble("ProfessionalTax") * monthDiff));
                double ProfessionalTax = resultSet.getDouble("ProfessionalTax") + (resultSet1.getDouble("ProfessionalTax") * monthDiff);
                DetailsMap.put("tPFTax", formatter.format(ProfessionalTax));

                DetailsMap.put("otherGross", "0.00");

                //  double sal = completeGross - (TA + hra + ProfessionalTax) + AttAllowance;
               

                DetailsMap.put("pf", resultSet.getString("EmployeePF"));
                DetailsMap.put("ppf", formatter.format(resultSet1.getDouble("EmployeePF") * monthDiff));
                double EmployeePF = resultSet.getDouble("EmployeePF") + (resultSet1.getDouble("EmployeePF") * monthDiff);
                DetailsMap.put("tpf", formatter.format(EmployeePF));


                DetailsMap.put("lic", resultSet.getString("earnedLife"));
 double licFromSalary = resultSet.getDouble("earnedLife") + (resultSet1.getDouble("Life") * monthDiff);
                DetailsMap.put("plic", formatter.format(resultSet1.getDouble("Life") * monthDiff));
                double TutionFee = Double.parseDouble((String) TaxExemptionMap.get("TutionFee"));
                DetailsMap.put("TutionFees", formatter.format(TutionFee));

                double ppf = Double.parseDouble((String) TaxExemptionMap.get("ppf"));
                DetailsMap.put("ppf1", formatter.format(ppf));

                double lic = Double.parseDouble((String) TaxExemptionMap.get("lic"));
                DetailsMap.put("LICPremium", formatter.format(lic));
                double taxSavingFD = Double.parseDouble((String) TaxExemptionMap.get("taxSavingFD"));
                DetailsMap.put("taxSavingSD", formatter.format(taxSavingFD)); // Tax savings SD
                double ELSS=Double.parseDouble((String) TaxExemptionMap.get("ELSS"));
                DetailsMap.put("MutualFunds", formatter.format(ELSS));

                 double HbLoanIns=Double.parseDouble((String) TaxExemptionMap.get("HbLoanIns"));
                DetailsMap.put("HBLPrinciple", formatter.format(HbLoanIns));
                
                 double NSC=Double.parseDouble((String) TaxExemptionMap.get("NSC"));
                 double PostOfficeTermDeposit=Double.parseDouble((String) TaxExemptionMap.get("PostOfficeTermDeposit"));
                 double Others=Double.parseDouble((String) TaxExemptionMap.get("Others"));
                 double _80CCD_NPS=Double.parseDouble((String) TaxExemptionMap.get("80CCD_NPS"));
                //DetailsMap.put("HBLPrinciple", "0.00");
                double totalSavings = EmployeePF + TutionFee + ppf + lic + taxSavingFD+ELSS+HbLoanIns+NSC+PostOfficeTermDeposit+Others+_80CCD_NPS+licFromSalary;
                DetailsMap.put("totalSavings", formatter.format(totalSavings));
                double maxSavings = totalSavings;
                if (totalSavings > 150000) {
                    maxSavings = 150000;
                }
                DetailsMap.put("maxSavings", formatter.format(maxSavings));

                //double HBLoan = Double.parseDouble((String) TaxExemptionMap.get("HBLoan")) + (resultSet1.getDouble("HBLoan") * monthDiff);
                double HBLoan = Double.parseDouble((String) TaxExemptionMap.get("HBLoan"));
                DetailsMap.put("pIntrestOnHB", formatter.format(HBLoan));
                double totalHBLoan = HBLoan;
                if (HBLoan > 200000) {
                    totalHBLoan = 200000;
                }
                DetailsMap.put("tIntrestOnHB", formatter.format(totalHBLoan));
                double eduloan = Double.parseDouble((String) TaxExemptionMap.get("eduloan")) ;
                DetailsMap.put("intrestOnEdu", formatter.format(eduloan));

                DetailsMap.put("healthInsFrom", resultSet.getString("earnedHelth"));
                DetailsMap.put("phealthInsFrom", formatter.format(resultSet1.getDouble("Health") * monthDiff));
                double Health = resultSet.getDouble("earnedHelth") + (resultSet1.getDouble("Health") * monthDiff);
                DetailsMap.put("thealthInsFrom", formatter.format(Health));

                double healthInsDirect = 0.00;
                    healthInsDirect = Double.parseDouble((String) TaxExemptionMap.get("HealthDirect"));
                DetailsMap.put("healthInsDirect", formatter.format(healthInsDirect));
                double v_Earned_TaxableSalary = 0.00;//taxablesal
                double taxablesal = v_Earned_TaxableSalary;//taxablesal
                double temptax = 0.00;
                double rebate = 0.00;
                double v_Edu_Cess = 0.00;
                double totalTax = 0.00;
                double taxRealised = 0.00;
                double balanceTax = 0.00;
                double taxdeductable = 0.00;
                double v_IncomeTax_Calc_TE = 0.00;// tempTax
                double taxOnAbove = 0.00; // tempTax
                // v_Earned_TaxableSalary = basic + da + hra + TA + Entertainment + CCA + EmpOthers + ProjectPay + nsSplAllowance + AttAllowance - EmployeePF - Health - ProfessionalTax;
                // v_Earned_TaxableSalary+=Bonus-TA;
                //System.out.println("v_Earned_TaxableSalary----first--" + v_Earned_TaxableSalary);
                double v_TempEmpSavings5ForTaxCalc1 = (basic+da)*0.4;
                /*Second calculation based on 10% of Basic and DA and Subtracting it from HRE(EmpSavings5) given*/
                 //double v_TempEmpSavings5ForTaxCalc2 = resultSet2.getDouble("EmpSavings5") - (resultSet1.getDouble("EarnedBasicPay") + resultSet1.getDouble("EarnedDA")) * 0.1;
                 double v_TempEmpSavings5ForTaxCalc2 = resultSet1.getDouble("EmpSavings5") - (basic + da) * 0.1;
                if (v_TempEmpSavings5ForTaxCalc2 < 0) {
                    v_TempEmpSavings5ForTaxCalc2 = 0.00;
                }
                double v_Earned_HRA = hra;

                 double v_FinalEmpSavings5ForTaxCalc = 0.00;
                 //case0: 
                 if ((v_TempEmpSavings5ForTaxCalc1 == v_Earned_HRA)) {
                    v_FinalEmpSavings5ForTaxCalc = v_TempEmpSavings5ForTaxCalc1;
                }

                /*case1:*/
                if ((v_TempEmpSavings5ForTaxCalc1 < v_Earned_HRA) && (v_TempEmpSavings5ForTaxCalc1 < v_TempEmpSavings5ForTaxCalc2)) {
                    v_FinalEmpSavings5ForTaxCalc = v_TempEmpSavings5ForTaxCalc1;
                }

                /*case2:*/
                if ((v_TempEmpSavings5ForTaxCalc2 < v_Earned_HRA) && (v_TempEmpSavings5ForTaxCalc2 < v_TempEmpSavings5ForTaxCalc1)) {
                    v_FinalEmpSavings5ForTaxCalc = v_TempEmpSavings5ForTaxCalc2;
                }

                /*case3:*/
                if ((v_Earned_HRA < v_TempEmpSavings5ForTaxCalc1) && (v_Earned_HRA < v_TempEmpSavings5ForTaxCalc2)) {
                    v_FinalEmpSavings5ForTaxCalc = v_Earned_HRA;
                }
                 double finalSavingsExemption=resultSet1.getDouble("EmpSavings5");
                 if(resultSet1.getDouble("EmpSavings5")>v_FinalEmpSavings5ForTaxCalc){
                     finalSavingsExemption=v_FinalEmpSavings5ForTaxCalc;
                 }
                 
                double sal = completeGross - (TA + finalSavingsExemption + ProfessionalTax);
                v_Earned_TaxableSalary = sal - (maxSavings + Health + eduloan + totalHBLoan + healthInsDirect+resultSet1.getDouble("EmpSavings6"));
                taxablesal = v_Earned_TaxableSalary;
               // System.out.println("first v_Earned_TaxableSalary" + v_Earned_TaxableSalary);
                //double v_TempEmpSavings5ForTaxCalc1 = (resultSet1.getDouble("EarnedBasicPay") + resultSet1.getDouble("EarnedDA")) * 0.4;
                 
                DetailsMap.put("miracleSal", formatter.format(Math.round(sal)));
                DetailsMap.put("totalSal", formatter.format(Math.round(sal)));
//                v_Earned_TaxableSalary = v_Earned_TaxableSalary - (resultSet2.getDouble("EmpSavings1") + resultSet2.getDouble("EmpSavings2") + resultSet2.getDouble("EmpSavings3") + resultSet2.getDouble("EmpSavings4") + v_FinalEmpSavings5ForTaxCalc);
                // double v_TaxableIncomeForTaxCalc = v_Earned_TaxableSalary - 250000.00;//Taxonabove --- v_TaxableIncomeForTaxCalc
                //  double temp_v_TaxableIncomeForTaxCalc=v_TaxableIncomeForTaxCalc;
                //System.out.println("v_TaxableIncomeForTaxCalc temp_v_TaxableIncomeForTaxCalc--"+temp_v_TaxableIncomeForTaxCalc);
                //v_Earned_TaxableSalary-=v_FinalEmpSavings5ForTaxCalc;
                double temp_v_TaxableIncomeForTaxCalc = v_Earned_TaxableSalary;
                double v_TaxableIncomeForTaxCalc = v_Earned_TaxableSalary;
                if (v_TaxableIncomeForTaxCalc > 250000) {

                    /*Removal of Non Taxable income ie., 2.5L from the gross calculated*/
                    v_TaxableIncomeForTaxCalc = v_Earned_TaxableSalary - 250000.00;
                         double v_TaxableIncomeForTaxCalc1 = v_TaxableIncomeForTaxCalc;
                  //  System.out.println("v_TaxableIncomeForTaxCalc( v_Earned_TaxableSalary - 250000.00)---" + v_TaxableIncomeForTaxCalc);
                    if (v_TaxableIncomeForTaxCalc > 0) {
                        // temptax = (double) Taxonabove / 10;
                        if (v_TaxableIncomeForTaxCalc <= 250000) {
                            v_IncomeTax_Calc_TE = v_TaxableIncomeForTaxCalc * 0.1;// -- 20000

                        } else {
                            v_IncomeTax_Calc_TE = 250000 * 0.1; // -- 25000
                            v_TaxableIncomeForTaxCalc = v_TaxableIncomeForTaxCalc - 250000; // -- 3500000
                        }
//                        if (v_TaxableIncomeForTaxCalc > 250000) {
//                            //Taxonabove = Taxonabove - 250000.00;
///*Calculation of 10% of the Taxable Income calculated after the removal of non taxable income for incomeTax(TE) calculation*/
//                            v_IncomeTax_Calc_TE = v_TaxableIncomeForTaxCalc * 0.1;
//                            System.out.println("v_TaxableIncomeForTaxCalc > 250000 v_IncomeTax_Calc_TE==" + v_IncomeTax_Calc_TE);
//                            v_TaxableIncomeForTaxCalc = v_TaxableIncomeForTaxCalc - 250000;
//                        }
//                        if (v_TaxableIncomeForTaxCalc > 0) {
//
//                            if ((v_TaxableIncomeForTaxCalc > 250000) && (v_TaxableIncomeForTaxCalc <= 500000)) {
//                                v_IncomeTax_Calc_TE = v_IncomeTax_Calc_TE + (v_TaxableIncomeForTaxCalc * 0.2);
//                            }
//                            /*Calculation of 20% of the Taxable Income calculated after the removal of non taxable income for incomeTax(TE) calculation*/
//                            System.out.println("v_IncomeTax_Calc_TE==" + v_IncomeTax_Calc_TE);
//
//                            // temptax = temptax + (double) (Taxonabove * 2) / 10;
//                            if (v_TaxableIncomeForTaxCalc > 500000) {
//                                //Taxonabove = Taxonabove - 500000.00;
//                                v_IncomeTax_Calc_TE = v_IncomeTax_Calc_TE + (500000 * 0.2);
//                                v_TaxableIncomeForTaxCalc = v_TaxableIncomeForTaxCalc - 500000;
//                                if (v_TaxableIncomeForTaxCalc > 0) {
//                                    // temptax = temptax + (double) (Taxonabove * 3) / 10;
//                                    v_IncomeTax_Calc_TE = v_IncomeTax_Calc_TE + (v_TaxableIncomeForTaxCalc * 0.3);
//                                }
//                            }
//                            System.out.println("v_IncomeTax_Calc_TE==" + v_IncomeTax_Calc_TE);
//                        }
//                        if (temp_v_TaxableIncomeForTaxCalc < 500000) {
//                            v_IncomeTax_Calc_TE = v_IncomeTax_Calc_TE - 2000;
//                        }
//                        EDucess = v_IncomeTax_Calc_TE * 0.03;
//                        v_IncomeTax_Calc_TE = v_IncomeTax_Calc_TE + EDucess;
//                        System.out.println("v_IncomeTax_Calc_TE==" + v_IncomeTax_Calc_TE);
//                        rebate = 0.00;
//                        if (v_Earned_TaxableSalary < 500000) {
//                            rebate = 2000;
//                        }
//                        totalTax = (v_IncomeTax_Calc_TE - rebate) + EDucess;
//                        
                        if (v_TaxableIncomeForTaxCalc > 0) { //-- 3500000 ----------------
                           // if ((v_TaxableIncomeForTaxCalc > 250000) && (v_TaxableIncomeForTaxCalc <= 500000)) {
                             if ((v_TaxableIncomeForTaxCalc1 > 250000) && (v_TaxableIncomeForTaxCalc <= 500000)) {
                                v_IncomeTax_Calc_TE = v_IncomeTax_Calc_TE + (v_TaxableIncomeForTaxCalc * 0.2);
                            }
                            if (v_TaxableIncomeForTaxCalc > 500000) {
                                v_IncomeTax_Calc_TE = v_IncomeTax_Calc_TE + (500000 * 0.2);
                                v_TaxableIncomeForTaxCalc = v_TaxableIncomeForTaxCalc - 500000;
                                if (v_TaxableIncomeForTaxCalc > 0) {
                                    v_IncomeTax_Calc_TE = v_IncomeTax_Calc_TE + (v_TaxableIncomeForTaxCalc * 0.3);
                                }
                            }
                        }
                        taxOnAbove = v_IncomeTax_Calc_TE;
                        if (temp_v_TaxableIncomeForTaxCalc < 500000) {
                            v_IncomeTax_Calc_TE = v_IncomeTax_Calc_TE - Double.parseDouble(com.mss.mirage.util.Properties.getProperty("REBATE"));
                        }
                        
                        
                        v_Edu_Cess = v_IncomeTax_Calc_TE * 0.03;
                        v_IncomeTax_Calc_TE = v_IncomeTax_Calc_TE + v_Edu_Cess;
                        totalTax = v_IncomeTax_Calc_TE;
                    }
                    taxRealised = TDS_uptoPreviousMonths;
                    if (month == 3) {
                        taxRealised = 0;
                    }
                    balanceTax = v_IncomeTax_Calc_TE - taxRealised;
                    if(monthDiff!=0){
                    taxdeductable = Math.round((v_IncomeTax_Calc_TE - taxRealised) / (monthDiff + 1));
                    }else{
                        taxdeductable=balanceTax;
                    }
                //    System.out.println("v_IncomeTax_Calc_TE==" + v_IncomeTax_Calc_TE);
                } else {
              //      System.out.println("Not Aplicable!!");
                }
              //  DetailsMap.put("taxOnAbove", formatter.format(temptax));
                if (v_Earned_TaxableSalary < 500000) {
                    rebate = Double.parseDouble(com.mss.mirage.util.Properties.getProperty("REBATE"));
                }
                 DetailsMap.put("HRE", formatter.format(v_FinalEmpSavings5ForTaxCalc));
                if(taxOnAbove<0){
                    taxOnAbove=0;
                }
                if(rebate<0){
                    rebate=0;
                }
                if(v_Edu_Cess<0){
                    v_Edu_Cess=0;
                }
                if(totalTax<0){
                    totalTax=0;
                }
                if(taxRealised<0){
                    taxRealised=0;
                }
                if(balanceTax<0){
                    balanceTax=0;
                }
                if(taxdeductable<0){
                    taxdeductable=0;
                }
                
                DetailsMap.put("taxableSal", formatter.format(Math.round(taxablesal)));
                
                DetailsMap.put("taxOnAbove", formatter.format(Math.round(taxOnAbove)));
                DetailsMap.put("rebate", formatter.format(rebate));
                DetailsMap.put("eduCess", formatter.format(Math.round(v_Edu_Cess)));
                DetailsMap.put("totalTax", formatter.format(Math.round(totalTax)));
                DetailsMap.put("taxRealised", formatter.format(Math.round(taxRealised)));
                DetailsMap.put("taxRealisedByOthers", "-");
                //balanceTax = totalTax - taxRealised;
                DetailsMap.put("balanceTax", formatter.format(Math.round(balanceTax)));
                // taxdeductable = (double) balanceTax / (monthDiff - 1);
                DetailsMap.put("taxdeductable", formatter.format(taxdeductable));
            }
            //System.out.println("HashMap-----------------" + DetailsMap.size());
            if (DetailsMap.size() > 0) {
                List DataList = new ArrayList();
                DataList.add(DetailsMap);
                String PaySlipSource = ReportProperties.getProperty("TEFJRXML");
                //System.out.println("BankReportsSource==" + PaySlipSource);
                //System.out.println("before ");
                JasperReport jasperReport = JasperCompileManager.compileReport(PaySlipSource);
                //System.out.println("after compiling");
                JRBeanCollectionDataSource jrxmlds = new JRBeanCollectionDataSource(DataList);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, RBMap, jrxmlds);
                //System.out.println("asd");
                //System.out.println("After");
                byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);

                String PaySlipDestination = ReportProperties.getProperty("PayslipDownloadLocation");
                File file = new File(PaySlipDestination + "/" + DetailsMap.get("empName") + "_" + DetailsMap.get("empId") + "of" + monthName + ".pdf");
                JRPdfExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE, file);
//                exporter.setParameter(JRPdfExporterParameter.OWNER_PASSWORD, password);
//                exporter.setParameter(JRPdfExporterParameter.USER_PASSWORD, password);
                exporter.setParameter(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.TRUE);
                exporter.setParameter(JRPdfExporterParameter.PERMISSIONS,new Integer(PdfWriter.AllowCopy | PdfWriter.AllowPrinting));
                exporter.exportReport();
                Date date = new Date();
                fileName = file.getName();
                //System.out.println("fileName-->" + fileName);
                if (file.exists()) {
                  //  System.out.println("in if-->");
                    inputStream = new FileInputStream(file);
                    outputStream = httpServletResponse.getOutputStream();
                    httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
                    int noOfBytesRead = 0;
                    byte[] byteArray = null;
                    while (true) {
                        byteArray = new byte[1024];
                        noOfBytesRead = inputStream.read(byteArray);
                        if (noOfBytesRead == -1) {
                            break;
                        }
                        outputStream.write(byteArray, 0, noOfBytesRead);
                    }
                }


                //System.out.println("testtt");
                outputStream.flush();
                outputStream.close();
               // System.out.println("close---");
            } else {

                reportResult = "<font style='color:red;font-size:15px;'>No records are available between selected dates</font>";
                //   httpServletRequest.getSession(false).setAttribute("resultMessage", "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>");
               // httpServletRequest.getSession(false).setAttribute("resultMessage12", "<font style='color:red;font-size:15px;'>No records exists for the given Month and Year !!</font>");
            }
        } catch (Exception ex) {
           // ex.printStackTrace();


        } finally {

            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (resultSet1 != null) {
                    resultSet1.close();
                    resultSet1 = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (statement1 != null) {
                    statement1.close();
                    statement1 = null;
                }
               
//                if (statement2 != null) {
//                    statement2.close();
//                    statement2 = null;
//                }

                if (connection != null) {
                    connection.close();
                    connection = null;
                }

            } catch (SQLException sqle) {
                sqle.printStackTrace();

            }
        }
        return reportResult;
    }





    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;

    }
    /*
     * Method for Excel Format Logistics DocRepository Download
     */

    public String getDownloadType() {
        return downloadType;
    }

    /**
     * @param downloadType the downloadType to set
     */
    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType;
    }

    /**
     * @return the sheetType
     */
    public String getSheetType() {
        return sheetType;
    }

    /**
     * @param sheetType the sheetType to set
     */
    public void setSheetType(String sheetType) {
        this.sheetType = sheetType;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the leaveType
     */
    public String getLeaveType() {
        return leaveType;
    }

    /**
     * @param leaveType the leaveType to set
     */
    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    /**
     * @return the startdate
     */
    public String getStartdate() {
        return startdate;
    }

    /**
     * @param startdate the startdate to set
     */
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    /**
     * @return the enddate
     */
    public String getEnddate() {
        return enddate;
    }

    /**
     * @param enddate the enddate to set
     */
    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    /**
     * @return the reportsToId
     */
    public String getReportsToId() {
        return reportsToId;
    }

    /**
     * @param reportsToId the reportsToId to set
     */
    public void setReportsToId(String reportsToId) {
        this.reportsToId = reportsToId;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmpnameById() {
        return empnameById;
    }

    public void setEmpnameById(String empnameById) {
        this.empnameById = empnameById;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the empNo
     */
    public String getEmpNo() {
        return empNo;
    }

    /**
     * @param empNo the empNo to set
     */
    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    /**
     * @return the payslipFlag
     */
    public int getPayslipFlag() {
        return payslipFlag;
    }

    /**
     * @param payslipFlag the payslipFlag to set
     */
    public void setPayslipFlag(int payslipFlag) {
        this.payslipFlag = payslipFlag;
    }

    /**
     * @return the payslipResponse
     */
    public String getPayslipResponse() {
        return payslipResponse;
    }

    /**
     * @param payslipResponse the payslipResponse to set
     */
    public void setPayslipResponse(String payslipResponse) {
        this.payslipResponse = payslipResponse;
    }
}

