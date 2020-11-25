import org.jfree.ui.RefineryUtilities;

public class GraphThread extends Thread{
	private boolean done=false;//di default non ha finito
	public void run() {

		PingChart demo = new PingChart("PingChart",10,2000);//Crea ping chart
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setTitle("Grafìco de los mesicanos");
		demo.setVisible(true);
		done=true;// imposta la flag done per segnalare al JTipPane l'avvenuto caricamento
	}
	public boolean isDone() {
		return done;
	}
	
}
