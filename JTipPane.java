import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jfree.ui.RefineryUtilities;

public class JTipPane extends JFrame{//JTipPanes : mostra tips durante il caricamento
	private JLabel title,tip;
	public JTipPane() {
		this.title= new JLabel("Raccolta ping e generazione grafico...");
		this.title.setForeground(Color.red);
		this.title.setFont(new Font("Serif", Font.PLAIN, 32));
		this.tip= new JLabel("Tips");
		this.tip.setFont(new Font("Serif", Font.PLAIN, 25));
		this.setLayout(new GridLayout(2,1));
		this.add(this.title);
		this.add(this.tip);
		this.setTitle("El pingo");
		this.setSize(800,600);
		this.setVisible(true);
		this.process();
	}
	public void setTip(String t) {
		this.tip.setText(t);
	}
	public void process()  {
		GraphThread g= new GraphThread();//crea thread con il grafico e procedi a mostrare la schermata di caricamento mentre il thread fa il suo lavoro
		g.start();
		ArrayList<String> s= new ArrayList<String>();//ArrayList di tips
		s.add("Il microfono di Angelo è così bass boosted perchè ha un ping negativo");
		s.add("Fingere di avere un ping alto durante l'ora di inglese non fa aumentare i propri XP");
		s.add("GeoGenta 3.0 non ha avuto patch al sistema di valutazione automatico perchè ritengono che sia accurato");
		s.add("Exam.net non impedisce di copiare");
		s.add("Giovanni in realtà finge di non avere competenze informatiche avanzate per evitare rotture");
		s.add("Ho gettato troppo tempo in questo JTipPane");
		s.add("Se leggi questo ci sta mettendo più del dovuto");
		s.add("ScanaBrigo premium ha ancora pochi utenti");
		s.add("Non esco di casa da 3 giorni");
		s.add("Sono finiti i tip");
        this.showTips(s,g);
	}
	private void showTips(ArrayList<String> s,GraphThread g) {
		int i=0;
		while(!g.isDone()) {//continua a mostrare tips fino al caricamento del grafico
			this.setTip(s.get(i%s.size()));
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
		this.dispose();//Distruggi il tip pane quando non serve più
	}
}
