package ping;

public class RunTest {

	public static void main(String[] args) {
		PingIP p = new PingIP();
		new Thread(p, "CMD4").start();
		new Thread(p, "CMD3").start();
		new Thread(p, "CMD2").start();
		new Thread(p, "CMD1").start();
		new Thread(p, "CMD4").start();
		new Thread(p, "CMD3").start();
		new Thread(p, "CMD2").start();
		new Thread(p, "CMD1").start();
		new Thread(p, "CMD4").start();
		new Thread(p, "CMD3").start();
		new Thread(p, "CMD2").start();
		new Thread(p, "CMD1").start();
	}

}
