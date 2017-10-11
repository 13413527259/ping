package ping;

public class Hp{

	public static void main(String[] args) {
		Hp hp =new Hp(1); 
		new Thread() {@Override
		public void run() {

			synchronized (this) {
				for (int i = 0; i < 1000; i++) {
					hp.add();
				}
			}
		}}.start();
		new Thread() {@Override
			public void run() {

				synchronized (this) {
					for (int i = 0; i < 1000; i++) {
						hp.del();
					}
				}
			}}.start();
			new Thread() {@Override
				public void run() {

					synchronized (this) {
						for (int i = 0; i < 1000; i++) {
							hp.del();
						}
					}
				}}.start();
				new Thread() {@Override
					public void run() {

						synchronized (this) {
							for (int i = 0; i < 1000; i++) {
								hp.add();
							}
						}
					}}.start();
	}

	private int i;

	public Hp() {
	}

	public Hp(int i) {
		this.i = i;
	}


	public void add() {
		i++;
		showI();
	}

	public void del() {
		i--;
		showI();
	}

	public void showI() {
		System.out.println("............." + i);
	}

}
