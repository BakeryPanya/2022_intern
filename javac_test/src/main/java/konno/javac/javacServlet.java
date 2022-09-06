package konno.javac;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;






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
		HttpSession session = request.getSession(true);
		response.setCharacterEncoding("Shift_JIS");
		PrintWriter out = response.getWriter();
		
		File file = new File("code/Main.java");
		//ファイルパスは環境毎に変わりそうなのでラズパイとかで処理するならカレントディレクトリにcodeフォルダを作ってMain.javaを置いておきましょう。
		 
		String code = request.getParameter("code");
		int num = Integer.parseInt(request.getParameter("number"));
		//jspからテキストエリアのデータを格納する
		
		
		try(FileOutputStream stream = new FileOutputStream(file);
			    OutputStreamWriter writer = new OutputStreamWriter(stream);){//この処理を書くことによってfcloseは自動で行われる

			    // 文字列の書き込み
			    writer.write(code);

			} catch (FileNotFoundException e) {
			    e.printStackTrace();
			} catch (IOException e) {
			    e.printStackTrace();
			}
		
		
		//ここからjavacコンパイル処理
		try {
			int q_check = 0;
			boolean error = false;
			boolean error1 = false;
			
            Runtime runtime = Runtime.getRuntime();
            
            Process p = runtime.exec("cmd /c javac code/Main.java");
            //コマンドを書く場所
            
            InputStream in = p.getInputStream();
            InputStream a =  p.getErrorStream();
            //読み込み処理をする
            
            BufferedReader br = new BufferedReader(new InputStreamReader(in,"Shift_JIS"));
            BufferedReader br1 = new BufferedReader(new InputStreamReader(a,"Shift_JIS"));
            //読み込みはcmdに合わせたShift_JISで行う
            //このBufferedReaderに通してwhileで表示処理するとうまくいく
            
            //コンパイル結果表示処理
            String v;
            while ((v = br.readLine()) != null) {
            	out.println(v);
            	error = true;
            }//コンパイル結果表示
            
            while ((v = br1.readLine()) != null) {
            	out.println(v);
            	error1 = true; 
            }//コンパイルエラー表示処理
            
            //ここまでコンパイル結果表示処理
            
            //pの破棄をするのとreturncodeでデバッグ用処理
            int ret = p.waitFor();
            System.out.println("return code =" + ret);
            p.destroy();
            
            
      //実行処理 
          if(num != 0) {//問題番号を指定しないで送信した場合のエラー
        	  //json ファイル読み込み
        	  ObjectMapper objectMapper = new ObjectMapper();
      		  JsonNode json = objectMapper.readTree(Paths.get("code/data_"+String.valueOf(num)+".json").toFile());
      		  System.out.println(json.toString());
      		
      		  String test_num_s = (json.get("test_num")).toString();
      		  int test_num = Integer.parseInt(test_num_s);
      		  System.out.println(test_num);
      		  session.setAttribute("test_num", test_num_s);
      		
      		  if( error==false && error1==false) {//どちらもfalseであればコンパイルエラーはでていない
      			  try {
      				  //変数のスコープ的にまだいきてたので変数名を変更します
      				  for(int i=0;i<test_num;i++) {
      					  String name;
      					  name = "testcase";
      					  System.out.println(name);
          				
      					  String ans = (json.get(name).get(i).get("ans")).toString();
      					  System.out.println(ans);
      					  ans = ans.substring(1,ans.length()-1);
          	            
      					  String hint = (json.get(name).get(i).get("hint")).toString();
      					  hint = hint.substring(1,hint.length()-1);
          	            
      					  String hint_code = (json.get(name).get(i).get("hint_code")).toString();
      					  hint_code = hint_code.substring(1,hint_code.length()-1);
          	            
      					  String execute = (json.get(name).get(i).get("exec")).toString();
      					  execute = execute.substring(1,execute.length()-1);
          	            
          	            
      					  Runtime runtime1 = Runtime.getRuntime();
      					  Process q = runtime1.exec(execute,null,new File("code"));
      					  //カレントディレクトリがなぜかデスクトップなのでデスクトップ上にcodeファイルを実装している
      					  //この処理は自身の環境が変わるたびに変わりそうなので毎回カレントディレクトリを確認をしてパスをを通しなおす必要がある
      					  InputStream in1 = q.getInputStream();
      					  InputStream b = q.getErrorStream();
      					  BufferedReader br2 = new BufferedReader(new InputStreamReader(in1,"Shift_JIS"));
      					  BufferedReader br3 = new BufferedReader(new InputStreamReader(b,"Shift_JIS"));
      					  //読み込み書き込み処理を行う。詳しくはコンパイル処理と変わらない
          	            
      					  //表示処理
      					  String s = null;
      					  StringBuilder check = new StringBuilder();
          	       
      					  while ((s = br2.readLine()) != null) {
      						session.setAttribute("kekka", s);
      						  check.append(s);
      					  }//実行結果の表示処理
          	            
      					  while ((s = br3.readLine()) != null) {
      						session.setAttribute("kekka_error", s);
      					  }//実行処理のエラー表示処理
          	            
      					  //ここまで表示処理 
          	            
          	            
      					  //問題番号を取得0の場合は選択されていないためエラー処理となる
      					  
              	            
      						  System.out.println(ans);
      						  System.out.println("---");
      						  System.out.println(check.toString());
      						  session.setAttribute("testcase",ans);
      						  if((check.toString()).contentEquals(ans) == true) {
      							session.setAttribute("exe", "正解");
      							  q_check+=1;
      							  
      						  }else {
      							session.setAttribute("exe", "出力結果が間違っています");
      							  if((check.toString()).contentEquals(hint) == true) {
      								  out.println(hint_code);
      							  }
      						  }
              	           
          	            
          	              //正誤判定ここまで
          	            
          	            
          	            
      					  //ここから問題とあっているかの正誤判定処理
      					  //一旦はHelloworldで
          	            
          	           
          	            
          	            
      					  //pの破棄をするのとreturncodeでデバッグ用処理
      					  int ret1 = q.waitFor();
      					  System.out.println("return code =" + ret1);//実行結果のreturnコードを確認
      					  q.destroy();
      					
      				  }//for終わり
      				
      				  if(q_check==test_num) {
      					session.setAttribute("check", "テストケースはすべて成功しました！");
      				  }else {
      					session.setAttribute("check", "テストケースには"+String.valueOf(q_check)+"個中"+String.valueOf(test_num)+"個成功しました");
      					
      				  }
      	            
      	           
      			  	} catch (Exception e) {
      			  		System.out.println(e);
      			  	}
      		
      		  }else{
      			  out.println("コンパイルエラーが発生しているため動作を停止しました");
      	   }
      		//実行処理のifはここまでです。
        }else {
        	out.println("問題番号が選択されていません");
        	
        }
          
   
         
          
        } catch (Exception e) {//最後のcatch
            System.out.println(e);
        }
		
		
        response.sendRedirect("exe/index2.jsp");
		
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
