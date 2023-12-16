public class GewinnPruefen
{
	public static int
		feldHoehe = 6,
		feldBreite = 7,
		maxSpielerzahl = 2,
		aktuellerSpieler = 1,
		anzahlSteineFuerGewinn = 4;

	public static final int[] aktuellerStein = {1,5};

	public static int[][] feld =
		{
			{0,0,0,0,0,0,1},
			{0,1,0,1,1,1,1},
			{0,0,7,0,1,0,1},
			{0,0,0,1,0,0,1},
			{0,0,1,0,1,0,1},
			{0,1,6,0,0,9,1},
			{0,1,1,1,1,5,1},
		};

	public static void main(String[] args) {
		gewinnPruefen();
		return;
	}

	public static boolean zeilenPruefen()
	{
		int[] curPos = {aktuellerStein[0],aktuellerStein[1]};
		/* ––– ZEILEN *–––– */
		
		for (int i = 0; i < anzahlSteineFuerGewinn; i++)
		{
			if (aktuellerStein[1]-i < 0)
			{
				System.out.println("### [index < 0]");
				break;
			}
			for (int j = 0; j < anzahlSteineFuerGewinn; j++)
			{
				curPos[1] = aktuellerStein[1]-i+j;
				if (curPos[1] >= feldBreite)
				{
					System.out.print(" ## [array outofbounds]");
					break;
				}
				System.out.print(feld[curPos[0]][curPos[1]]);
				if (feld[curPos[0]][curPos[1]] != aktuellerSpieler)
				{
					System.out.print(" [wrong value]");
					break;
				}
				if (j >= anzahlSteineFuerGewinn-1)
				{
					System.out.print(" [win Zeil] (skipped "+((anzahlSteineFuerGewinn-1)-i)+" iteration)\n");
					return true;
				}
			}
			System.out.println();
		}
		return false;
	}

	public static boolean spaltenPruefen()
	{
		int[] curPos = {aktuellerStein[0],aktuellerStein[1]};
		// ––––– SPALTEN PRÜFEN –––––
		System.out.println("\n––––– Spalten");
		//4 mal mit i
		for (int i = 0; i < anzahlSteineFuerGewinn; i++)
		{
			//falls (stein.y +i) kleiner feldgröße/array
			if (aktuellerStein[0]-i < 0)
			{
				System.out.println("### [index < 0]");
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
					System.out.print(" ## [array outofbounds]");
					break;
				}
				System.out.print(feld[curPos[0]][curPos[1]]);
				//wenn value combo break
				if (feld[curPos[0]][curPos[1]] != aktuellerSpieler)
				{
					System.out.print(" [wrong value]");
					break;
				}
				//wenn volle reihe ohne combo break
				if (j >= anzahlSteineFuerGewinn-1)
				{
					System.out.print(" [win Spalt] (skipped "+((anzahlSteineFuerGewinn-1)-i)+" iteration)\n");
					return true;
				}
			}
			System.out.println();
		}
		return false;
	}

	public static boolean diagonalenRechtsPruefen()
	{
		int curPos = 0;
		// ––––– DIAGONALEN PRÜFEN (\) –––––
		System.out.println("\n––––– Diagonalen rechts");
		//4 mal mit i
		for (int i = 0; i < anzahlSteineFuerGewinn; i++)
		{
			if (aktuellerStein[0]-i < 0 || aktuellerStein[1]-i < 0)
			{
				System.out.println("### [index < 0]");
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
					System.out.print(" ## [array outofbounds]");
					break;
				}
				System.out.print(feld[curPos][curPos]);
				//wenn value combo break
				if (feld[curPos][curPos] != aktuellerSpieler)
				{
					System.out.print(" [wrong value]");
					break;
				}
				//wenn volle reihe ohne combo break
				if (j >= anzahlSteineFuerGewinn-1)
				{
					System.out.print(" [win diagR] (skipped "+((anzahlSteineFuerGewinn-1)-i)+" iterations)\n");
					return true;
				}
			}
			System.out.println();
		}
		return false;
	}
	public static boolean diagonalenLinksPruefen()
	{
		int[] curPos = {0,0};
		// ––––– DIAGONALEN PRÜFEN (/) –––––
		System.out.println("\n––––– Diagonalen links");
		// 4 times with i
		for (int i = 0; i < anzahlSteineFuerGewinn; i++)
		{
			if (aktuellerStein[1] - i < 0)
			{
				System.out.println("### [index < 0]");
				break;
			}

			for (int j = 0; j < anzahlSteineFuerGewinn; j++)
			{
				curPos[0] = aktuellerStein[0] + i - j;
				curPos[1] = aktuellerStein[1] - i + j;

				if (curPos[1] >= feldBreite || aktuellerStein[0] + i >= feldHoehe)
				{
					System.out.print(" ## [array outofbounds]");
					break;
				}
				
				if (curPos[0] < 0) {
					break;
				}

				System.out.print(feld[curPos[0]][curPos[1]]);

				if (feld[curPos[0]][curPos[1]] != aktuellerSpieler)
				{
					System.out.print(" [wrong value]");
					break;
				}

				if (j >= anzahlSteineFuerGewinn - 1)
				{
					System.out.print(" [win diagL] (skipped " + ((anzahlSteineFuerGewinn - 1) - i) + " iterations)\n");
					return true;
				}
			}
			System.out.println();
		}
		return false;
	}

	public static boolean gewinnPruefen()
	{
		if (zeilenPruefen() || spaltenPruefen() || diagonalenRechtsPruefen() || diagonalenLinksPruefen())
		{
			System.out.println("\nSpieler " + aktuellerSpieler + " hat gewonnen!)");
			return true;
		}
		return false;
	}
}
