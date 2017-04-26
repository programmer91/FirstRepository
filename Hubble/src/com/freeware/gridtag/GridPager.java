/*------------------------------------------------------------------------------
 * PACKAGE: com.freeware.gridtag
 * FILE   : GridPager.java
 * CREATED: Jul 24, 2004
 * AUTHOR : Prasad P. Khandekar
 *------------------------------------------------------------------------------
 * Change Log:
 *-----------------------------------------------------------------------------*/
package com.freeware.gridtag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * This class represents the grid pager.
 * @author Prasad P. Khandekar
 * @version 1.0
 * @since 1.0
 */
public final class GridPager extends TagSupport {
    private String mstrImgFirst;
    private String mstrImgPrevious;
    private String mstrImgNext;
    private String mstrImgLast;
    private String mstrAddImage;
    private String mstrAddAction;
    private String mstrScriptFunction;
    private String mstrSearchBy;
    private String mstrSearchFunction;
    
    public GridPager() {
        super();
        this.mstrImgFirst = "/MirageV2/includes/images/DBGrid/First.gif";
        this.mstrImgPrevious = "/MirageV2/includes/images/DBGrid/Previous.gif";
        this.mstrImgNext = "/MirageV2/includes/images/DBGrid/Next.gif";
        this.mstrImgLast = "/MirageV2/includes/images/DBGrid/Last.gif";
        this.setAddImage("/MirageV2/includes/images/DBGrid/Add.png");
        
    }
    
/*------------------------------------------------------------------------------
 * Getters
 *----------------------------------------------------------------------------*/
    public String getImgFirst() {
        return this.mstrImgFirst;
    }
    
    public String getImgPrevious() {
        return this.mstrImgPrevious;
    }
    
    public String getImgNext() {
        return this.mstrImgNext;
    }
    
    public String getImgLast() {
        return this.mstrImgLast;
    }
    public String getAddImage() {
        return mstrAddImage;
    }
    public String getAddAction() {
        return mstrAddAction;
    }
    
    public String getScriptFunction(){
        return mstrScriptFunction;
    }
    
    public String getSearchBy() {
        return mstrSearchBy;
    }
    
    public String getSearchFunction(){
        return mstrSearchFunction;
    }
/*------------------------------------------------------------------------------
 * Setters
 *----------------------------------------------------------------------------*/
    public void setImgFirst(String pstrImgFirst) {
        this.mstrImgFirst = pstrImgFirst;
    }
    
    public void setImgPrevious(String pstrImgPreviosu) {
        this.mstrImgPrevious = pstrImgPreviosu;
    }
    
    public void setImgNext(String pstrImgNext) {
        this.mstrImgNext = pstrImgNext;
    }
    
    public void setImgLast(String pstrImgLast) {
        this.mstrImgLast = pstrImgLast;
    }
    public void setAddImage(String pstrAddImage) {
        this.mstrAddImage = pstrAddImage;
    }
    public void setAddAction(String pstrAddAction) {
        this.mstrAddAction = pstrAddAction;
    }
    public void setScriptFunction(String pstrScriptFunction){
        this.mstrScriptFunction = pstrScriptFunction;
    }
    
    public void setSearchBy(String pstrSearchBy) {
        this.mstrSearchBy = pstrSearchBy;
    }
    
    public void setSearchFunction(String pstrSearchFunction){
        this.mstrSearchFunction = pstrSearchFunction;
    }
/*------------------------------------------------------------------------------
 * Overridden methods
 * @see javax.servlet.jsp.tagext.Tag
 *----------------------------------------------------------------------------*/
    /* (non-Javadoc)
     * @see javax.servlet.jsp.tagext.Tag#doEndTag()
     */
    public int doEndTag() throws JspException {
        DBGrid objTmp = null;
        
        try {
            objTmp = (DBGrid) getParent();
            objTmp.setPager(getCopy());
        } catch (ClassCastException CCEx) {
            throw new JspException("Error: ImageColumn tag is not a child of DBGrid", CCEx);
        } finally {
            if (objTmp != null) objTmp = null;
        }
        return EVAL_PAGE;
    }
    
    /* (non-Javadoc)
     * @see javax.servlet.jsp.tagext.Tag#doStartTag()
     */
    public int doStartTag() throws JspException {
        if (!(this.getParent() instanceof DBGrid))
            throw new JspException("Error: Column tag needs to be a child of DBGrid!");
        
        // This tag does not have body contents.
        return SKIP_BODY;
    }
    
/*------------------------------------------------------------------------------
 * Methods
 *----------------------------------------------------------------------------*/
    public void renderPager(int pintCurrPage, int pintTotRec, int pintPageSize) throws JspException {
        int          intPages = 0;
        StringBuffer objBuf = null;
        int recordStart = 0;
        int recordEnd = 0;
        int curRecord = 0;
        String pageRecordsInfo = null;
        String javaScriptFunction = null;
        try {
            if ((pintTotRec % pintPageSize) == 0)
                intPages = pintTotRec / pintPageSize;
            else
                intPages = (pintTotRec / pintPageSize) + 1;
            
            /**
             * Calculation to render the current page records information
             */
            if(pintCurrPage == 1){
                if(pintTotRec == 0){
                    pageRecordsInfo = "(0 - 0 of "+(pintTotRec)+")";
                } else if(pintTotRec >= pintPageSize){
                    pageRecordsInfo = "(1 - "+pintPageSize+" of "+(pintTotRec)+")";
                }else {
                    recordEnd = pintTotRec % pintPageSize;
                    pageRecordsInfo = "(1 - "+recordEnd+" of "+(pintTotRec)+")";
                }
                
            }else if(pintCurrPage == intPages){
                
                recordEnd = pintTotRec % pintPageSize;
                
                if(recordEnd == 0){
                    curRecord = (pintTotRec-pintPageSize)+1;
                    recordEnd = (curRecord+pintPageSize)-1;
                    pageRecordsInfo = "("+curRecord+" - "+recordEnd+" of "+(pintTotRec)+")";
                }else{
                    curRecord = (pintTotRec-recordEnd)+1;
                    pageRecordsInfo = "("+curRecord+" - "+pintTotRec+" of "+(pintTotRec)+")";
                }
                
            }else if((pintCurrPage != 1) && (pintCurrPage != intPages)){
                curRecord = ((pintCurrPage-1) * pintPageSize)+1;
                recordEnd = ((pintCurrPage-1) * pintPageSize)+pintPageSize;
                pageRecordsInfo = "("+curRecord+" - "+recordEnd+" of "+(pintTotRec)+")";
            }
            
            if(getScriptFunction() != null){
                javaScriptFunction = getScriptFunction();
            }else{
                javaScriptFunction = "doNavigate";
            }
            
            objBuf = new StringBuffer();
            objBuf.append("<table BORDER=0 CELLSPACING=0 CELLPADDING=0 ");
            objBuf.append("WIDTH=\"100%\">\r\n");
            //objBuf.append("<tr CLASS=\"gridPager gridPageOfPage\">\r\n");
            objBuf.append("<tr>\r\n");
            objBuf.append("<td ALIGN=\"left\" WIDTH=\"50%\">\r\n");
            objBuf.append("<a HREF=\"javascript:"+javaScriptFunction+"('F', " + intPages +
                    ")\">\r\n");
            objBuf.append("<img SRC=\"" + this.mstrImgFirst + "\" BORDER=0></a>\r\n");
            objBuf.append("<a HREF=\"javascript:"+javaScriptFunction+"('P', " + intPages +
                    ")\">\r\n");
            objBuf.append("<img SRC=\"" + this.mstrImgPrevious + "\" BORDER=0></a>\r\n");
            
            objBuf.append("<span class=\"gridPager\">"+pageRecordsInfo+"</span>\r\n");
            
            objBuf.append("<a HREF=\"javascript:"+javaScriptFunction+"('N', " + intPages +
                    ")\">\r\n");
            objBuf.append("<img SRC=\"" + this.mstrImgNext + "\" BORDER=0></a>\r\n");
            objBuf.append("<a HREF=\"javascript:"+javaScriptFunction+"('L', " + intPages +
                    ")\">\r\n");
            objBuf.append("<img SRC=\"" + this.mstrImgLast + "\" BORDER=0></a>\r\n");
            
            // START:For Adding Button
            if(!"".equals(this.getAddAction()) && this.getAddAction()!=null) {
                objBuf.append("<a HREF=\"");
                objBuf.append(resolveFields(this.getAddAction()));
                objBuf.append("\" style=\"padding-left=30px;\">\r\n");
                objBuf.append("<img SRC=\"" + this.getAddImage() + "\" BORDER=\"0\" WIDTH=\"51\" HEIGHT=\"20\">");
                objBuf.append("</a>\r\n");
            }
            //END: For Adding Button
            
            //START: For Adding Search Capability
            if(!"".equalsIgnoreCase(this.getSearchBy()) && this.getSearchBy()!=null) {
                objBuf.append("<span class=\"gridPager\">&nbsp;&nbsp;Search By:&nbsp;</span>");
                objBuf.append("<input type=text  name='searchBy' class='inputTextBlue'  value='" + this.getSearchBy() + "' " + " onkeypress='return handleEnter(this,event);'>");
                objBuf.append("<input type='button' name='searchButton' class='buttonBg' value='Search' onclick='"+this.getSearchFunction()+"();'>");
            }
            //END: For Adding Search Capability
            objBuf.append("</td>\r\n");
            
            // START:For Adding Button
            /*objBuf.append("<td ALIGN=\"left\" WIDTH=\"20%\">\r\n");
            if(!"".equals(this.getAddAction()) && this.getAddAction()!=null) {
                objBuf.append("<a HREF=\"");
                objBuf.append(resolveFields(this.getAddAction()));
                objBuf.append("\">\r\n");
                objBuf.append("<img SRC=\"" + this.getAddImage() + "\" BORDER=\"0\" WIDTH=\"24\" HEIGHT=\"12\">");
                objBuf.append("</a>\r\n");
            }
            objBuf.append("</td>\r\n");*/
            //END: For Adding Button
            objBuf.append("<td CLASS=\"gridPageOfPage\" WIDTH=\"25%\" >\r\n");
            objBuf.append("Go To Page:");
            objBuf.append("<select id='goToPageGrid' name='goToPageGrid' value='"+pintCurrPage+"' onchange='goToPage(this);'>\r\n");
            //System.err.println("pintCurrPage---"+pintCurrPage);
            objBuf.append("<option value=''>Select</option>");
            for(int index=1;index<=intPages;index++) {
                objBuf.append("<option value='"+index+"'>"+index+"</option>");
            }
            objBuf.append("</select>");
            objBuf.append("</td>\r\n");
            objBuf.append("<td ALIGN=\"right\" WIDTH=\"25%\" ");
            objBuf.append("CLASS=\"gridPageOfPage\">\r\n");
            if(pintTotRec == 0){
                objBuf.append("Page 0 of " + intPages);
            }else{
                objBuf.append("Page " + pintCurrPage + " of " + intPages);
            }
            objBuf.append("</td>\r\n");
            objBuf.append("</tr>\r\n");
            objBuf.append("</table>\r\n");
            this.pageContext.getOut().println(objBuf.toString());
        } catch (IOException IoEx) {
            throw new JspException("Error: While drawing grid pager!", IoEx);
        } finally {
            if (objBuf != null) objBuf = null;
        }
    }
    
/*------------------------------------------------------------------------------
 * Helpers
 *----------------------------------------------------------------------------*/
    
    private String resolveFields(String pstrUrl) throws ClassCastException {
        int    intPos = 0;
        int    intEnd = 0;
        String strCol = null;
        String strRet = null;
        DBGrid objTmp = null;
        
        strRet = pstrUrl;
        objTmp = (DBGrid) getParent();
        intPos = strRet.indexOf("{");
        while (intPos >= 0) {
            intEnd = strRet.indexOf("}", intPos + 1);
            if (intEnd != -1) {
                strCol = strRet.substring(intPos + 1, intEnd);
                strRet = strRet.substring(0, intPos) +
                        objTmp.getColumnValue(strCol) +
                        strRet.substring(intEnd + 1);
            }
            intPos = strRet.indexOf("{", intPos +1);
        }
        return strRet;
    }
    

    private GridPager getCopy() {
        GridPager objRet = null;
        
        objRet = new GridPager();
        objRet.setId(this.getId());
        objRet.setPageContext(this.pageContext);
        objRet.setParent(this.getParent());
        objRet.setImgFirst(this.mstrImgFirst);
        objRet.setImgPrevious(this.mstrImgPrevious);
        objRet.setImgNext(this.mstrImgNext);
        objRet.setImgLast(this.mstrImgLast);
        objRet.setAddImage(this.mstrAddImage);
        objRet.setAddAction(this.mstrAddAction);
        objRet.setScriptFunction(this.mstrScriptFunction);
        objRet.setSearchBy(this.mstrSearchBy);
        objRet.setSearchFunction(this.mstrSearchFunction);
        return objRet;
    }
}
