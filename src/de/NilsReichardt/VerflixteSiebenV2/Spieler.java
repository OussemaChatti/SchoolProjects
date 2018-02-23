package de.NilsReichardt.VerflixteSiebenV2;

import javax.swing.*;

public class Spieler {

	private int zPunkte;
	private int zVermoegen;
	private int zEinsatz;
	private boolean zWuerfelrecht = false; // Standardmäßig auf false
	private String zName;

	private Wuerfel zKenntWuerfel1, zKenntWuerfel2; // Eigentlich zKenntWuerfel1, zKenntWuerfel2
	private Spieler zKenntAnderenSpieler;

	private Topf zTopf; // Eigentlich zKenntTopf

	public Spieler(int pVermoegen, String pName, Topf pTopf, Wuerfel pWuerfel1, Wuerfel pWuerfel2)
	{
		setVermoegen(pVermoegen);
		setName(pName);
		zTopf = pTopf;
		zKenntWuerfel1 = pWuerfel1;
		zKenntWuerfel2 = pWuerfel2;
	}

	public void wuerfeln()
	{
		if(hatWuerfelrecht())
		{
			int hWuerfel1 = zKenntWuerfel1.rollen();  // Zahl aus Würfel 1
			int hWuerfel2 = zKenntWuerfel2.rollen();  // Zahl aus Würfel 2
			int hWuerfelSumme = hWuerfel1 + hWuerfel2;  // Beiden Zahlen addieren

			if(hWuerfelSumme == 7)
			{
				System.out.println(getName() + " hat eine " + hWuerfel1 + " und eine " + hWuerfel2 + " gewürfelt (=" + hWuerfelSumme + ").");
				setPunkte(getPunkte() - 7); // 7 Punkte abziehen
				System.out.println("Da " + getName() + " eine Sieben gewürfelt hat, werden 7 Punkten von seinen Punkten abgezogen. Seine gesamte Punktzahl beträgt: " + getPunkte());

				// Würfelrecht tauschen
				setWuerfelrecht(false);
				zKenntAnderenSpieler.setWuerfelrecht(true);
				System.out.println("Das Würfelrecht wechselt. Somit hat nun " + zKenntAnderenSpieler.getName() + " das Würfelrecht.");
			}
			else
			{
				// Punkte zum Punkte-Konto hinzufügen
				addPunkte(hWuerfelSumme);
				System.out.println(getName() + " hat eine " + hWuerfel1 + " und eine " + hWuerfel2 + " gewürfelt (=" + hWuerfelSumme + "). Seine gesamte Punktzahl beträgt: " + getPunkte());
			}
		}
		else
		{
			JOptionPane.showMessageDialog(new JFrame(), getName() + " hat nicht das Würfelrecht!", "Würfelrecht", JOptionPane.INFORMATION_MESSAGE); // Fehlermeldung
		}
	}

	public void anderenSpielerKennenlernen(Spieler pAndererSpieler)
	{
		zKenntAnderenSpieler = pAndererSpieler;
		System.out.println(getName() + " kennt nun " + zKenntAnderenSpieler.getName() + ".");
	}

	public void rundeBeenden()
	{
		if(zKenntAnderenSpieler != null)
		{
			System.out.println("");
			System.out.println("Die Runde wird beendet!");
			if(getPunkte() > zKenntAnderenSpieler.getPunkte())
			{
				System.out.println(getWinnerMessage(this));
				this.addVermoegen(zTopf.auszahlen(zTopf.getInhalt()));
			}
			else
			{
				if(getPunkte() < zKenntAnderenSpieler.getPunkte())
				{
					System.out.println(getWinnerMessage(zKenntAnderenSpieler));
					zKenntAnderenSpieler.addVermoegen(zTopf.auszahlen(zTopf.getInhalt()));
				}
				else
				{
					System.out.println("Unentschieden! Beide Spieler haben " + getPunkte() + " Punkte!");
				}
			}

			// Punkte zurücksetzten
			this.setPunkte(0);
			zKenntAnderenSpieler.setPunkte(0);
			System.out.println("Die Punkte von allen Spielern wurden zurückgesetzt.");

			// Inhalt des Topfes
			System.out.println("Im Topf befinden sich noch " + zTopf.getInhalt() + " Bitcoins.");
		}
		else
		{
			JOptionPane.showMessageDialog(new JFrame(), getName() + " kennt den anderen Spieler noch nicht", "Anderer Spieler?", JOptionPane.INFORMATION_MESSAGE); // Fehlermeldung
		}
	}

	// Getter & Setter
	public int getPunkte()
	{
		return zPunkte;
	}
	public void setPunkte(int pPunkte)
	{
		if(pPunkte > 0)
		{
			zPunkte = pPunkte;
		}
		else
		{
			zPunkte = 0;
		}
	}
	public void addPunkte(int pPunkte)
	{
		setPunkte(getPunkte() + pPunkte);
	}

	public int getEinsatz()
	{
		return zEinsatz;
	}
	public void setEinsatz(int pEinsatz)
	{
		if(pEinsatz > 0)
		{
			zEinsatz = pEinsatz;
		}
		else
		{
			zEinsatz = 0;
		}

		if((getVermoegen() - getEinsatz()) >= 0)
		{
			zTopf.einzahlen(getEinsatz());
			setVermoegen(getVermoegen() - getEinsatz());

			System.out.println(getName() + " legt " + getEinsatz() + " in den Topf. Sein Vermögen beträgt nun noch " + getVermoegen() + " Bitcoins.");
		}
		else
		{
			JOptionPane.showMessageDialog(new JFrame(), "Der Einsatz von " + getEinsatz() + " Bitcoins ist zu hoch. Dein momentanes Vermögen beträgt " + getVermoegen() + " Bitcoins.", "Einsatz", JOptionPane.INFORMATION_MESSAGE); // Fehlermeldung
		}
	}

	public int getVermoegen()
	{
		return zVermoegen;
	}
	public void setVermoegen(int pVermoegen)
	{
		if(pVermoegen > 0)
		{
			zVermoegen = pVermoegen;
		}
		else
		{
			zVermoegen = 0;
		}
	}
	public void addVermoegen(int pVermoegen)
	{
		setVermoegen(getVermoegen() + pVermoegen);
	}

	public String getName()
	{
		return zName;
	}
	public void setName(String pName)
	{
		zName = pName;
	}

	public boolean hatWuerfelrecht()
	{
		if(zWuerfelrecht)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void setWuerfelrecht(boolean pWuerfelrecht)
	{
		if(zKenntAnderenSpieler != null)
		{
			if(pWuerfelrecht)
			{
				// Prüfen, ob der andere Spieler schon das Würfelrecht hat
				if(zKenntAnderenSpieler.hatWuerfelrecht())
				{
					JOptionPane.showMessageDialog(new JFrame(), zKenntAnderenSpieler.getName() + " hat schon das Würfelrecht!", "Würfelrecht", JOptionPane.INFORMATION_MESSAGE); // Fehlermeldung
				}
				else
				{
					zWuerfelrecht = true;
				}
			}
			else
			{
				zWuerfelrecht = false;
				zKenntAnderenSpieler.setWuerfelrecht(true);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(new JFrame(), getName() + " kennt den anderen Spieler noch nicht", "Anderer Spieler?", JOptionPane.INFORMATION_MESSAGE); // Fehlermeldung
		}
	}

	public String getWinnerMessage(Spieler pSpieler)
	{
		return pSpieler.getName() + " hat mit " + pSpieler.getPunkte() + " gewonnen! Er bekommt " + zTopf.getInhalt() + " Bitcoins.";
	}

}
