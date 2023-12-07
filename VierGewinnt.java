/*

–––––––––––– GRUNDLAGEN
- Zweidimensionales int-Feld für Spielfeld
- Erste Dimension gibt Spalten an -> feld[4][0] ist oben rechts
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
        feldHoehe = 4,
        feldBreite = 4,
        maxSpielerzahl = 2,
        aktuellerSpieler = 1,
        anzahlSteineFuerGewinn = 4;

    public static int[][] feld = new int[feldHoehe][feldBreite];

    public static void main(String[] args)
    {
        System.out.println("hi :)");
        return;
    }
}
