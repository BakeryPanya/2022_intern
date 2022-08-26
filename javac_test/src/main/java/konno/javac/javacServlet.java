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
		
		//json ファイル読み込み
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode json = objectMapper.readTree(Paths.get("code/data.json").toFile());
		System.out.println(json.toString());
		
		
		//ここからjavacコンパイル処理
		try {
			
			boolean error = false;
			boolean error1 = false;
			
            Runtime runtime = Runtime.getRuntime();
            
            Process p = runtime.exec("cmd /c javac code/Main.java");
            //コマンドを書く場所
            
            InputStream in = p.getInputStream();
            InputStream a = p.getErrorStream();
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
    		if( error==false && error1==false) {//どちらもfalseであればコンパイルエラーはでていない
    			try {
    				//変数のスコープ的にまだいきてたので変数名を変更します
    				String ans = json.get("ans_array_" + String.valueOf(num)).get(String.valueOf(num)).get("ans").toString();
    	            ans = ans.substring(1,ans.length()-1);
    	            
    	            String hint = json.get("ans_array_" + String.valueOf(num)).get(String.valueOf(num)).get("hint").toString();
    	            hint = hint.substring(1,hint.length()-1);
    	            
    	            String hint_code = json.get("ans_array_" + String.valueOf(num)).get(String.valueOf(num)).get("hint_code").toString();
    	            hint_code = hint_code.substring(1,hint_code.length()-1);
    	            
    	            String execute = json.get("ans_array_" + String.valueOf(num)).get(String.valueOf(num)).get("execute").toString();
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
    	            String s;
    	            StringBuilder check = new StringBuilder();
    	       
    	            while ((s = br2.readLine()) != null) {
    	            	out.println(s);
    	            	check.append(s);
    	            }//実行結果の表示処理
    	            
    	            while ((s = br3.readLine()) != null) {
    	            	out.println(s);
    	            }//実行処理のエラー表示処理
    	            
    	            //ここまで表示処理
    	            
    	            
    	            //問題番号を取得0の場合は選択されていないためエラー処理となる
    	            if(num != 0) {
        	            
        	            System.out.println(ans);
        	            System.out.println("---");
        	            System.out.println(check.toString());
        	            if((check.toString()).contentEquals(ans) == true) {
        	            	out.println("正解");
        	            }else {
        	            	out.println("出力結果が間違っています");
        	            	if((check.toString()).contentEquals(hint) == true) {
        	            		out.println(hint_code);
        	            	}
        	            	
        	            }
        	            
    	            }else {
    	            	out.println("問題番号が選択されていません");
    	            }
    	            
    	            
    	            //正誤判定ここまで
    	            
    	            
    	            
    	            //ここから問題とあっているかの正誤判定処理
    	            //一旦はHelloworldで
    	            
    	           
    	            
    	            
    	          //pの破棄をするのとreturncodeでデバッグ用処理
    	            int ret1 = q.waitFor();
    	            System.out.println("return code =" + ret1);//実行結果のreturnコードを確認
    	            q.destroy();
    	            
    	           
    	        } catch (Exception e) {
    	            System.out.println(e);
    	        }
    		
    		}else{
    			out.println("コンパイルエラーが発生しているため動作を停止しました");
    		}
    		//実行処理のifはここまでです。
           
        } catch (Exception e) {//最後のcatch
            System.out.println(e);
        }
		
		
	
    
		
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
