package cn.xidian.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;


//GZip压缩对文本效果很好，对二进制数据效果较差！二进制BZip压缩效果好
public class GZipFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		MyBufferedResponse myBufferedResponse = new MyBufferedResponse(response);
		
		chain.doFilter(request, myBufferedResponse);
		
		//拿到未压缩的字节数组
		byte[] out = myBufferedResponse.getBuffer();
		System.out.println("压缩前大小："+out.length);//原始大小
		
		//将数据压缩并写进缓冲数组byteArrayOutputStream
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
		gzipOutputStream.write(out);
		gzipOutputStream.close();//注意要close，把数据刷到byteArrayOutputStream中，否则可能缓冲区数据为空。
		
		
		//拿到压缩后的字节数组
		byte gzip[] = byteArrayOutputStream.toByteArray();
		System.out.println("压缩后大小："+gzip.length);//压缩后大小
		
		//设置输出头，并将压缩后的数据打给浏览器
		response.setHeader("content-encoding", "gzip");
		response.setContentLength(gzip.length);
		response.getOutputStream().write(gzip);
		
	}
	
	@Override
	public void destroy() {}
	@Override
	public void init(FilterConfig config) throws ServletException {}
}



class MyBufferedResponse extends HttpServletResponseWrapper{

	private HttpServletResponse response;
	private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();//字节缓冲区
	private PrintWriter myPrintWriter;
	
	public MyBufferedResponse(HttpServletResponse response) {
		super(response);
		this.response = response;
	}
	
	//ServletOutputStream是抽象类，不能new，所以要自己写一个ServletOutputStream
	@Override
	public ServletOutputStream getOutputStream() throws IOException {	
		return new MyServletOutputStream(byteArrayOutputStream);
	}
	
	@Override
	public PrintWriter getWriter() throws IOException {
		//myPrintWriter = new PrintWriter(byteArrayOutputStream);//字节转化成字符，可能导致乱码！需要再包装一层！
		myPrintWriter = new PrintWriter(new OutputStreamWriter(byteArrayOutputStream, this.response.getCharacterEncoding()));
		return myPrintWriter;
	}
	
	public byte[] getBuffer(){
		try {
			if(myPrintWriter!=null){
				myPrintWriter.close();
			}
			if(byteArrayOutputStream!=null){
				byteArrayOutputStream.flush();
				return byteArrayOutputStream.toByteArray();
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}

class MyServletOutputStream extends ServletOutputStream{
	private ByteArrayOutputStream byteArrayOutputStream;
	public MyServletOutputStream(ByteArrayOutputStream byteArrayOutputStream){
		this.byteArrayOutputStream = byteArrayOutputStream;
	}
	@Override
	public void write(int b) throws IOException {
		this.byteArrayOutputStream.write(b);
	}
}










