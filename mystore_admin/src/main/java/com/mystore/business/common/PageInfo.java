package com.mystore.business.common;

import org.apache.struts2.ServletActionContext;

public class PageInfo{

	private static int pagesize = 10;
	
	/*
	 * pages：当前页码，默认显示第一页
	 * count：是总记录数
	 */
	public static String getPageForCountry_bj(int pages, int count,int pageSize){
		pagesize = pageSize;
		String PAGE_CODE_FORM=  ServletActionContext.getRequest().getParameter("PAGE_CODE_FORM");
		if(null == PAGE_CODE_FORM) PAGE_CODE_FORM = "";
		
		StringBuffer buffer = new StringBuffer();
		int liststep = 10;//最多显示分页页数
		int pagescount = (int) Math.ceil((double) count / pagesize);//求总页数，ceil（num）取整不小于num
		if (pagescount < pages) {
		    pages = pagescount;//如果分页变量大总页数，则将分页变量设计为总页数
		}
		if (pages < 1) {
		    pages = 1;//如果分页变量小于１,则将分页变量设为1
		}
		int listbegin = (pages - (int) Math.ceil((double) liststep / 2));//从第几页开始显示分页信息
		if (listbegin < 1) { //当前页-(总显示的页列表数/2)
		    listbegin = 1;
		}
		int listend = pages + liststep / 2;//分页信息显示到第几页//当前页+(总显示的页列表数/2)
		if (listend > pagescount) {
		    listend = pagescount + 1;
		}
		if(count == 0) return ""; 
		
		//<显示上一页
		if(pages == 1){
			buffer.append("<a href=\"javascript:void(0)\" title=\"上一页\" style=\"vertical-align:bottom\">");			
			buffer.append("<<</a>&nbsp;&nbsp;");
		}else{
			buffer.append("<a href=\"javascript:void(0)\" title=\"上一页\" style=\"vertical-align:bottom\" onclick=\"jumppage('"+(pages - 1)+"');return false\">");
			buffer.append("<<</a>&nbsp;&nbsp;");
		}
		
		
		//1前加入省略号。
		if(listbegin>1){
			buffer.append("<span style='color:blue;font-size:16px;'>...</span>&nbsp;&nbsp;");
		}
		//<显示分页码
		for (int i = listbegin; i < listend; i++) {
		    if (i != pages) {//如果i不等于当前页
		    	buffer.append("<a href=\"javascript:void(0)\" onclick=\"jumppage('"+(i)+"');return false\">"+i+"</a>&nbsp;&nbsp;");
		    } else {
		    	buffer.append("<font style='text-decoration:none;color:red'>" + i + "</font>&nbsp;&nbsp;");
		    }
		}//显示分页码>
		
		//页码后...页码省略号
		if(pagescount>listend){
			buffer.append("<span style='color:blue;font-size:16px;'>...</span>&nbsp;&nbsp;");
		}
		//<显示下一页
		if(pagescount == 1){
			buffer.append("<a href=\"javascript:void(0)\" title=\"下一页\" style=\"vertical-align:bottom\">");
			buffer.append(">></a>&nbsp;&nbsp;");
		}else{
			buffer.append("<a href=\"javascript:void(0)\" title=\"下一页\" style=\"vertical-align:bottom\"  onclick=\"jumppage('"+(pages + 1)+"');return false\">");
			buffer.append(">></a>&nbsp;&nbsp;");
		}
		buffer.append("<input type=\"hidden\" id=\"pageNo\" name=\"pageNo\" value='"+pages+"' />");
		buffer.append("<input type=\"hidden\" value='"+pagescount+"' id=\"pagescount\"/>");
		buffer.append("<input type=\"hidden\" id=\"pageSize\" name=\"pageSize\"  value='"+pagesize+"' />");
		buffer.append("<input type=\"hidden\" id=\"clear\" name=\"clear\" />");
		return buffer.toString();
	}
	
	public static String setPage(Integer pageNo,Integer pageSize,Integer totalPages,Integer totalResults){
	                 StringBuilder page= new StringBuilder("<div class=\"pager\"><div class=\"a\">");
	                 if(totalPages==1){
	                	 page.append("<a href=\"javascript:void(0);\" title=\"上一页\">上一页</a>&nbsp;&nbsp;");
	                	 	page.append("<b>1</b>&nbsp;");
	                			 page.append("&nbsp;<a href=\"javascript:void(0);\" title=\"下一页\">下一页</a>");
	                 }
	                 int totle=5;
	                 if(totalPages>1){
	                     if(pageNo>1){
	                    	 page.append("<a href=\"javascript:void(0);\" title=\"上一页\" onclick=\"javascrpt:jumppage("+(pageNo-1)+");\">上一页</a>&nbsp;&nbsp;");
	                     }else if(pageNo==1){
	                    	 page.append("&nbsp;<a href=\"javascript:void(0);\" title=\"上一页\">上一页</a>");
	                     }
	                     
	                     StringBuilder content = new StringBuilder("");
	                     StringBuilder left = new StringBuilder("");
	                     int leftlength=0;
	                     StringBuilder right = new StringBuilder("");
	                     int rightlength=0;
	                     StringBuilder midle = new StringBuilder("");
	                     for(int i=pageNo-totle/2;i<pageNo;i++){
	                         if(i>0){
	                            left.append("&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascrpt:jumppage("+i+");\">"+i+"</a>&nbsp;&nbsp;");
	                            leftlength++;
	                         }
	                     }
	                     midle.append("&nbsp;<b>"+pageNo+"</b>&nbsp;");
	                     for(int i=pageNo+1;i<=pageNo+totle/2;i++){
	                         if(i<=totalPages){
	                             right.append("&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascrpt:jumppage("+i+");\">"+i+"</a>&nbsp;");
	                             rightlength++;
	                         }
	                     }
	                     
	                     if(leftlength+rightlength < totle-1){
	                        if(pageNo-leftlength-1>0 || pageNo+rightlength<totalPages){
	                             while(leftlength+rightlength<totle-1){
	                                 if(pageNo-leftlength-1>0){
	                                     leftlength++;
	                                     left = new StringBuilder("&nbsp;<a href=\"javascript:void(0);\"  onclick=\"javascrpt:jumppage("+(pageNo-leftlength)+");\">"+(pageNo-leftlength)+"</a>&nbsp;").append(left);    
	                                 }else if(pageNo+rightlength<totalPages){
	                                     rightlength++;
	                                     right.append("&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascrpt:jumppage("+(pageNo+rightlength)+");\">"+(pageNo+rightlength)+"</a>&nbsp;");    
	                                 }else break;
	                             }
	                         }
	                     }
	                     content.append(left.toString()).append(midle.toString()).append(right.toString());
	                    
	                     if(pageNo-leftlength-1 > 1){
	                    	 page.append("&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascrpt:jumppage(1);\">"+1+"</a><span>...</span>");
	                     }
	                     else if(pageNo-leftlength-1 == 1) {
	                    	 page.append("&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascrpt:jumppage(1);\">"+1+"</a>&nbsp;");
	                     }
	                     page.append(content);
	                    
	                     if(pageNo+rightlength < totalPages-1){
	                    	 page.append("<span>...</span><a href=\"javascript:void(0);\" onclick=\"javascrpt:jumppage("+totalPages+");\">"+totalPages+"</a>&nbsp;");
	                    }
	                     else if(pageNo+rightlength == totalPages-1){
	                    	 page.append("&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascrpt:jumppage("+totalPages+");\">"+totalPages+"</a>&nbsp;");
	                     }
	                    
	                     if(pageNo<totalPages){
	                    	 page.append("&nbsp;<a href=\"javascript:void(0);\" title=\"下一页\" onclick=\"javascrpt:jumppage("+(pageNo+1)+");\">下一页</a>&nbsp;");
	                     }
	                     else if(pageNo==totalPages){
	                    	 page.append("&nbsp;<a href=\"javascript:void(0);\" title=\"下一页\">下一页</a>&nbsp;");;
	                     }
	                 }
	                 page.append("</div></div>");;
	                 return page.toString();  
	}
	
	
}
