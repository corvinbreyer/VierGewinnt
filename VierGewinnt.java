/*

–––––––––––– GRUNDLAGEN
- Zweidimensionales int-Feld für Spielfeld
- Erste Dimension gibt Spalten an -> feld[4][0] ist unten links
- Feld-Inhalt 0 bedeutet Feld leer


–––––––––––– STRUKTUR
–––––– Spielfeld initiiern und mit 0 befüllen [25%] (Milena/Marius)
- Spielfeld initiiern und mit 0 befüllen
- Spielfeld ausgeben (mit feldHoehe und feldBreite durch alle Felder iterieren)

–––––– Eingabe von aktuellem Spieler verarbeiten [25%] (Milena/Marius)
- Spalten von Benutzer ausgewählt werden
- Automatisch Zeilen (anhand "Gravitation") berechnen (bzw. ob Zeile noch frei)

–––––– Spielerwechsel [10%] (mal schauen)
- Dynamisch, da maxSpielerzahl unterschiedlich sein kann

–––––– Probe auf Gewinn (hor, vert, diag) [40%] (Eva & Corvin)
- Ideal: Prüft [anzahlSteineFuerGewinn]-mal davor und danach auf gleiche Steine
- Mit zwei Schleifen Anzahl Steine prüfen und Offset der geprüften Zellen verschieben

–––––– Dokumentation (jeder)

*/


public class VierGewinnt
{
    public static int
        feldHoehe = 7,
        feldBreite = 7,
        maxSpielerzahl = 2,
        aktuellerSpieler = 1,
        anzahlSteineFuerGewinn = 4;
    
    public static int[] aktuellerStein = new int[2];
    public static int[][] feld = new int[feldHoehe][feldBreite];

    public static void main(String[] args)
    {
        System.out.println("hi :)");

//Spilerfeldeingabe Marius + Milena
        import java.util.Scanner;

public class EingabeSpieler {
	private static final int feldBreite = 7; // Anpassen der Feldbreite nach Bedarf
	private static final int feldHoehe = 6;  // Anpassen der Feldhöhe nach Bedarf
	private static int[][] spielfeld = new int[feldHoehe][feldBreite];

	// Funktion, um das Spielfeld zu initiieren (alle Felder mit 0 befüllen)
	public static void initSpielfeld() {
		for (int i = 0; i < feldHoehe; i++) {
			for (int j = 0; j < feldBreite; j++) {
				spielfeld[i][j] = 0;
			}
		}
	}

	// Funktion, um das Spielfeld auszugeben
	public static void printSpielfeld() {
		for (int i = 0; i < feldHoehe; i++) {
			for (int j = 0; j < feldBreite; j++) {
				System.out.print(spielfeld[i][j] + " ");
			}
			System.out.println(); // Neue Zeile für den nächsten Spielfeld-Row
		}
	}

	// Funktion, um einen Spielerstein zu setzen (bis zur nächsten freien Position durchfallen lassen)
	public static void setzeStein(int spieler, int spalte) {
		for (int i = feldHoehe - 1; i >= 0; i--) {
			if (spielfeld[i][spalte] == 0) {
				spielfeld[i][spalte] = spieler;
				break;
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		initSpielfeld();
		printSpielfeld();

		int currentPlayer = 1;

		while (true) {
			System.out.println("Spieler " + currentPlayer + ", wähle eine Spalte (0-" + (feldBreite - 1) + "): ");
			int selectedColumn = scanner.nextInt();

			if (selectedColumn < 0 || selectedColumn >= feldBreite) {
				System.out.println("Ungültige Spalte. Bitte erneut eingeben.");
				continue;
			}

			setzeStein(currentPlayer, selectedColumn);
			printSpielfeld();

			currentPlayer = (currentPlayer == 1) ? 2 : 1;
		}
	}
}
        return;
    }
}
