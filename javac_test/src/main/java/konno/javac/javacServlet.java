package konno.javac;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 Servlet implementation class javacServlet
 */
@WebServlet("/javacServlet")

public class javacServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public javacServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("Shift_JIS");
		PrintWriter out = response.getWriter();
		//ここで受け取ったデータを変換したい。
		File file = new File("code//Main.java");
		 
		String code = request.getParameter("code");
		
		
		try(FileOutputStream stream = new FileOutputStream(file);
			    OutputStreamWriter writer = new OutputStreamWriter(stream);){

			    // 文字列の書き込み
			    writer.write(code);

			} catch (FileNotFoundException e) {
			    e.printStackTrace();
			} catch (IOException e) {
			    e.printStackTrace();
			}
		
		
		
		
		//ここからjavacコンパイル処理
		try {
			
            Runtime runtime = Runtime.getRuntime();
            Process p = runtime.exec("cmd /c/Users/noair/OneDrive/デスクトップ/code ipconfig");
            //この部分のパスが通らない実行処理も同様。ここさえ通れば結構望みが見えそうです。
            InputStream in = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in,"Shift_JIS"));
            String v;
            while ((v = br.readLine()) != null) {
            	out.println(v);
            }
            int ret = p.waitFor();
            
            System.out.println(v);
            out.println("return code =" + ret);
            p.destroy();
            
           
        } catch (Exception e) {
            System.out.println(e);
        }
		//実行処理
//		try {
//	
//            Runtime runtime = Runtime.getRuntime();
//            Process q = runtime.exec("cmd /c/Users/noair/OneDrive/デスクトップ/code java Main");
//            InputStream in = q.getInputStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(in,"Shift_JIS"));
//            String s;
//            while ((s = br.readLine()) != null) {
//            	out.println(s);
//            	System.out.println(s+"a");
//            }
//            int ret = q.waitFor();
//            
//            out.println("return code =" + ret);
//            q.destroy();
//            
//           
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//	
    
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
