/*
––––––––––––––––––––––– PROJEKTARBEIT JAVA
––––––––– Ausarbeitung von MOS-WON23B: Corvin, Eva, Marius, Milena
––––––––– Fertiggestellt am 15.12.2023
–––––––––––––––––––––––


–––––––––––– GRUNDLAGEN
- Zweidimensionales int-Feld für Spielfeld
- Erste Dimension gibt Spalten an -> feld[4][0] ist oben rechts
- Feld-Inhalt 0 bedeutet Feld leer


–––––––––––– STRUKTUR
–––––– Spielfeld initiiern und mit 0 befüllen (Milena/Marius)
- Spielfeld initiiern und mit 0 befüllen
- Spielfeld ausgeben (mit feldHoehe und feldBreite durch alle Felder iterieren)

–––––– Eingabe von aktuellem Spieler verarbeiten (Milena/Marius)
- Spalten von Benutzer ausgewählt werden
- Automatisch Zeilen (anhand "Gravitation") berechnen (bzw. ob Zeile noch frei)

–––––– Spielerwechsel
- Dynamisch, da maxSpielerzahl unterschiedlich sein kann

–––––– Probe auf Gewinn (hor, vert, diag) (Eva & Corvin)
- Ideal: Prüft [anzahlSteineFuerGewinn]-mal davor und danach auf gleiche Steine
- Mit zwei Schleifen Anzahl Steine prüfen und Offset der geprüften Zellen verschieben

–––––– Dokumentation (jeder)

 */

import java.util.Scanner;

public class VierGewinnt
{
	public static int
	runde = 0,
	feldHoehe = 6,
	feldBreite = 7,
	maxSpielerzahl = 2,
	aktuellerSpieler = 0,
	anzahlSteineFuerGewinn = 4;

	public static boolean gewinnDebug = false;
	public static int[]
			aktuellerStein = {5,1},
			siege = new int[maxSpielerzahl];
	public static int[][] feld = new int[feldHoehe][feldBreite];

	//Marius, Milena, Corvin
	public static void main(String[] args)
	{
		//Gibt Titel aus
		runde += 1;
		String striche = "";
		for (int i = 0; i < anzahlSteineFuerGewinn; i++) striche += "–";
		System.out.println("\n" + striche + " " + anzahlSteineFuerGewinn + " GEWINNT! " + striche + "\n–– Runde " + runde + " ––\n");
		
		
		initfeld(); //beschreibt Spielfeld mit 0 und gibt Spielfeld aus

		do {
			spielerWechseln(); //wechselt Spieler
			zugAnfragen(); //setzt Stein und gibt Feld aus
		} while (!gewinnPruefen());
		
		System.out.println("\n\n");
		main(args);
		return;
	}

	//Marius, Milena
	// Funktion, um das Spielfeld zu initiieren (alle Felder mit 0 befüllen)
	public static void initfeld() {
		for (int i = 0; i < feldHoehe; i++) {
			for (int j = 0; j < feldBreite; j++) {
				feld[i][j] = 0;
			}
		}
		printfeld();
		return;
	}

	//Marius, Milena
	// Funktion, um das Spielfeld auszugeben
	public static void printfeld() {
		for (int i = 0; i < feldHoehe; i++) {
			for (int j = 0; j < feldBreite; j++) {
				System.out.print(feld[i][j] + " ");
			}
			System.out.println(); // Neue Zeile für den nächsten Spielfeld-Row
		}
		return;
	}

	//Marius, Milena
	public static void zugAnfragen()
	{
		Scanner scanner = new Scanner(System.in);
		int selectedColumn = 0;

		while (true) {
			System.out.print("\n–––\nSpieler " + aktuellerSpieler + ", wähle eine Spalte (1 - " + feldBreite + "): ");
			selectedColumn = scanner.nextInt()-1;

			if (selectedColumn < 0 || selectedColumn >= feldBreite)
			{
				System.out.println("\nUngültige Spalte. Bitte erneut eingeben.\n");
				continue;
			}

			if (feld[0][selectedColumn] != 0)
			{
				System.out.println("\nSpalte voll. Bitte erneut eingeben.\n");
				continue;
			}
			break;
		};

		aktuellerStein[1] = selectedColumn;
		setzeStein();
		printfeld();
		return;
	}

	//Marius, Milena
	// Funktion, um einen Spielerstein zu setzen (bis zur nächsten freien Position durchfallen lassen)
	public static void setzeStein()
	{
		for (int i = feldHoehe - 1; i >= 0; i--)
		{
			if (feld[i][aktuellerStein[1]] == 0)
			{
				feld[i][aktuellerStein[1]] = aktuellerSpieler;
				aktuellerStein[0] = i;
				break;
			}
		}
		return;
	}

	//Corvin
	public static void spielerWechseln() {
		aktuellerSpieler = (aktuellerSpieler >= maxSpielerzahl) ? 1 : aktuellerSpieler + 1;
		return;
	}

	//Corvin, Eva
	public static boolean zeilenPrüfen()
	{
		int[] curPos = {aktuellerStein[0],aktuellerStein[1]};
		if (gewinnDebug) System.out.println("\n––––– Zeilen");
		//4 mal mit i
		for (int i = 0; i < anzahlSteineFuerGewinn; i++)
		{
			//falls (stein.x +i) kleiner feldgröße/array
			if (aktuellerStein[1]-i < 0)
			{
				if (gewinnDebug) System.out.println("### [index < 0]");
				break;
			}
			//4 mal mit j
			for (int j = 0; j < anzahlSteineFuerGewinn; j++)
			{
				//curpos: stein.x - (i:Offset Block)+(j:Anzahl Steine in Reihe)
				curPos[1] = aktuellerStein[1]-i+j;
				//falls curPos größer feldgröße/array
				if (curPos[1] >= feldBreite)
				{
					if (gewinnDebug) System.out.print(" ## [array outofbounds]");
					break;
				}
				if (gewinnDebug) System.out.print(feld[curPos[0]][curPos[1]]);
				//wenn value combo break
				if (feld[curPos[0]][curPos[1]] != aktuellerSpieler)
				{
					if (gewinnDebug) System.out.print(" [wrong value]");
					break;
				}
				//wenn volle reihe ohne combo break
				if (j >= anzahlSteineFuerGewinn-1)
				{
					if (gewinnDebug) System.out.print(" [win Zeil] (skipped "+((anzahlSteineFuerGewinn-1)-i)+" iteration)\n");
					return true;
				}
			}
			if (gewinnDebug) System.out.println();
		}
		return false;
	}

	//Corvin, Eva
	public static boolean spaltenPrüfen()
	{
		int[] curPos = {aktuellerStein[0],aktuellerStein[1]};
		// ––––– SPALTEN PRÜFEN –––––
		if (gewinnDebug) System.out.println("\n––––– Spalten");
		//4 mal mit i
		for (int i = 0; i < anzahlSteineFuerGewinn; i++)
		{
			//falls (stein.x +i) kleiner feldgröße/array
			if (aktuellerStein[0]-i < 0)
			{
				if (gewinnDebug) System.out.println("### [index < 0]");
				break;
			}
			//4 mal mit j
			for (int j = 0; j < anzahlSteineFuerGewinn; j++)
			{
				//curpos: stein.x - (i:Offset Block)+(j:Anzahl Steine in Reihe)
				curPos[0] = aktuellerStein[0]-i+j;
				//falls curPos größer feldgröße/array
				if (curPos[0] >= feldHoehe)
				{
					if (gewinnDebug) System.out.print(" ## [array outofbounds]");
					break;
				}
				if (gewinnDebug) System.out.print(feld[curPos[0]][curPos[1]]);
				//wenn value combo break
				if (feld[curPos[0]][curPos[1]] != aktuellerSpieler)
				{
					if (gewinnDebug) System.out.print(" [wrong value]");
					break;
				}
				//wenn volle reihe ohne combo break
				if (j >= anzahlSteineFuerGewinn-1)
				{
					if (gewinnDebug) System.out.print(" [win Spalt] (skipped "+((anzahlSteineFuerGewinn-1)-i)+" iteration)\n");
					return true;
				}
			}
			if (gewinnDebug) System.out.println();
		}
		return false;
	}

	//Corvin, Eva
	public static boolean diagonalenRechtsPruefen()
	{
		int curPos = 0;
		// ––––– DIAGONALEN PRÜFEN (\) –––––
		if (gewinnDebug) System.out.println("\n––––– Diagonalen rechts");
		//4 mal mit i
		for (int i = 0; i < anzahlSteineFuerGewinn; i++)
		{
			//falls (stein.x +i) kleiner feldgröße/array
			if (aktuellerStein[0]-i < 0 || aktuellerStein[1]-i < 0)
			{
				if (gewinnDebug) System.out.println("### [index < 0]");
				break;
			}
			//4 mal mit j
			for (int j = 0; j < anzahlSteineFuerGewinn; j++)
			{
				//curpos: stein.x - (i:Offset Block)+(j:Anzahl Steine in Reihe)
				curPos = aktuellerStein[1]-i+j;
				//falls curPos größer feldgröße/array
				if (curPos >= feldBreite || curPos >= feldHoehe)
				{
					if (gewinnDebug) System.out.print(" ## [array outofbounds]");
					break;
				}
				if (gewinnDebug) System.out.print(feld[curPos][curPos]);
				//wenn value combo break
				if (feld[curPos][curPos] != aktuellerSpieler)
				{
					if (gewinnDebug) System.out.print(" [wrong value]");
					break;
				}
				//wenn volle reihe ohne combo break
				if (j >= anzahlSteineFuerGewinn-1)
				{
					if (gewinnDebug) System.out.print(" [win diagR] (skipped "+((anzahlSteineFuerGewinn-1)-i)+" iterations)\n");
					return true;
				}
			}
			if (gewinnDebug) System.out.println();
		}
		return false;
	}

	//Corvin, Eva
	public static boolean diagonalenLinksPruefen()
	{
		int[] curPos = {0,0};
		// ––––– DIAGONALEN PRÜFEN (/) –––––
		if (gewinnDebug) System.out.println("\n––––– Diagonalen links");
		// 4 times with i
		for (int i = 0; i < anzahlSteineFuerGewinn; i++)
		{
			// If (stein.x - i) is less than 0 or (stein.y + i) is greater than or equal to feldHoehe, break
			if (aktuellerStein[1] - i < 0)
			{
				if (gewinnDebug) System.out.println("### [index < 0]");
				break;
			}

			// 4 times with j
			for (int j = 0; j < anzahlSteineFuerGewinn; j++)
			{
				// curPos: stein.x - (i: Offset Block) + (j: Anzahl Steine in Reihe)
				curPos[0] = aktuellerStein[0] + i - j;
				curPos[1] = aktuellerStein[1] - i + j;

				// If curPos is greater than or equal to feldBreite, break
				if (curPos[1] >= feldBreite || aktuellerStein[0] + i >= feldHoehe)
				{
					if (gewinnDebug) System.out.print(" ## [array outofbounds]");
					break;
				}
				
				if (curPos[0] < 0) {
					break;
				}

				if (gewinnDebug) System.out.print(feld[curPos[0]][curPos[1]]);

				// If the value is not the current player, break
				if (feld[curPos[0]][curPos[1]] != aktuellerSpieler)
				{
					if (gewinnDebug) System.out.print(" [wrong value]");
					break;
				}

				// If a full diagonal line is found without breaking, print win message and return
				if (j >= anzahlSteineFuerGewinn - 1)
				{
					if (gewinnDebug) System.out.print(" [win diagL] (skipped " + ((anzahlSteineFuerGewinn - 1) - i) + " iterations)\n");
					return true;
				}
			}
			if (gewinnDebug) System.out.println();
		}
		return false;
	}

	//Corvin, Eva
	public static boolean gewinnPruefen()
	{
		if (zeilenPrüfen() || spaltenPrüfen() || diagonalenRechtsPruefen() || diagonalenLinksPruefen())
		{
			siege[aktuellerSpieler-1] += 1;
			System.out.println("\nSpieler " + aktuellerSpieler + " hat gewonnen! ("+siege[aktuellerSpieler-1]+" Siege)");
			return true;
		}
		return false;
	}
}
