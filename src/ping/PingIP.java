package ping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class PingIP implements Runnable {

	List<String> ips = new ArrayList<>();

	private String ip;
	private String ipHead;
	private int ipNum;

	public PingIP() {
		try {
			ip = getIP();
			ipHead=getIPHead();
			ipNum = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getIPHead() {
		return ipHead = ip.substring(0, ip.lastIndexOf('.') + 1);
	}

	public String getIP() throws Exception {
		InetAddress host = InetAddress.getLocalHost();
		String ip = host.getHostAddress();
		System.out.println("本机ip地址：" + ip);
		return ip;
	}

	// Object obj = new Object();

	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				if (ipNum < 25) {
					ipNum++;
					System.out.println(Thread.currentThread().getId() + "正在ping第" + ipNum + "个ip");
				} else {
					break;
				}
			}
			try {
//				Thread.currentThread().sleep((int)(Math.random()*10000));
				pingIps(ipNum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void pingIps(int ipno) throws Exception {
		String IP=ipHead+ipno;
		Process p = Runtime.getRuntime().exec("ping -n 1 " + IP);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));) {
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			if (sb.toString().contains("TTL")) {
				System.out.println("***********" + IP+"************");
				ips.add(IP);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		p.destroy();
		// ipNum++;
		System.err.println(Thread.currentThread().getId()+">>>"+IP);

	}

	public void ping() throws Exception {
		Process p = Runtime.getRuntime().exec("ping " + "192.168.6.201");
		// 设置编码为GBK
		InputStreamReader ir = new InputStreamReader(p.getInputStream(), Charset.forName("GBK"));
		BufferedReader br = new BufferedReader(ir);
		String line = null;
		// StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			if (line.length() != 0) {
				System.out.println(line);
				// sb.append(line + "\r\n");
			}
		}
		// System.out.println("本次指令返回的消息是：");
		// System.out.println(sb.toString());
	}

}
