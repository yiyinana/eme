package cn.xidian.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class CharactorEncodingFilter implements Filter {

	private String defaultCharset = "UTF-8";
	private FilterConfig config;
	
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String initCharset = config.getInitParameter("charset");
		if (initCharset==null) {
			initCharset = defaultCharset;
		}
		
		request.setCharacterEncoding(initCharset);//只对post提交有效，对get无效，故需要增强
		response.setCharacterEncoding(initCharset);
		response.setContentType("text/html;charset="+initCharset);
		
		chain.doFilter(new MyCharactorEncodingRequest(request), response);
		
	}
	

	public void destroy() {}

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}

class MyCharactorEncodingRequest extends HttpServletRequestWrapper{
	
	private HttpServletRequest request;
	
	public MyCharactorEncodingRequest(HttpServletRequest request){
		super(request);
		this.request = request;
	}

	@Override
	public String getParameter(String paramName) {
		String paramValue = this.request.getParameter(paramName);
		if (paramValue == null) {
			return null;
		}
		if(!this.request.getMethod().equalsIgnoreCase("get")){
			return paramValue;
		}
		try {
			paramValue =new String(paramValue.getBytes("ISO8859-1"),this.request.getCharacterEncoding());
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return paramValue;
	}
	
	
}
