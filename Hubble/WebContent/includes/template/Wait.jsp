<%@ page contentType="text/html; charset=UTF-8" errorPage="../exception/ErrorDisplay.jsp"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Please Wait..</title>
        <style type="text/css">
            body {
            margin:0;
            padding:40px;
            background:#fff;
            font:70% Arial, Helvetica, sans-serif;
            color:#555;
            line-height:180%;
            }
            h6{
            font-size:180%;
            font-weight:normal;
            color:#555;
            }
            
            .progressBar{
            width:216px;
            height:41px;
            background:url(bg_bar.gif) no-repeat 0 0;
            position:relative;
            }
            .progressBar span{
            position:absolute;
            display:block;
            width:200px;
            height:25px;
            background:url(../../includes/images/progressbar/bar.gif) no-repeat 0 0;
            top:8px;
            left:8px;
            overflow:hidden;
            text-indent:-8000px;
            }
            .progressBar em{
            position:absolute;
            display:block;
            width:200px;
            height:25px;
            background:url(../../includes/images/progressbar/bg_cover.gif) repeat-x 0 0;
            top:0;
            }
        </style>
    </head>
    <body>
        <table width="1000" height="580" align="center" border="0" cellpadding="0" cellspacing="1">
            <tr>
                <td align="center" valign="center">
                    Processing your request please wait...
                    <p class="progressBar">
                        <span><em style="left:200px">25%</em></span>
                    </p>
                    
                </td>
            </tr>
            
        </table>
    </body>
</html>

