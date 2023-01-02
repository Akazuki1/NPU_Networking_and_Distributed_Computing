
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * Class <em>HttpClient</em> is a class representing a simple HTTP client.
 *
 * @author wben
 */

public class HttpClient {

	/**
	 * default HTTP port is port 80
	 */
	private static int port = 80;

	/**
	 * Allow a maximum buffer size of 8192 bytes
	 */
	private static int buffer_size = 8192;

	/**
	 * Response is stored in a byte array.
	 */
	private byte[] buffer;

	/**
	 * My socket to the world.
	 */
	Socket socket = null;

	/**
	 * Default port is 80.
	 */
	private static final int PORT = 80;

	/**
	 * Output stream to the socket.
	 */
	BufferedOutputStream ostream = null;

	/**
	 * Input stream from the socket.
	 */
	BufferedInputStream istream = null;

	/**
	 * StringBuffer storing the header
	 */
	private StringBuffer header = null;

	/**
	 * StringBuffer storing the response.
	 */
	private StringBuffer response = null;
	
	/**
	 * String to represent the Carriage Return and Line Feed character sequence.
	 */
	static private String CRLF = "\r\n";

	/**
	 * HttpClient constructor;
	 */
	public HttpClient() {
		buffer = new byte[buffer_size];
		header = new StringBuffer();
		response = new StringBuffer();
	}

	/**
	 * <em>connect</em> connects to the input host on the default http port --
	 * port 80. This function opens the socket and creates the input and output
	 * streams used for communication.
	 */
	public void connect(String host) throws Exception {

		/**
		 * Open my socket to the specified host at the default port.
		 */
		socket = new Socket(host, PORT);

		/**
		 * Create the output stream.
		 */
		ostream = new BufferedOutputStream(socket.getOutputStream());

		/**
		 * Create the input stream.
		 */
		istream = new BufferedInputStream(socket.getInputStream());
	}

	/**
	 * <em>processGetRequest</em> process the input GET request.
	 */
	public void processGetRequest(String request) throws Exception {
		/**
		 * Send the request to the server.
		 */
		request += CRLF + CRLF;
		buffer = request.getBytes();
		ostream.write(buffer, 0, request.length());
		ostream.flush();
		/**
		 * waiting for the response.
		 */
		processResponse();
	}
	
	/**
	 * <em>processPutRequest</em> process the input PUT request.
	 */
	public void processPutRequest(String request) throws Exception {
		//=======start your job here============//

		try{
			String[] str = request.split(" ");
			if(str[0].equals("PUT")&&str[1].startsWith("/")&&str[2].startsWith("HTTP/")){
				String path = str[1].substring(1);
				File file = new File(path);
				if(file.exists()){
					long length = file.length();
					request = request + CRLF;
					request = request + "Content-Length: " + length;
					request = request + CRLF + CRLF;
					buffer = Files.readAllBytes(file.toPath());
					byte[] header = request.getBytes();
					ostream.write(header);
					ostream.write(buffer);
					ostream.flush();

					processResponse();

				} else{
					System.out.println("文件路径有误，找不到指定文件");
				}
			} else {
				System.out.println("输入指令格式有误，请重新输入");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//=======end of your job============//
	}
	
	/**
	 * <em>processResponse</em> process the server response.
	 * 
	 */
	public void processResponse() throws Exception {
		int last = 0, c = 0;
		/**
		 * Process the header and add it to the header StringBuffer.
		 */
		boolean inHeader = true; // loop control
		while (inHeader && ((c = istream.read()) != -1)) {
			switch (c) {
			case '\r':
				break;
			case '\n':
				if (c == last) {
					inHeader = false;
					break;
				}
				last = c;
				header.append("\n");
				break;
			default:
				last = c;
				header.append((char) c);
			}
		}

		/**
		 * Read the contents and add it to the response StringBuffer.
		 */
		int len = 0;
		while ((len = istream.read(buffer)) != -1) {
			response.append(new String(buffer,"iso-8859-1"), 0, len);
		}
	}

	/**
	 * Get the response header.
	 */
	public String getHeader() {
		return header.toString();
	}

	/**
	 * Get the server's response.
	 */
	public String getResponse() {
		return response.toString();
	}

	/**
	 * Close all open connections -- sockets and streams.
	 */
	public void close() throws Exception {
		socket.close();
		istream.close();
		ostream.close();
	}
}
